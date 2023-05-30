# 第七个中间件---数据库路由组件

### 使用方法

1. 使用注解

   @DBRouter(key = "userId")
   
   key为分库分表根据的属性
   

### 学习要点

1. springboot自动加载自定义配置yml创建多数据源。

1. 通过注解切面拦截需要路由的DAO方法，并做路由散列计算。

3. 最终由继承了AbstractRoutingDataSource的实现泪处理数据源的动态切换

   