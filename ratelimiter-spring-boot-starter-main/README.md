# 第三个中间件---方法限流

### 使用方法

1. 使用注解

   @DoRateLimiter(permitsPerSecond = 1, returnJson = "{\"code\":\"5xx\",\"info\":\"调用方法超过最大次数，限流返回！\"}")
   
   permitsPerSecond为每秒最多的访问次数， returnJson为自定义的返回值
   

### 学习要点

1. 主要是对现有解决方案的包装，未来可以深入了解RateLimiter。

2. 使用ConcurrentHashMap，为每一个方法独立创建限流。

   