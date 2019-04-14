package com.xuecheng.manage_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : zhangJW
 * @Description : java类作用描述
 * @CreateDate : 2019/4/12 18:47
 * @Updat :  更新作者
 * @UpdateDate : 2019/4/12 18:47
 * @UpdateRemark : 更新内容注释
 * @Version : 1.0
 */
@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain.cms")//扫描实体类
@ComponentScan(basePackages={"com.xuecheng.api"})//扫描接口
@ComponentScan(basePackages={"com.xuecheng.manage_cms"})//扫描本项目下的所有类
public class CmsManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsManageApplication.class,args);
    }
}
