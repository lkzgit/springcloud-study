# springcloud-study
springcloud组件学习入门使用
  cloud 开头是尚硅谷学习
  nacos 自主学习新的



###  SkyWalking  单元学习
参考网址： 
官网：http://skywalking.apache.org/
下载：http://skywalking.apache.org/downloads/
Github：https://github.com/apache/skywalking
图片见doc skywalking
1.组成部分

  1.1整个架构分成四部分:
  
    1、上部分Agent ：负责从应用中，收集链路信息，发送给 SkyWalking OAP 服务器；
    2、下部分 SkyWalking OAP ：负责接收Agent发送的Tracing数据信息，然后进行分析(Analysis Core)，存储到外部存储器(Storage)，最终提供查询(Query)功能；
    3、右部分Storage：Tracing数据存储，目前支持ES、MySQL、Sharding Sphere、TiDB、H2多种存储器，目前采用较多的是ES，主要考虑是SkyWalking开发团队自己的生产环境采用ES为主；
    4、左部分SkyWalking UI：负责提供控台，查看链路等等；
    
2.SkyWalking 环境搭建部署 图片: 环境搭建

    1，下载 SkyWalking 软件包；
    2，搭建一个 SkyWalking OAP 和SkyWalking UI服务；
    3，启动一个Spring Boot应用，并配置SkyWalking Agent；
    数据存储暂时先使用它默认的H2数据库存储，后续我们再使用其他存储；
    
    2.1 ；下载 SkyWalking 软件包
    
        对于 SkyWalking 的软件包，有两种方式获取：
        2.1.1：手动编译
        2.1.2：官方包
        一般情况下，我们建议使用官方包，手动编译也可以；
        从这里下载：http://skywalking.apache.org/downloads/
        
    2.2 ：SkyWalking OAP 搭建
    
    解压：tar -zxvf apache-skywalking-apm-8.1.0.tar.gz
    解压后即完成了安装，不需要做其他操作；
    切换：cd apache-skywalking-apm-bin
    图片目录：解压目录
    
        2.2.1 目录说明：
        agent #SkyWalking Agent
        bin #执行脚本
        config #SkyWalking OAP Server 配置文件
        LICENSE
        licenses
        NOTICE
        oap-libs #SkyWalking OAP Server
        README.txt
        tools
        webapp #SkyWalking UI
3、启动 SkyWalking OAP 服务
    切换到bin目录：./startup.sh
    
    启动后会启动两个服务，一个是skywalking-oap-server，一个是skywalking-web-ui；
    查看安装目录下的 ./logs 下的日志文件，检查两个服务的日志文件是否启动成功；
    skywalking-oap-server服务启动后会占用：11800 和 12800 两个端口；
    skywalking-web-ui服务会占用 8080 端口；
    如果想要修改SkyWalking UI服务的参数，可以编辑webapp/webapp.yml 配置文件，比如：
    server.port：SkyWalking UI服务端口，默认是8080；
    collector.ribbon.listOfServers：SkyWalking OAP服务地址数组，SkyWalking UI界面的
    数据是通过请求SkyWalking OAP服务来获得
    
    修改 OAP 配置文件
    vi config/application.yml
    修改说明：
    storage.elasticsearch7 配置项，设置使用 Elasticsearch 7.X 版本作为存储器。
    这里，我们打开注释，并记得通过 nameSpace 设置 Elasticsearch 集群名。
    storage.elasticsearch 配置项，设置使用 Elasticsearch 6.X 版本作为存储器。
    这里，我们无需做任何改动。
    如果胖友使用 Elasticsearch 6.X 版本作为存储器，记得设置这个配置项，而不是 storage.elasticsearch7 配置项。
    storage.h2 配置项，设置使用 H2 作为存储器。
    这里，我们需要手动注释掉，因为 H2 是默认配置的存储器。
    重点修改 storage 配置项，通过 storage.selector 配置项来设置具体使用的存储器。
    storage.elasticsearch 配置项，设置使用 Elasticsearch 6.X 版本作为存储器。胖友可以主要修改 nameSpace、clusterNodes 两个配置项即可，设置使用的 Elasticsearch 的集群和命名空间。
    storage.elasticsearch7 配置项，设置使用 Elasticsearch 7.X 版本作为存储器。
    还有 MySQL、H2、InfluxDB 等等存储器的配置可以选择
    
    
  3.1 : 访问SkyWalking UI界面：http://192.168.172.128:8080/
    图片 ； skywalking访问ui页面.
    
4. SkyWalking Agent跟踪微服务 图片：链式跟踪
    在项目服务器上 startup.sh 脚本
    
    #!/bin/sh
    # SkyWalking Agent配置
    export SW_AGENT_NAME=11-springboot #Agent名字,一般使用`spring.application.name` 
    export SW_AGENT_COLLECTOR_BACKEND_SERVICES=127.0.0.1:11800 #配置 Collector 地址。
    export SW_AGENT_SPAN_LIMIT=2000 #配置链路的最大Span数量，默认为 300。
    export JAVA_AGENT=-javaagent:/usr/local/apache-skywalking-apm-bin/agent/skywalking-agent.jar
    java $JAVA_AGENT -jar 11-springboot-1.0.0.jar #jar启动
    
    在启动程序前加一个-javaagent 参数即可完成对程序的跟踪；