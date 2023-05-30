package middleware.test.task;

import middleware.schedule.annotation.DcsScheduled;
import org.springframework.stereotype.Component;

@Component("demoTaskOne")
public class DemoTaskOne {

    @DcsScheduled(cron = "0/3 * * * * *", desc = "01定时任务执行测试：taskMethod01", autoStartup = true)
    public void taskMethod01() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        System.out.println("01定时任务执行测试：taskMethod01");
    }

    @DcsScheduled(cron = "0/5 * * * * *", desc = "01定时任务执行测试：taskMethod02", autoStartup = false)
    public void taskMethod02() {
        try {
            Thread.sleep(35);
        } catch (InterruptedException ignored) {
        }
        System.out.println("01定时任务执行测试：taskMethod02");
    }

}
