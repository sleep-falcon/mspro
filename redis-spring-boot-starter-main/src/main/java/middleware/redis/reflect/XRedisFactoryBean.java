package middleware.redis.reflect;

import middleware.redis.config.XRedisProperties;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


public class XRedisFactoryBean<T> implements FactoryBean<T> {

    private Class<T> mapperInterface;

    @Resource
    XRedisProperties properties;

    @Resource(name = "jedis")
    Jedis jedis;

    public XRedisFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {
            jedis.auth(properties.getPwd());
            String name = method.getName();
            if ("get".equals(name)) {
                return jedis.srandmember(args[0].toString());
            }
            if ("set".equals(name)) {
                return jedis.sadd(args[0].toString(), args[1].toString());
            }
            return "未知操作！无效处理！";
        };
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{mapperInterface}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }
}