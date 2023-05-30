package middleware.schedule.annotation;

import middleware.schedule.DoJoinPoint;
import middleware.schedule.config.DcsSchedulingConfiguration;
import middleware.schedule.task.CronTaskRegister;
import middleware.schedule.task.SchedulingConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//使用注解后IOC容器自动加载DcsSchedulingConfiguration等类
@ImportAutoConfiguration({DcsSchedulingConfiguration.class,SchedulingConfig.class, CronTaskRegister.class, DoJoinPoint.class})
@ComponentScan("middleware.*")
public @interface EnableDcsScheduling {
}
