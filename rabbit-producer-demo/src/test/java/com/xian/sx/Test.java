package com.xian.sx;

import com.xian.sx.config.RabbitConfig;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : zhangJW
 * @Description : java类作用描述
 * @CreateDate : 2019/4/17 19:00
 * @Updat :  更新作者
 * @UpdateDate : 2019/4/17 19:00
 * @UpdateRemark : 更新内容注释
 * @Version : 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @org.junit.Test
    public void test1(){
        //发送消息
        for (int i=0;i<5;i++){
            String message = "给用户发送邮件并且发送短信"+i;
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPICS_INFORM,"inform.sms.email",message);
            System.out.println("Send Message is:'" + message + "'");
        }
    }
    }

