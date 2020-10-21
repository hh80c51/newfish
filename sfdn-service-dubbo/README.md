user服务
本服务主要用来方便开发测试使用
现在模块开发测试的功能：

1.搭建了简单的dubbo服务

user-service-provider 服务提供者
端口8081，应用名user-provider

user-service-api 提供者方法接口

user-service-consumer 服务消费者
端口8082，应用名user-consumer

问题：
1.配置文件不配置数据源会报错
解决：pom引入了其他工程的依赖，这个其他工程的依赖需要配置数据源
第一种办法：pom文件中
        <dependency>
            <groupId>com.fish</groupId>
            <artifactId>user-service-api</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <scope>compile</scope>
        <exclusions>
            <exclusion>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
            </exclusion>
            <exclusion>
                <groupId>com.alibaba</groupId>
                <artifactId>druid-spring-boot-starter</artifactId>
            </exclusion>
        </exclusions>
        </dependency>
第二种办法：去掉druid数据源自动配置
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})

2.druid和druid starter包都要引入

3.log4j包需要引入

4.编译错误：Execution repackage of goal org.springframework.boot:spring-boot-maven-plugin:2.3.1.RELEASE:repackage
user-service-api不需要打包，将pom文件中build模块加到需要打包的子项目下.