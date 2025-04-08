# SpringCloudAilibaba_demo
## 本分支初始版 通过restTemplate来进行远程调用

· Spring Cloud 2023 与 Spring Boot 3.2.x，最低支持 JDK 17。

# Nacos配置文件
## order-server.yaml
```
author: cc

spring:
  datasource:
    username: root
    password: root
    url: jdbc:mysql://localhost:3306/seata_order?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource

    #初始化时运行sql脚本
    schema: classpath:sql/schema.sql
    initialization-mode: never

  cloud:
    sentinel:
      transport:
        dashboard: 127.0.0.1:8080  
    
#设置mybatis
mybatis:
  mapper-locations: classpath:mapper/*Mapper.xml
  #config-location: classpath:mybatis-config.xml
  typeAliasesPackage: com.tulingxueyuan.order.pojo
  configuration:
    mapUnderscoreToCamelCase: true

# file模式的配置
seata:
  application-id: ${spring.application.name}
  tx-service-group: ${spring.application.name}-group
  service:
    vgroup-mapping:
      order-server-group: default
    grouplist:
      default: 127.0.0.1:8091    
  
```
## stock-server.yaml
```
author: cc

spring:
  datasource:
    username: root
    password: root
    url: jdbc:mysql://localhost:3306/seata_stock?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource

    #初始化时运行sql脚本
    schema: classpath:sql/schema.sql
    initialization-mode: never
    
#设置mybatis
mybatis:
  mapper-locations: classpath:mapper/*Mapper.xml
  #config-location: classpath:mybatis-config.xml
  typeAliasesPackage: com.tulingxueyuan.stock.pojo
  configuration:
    mapUnderscoreToCamelCase: true

# file模式的配置
seata:
  application-id: ${spring.application.name}
  tx-service-group: ${spring.application.name}-group
  service:
    vgroup-mapping:
      stock-server-group: default
    grouplist:
      default: 127.0.0.1:8091    
```
## gateway.yaml
```
spring:
  cloud: 
    gateway:
      #路由规则
      routes:
        - id: order_route  # 路由的唯一标识，路由到order
          uri: lb://order-server  #需要转发的地址   lb: 使用nacos中的本地负载均衡策略  order-service服务名
          #断言规则 用于路由规则的匹配
          predicates:
            - Path=/order/**
        - id: stock_route  # 路由的唯一标识，路由到order
          uri: lb://stock-server  #需要转发的地址   lb: 使用nacos中的本地负载均衡策略  order-service服务名
          #断言规则 用于路由规则的匹配
          predicates:
            - Path=/stock/**
```