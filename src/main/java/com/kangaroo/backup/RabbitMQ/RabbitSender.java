package com.kangaroo.backup.RabbitMQ;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 消息队列生产者
 */
@Component
public class RabbitSender {

    @Value("${spring.rabbitmq.DLQ}")
    private String DLQ;

    @Value("${spring.rabbitmq.DLE}")
    private String DLE;

    @Value("${spring.rabbitmq.mainExchange}")
    private String me;

    @Value("${spring.rabbitmq.overduePQ}")
    private String baseOPQ;

    /**
     * 以后可能用，单独的订单过期处理
     * @param trandId 交易单ID
     * @param taskId 任务ID
     */
    public void sendTransId(int trandId, int taskId) {
        try {
            Connection connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel();
            String message = String.valueOf(trandId);
            String qn = baseOPQ + taskId;
            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder().contentEncoding("UTF-8").contentType("text/plain").headers(new HashMap<>()).priority(0);
            channel.basicPublish(me, qn, builder.build(), message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] trans Sent '" + message + "'");
            channel.close();
            connection.close();
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 任务逾期处理
     * @param id 任务ID
     * @param exp 过期时间
     */
    public void sentTaskId(int id, long exp) {
        String message = String.valueOf(id);
        String qn = "Overdue " + baseOPQ + id;
        String dlq = DLQ;
        send(message, qn, exp, dlq);
    }

    public void sentTaskIdForAutoComplete(int id, long exp) {
        String message = String.valueOf(id);
        String qn = "Auto " + baseOPQ + id;
        String dlq = DLQ + "2";
        send(message, qn, exp, dlq);
    }

    private void send(String message, String qn, long exp, String dlq) {
        try {
            Connection connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel();
            Map<String,Object> args = new HashMap<>(4);
            args.put("x-dead-letter-exchange", DLE);
            args.put("x-dead-letter-routing-key", dlq);
            args.put("x-message-ttl", exp);
            channel.queueDeclare(qn, true, false, false, args);
            channel.queueBind(qn, me, qn);
            channel.queueBind(dlq, DLE, dlq);
            //天坑：rabbitTemplate自动设置了header，而amqp client没有，自己设置。
            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder().contentEncoding("UTF-8").contentType("text/plain").headers(new HashMap<>()).priority(0);
            channel.basicPublish(me, qn, builder.build(), message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] task Sent '" + message + "'");
            channel.close();
            connection.close();
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
