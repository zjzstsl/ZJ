## 持久层技术组件

    集成了mybatis-plus为tools应用项目提供持久化开发能力。

### 能力描述

- 集成[mybatis-plus](http://baomidou.oschina.io/mybatis-plus-doc/#/ "mybatis-plus")
- 使用 druid 数据连接池
- 提供多数据源能力，使用配置相见 《tools-for-test》项目
- //TODO 集成sharding-jdbc 提供多个数据源的支持 （目前为简单实现，除默认数据源外，只提供了一个数据的定义，见：MultiDataSourceAutoConfiguration）
实现参考：http://blog.csdn.net/catoop/article/details/50575038


### Quick Start

1、在pom.xml中加入

``` xml
<dependency>
    <groupId>org.tis</groupId>
    <artifactId>tools-persistence-spring-boot-starter</artifactId>
    <version>${tools-starter.version}</version>
</dependency>
```

2、开发业务逻辑：controller、service、dao（mapper.java、mapper.xml）

3、在yml或properties中配置mybatis-plus和数据源配置

4、使用注解 @org.tis.tools.starter.multidatasource.annotion.DataSource 指明数据源，如：
以下代码指明了 treeHis()方法在执行时使用名称为otgerDataSource的数据源
``` java
@Override
@DataSource(name = "otherDataSource")
public List<DemoTreeVo> treeHis() {
    return demoMapper.tree();
}
```
>注： 一般在Service或Mapper类的方法上指定数据源
