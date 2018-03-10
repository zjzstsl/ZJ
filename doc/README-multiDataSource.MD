# 方式一： 使用Tools自己提供给的多数据

1. 在yml中开启 tools.multi-datasource-open=true

1. 在yml中配置多个数据源
//TODO  ...

1. 在service类的方法上通过注解，指定数据源名称
如：@DataSource("other")

如果不指定，系统默认取名称为“default”的数据源
如：@DataSource
等同于 @DataSource("default")


# 扩展多数据源

//TODO .....

# 方式二： 使用Sharding jdbc的多数据源
//TODO ... 集成后写手册
