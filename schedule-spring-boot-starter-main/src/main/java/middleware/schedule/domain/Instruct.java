package middleware.schedule.domain;

public class Instruct {

    private String ip;                  //机器IP
    private String schedulerServerId;   //任务服务ID；工程名称En
    private String beanName;            //类对象名称
    private String methodName;          //方法名称
    private String cron;                //任务执行
    private Integer status;             //Constants.InstructStatus 0关闭、1启动、2更新

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSchedulerServerId() {
        return schedulerServerId;
    }

    public void setSchedulerServerId(String schedulerServerId) {
        this.schedulerServerId = schedulerServerId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
