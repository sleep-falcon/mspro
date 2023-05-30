package middleware.schedule.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("middleware.scheudle")
public class StarterServiceProperties {

    private String zkAddress;           //zookeeper服务地址；x.x.x.x:2181
    private String schedulerServerId;   //任务服务ID；  工程名称En
    private String schedulerServerName; //任务服务名称；工程名称Ch

    public String getZkAddress() {
        return zkAddress;
    }

    public void setZkAddress(String zkAddress) {
        this.zkAddress = zkAddress;
    }

    public String getSchedulerServerId() {
        return schedulerServerId;
    }

    public void setSchedulerServerId(String schedulerServerId) {
        this.schedulerServerId = schedulerServerId;
    }

    public String getSchedulerServerName() {
        return schedulerServerName;
    }

    public void setSchedulerServerName(String schedulerServerName) {
        this.schedulerServerName = schedulerServerName;
    }
}
