## swagger技术组件

    集成了swagger为tools应用项目提供接口文档化发能力。

### 能力描述

- 集成参考[spring-boot-starter-swagger](https://github.com/SpringForAll/spring-boot-starter-swagger)
- @EnableSwagger2Doc

### Quick start

1、在pom.xml中加入
``` xml
<dependency>
    <groupId>org.tis</groupId>
    <artifactId>tools-swagger-spring-boot-starter</artifactId>
    <version>${tools-starter.version}</version>
</dependency>
```
2、在启动类上增加注解 @org.tis.tools.starter.swagger.EnableSwagger2Doc
如：
``` java
@EnableSwagger2Doc
@SpringBootApplication
public class ToolsForTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolsForTestApplication.class, args);
    }
}
```
3、在项目配置文件中加入swagger相关配置，如：
``` yml
swagger:
  enabled: true
  title: tools-for-test
  description: 测试项目
  version: 0.0.1-SANPSHOT
  license: Apache License, Version 2.0
  licenseUrl: https://www.apache.org/licenses/LICENSE-2.0.html
  contact.name: Shiyunlai
  contact.email: shi.yunlai@chinamworld.com
  base-package: org.tis
```
>以上为基础配置（具体内容根据项目信息修改），更多配置使用请参考[配置说明](https://github.com/SpringForAll/spring-boot-starter-swagger/blob/master/README.md#%E9%85%8D%E7%BD%AE%E8%AF%B4%E6%98%8E)

4、在服务接口上以swagger注解，记录注释信息，
如：
```java
@Api(tags = "用户管理")
@RestController
public class UserController {
    
    @ApiOperation("用户列表")
    @GetMapping("/users")
    public List<User> list(@ApiParam("查看第几页") @RequestParam int pageIndex,
                           @ApiParam("每页多少条") @RequestParam int pageSize) {
        List<User> result = new ArrayList<>();
        result.add(new User("aaa", 50, "北京", "aaa@ccc.com"));
        result.add(new User("bbb", 21, "广州", "aaa@ddd.com"));
        return result;
    }
    ...
}
```
>swagger注解的使用请参考[swagger常用注解说明](https://www.jianshu.com/p/12f4394462d5)，更多请自行查阅。

5、启动应用项目，访问以下url，查看API信息

    http://ip:port/swagger-ui.html
