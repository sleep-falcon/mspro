package middleware.test.service;

import middleware.redis.annotation.XRedis;

@XRedis
public interface IRedisService {

    String get(String key);

    void set(String key, String val);

}
