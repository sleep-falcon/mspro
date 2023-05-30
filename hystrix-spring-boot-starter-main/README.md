# 第二个中间件---方法超时熔断

### 使用方法

1. 使用注解

   @DoHystrix(timeout = 350, returnJson = "{\"code\":\"1111\",\"info\":\"调用方法超过350毫秒，熔断返回！\"}")
   
   timeout为需要设定的时限， returnJson为自定义的返回值
   

### 学习要点

1. 主要是对现有解决方案的包装，未来可以深入了解Hystrix。

2. 针对接口，自动装配方案出现区别。

   @Resource(name = "hystrixValveImpl")

   @Autowired+@Qualifier("hystrixValveImpl")

   