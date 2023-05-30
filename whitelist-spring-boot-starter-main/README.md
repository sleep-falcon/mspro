# 第一个中间件---白名单

### 使用方法

1. 配置yml

   ```yaml
   #白名单用户
   whitelist:
   	users: xxx,xx,xxxx
   ```

2. 使用注解

   @DoWhiteList(key = "userId", returnJson = "{\"code\":\"502\",\"info\":\"非白名单可访问用户拦截！\"}")

   key为需要拦截的属性， returnJson为自定义的返回值

### 学习要点

1. 初次尝试AOP编程，定义切面、切点、Advice
2. 使用@configurationProperities( ) 读取yaml的值
3. 根据ProceedingJointPoint 或得被拦截方法（name、输入args、注解）
4. 自定义注解