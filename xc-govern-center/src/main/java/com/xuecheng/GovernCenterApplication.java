package com.xuecheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author : zhangJW
 * @Description : java类作用描述
 * @CreateDate : 2019/4/21 13:42
 * @Updat :  更新作者
 * @UpdateDate : 2019/4/21 13:42
 * @UpdateRemark : 更新内容注释
 * @Version : 1.0
 */
@EnableEurekaServer//标识这是一个Eureka服务
@SpringBootApplication
public class GovernCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(GovernCenterApplication.class);
    }
}
