package com.quectel.listenerMQ;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quectel.mq.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 监听类
 * @Author maxton.zhang
 * @Date 2019/4/16 10:16
 * @Version 1.0
 */
@Slf4j
@Component
public class ConsumerConfig {

    @Autowired
    private WebSocketServerEndpoint webSocketServerEndpoint;

    private static ConcurrentHashMap<String, JSONArray> map = new ConcurrentHashMap<>();

    @RabbitListener(queues = RabbitmqConfig.QUEUE_MASTER)
    public void onMessage(Message message) {
        String msg = new String(message.getBody());

        JSONObject json = JSONObject.parseObject(msg);
        String item = json.get("item") + "";
        String task_id = json.get("task_id") + "";  // 得到taskId
        String ret = json.getString("ret");

        // 将消息判断存数据库
        if (!map.containsKey(task_id)) {
            JSONArray jsonArr = new JSONArray();
            jsonArr.add(json);
            map.put(task_id, jsonArr);
        } else {
            map.get(task_id).add(json);
        }

        String webSocketMsg = map.get(task_id).toJavaObject(String.class);
        // 消息发送页面
        webSocketServerEndpoint.sendMessageToAll(webSocketMsg);
        System.out.println("发页面消息: " + webSocketMsg);

        //当其中item等于TEST-DONE
        if ("TEST-DONE".equals(item)) {
            String jsonArrStr = map.get(task_id).toJavaObject(String.class);
            System.out.println("存数据库" + jsonArrStr);
        }

    }

    public void sendMsgPage(String task_id) {
//        task_id = "1116599766248198144";
        String webSocketMsg = map.get(task_id).toJavaObject(String.class);
        // 消息发送页面
        webSocketServerEndpoint.sendMessageToAll(webSocketMsg);
        System.out.println("发页面消息: " + webSocketMsg);
    }
}
