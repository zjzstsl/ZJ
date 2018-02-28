# Tools的注册中心

    如果有注册中心，请最先启动！

# 如何启动

>单独启动一个注册中心

``` shell
run.sh tools-eureka-server
```
或
``` shell
java -jar tools-eureka-server-0.0.1-SNAPSHOT.jar
```
或
``` shell
java -jar tools-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=single
```

    其中
    --spring.profiles.active=single 指明使用 application-single.properties 配置启动
    默认启动端口为18000

>以集群方式启动多个注册中心

``` shell
run.sh tools-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=cluster --server.port=${port} --eureka.instance.hostname=${instance-hostname} --eureka.client.service-url.defaultZone=${other-eureka-url}
```
或
``` shell
java -jar tools-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=cluster --server.port=${port} --eureka.instance.hostname=${instance-hostname} --eureka.client.service-url.defaultZone=${other-eureka-url}
```

>>其中
    
    --spring.profile.active=cluster 指明使用 application-cluster.properties 配置启动
    --server.port=${port} 指定本实例的端口 ${port}，建议每次 18000+1。 如果不指定，默认用18000
    --eureka.instance.hostname=${instance-hostname} 指定本实例的名称，如：eureka-server-1
    --eureka.client.service-url.defaultZone=${other-eureka-url} 指定集群中另一个注册中心的url
    ${other-eureka-url} 的结构为：http://${eureka.instance.hostname}:${server.port}/eureka/
    
 >>举例
 
    假如，准备在同一台服务器上，启动两个服务注册中心，构成集群:
    第一个：
        eureka.instance.hostname=eureka-server-1
        server.port=18001
    第二个
        eureka.instance.hostname=eureka-server-2
        server.port=18002
    则为了构成集群注册中心，他们应该相互引用，启动命令应该为：

启动第一个 
    
``` shell
java -jar tools-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=cluster --server.port=18001 --eureka.instance.hostname=eureka-server-1 --eureka.client.service-url.defaultZone=http://eureka-server-2:18002/eureka/
```
 
启动第二个
    
``` shell
java -jar tools-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=cluster --server.port=18002 --eureka.instance.hostname=eureka-server-2 --eureka.client.service-url.defaultZone=http://eureka-server-1:18001/eureka/
```
 
注：eureka-server-1、eureka-server-2 这两个，修改过本机的 /etc/hosts 增加了两条IP映射
``` shell
127.0.0.1       eureka-server-1
127.0.0.1       eureka-server-2
```
