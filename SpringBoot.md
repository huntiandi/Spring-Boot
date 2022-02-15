SpringBoot

一、环境

* spring Boot2要求Java要在1.8之上，maven要在3.3之上

二、spring Boot和spring

2.1springBoot能做什么

* spring的生态覆盖了：web开发(Spring Framework)，数据访问(SpringData)，安全控制(SpringSecurity)，分布式(SpringCloud)，消息服务(AMQP)、移动开发(SpringMobile)、批处理(Batch).......

* spring Boot可以快速创建出生产级别的spring应用，是一个高层框架，底层是spring，它可以帮助我们整合spring的整个生态圈，让我们开始编程

* 由于spring5进行了重大升级，所以有了响应式编程，老版本使用的是servlet那一套，而boot2使用了响应式的编程

![](F:\4.png)

2.2spring Boot的优点

1. 创建一个独立的spring的应用
2. 内嵌了web服务器，不再需要将代码打成war包放入tomcat服务器
3. 使用stater依赖，简化构建配置，避免jar包冲突
4. 自动配置spring以继第三方应用
5. 提供生产级别的监控，外部配置
6. 无代码产生，无需编写xml

三、微服务

3.1、基本概念

* 微服务是一种架构风格
* 一个应用拆分为一组小型服务
* 每个服务运行在自己的进程内，可以独立部署和升级
* 服务之间需要通信使用轻量级的HTTP
* 服务围绕着业务功能拆分
* 可以由全自动部署机制独立部署
* 去中心化，服务自治，服务可以使用不同语言，不同存储技术

3.2分布式

* 由于微服务的出现，自然也就出现了分布式；而分布式的出现也引出了分布式的困难
  * 