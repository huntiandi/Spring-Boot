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
  * 远程调用问题(a服务放在了3台机器，b服务放在了2台机器，c服务放在4台机器；abc呼啸调用就出现了远程调用问题。一般使用HTTP来解决远程调用的通信问题)
  * 服务发现(由于每一个服务都放在了多台机器，哪么那一台才是好的那一台是宕机的于是出现了服务发现问题)
  * 负载均衡(当服务全部是正常时，到底访问哪一个机器就需要负载均衡来解决了)
  * 服务容错(由于每个服务都是多台机器，若正在调用的机器发生了异常，或者网络出现了异常应该做什么反应)
  * 配置管理(因为一个服务放在了多台机器，修改配置时只改配置中心的配置即可，不需要将每一台机器都修改一次)
  * 服务监控(多台机器的运行状况)
  * 链路追踪(abc多个服务之间的调用情况)
  * 日志管理(多台机器的多个日志收集问题)
  * 任务调度(定时任务应该在哪一台机器运行)
  * ......
* 解决大方案：SpringBoot+SpringCloud

3.3云原生

* 服务自愈(若生产某一台机器宕机，如何重新拉起一台)
* 弹性伸缩(流量高峰增加机器，流量低峰减少机器)

- 服务隔离(一台机器的多个服务互不影响)
- 自动化部署(由于机器和服务模块众多，可以自动化)

- 灰度发布(发布时，先发布一台机器，再逐个替换)
- 流量治理(治理低性能机器少连接，高性能的机器多连接)

- ......

---

四、Spring Boot入门

4.1、官方文档的查看

* 官方参考文档从入门到精通

​       https://docs.spring.io/spring-boot/docs/current/reference/html/

* 查看版本新特性；

  https://github.com/spring-projects/spring-boot/wiki#release-notes

4.2、Hello World

4.2.1设置maven，maven要在3.3之上，其次使用阿里云镜像会快一点，使用1.8编译

```xml
<mirrors>
      <mirror>
        <id>nexus-aliyun</id>
        <mirrorOf>central</mirrorOf>
        <name>Nexus aliyun</name>
        <url>http://maven.aliyun.com/nexus/content/groups/public</url>
      </mirror>
  </mirrors>
 
  <profiles>
         <profile>
              <id>jdk-1.8</id>
              <activation>
                <activeByDefault>true</activeByDefault>
                <jdk>1.8</jdk>
              </activation>
              <properties>
                <maven.compiler.source>1.8</maven.compiler.source>
                <maven.compiler.target>1.8</maven.compiler.target>
                <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
              </properties>
         </profile>
  </profiles>
```

4.2.2创建hello world

1. 在idea中创建一个maven项目，不需要选择模板
2. 在pom文件中加入如下代码

```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.3</version>
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
```

​     3.创建main方法，告诉springBoot这是一个spring Boot项目

```java
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
```

​    4.业务逻辑

```java
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello boot";
    }
}
```

   5.可以创建一个application.properties修改配置

```xml
server.port=8888
```

   6.简化部署，可以打成一个jar包直接java -jar xxx.jar来运行

```xml
 <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```

* 要==取消==掉cmd中的快速编辑模式，要不然会卡住不动
* 为什么spring Boot的==jar包可以直接运行==

4.3、Spring Boot的特点

4.3.1、依赖管理

* 

4.3.2、自动装配