package middleware.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Constants {

    //给不同的方法设置个性化的限流
    public static Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<String, RateLimiter>();
}
