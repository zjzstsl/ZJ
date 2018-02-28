# Tools的配置中心

   配置中心作为服务注册者，向注册中心注册。 

# 如何启动

>单独启动一个配置中心

``` shell
run.sh tools-config-server
```
或
``` shell
java -jar tools-config-server-0.0.1-SNAPSHOT.jar
```

默认使用 18010 端口

>以集群方式启动多个注册中心

``` shell
run.sh tools-config-server-0.0.1-SNAPSHOT.jar --server.port=${port} --eureka.client.service-url.defaultZone=${tools-eureka-server-url}
```
或
``` shell
java -jar tools-config-server-0.0.1-SNAPSHOT.jar --server.port=${port} --eureka.client.service-url.defaultZone=${tools-eureka-server-url}
```

其中
    
    --server.port=${port} 指定新的端口，建议从18010 顺序＋1使用，且不大于 18019.
    --eureka.client.service-url.defaultZone=${tools-eureka-server-url} 指定服务注册中心，不指定时默认为 http://127.0.0.1:18000/eureka/
    如果调整了注册中心实例名和端口，则需要指定。
