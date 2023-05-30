package middleware.ratelimiter.valve.impl;

import middleware.ratelimiter.Constants;
import middleware.ratelimiter.annotation.DoRateLimiter;
import middleware.ratelimiter.valve.ValveService;
import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class RateLimiterValve implements ValveService {

    @Override
    public Object access(ProceedingJoinPoint jp, Method method, DoRateLimiter doRateLimiter, Object[] args) throws Throwable {
        // 判断是否开启
        if (0 == doRateLimiter.permitsPerSecond()) return jp.proceed();

        //获得方法的class.method即type，使得每个方法一个RateLimiter
        String clazzName = method.getClass().getName();
        String methodName = method.getName();
        String key = clazzName + "." + methodName;

        //新方法创建RateLimiter
        if (null == Constants.rateLimiterMap.get(key)) {
            Constants.rateLimiterMap.put(key, RateLimiter.create(doRateLimiter.permitsPerSecond()));
        }

        //获得对应的RateLimiter，可以则放行，多了则限流
        RateLimiter rateLimiter = Constants.rateLimiterMap.get(key);
        if (rateLimiter.tryAcquire()) {
            return jp.proceed();
        }

        return JSON.parseObject(doRateLimiter.returnJson(), method.getReturnType());
    }

}
