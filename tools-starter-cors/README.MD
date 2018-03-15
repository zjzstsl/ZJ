## 允许服务端跨域
    
    允许微服务接受跨域请求
    
### 能力

- 与Spring Boot的CrossOrigin跨域相比，这个Starter可以全局设置微服务支持跨域请求.
- 也可以通过配置指定跨域的具体设置，见[Quick start](#Quick start )

### Quick start

1、在pom.xml中加入
``` xml
<dependency>
    <groupId>org.tis</groupId>
    <artifactId>tools-cors-spring-boot-starter</artifactId>
    <version>${tools-starter.version}</version>
</dependency>
```
2、在启动类上标柱 @EnableServiceCORS
```java
@EnableServiceCORS
@SpringBootApplication
public class ToolsForTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolsForTestApplication.class, args);
    }
}
```
3、只要使用了@EnableServiceCOTS，默认已经开启了跨域能力，如需关系，请设置： tools.cors.open=flase
``` yml
tools: 
    cors: 
        open: false
```
4、如果需要特殊指定跨域配置请参考以下配置项说明

>tools.cors.open=true/false ：开启跨域能力

>tools.cors.allow-credentials ：是否允许发送cookie 

>tools.cors.allow-headers ：允许获取那些Header字段 

>tools.cors.allow-methods ： 允许请求哪些HTTP方法

>tools.cors.allow-origins ： 允许哪些源发起CORS请求

>tools.cors.mapping-path ： 指定那些url允许CORS请求

>tools.cors.max-age ： 指定CORS预检查有效时间（单位秒）


