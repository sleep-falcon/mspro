# 第四个中间件---自定义拦截方法

### 使用方法

1. 使用注解

   @DoMethodExt(method = "blacklist", returnJson = "{\"code\":\"5xx\",\"info\":\"自定义校验方法拦截，不允许访问！\"}")
   
   method为自定义的拦截方法名（同一个类下，相同输入参数）， returnJson为自定义的返回值
   

### 学习要点

1. 学习了ProceedingJoinPoint 与 获取到的method的区别

   method可以获取方法声明方面的信息，jp则可以获取对象的信息

2. 了解了jp.getThis()与jp.getTarget()的区别，以及method.invoke( , )的用法。

   