package  middleware.schedule.config;

import com.alibaba.fastjson.JSON;
import org.apache.curator.framework.CuratorFramework;
import  middleware.schedule.annotation.DcsScheduled;
import  middleware.schedule.common.Constants;
import  middleware.schedule.domain.ExecOrder;
import  middleware.schedule.service.HeartbeatService;
import  middleware.schedule.service.ZkCuratorServer;
import  middleware.schedule.task.CronTaskRegister;
import  middleware.schedule.task.SchedulingRunnable;
import  middleware.schedule.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static middleware.schedule.common.Constants.Global.*;

public class DcsSchedulingConfiguration implements ApplicationContextAware, BeanPostProcessor, ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(DcsSchedulingConfiguration.class);

    //生成对Map进行包装的Set。这个Set和被包装的Map拥有相同的key顺序（遍历Set调用的还是Map的keySet）
    //相同的并发特性（也就是说如果对ConcurrentHashMap进行包装，得到的Set也将线程安全）
    private final Set<Class<?>> nonAnnotatedClasses = Collections.newSetFromMap(new ConcurrentHashMap<>(64));

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Constants.Global.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //得倒代理的对象
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(bean);
        if (this.nonAnnotatedClasses.contains(targetClass)) return bean;
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        for (Method method : methods) {
            //得倒所有带DcsScheduled注解的方法（定时任务）
            DcsScheduled dcsScheduled = AnnotationUtils.findAnnotation(method, DcsScheduled.class);
            if (null == dcsScheduled || 0 == method.getDeclaredAnnotations().length) continue;
            //Map<String, List<ExecOrder>> execOrderMap
            //key为beanName，一个bean内可能有好几个定时任务，因此用list保存
            //没有就创建ArrayList<>()
            List<ExecOrder> execOrderList = Constants.execOrderMap.computeIfAbsent(beanName, k -> new ArrayList<>());
            //记录任务方法的属性
            ExecOrder execOrder = new ExecOrder();
            execOrder.setBean(bean);
            execOrder.setBeanName(beanName);
            execOrder.setMethodName(method.getName());
            execOrder.setDesc(dcsScheduled.desc());
            execOrder.setCron(dcsScheduled.cron());
            execOrder.setAutoStartup(dcsScheduled.autoStartup());
            execOrderList.add(execOrder);
            //处理过的类加入set，避免重复
            this.nonAnnotatedClasses.add(targetClass);
        }
        return bean;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
            //1. 初始化配置
            init_config(applicationContext);
            //2. 初始化服务
            init_server(applicationContext);
            //3. 启动任务
            init_task(applicationContext);
            //4. 挂载节点
            init_node();
            //5. 心跳监听
            HeartbeatService.getInstance().startFlushScheduleStatus();
            logger.info("middleware schedule init config、server、task、node、heart done!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //1. 初始化配置
    private void init_config(ApplicationContext applicationContext) {
        try {
            StarterServiceProperties properties = applicationContext.getBean("middlware-schedule-starterAutoConfig", StarterAutoConfig.class).getProperties();
            Constants.Global.zkAddress = properties.getZkAddress();
            Constants.Global.schedulerServerId = properties.getSchedulerServerId();
            Constants.Global.schedulerServerName = properties.getSchedulerServerName();
            InetAddress id = InetAddress.getLocalHost();
            Constants.Global.ip = id.getHostAddress();
        } catch (Exception e) {
            logger.error("middleware schedule init config error！", e);
            throw new RuntimeException(e);
        }
    }

    //2. 初始化服务
    private void init_server(ApplicationContext applicationContext) {
        try {
            //获取zk连接
            CuratorFramework client = ZkCuratorServer.getClient(Constants.Global.zkAddress);
            //节点组装
            path_root_server = StrUtil.joinStr(path_root, LINE, "server", LINE, schedulerServerId);
            path_root_server_ip = StrUtil.joinStr(path_root_server, LINE, "ip", LINE, Constants.Global.ip);
            //创建节点&递归删除本服务IP下的旧内容
            ZkCuratorServer.deletingChildrenIfNeeded(client, path_root_server_ip);
            ZkCuratorServer.createNode(client, path_root_server_ip);
            ZkCuratorServer.setData(client, path_root_server, schedulerServerName);
            //添加节点&监听
            ZkCuratorServer.createNodeSimple(client, Constants.Global.path_root_exec);
            ZkCuratorServer.addTreeCacheListener(applicationContext, client, Constants.Global.path_root_exec);
        } catch (Exception e) {
            logger.error("middleware schedule init server error！", e);
            throw new RuntimeException(e);
        }
    }

    //3. 启动任务
    private void init_task(ApplicationContext applicationContext) {
        CronTaskRegister cronTaskRegistrar = applicationContext.getBean("middlware-schedule-cronTaskRegister", CronTaskRegister.class);
        Set<String> beanNames = Constants.execOrderMap.keySet();
        for (String beanName : beanNames) {
            List<ExecOrder> execOrderList = Constants.execOrderMap.get(beanName);
            for (ExecOrder execOrder : execOrderList) {
                if (!execOrder.getAutoStartup()) continue;
                SchedulingRunnable task = new SchedulingRunnable(execOrder.getBean(), execOrder.getBeanName(), execOrder.getMethodName());
                cronTaskRegistrar.addCronTask(task, execOrder.getCron());
            }
        }
    }

    //4. 挂载节点
    private void init_node() throws Exception {
        Set<String> beanNames = Constants.execOrderMap.keySet();
        for (String beanName : beanNames) {
            List<ExecOrder> execOrderList = Constants.execOrderMap.get(beanName);
            for (ExecOrder execOrder : execOrderList) {
                String path_root_server_ip_clazz = StrUtil.joinStr(path_root_server_ip, LINE, "clazz", LINE, execOrder.getBeanName());
                String path_root_server_ip_clazz_method = StrUtil.joinStr(path_root_server_ip_clazz, LINE, "method", LINE, execOrder.getMethodName());
                String path_root_server_ip_clazz_method_status = StrUtil.joinStr(path_root_server_ip_clazz, LINE, "method", LINE, execOrder.getMethodName(), "/status");
                //添加节点
                ZkCuratorServer.createNodeSimple(client, path_root_server_ip_clazz);
                ZkCuratorServer.createNodeSimple(client, path_root_server_ip_clazz_method);
                ZkCuratorServer.createNodeSimple(client, path_root_server_ip_clazz_method_status);
                //添加节点数据[临时]
                //execOrder除了bean的所有信息
                ZkCuratorServer.appendEphemeralData(client, path_root_server_ip_clazz_method + "/value", JSON.toJSONString(execOrder));
                //添加节点数据[永久]
                //1：自动开启了
                //0:未开启
                ZkCuratorServer.setData(client, path_root_server_ip_clazz_method_status, execOrder.getAutoStartup() ? "1" : "0");
            }
        }
    }

}
