package middleware.schedule.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class ExecOrder {

    @JSONField(serialize = false)
    private Object bean;         //类对象，不需要序列化
    private String beanName;     //类对象名称
    private String methodName;   //方法名称
    private String desc;         //任务描述
    private String cron;         //任务执行
    private Boolean autoStartup; //任务状态

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Boolean getAutoStartup() {
        return autoStartup;
    }

    public void setAutoStartup(Boolean autoStartup) {
        this.autoStartup = autoStartup;
    }

}
