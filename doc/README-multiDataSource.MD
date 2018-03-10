# 方式一： 使用Tools自己提供给的多数据

1, 在yml中开启 tools.multi-datasource-open=true

2, 在yml中配置多个数据源

如： 新增名称为otherDataSource的数据源

``` yml
tools:
  # 开启mybatis-plus的性能监控插件
  mybatis-performance-open: true
  multi-datasource:
    # 多数据源名称
    datasource-name: otherDataSource
    url: jdbc:mysql://localhost:3306/test?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false
    username: root
    password: root123098
```

3, 在service类的方法上通过注解，指定数据源名称

如：

``` java
   /**
     * 从另外一个数据源获取历史节点信息
     *
     * @return
     */
    @Override
    @DataSource(name = "otherDataSource") //使用指定的数据源
    public List<DemoTreeVo> treeHis() {
        return demoMapper.tree();
    }
```

注：
如果不指定，系统默认取名称为“default”的数据源
如：@DataSource
等同于 @DataSource("default")


# 扩展多数据源
//FIXME 目前还未支持自由配置yml，提供多个数据源


# 方式二： 使用Sharding jdbc的多数据源
//TODO ... 集成后写手册
