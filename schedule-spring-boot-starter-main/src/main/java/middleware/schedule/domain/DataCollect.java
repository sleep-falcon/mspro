package middleware.schedule.domain;

public class DataCollect {

    private int ipCount;
    private int serverCount;
    private int beanCount;
    private int methodCount;

    public DataCollect(int ipCount, int serverCount, int beanCount, int methodCount) {
        this.ipCount = ipCount;
        this.serverCount = serverCount;
        this.beanCount = beanCount;
        this.methodCount = methodCount;
    }

    public int getIpCount() {
        return ipCount;
    }

    public void setIpCount(int ipCount) {
        this.ipCount = ipCount;
    }

    public int getServerCount() {
        return serverCount;
    }

    public void setServerCount(int serverCount) {
        this.serverCount = serverCount;
    }

    public int getBeanCount() {
        return beanCount;
    }

    public void setBeanCount(int beanCount) {
        this.beanCount = beanCount;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public void setMethodCount(int methodCount) {
        this.methodCount = methodCount;
    }
}
