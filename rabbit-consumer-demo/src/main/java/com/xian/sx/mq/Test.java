package com.xian.sx.mq;

import com.rabbitmq.client.Channel;
import com.xian.sx.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author : zhangJW
 * @Description : java类作用描述
 * @CreateDate : 2019/4/17 19:00
 * @Updat :  更新作者
 * @UpdateDate : 2019/4/17 19:00
 * @UpdateRemark : 更新内容注释
 * @Version : 1.0
 */
@Component
public class Test {
    //监听email队列
    @RabbitListener(queues = {RabbitConfig.QUEUE_INFORM_EMAIL})
    public void receive_email(String msg, Message message, Channel channel){
        System.out.println(msg);
    }
    //监听sms队列
    @RabbitListener(queues = {RabbitConfig.QUEUE_INFORM_SMS})
    public void receive_sms(String msg,Message message,Channel channel){
        System.out.println(msg);
    }



}

