package com.quectel.controller;

import com.quectel.listenerMQ.ConsumerConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 消息生产者
 * @Author maxton.zhang
 * @Date 2019/4/15 18:37
 * @Version 1.0
 */
@Controller
public class SendMsgController {

    @Autowired
    private ConsumerConfig consumerConfig;

    @RequestMapping(value = "/senderMsg/{task_id}", method = RequestMethod.GET)
    @ResponseBody
    public String senderMsg(@PathVariable String task_id) {
        consumerConfig.sendMsgPage(task_id);
        return "成功";
    }

}
