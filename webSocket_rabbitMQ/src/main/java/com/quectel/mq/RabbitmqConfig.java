package com.quectel.mq;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 定义MQ消息传递方式和名称 每个测试机的交换机名称不同，需要单独定义
 * @Author maxton.zhang
 * @Date 2019/4/15 14:56
 * @Version 1.0
 */
@Configuration
public class RabbitmqConfig {
    //交换机
    public static final String EXCHANGE_TOPICS_WEBSOCKET = "exchange_topics_websocket";
    //队列
    public static final String QUEUE_MASTER = "master_websocket";

    //声明交换机,交换机配置
    @Bean(EXCHANGE_TOPICS_WEBSOCKET)
    public Exchange EXCHANGE_TOPICS_INFORM() {
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_WEBSOCKET).durable(true).build();
    }

    //声明队列 QUEUE_MASTER
    @Bean(QUEUE_MASTER)
    public Queue QUEUE_MASTER() {
        Queue queue = new Queue(QUEUE_MASTER);
        return queue;
    }

    //绑定队列到交换机
    @Bean
    public Binding BINDING_QUEUE_MASTER(@Qualifier(QUEUE_MASTER) Queue queue, @Qualifier(EXCHANGE_TOPICS_WEBSOCKET) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("websocket").noargs();
    }

}
