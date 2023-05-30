package middleware.test;

import com.alibaba.fastjson.JSON;
import org.apache.curator.framework.CuratorFramework;
import middleware.schedule.domain.Instruct;
import middleware.schedule.export.DcsScheduleResource;
public class ApiTest {

    public static void main(String[] args) throws Exception {
        Instruct instruct = new Instruct();
        instruct.setIp("10.13.152.95");
        instruct.setSchedulerServerId("demo-springboot-helloworld");
        instruct.setBeanName("demoTask");
        instruct.setMethodName("taskMethod02");
        instruct.setCron("0/3 * * * * *");
        instruct.setStatus(1);
        System.out.println(JSON.toJSONString(instruct));

        DcsScheduleResource workerArkResource = new DcsScheduleResource("127.0.0.1:2181");
        CuratorFramework client = workerArkResource.getClient();
        //client.setData().forPath("/middleware/schedule/exec", JSON.toJSONString(instruct).getBytes("utf-8"));

        //List<DcsScheduleInfo> res = workerArkResource.queryDcsScheduleInfoList("demo-springboot-helloworld");

        //DataCollect dataCollect = workerArkResource.queryDataCollect();

        // 递归删除节点
        client.delete().deletingChildrenIfNeeded().forPath("/middleware/schedule/server/itstack-demo-springboot-helloworld/ip/10.13.152.95");

    }

}
