### SpringBoot

#### 一、环境

* spring Boot2要求Java要在1.8之上，maven要在3.3之上

#### 二、spring Boot和spring

##### 2.1springBoot能做什么

* spring的生态覆盖了：web开发(Spring Framework)，数据访问(SpringData)，安全控制(SpringSecurity)，分布式(SpringCloud)，消息服务(AMQP)、移动开发(SpringMobile)、批处理(Batch).......

* spring Boot可以快速创建出生产级别的spring应用，是一个高层框架，底层是spring，它可以帮助我们整合spring的整个生态圈，让我们开始编程

* 由于spring5进行了重大升级，所以有了响应式编程，老版本使用的是servlet那一套，而boot2使用了响应式的编程

![](F:\4.png)

##### 2.2spring Boot的优点

1. 创建一个独立的spring的应用
2. 内嵌了web服务器，不再需要将代码打成war包放入tomcat服务器
3. 使用stater依赖，简化构建配置，避免jar包冲突
4. 自动配置spring以继第三方应用
5. 提供生产级别的监控，外部配置
6. 无代码产生，无需编写xml

#### 三、微服务

##### 3.1、基本概念

* 微服务是一种架构风格
* 一个应用拆分为一组小型服务
* 每个服务运行在自己的进程内，可以独立部署和升级
* 服务之间需要通信使用轻量级的HTTP
* 服务围绕着业务功能拆分
* 可以由全自动部署机制独立部署
* 去中心化，服务自治，服务可以使用不同语言，不同存储技术

##### 3.2分布式

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

##### 3.3云原生

* 服务自愈(若生产某一台机器宕机，如何重新拉起一台)
* 弹性伸缩(流量高峰增加机器，流量低峰减少机器)

- 服务隔离(一台机器的多个服务互不影响)
- 自动化部署(由于机器和服务模块众多，可以自动化)

- 灰度发布(发布时，先发布一台机器，再逐个替换)
- 流量治理(治理低性能机器少连接，高性能的机器多连接)

- ......

---

#### 四、Spring Boot入门

##### 4.1、官方文档的查看

* 官方参考文档从入门到精通

​       https://docs.spring.io/spring-boot/docs/current/reference/html/

* 查看版本新特性；

  https://github.com/spring-projects/spring-boot/wiki#release-notes

##### 4.2、Hello World

###### 4.2.1设置maven

​          maven要在3.5之上，其次使用阿里云镜像会快一点，使用1.8编译

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

###### 4.2.2创建hello world

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

##### 4.3、Spring Boot的特点

###### 4.3.1、依赖管理

* 父项目做的依赖

```xml
<!--每一个boot项目都会依赖这个parent，而这个parent又依赖于dependencies，在dependencies中定义了常用的依赖的版本号，也就是版本仲裁机制--> 
<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.3</version>
    </parent>
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.5.3</version>
  </parent>
```

* 自动仲裁：无需关注版本号，引入默认依赖时不需要写版本号，当然也可以修改默认的版本号，例如想要更改mysql的驱动，在pom文件中加入一个properties修改为想要的mysql版本号Maven会就近配置

```xml
<properties>
      <mysql.version>5.1.43</mysql.version>
</properties>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>${mysql.version}</version>
</dependency>
```

* 导入各类starter场景启动器

```xml
1.官方给我出的各类场景驱动器都是 spring-boor-starter-*的格式   *就是各类场景
2.只要引入starter，这个场景所需要的常规依赖都会自动导入
3.spring Boot几乎涵盖了所有的场景
https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html#using-boot-starter
4.除了官方给出的starter还会有一些第三方给出的starter，包括我们也可以自定义starter格式一般都是：*-spring-boot-starter
5.所有的场景启动器都是依赖于
 <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
      <version>2.5.3</version>
      <scope>compile</scope>
 </dependency>
```

###### 4.3.2、自动装配

* 先有个概念和体验，后面具体来说

* 例如我们引入web-starter后自动配置了tomcat以及springMVC等

* 以mvc为例

  * 在我们引入web-starter的时候引入了mvc的全套组件

  ```xml
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-web</artifactId>
        <version>5.3.9</version>
        <scope>compile</scope>
      </dependency>
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.9</version>
        <scope>compile</scope>
      </dependency>
  ```

  而且帮我们配好了mvc常见的组件例如：字符集编码，文件上传等

  - 默认的包结构(自动扫包机制)

  - - 主程序所在包及其下面的所有子包里面的组件都会被默认扫描进来
    - 无需以前的包扫描配置

  - - 想要改变扫描路径，@SpringBootApplication(scanBasePackages=**"com.yang"**)

  - - - 或者@ComponentScan 指定扫描路径

* 各种配置拥有默认值

- - 默认配置最终都是映射到某个类上，如：MultipartProperties
  - 配置文件的值最终会绑定每个类上，这个类会在容器中创建对象

- 按需加载所有自动配置项

- - 引入了哪些场景这个场景的自动配置才会开启

- - SpringBoot所有的自动配置功能都在 spring-boot-autoconfigure 包里面

##### 4.4、容器功能

###### 4.4.1、组件添加

* @Configuration注解

  * 该注解告诉spring Boot该类为一个配置类，在类中使用@Bean来添加组件

  * Full模式
    * 默认情况是full模式，spring Boot会每次检查容器中的组件，若已经有，会使用已有的组件，若没有会进行注册
    * 若有组件依赖时，会使用full模式，其他使用lite模式
  * Lite模式
    * 运行时不用生成CGLIB子类，提高运行性能，降低启动时间，可以作为普通类使用。但是不能声明@Bean之间的依赖
* 之前的@Component、@Controller、@Service、@Repository也可以进行组件添加
* @Import：可以导入多个类，底层是一个数组；给容器中自动创建出该类的组件，默认组件名字及时全类名

###### 4.4.2、条件装配

* @Conditional注解：满足某个条件才进行组件的注入
* 可以加在方法也可以直接加在类上面

```java
 @Bean
    @ConditionalOnBean(Pet.class)
    public User user01(){
        User user = new User("张三", 18);
        user.setPet(pet01());
        return user;
    }
//以该注解为例，当容器中有Pet类型的bean时才对user进行注入
```

###### 4.4.3、原生配置文件引入

* ==@ConfigurationProperties(prefix = "mycar")==注解加在bean上面，将properties内容绑定到bean上面
* 因为只有在容器的组件才能使用spring Boot的强大功能，所以要在bean上面添加@Component注解
* 当然有时候我们需要用的是第三方的bean，没法用@Component注解，我们可以在我们的配置类上面添加@EnableConfigurationProperties(bean.class)代表开启配置绑定功能，会把这个bean自动注册到容器中

##### 4.5、自动配置原理

###### 4.5.1、@SpringBootApplication注解

* 该注解是一个复合注解由 @SpringBootConfiguration、@EnableAutoConfiguration、@ComponentScan这三个注解构成
* 其中@SpringBootConfiguration注解代表当前类是一个配置类，是spring Boot的核心配置类
* @ComponentScan注解可以指定扫描包
* @EnableAutoConfiguration该注解是spring Boot自动装配的核心注解

###### 4.5.2、@EnableAutoConfiguration注解

* 该注解也是合成注解由@AutoConfigurationPackage、@Import这两个注解构成

* 其中@AutoConfigurationPackage注解是自动配置包的意思，主要是指定了包默认规则

  * ```java
    @Import({Registrar.class})
    public @interface AutoConfigurationPackage {
    //Import注解为spring Boot注册一系列的组件
    //利用Registrar方法将指定包下的所有组件都注入；也就是主应用程序所在的包，所以这就是为什么默认包路径是和主应用程序的路劲是一致的
    //获取包路径
    //  (new AutoConfigurationPackages.PackageImports(metadata)).getPackageNames()
    ```

* @Import({AutoConfigurationImportSelector.class})注解批量导入组件

  * AutoConfigurationImportSelector是关键类，而这个类中的 getCandidateConfigurations(annotationMetadata, attributes)获取到所有需要导入到容器中的配置类
  * 利用工厂加载 Map<String, List<String>> loadSpringFactories(@Nullable ClassLoader classLoader)；得到所有的组件
  * 从META-INF/spring.factories位置来加载一个文件。默认扫描当前系统里面所有META-INF/spring.factories位置的文件 spring-boot-autoconfigure-2.5.3.RELEASE.jar包里面也有META-INF/spring.factories
  * spring.factories 文件里面写死了spring-boot一启动就要给容器中加载的所有配置类，一共127个xxxAutoConfiguration；然后spring Boot会根据按条件加载的规则(@Conditional)，加载需要的组件

4.5.3