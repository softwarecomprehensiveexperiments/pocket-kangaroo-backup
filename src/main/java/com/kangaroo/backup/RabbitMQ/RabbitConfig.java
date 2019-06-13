package com.kangaroo.backup.RabbitMQ;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AfterDomainEventPublication;

@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.DLE}")
    private String DLE;

    @Value("${spring.rabbitmq.DLQ}")
    private String DLQ;


    @Value("${spring.rabbitmq.mainExchange}")
    private String me;

    /**
     * 创建死信队列，放置到deadline的任务
     * @return 队列实例
     */
    @Bean
    public Queue dlq(){
        return new Queue(DLQ);
    }

    /**
     * 创建死信队列2，放置发布者未操作的任务
     * @return 队列实例
     */
    @Bean
    public Queue dlq2(){
        return new Queue(DLQ + "2");
    }

    /**
     * 创建死信交换机
     * @return 交换机实例
     */
    @Bean
    public DirectExchange generateDEx() {
        return new DirectExchange(DLE);
    }

    @Bean
    public DirectExchange generateDPEx() {
        return new DirectExchange(me);
    }
}
