package com.xuecheng.manage_cms;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author : zhangJW
 * @Description : java类作用描述
 * @CreateDate : 2019/4/12 19:19
 * @Updat :  更新作者
 * @UpdateDate : 2019/4/12 19:19
 * @UpdateRemark : 更新内容注释
 * @Version : 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {
    @Autowired
    RestTemplate restTemplate;
    @org.junit.Test
    public void test1(){
        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://localhost:31001/cms/config/getCmsBannerConfig/5a791725dd573c3574ee333f", Map.class);
        Map body = forEntity.getBody();
        System.out.println(body);
    }
}
