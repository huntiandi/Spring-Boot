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

* ==@Configuration==注解
  * 该注解告诉spring Boot该类为一个配置类，在类中使用@Bean来添加组件

  * Full模式
    * 默认情况是full模式，spring Boot会每次检查容器中的组件，若已经有，会使用已有的组件，若没有会进行注册
    * 若有组件依赖时，会使用full模式，其他使用lite模式
  * Lite模式
    * 运行时不用生成CGLIB子类，提高运行性能，降低启动时间，可以作为普通类使用。但是不能声明@Bean之间的依赖
    * 也就是将==proxyBeanMethods==设置为FALSE，每次调用@Bean就都是单独的一个对象，而不是从容器中取了
* 之前的@Component、@Controller、@Service、@Repository也可以进行组件添加
* @Import：可以导入多个类，底层是一个数组；给容器中自动创建出该类的组件，默认组件名字及时全类名

###### 4.4.2、条件装配

* ==@Conditional==注解：满足某个条件才进行组件的注入
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
* 因为只有在容器的组件才能使用spring Boot的强大功能，所以要在bean上面添加@Component注解才能让@ConfigurationProperties注解生效
* 当然有时候我们需要用的是第三方的bean，没法用@Component注解，我们可以在我们的配置类上面添加==@EnableConfigurationProperties==(bean.class)代表开启配置绑定功能，会把这个bean自动注册到容器中

##### 4.5、自动配置原理

###### 4.5.1、@SpringBootApplication注解

* 该注解是一个复合注解由 @SpringBootConfiguration、@EnableAutoConfiguration、@ComponentScan这三个注解构成
* 其中@SpringBootConfiguration注解代表当前类是一个配置类，是spring Boot的核心配置类
* @ComponentScan注解可以指定扫描包
* ==@EnableAutoConfiguration==该注解是spring Boot自动装配的核心注解

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

  * AutoConfigurationImportSelector是关键类，而这个类中的 getAutoConfigurationEntry方法中的getCandidateConfigurations(annotationMetadata, attributes)---->loadFactoryNames--->loadSpringFactories获取到所有需要导入到容器中的配置类
  * 利用工厂加载 Map<String, List<String>> loadSpringFactories(@Nullable ClassLoader classLoader)；得到所有的组件
  * 从META-INF/spring.factories位置来加载一个文件。默认扫描当前系统里面所有META-INF/spring.factories位置的文件 spring-boot-autoconfigure-2.5.3.RELEASE.jar包里面也有META-INF/spring.factories
  * spring.factories 文件里面写死了spring-boot一启动就要给容器中加载的所有配置类，一共127个xxxAutoConfiguration；然后spring Boot会根据按条件加载的规则(@Conditional)，加载需要的组件

###### 4.5.3自动装配原理

1. spring Boot中最重要的注解就是@SpringBootApplication，而这个注解时一个复合注解，由三个注解合成，其中实现自动装配的核心注解是@EnableAutoConfiguration，其中@Import({AutoConfigurationImportSelector.class})注解是重点
2. 利用AutoConfigurationImportSelector类跳转到SpringFactoriesLoaderloader类中的loderSpringFactories方法将SpringBoot中的META-INF/spring.factories加载进来
3. 工厂里面定义了许多的xxxAutoConfiguration，这些xxxAutoConfiguration按照条件生效，之后从xxxproperties中取值(EnableConfigurationProperties绑定了需要使用的properties，这个注解会将xxxproperties注册到我们的容器中)，而如果我们对属性进行了修改@ConditionalOnProperty(按条件生效)这个注解就会发挥作用，从application.properties中拿值

##### 4.6小结

1. 引入场景依赖

- * https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html#using-boot-starter

2. 查看自动配置了哪些（选做）

- - 自己分析，引入场景对应的自动配置一般都生效了
  - 配置文件中debug=true开启自动配置报告。Negative（不生效）\Positive（生效）

3. 是否需要修改

- - 参照文档修改配置项

- - - https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html#common-application-properties
    - 自己分析。xxxxProperties绑定了配置文件的哪些。

- - 自定义加入或者替换组件

- - - @Bean、@Component。。。

- - 自定义器  **XXXXXCustomizer**；
  - ......

#### 五、开发小技巧

##### 5.1、Lombok插件

* 简化JavaBenan的开发

```xml
<!--1.pom文件引入lombok依赖-->
<dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
 </dependency>
<!--2.在bean上添加想要的注解，例如get/set方法可以使用@Data注解，以及@ToString等注解;也可以使用@Slf4j添加日志-->
```

##### 5.2、dev-tools

* 不要钱的热部署，其实就是自动重启

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>
<!--修改代码后直接CTRL+F9就可以了-->
```

##### 5.3、Spring Initailizr自动创建spring Boot项目

* 在创建model时使用Spring Initailizr可以自动添加我们想要的场景，会自动搭建好主体架构，配置类，依赖

---

#### 六、Spring Boot核心功能

##### 6.1、配置文件

###### 1、文件类型

1.1、properties文件；和之前spring中的properties文件使用一致

1.2、==yaml文件==

* YAML 是 "YAML Ain't Markup Language"（YAML 不是一种标记语言）的递归缩写。在开发的这种语言时，YAML 的意思其实是："Yet Another Markup Language"（仍是一种标记语言）。
* 非常适合用来做以数据为中心的配置文件

###### 1.2.2、基本语法

* key: value；kv之间有空格
* 大小写敏感

- 使用缩进表示层级关系
- 缩进不允许使用tab，只允许空格

- 缩进的空格数不重要，只要相同层级的元素左对齐即可
- '#'表示注释

- 字符串无需加引号，如果要加，单引号与双引号，对字符串内容含义不同
  * 例如\n单引号不会换行，双引号会换行

###### 1.2.3、数据类型

* 字面量：基本数据类型

  * k: v
  * date类型时需要添加时区

* 对象，map，list，object 

  * ```properties
    行内写法：  k: {k1:v1,k2:v2,k3:v3}
    #或
    k: 
    	k1: v1
      k2: v2
      k3: v3
    ```

* 数组和set

```properties
行内写法：  k: [v1,v2,v3]
#或者
k:
 - v1
 - v2
 - v3
```

###### 1.2.4、提示信息

* 我们自定义得bean没有提示需要在pom文件中添加spring-boot-configuration-processor

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>


 <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <!--打包时不对其进行打包,因为没必要-->
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.springframework.boot</groupId>
                            <artifactId>spring-boot-configuration-processor</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
```



#### 6.2、web开发

##### 6.2.1、静态资源访问

* 关于是springBoot中web的官方文档https://docs.spring.io/spring-boot/docs/current/reference/html/web.html#web
* 默认情况下，Spring Boot 从类路径中名为`/static`（或`/public`或`/resources`或`/META-INF/resources`）的目录获取静态资源

##### 6.2.2、欢迎页面

- 静态资源路径下  index.html

- - 可以配置静态资源路径
  - 但是不可以配置静态资源的访问前缀。否则导致 index.html不能被默认访问

```yaml
spring:
#  mvc:
#    static-path-pattern: /res/**   这个会导致welcome page功能失效

  resources:
    static-locations: [classpath:/haha/]
```

- controller能处理/index

##### 6.2.3、自定义 `Favicon`

favicon.ico 放在静态资源目录下即可。

```yaml
spring:
#  mvc:
#    static-path-pattern: /res/**   这个会导致 Favicon 功能失效
```

##### 6.2.4、静态资源配置原理

- SpringBoot启动默认加载  xxxAutoConfiguration 类（自动配置类）
- SpringMVC功能的自动配置类 WebMvcAutoConfiguration，生效

```java
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = Type.SERVLET)
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class })
@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
@AutoConfigureAfter({ DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class,
		ValidationAutoConfiguration.class })
public class WebMvcAutoConfiguration {}
```

- 给容器中配了什么

```java
@Configuration(proxyBeanMethods = false)
@Import(EnableWebMvcConfiguration.class)
@EnableConfigurationProperties({ WebMvcProperties.class, ResourceProperties.class })
	@Order(0)
	public static class WebMvcAutoConfigurationAdapter implements WebMvcConfigurer {}
```

* 配置文件的相关属性和xxx进行了绑定。WebMvcProperties：spring.mvc； ResourceProperties：spring.resources

###### 2.4.2、关于配置类

* 配置类只有一个有参构造器

```java
	//有参构造器所有参数的值都会从容器中确定
//WebProperties webProperties；获取和spring.web绑定的所有的值的对象
//WebMvcProperties mvcProperties 获取和spring.mvc绑定的所有的值的对象
//ListableBeanFactory beanFactory Spring的beanFactory
//HttpMessageConverters 找到所有的HttpMessageConverters
//ResourceHandlerRegistrationCustomizer 找到 资源处理器的自定义器。=========
//DispatcherServletPath  
//ServletRegistrationBean   给应用注册Servlet、Filter....

  public WebMvcAutoConfigurationAdapter(WebProperties webProperties, WebMvcProperties mvcProperties, ListableBeanFactory beanFactory, ObjectProvider<HttpMessageConverters> messageConvertersProvider, ObjectProvider<WebMvcAutoConfiguration.ResourceHandlerRegistrationCustomizer> resourceHandlerRegistrationCustomizerProvider, ObjectProvider<DispatcherServletPath> dispatcherServletPath, ObjectProvider<ServletRegistrationBean<?>> servletRegistrations) {
           。。。
        }
```

* 资源的默认处理方式

```java
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            if (!this.resourceProperties.isAddMappings()) {
                logger.debug("Default resource handling disabled");
            } else {
                this.addResourceHandler(registry, "/webjars/**", "classpath:/META-INF/resources/webjars/");
                this.addResourceHandler(registry, this.mvcProperties.getStaticPathPattern(), (registration) -> {
                    registration.addResourceLocations(this.resourceProperties.getStaticLocations());
                    if (this.servletContext != null) {
                        ServletContextResource resource = new ServletContextResource(this.servletContext, "/");
                        registration.addResourceLocations(new Resource[]{resource});
                    }

                });
            }}
//this.resourceProperties.getStaticLocations()获取路径； this.mvcProperties.getStaticPathPattern()获取前缀(/**)
```

* 欢迎页的配置

```java
 @Bean
        public WelcomePageHandlerMapping welcomePageHandlerMapping(ApplicationContext applicationContext, FormattingConversionService mvcConversionService, ResourceUrlProvider mvcResourceUrlProvider) {
            WelcomePageHandlerMapping welcomePageHandlerMapping = new WelcomePageHandlerMapping(new TemplateAvailabilityProviders(applicationContext), applicationContext, this.getWelcomePage(), this.mvcProperties.getStaticPathPattern());
            。。。
            return welcomePageHandlerMapping;
        }
```

* 其中    new WelcomePageHandlerMapping  决定了只能在/下面才能使用；favicon.ico同理

```java
    WelcomePageHandlerMapping(TemplateAvailabilityProviders templateAvailabilityProviders, ApplicationContext applicationContext, Resource welcomePage, String staticPathPattern) {
        //要用欢迎页功能，必须是/**
        if (welcomePage != null && "/**".equals(staticPathPattern)) {
            logger.info("Adding welcome page: " + welcomePage);
            this.setRootViewName("forward:index.html");
        } else if (this.welcomeTemplateExists(templateAvailabilityProviders, applicationContext)) {
              // 调用Controller  /index
            logger.info("Adding welcome page template: index");
            this.setRootViewName("index");
        }}
```

##### 6.2.5、请求参数处理

###### 2.5.1关于rest风格

* Rest风格支持（*使用HTTP请求方式动词来表示对资源的操作*）

* 核心在于filter即HiddenHttpMethodFilter(*它会将除了get和post支持的方法之外 的方法进行转换*)

  * 在springBoot中我们对mvc进行了自动配置，所以在使用时我们需要在yaml中手动打开(*因为使用postman等工具不需要转换，可以直接访问*)

  * ```java
    	@Bean
    	@ConditionalOnMissingBean(HiddenHttpMethodFilter.class)
    	@ConditionalOnProperty(prefix = "spring.mvc.hiddenmethod.filter", name = "enabled", matchIfMissing = false)
    	public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter() {
    		return new OrderedHiddenHttpMethodFilter();
    	}
    ```

* 有上↗可知，我们在容器中没有HiddenHttpMethodFilter类时才会开启该配置，所以我们也可以自定义一个HiddenHttpMethodFilter，这样可以将“_methord”自定义为自己喜欢的名字

* rest实现原理

  1. 在表单提交的时候会有一个参数"_methord"
  2. 请求到达后会被拦截器拦截，将其"_methord"带的值获取，并判断该表单提交方式是否为post
  3. 原生request（post），包装模式requesWrapper重写了getMethod方法，返回的是传入的值即(*PUT,DELETE,PATCH*)
  4. 过滤器在放行时使用的wrapper，之后调用时方法就变成了原有的"_methord"(*PUT,DELETE,PATCH*)中的值

###### 2.5.2、请求映射原理

* springMvc的分析都是从DispatcherServletAutoConfiguration中引用的DispatcherServlet类的doDispatch()方法开始

* ```java
  	// 找到当前请求使用哪个Handler（Controller的方法）处理
     				mappedHandler = getHandler(processedRequest);
  ```

* 其中**RequestMappingHandlerMapping**：保存了所有@RequestMapping 和handler的映射规则。

* SpringBoot自动配置欢迎页的 WelcomePageHandlerMapping 。访问 /能访问到index.html；

* SpringBoot自动配置了默认 的 RequestMappingHandlerMapping

- 请求进来，挨个尝试所有的HandlerMapping看是否有请求信息。

- - 如果有就找到这个请求对应的handler
  - 如果没有就是下一个 HandlerMapping

- 我们需要一些自定义的映射处理，我们也可以自己给容器中放**HandlerMapping**。

###### 2.5.3、普通参数与注解

* @PathVariable、@RequestHeader、@ModelAttribute、@RequestParam、@MatrixVariable、@CookieValue、@RequestBody
  * 第一个是rest风格中获取参数使用的注解
  * 第二个是获取请求头参数
  * 第三个是model
  * 第四个获取请求路径中的参数
  * 矩阵变量，默认是禁用的，需要手动开启
  * 获取cookie值，获取整个请求体
* 原理：==待更新==

###### 2.5.4、Servlet API

* 我们可以传入一些原生的servlet来进行操作，例如WebRequest、ServletRequest、MultipartRequest、 HttpSession、等等
* 其中 **ServletRequestMethodArgumentResolver**  可以解析以上的部分参数

###### 2.5.5、复杂参数

* **Map**、**Model（map、model里面的数据会被放在request的请求域  request.setAttribute）edirectAttributes（ 重定向携带数据）**、**ServletResponse（response）**、
* **Map、Model类型的参数**，会返回 mavContainer.getModel（）；---> BindingAwareModelMap 是Model 也是Map

###### 2.5.6、自定义参数

* 

##### 6.2.6、数据响应与内容协商

##### 6.2.7、视图解析与模板引擎

##### 6.3、数据访问

###### 6.3.1、配置数据源

* 引入jdbc相关starter，jdbc-starter引入了数据源(HikariDataSource)，jdbc，事务(spring-tx)；没有导入数据库驱动，因为spring-boot并不知道你需要使用什么数据库

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jdbc</artifactId>
        </dependency>
```

* 导入数据库驱动，数据库的驱动要和数据库的版本对应，由于boot对版本进行了仲裁，所以当需要修改版本时，可以显示的写在version标签中，也可以在properties标签中修改<mysql.version>8.0.22</mysql.version>

```xml
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
<!--            <version>5.1.49</version>-->
        </dependency>
```

###### 6.3.2、数据的自动配置类

* DataSourceAutoConfiguration该类是数据源的配置类，修改相关配置时使用**spring.datasource**，其中底层默认的数据库连接池是我们没有自定义才会自动配置
* DataSourceTransactionManagerAutoConfiguration： 事务管理器的自动配置
* JdbcTemplateAutoConfiguration： **JdbcTemplate的自动配置，可以来对数据库进行crud**

- - 可以修改这个配置项@ConfigurationProperties(prefix = **"spring.jdbc"**) 来修改JdbcTemplate
  - @Bean@Primary    JdbcTemplate；容器中有这个组件

- JndiDataSourceAutoConfiguration： jndi的自动配置
- XADataSourceAutoConfiguration： 分布式事务相关的

###### 6.3.3、Druid数据源

* 引入starter

```xml
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.2.8</version>
        </dependency>
```

- 扩展配置项 **spring.datasource.druid**
- DruidSpringAopConfiguration.**class**,   监控SpringBean的；配置项：**spring.datasource.druid.aop-patterns**

- DruidStatViewServletConfiguration.**class**, 监控页的配置：**spring.datasource.druid.stat-view-servlet；默认开启**
-  DruidWebStatFilterConfiguration.**class**, web监控配置；**spring.datasource.druid.web-stat-filter；默认开启**

- DruidFilterConfiguration.**class**}) 所有Druid自己filter的配置

###### 6.3.4、整合mybatis操作

* 引入stater

```xml
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.0</version>
        </dependency>
```

* 自动装配的东西

  * SqlSessionFactory: 自动配置好了
  * SqlSession：自动配置了 **SqlSessionTemplate 组合了SqlSession**

  - @Import(**AutoConfiguredMapperScannerRegistrar**.**class**）；
  - Mapper： 只要我们写的操作MyBatis的接口标准了 **@Mapper 就会被自动扫描进来**

* 基于配置方式的整合

  1. 再resource目录下需要一个mybatis的全局变量文件，和一个存放mapper.xml的文件夹；
  2. 在mapper接口上标注@Mapper注解，mapper接口和mapper.xml绑定好
  3. 在application.yaml中指定mapper.xml的位置，以及配置文件的位置或者配置configuration属性
  4. 如果数据库表中字段是带_的需要开启mybatis的驼峰命名规则，可以在全局变量文件中的<setting>标签的mapUnderscoreToCamelCase属性设置为true
  5. 也可以不写全局变量的配置文件直接在yaml中configuration下面设置即可(**推荐**)

* 基于注解模式

  * 可以在mapper接口的方法上面写注解例如@Select，@Update，@Delete等
  * 简单的sql可以使用注解模式，不编写mapper.xml

* 总结

  * 引入mybatis-starter
  * **配置application.yaml中，指定mapper-location位置即可**

  - 编写Mapper接口并标注@Mapper注解
  - 简单方法直接注解方式，复杂方法编写mapper.xml进行绑定映射
- *@MapperScan("com.atguigu.admin.mapper") 简化，其他的接口就可以不用标注@Mapper注解*（不推荐）

###### 6.3.5、整合mybatis-Plus

* 

###### 6.3.6、整合redis

##### 6.4、单元测试

##### 6.5、指标监控

##### 6.6、原理解析
