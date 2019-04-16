package com.quectel.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 消息生产者
 * @Author maxton.zhang
 * @Date 2019/4/15 18:37
 * @Version 1.0
 */
@Controller
public class RabbitmqWSController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(path = "/sendMessage")
    @ResponseBody
    public void senderMsg() throws InterruptedException {
//        for (int i = 0; i < 10; i++) {
//            String msg = "第-" + i + "-条消息";
//            String message = "[{\"ret\":0,\"item\":\"CLIENT-ACK\",\"task_id\":\"1117713657598447616\",\"from\":\"172.18.112.43\",\"timestamp\":\"2019-04-15 16:57:57\"},{\"ret\":0,\"item\":\"case下载完成\",\"task_id\":\"1117713657598447616\",\"from\":\"172.18.112.43\",\"timestamp\":\"2019-04-15 16:57:59\"},{\"ret\":0,\"item\":\"配置Setting页完成\",\"task_id\":\"1117713657598447616\",\"from\":\"172.18.112.43\",\"timestamp\":\"2019-04-15 16:58:01\"},{\"ret\":0,\"item\":\"不需要升级,直接进入下一步测试\",\"task_id\":\"1117713657598447616\",\"from\":\"172.18.112.43\",\"timestamp\":\"2019-04-15 16:58:01\"},{\"ret\":0,\"item\":\"QAT执行case完成\",\"task_id\":\"1117713657598447616\",\"from\":\"172.18.112.43\",\"timestamp\":\"2019-04-15 16:58:09\"},{\"ret\":0,\"item\":\"复制log到共享完成\",\"task_id\":\"1117713657598447616\",\"from\":\"172.18.112.43\",\"timestamp\":\"2019-04-15 16:58:18\"},{\"ret\":0,\"item\":\"TEST-DONE\",\"task_id\":\"1117713657598447616\",\"from\":\"172.18.112.43\",\"timestamp\":\"2019-04-15 16:58:18\"}]";
//            System.out.println("发送的消息： " + msg);
//            Thread.sleep(1000);
//            rabbitTemplate.convertAndSend("exchange_topics_websocket", "websocket", msg);
//        }
        String m1 = "{\"ret\":0,\"item\":\"CLIENT-ACK\",\"task_id\":\"1116599766248198144\",\"from\":\"192.168.20.71\",\"timestamp\":\"2019-04-12 17:31:15\"}";
        String m2 = "{\"ret\":0,\"item\":\"case下载完成\",\"task_id\":\"1116599766248198144\",\"from\":\"192.168.20.71\",\"timestamp\":\"2019-04-12 17:31:17\"}";
        String m3 = "{\"ret\":0,\"item\":\"case配置setting完成\",\"task_id\":\"1116599766248198144\",\"from\":\"192.168.20.71\",\"timestamp\":\"2019-04-12 17:31:21\"}";
        String m4 = "{\"ret\":1,\"item\":\"共享不存在此版本，请检查测试版本是否填写错误\",\"task_id\":\"1116599766248198144\",\"from\":\"192.168.20.71\",\"timestamp\":\"2019-04-12 17:31:21\"}";
        String m5 = "{\"ret\":1,\"item\":\"TEST-DONE\",\"task_id\":\"1116599766248198144\",\"from\":\"192.168.20.71\",\"timestamp\":\"2019-04-12 17:31:21\"}";
        for (int i = 0; i < 5; i++) {
            Thread.sleep(5000);
            if (i == 0) {
                rabbitTemplate.convertAndSend("exchange_topics_websocket", "websocket", m1);
                System.out.println("发送的消息m1： " + m1);
            }
            if (i == 1) {
                rabbitTemplate.convertAndSend("exchange_topics_websocket", "websocket", m2);
                System.out.println("发送的消息m2： " + m2);
            }
            if (i == 2) {
                rabbitTemplate.convertAndSend("exchange_topics_websocket", "websocket", m3);
                System.out.println("发送的消息m3： " + m3);
            }
            if (i == 3) {
                rabbitTemplate.convertAndSend("exchange_topics_websocket", "websocket", m4);
                System.out.println("发送的消息m4： " + m4);
            }
            if (i == 4) {
                rabbitTemplate.convertAndSend("exchange_topics_websocket", "websocket", m5);
                System.out.println("发送的消息m5： " + m5);
            }
        }
    }

    // [{"ret":0,"item":"CLIENT-ACK","task_id":"1116634873772249088","from":"192.168.20.71","timestamp":"2019-04-12 17:31:15"},
    // {"ret":0,"item":"case下载完成","task_id":"1116634873772249088","from":"192.168.20.71","timestamp":"2019-04-12 17:31:17"},
    // {"ret":0,"item":"case配置setting完成","task_id":"1116634873772249088","from":"192.168.20.71","timestamp":"2019-04-12 17:31:21"},
    // {"ret":1,"item":"共享不存在此版本，请检查测试版本是否填写错误","task_id":"1116634873772249088","from":"192.168.20.71","timestamp":"2019-04-12 17:31:21"},
    // {"ret":1,"item":"TEST-DONE","task_id":"1116634873772249088","from":"192.168.20.71","timestamp":"2019-04-12 17:31:21"}]
}
