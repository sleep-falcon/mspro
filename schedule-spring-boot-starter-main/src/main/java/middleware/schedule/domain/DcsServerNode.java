package middleware.schedule.domain;

public class DcsServerNode {

    private String schedulerServerId;       //任务服务ID；  工程名称En
    private String schedulerServerName;     //任务服务名称；工程名称Ch

    public DcsServerNode(String schedulerServerId, String schedulerServerName) {
        this.schedulerServerId = schedulerServerId;
        this.schedulerServerName = schedulerServerName;
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
