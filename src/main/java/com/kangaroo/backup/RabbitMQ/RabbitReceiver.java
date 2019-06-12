package com.kangaroo.backup.RabbitMQ;

import com.kangaroo.backup.Domain.Task;
import com.kangaroo.backup.Service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver  {

    private TaskService taskService;

    @Autowired
    void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    private static final Logger logger = LoggerFactory.getLogger(RabbitReceiver.class);

    //天坑：queues不可以放在类上面
    @RabbitListener(queues = "overdue_queue")
    @RabbitHandler
    public void processOverdue(String id) {
        logger.info("------- overdue task ID: [{}]", id);
        int taskId = Integer.valueOf(id);
        taskService.processOverdue(taskId);
    }

    @RabbitListener(queues = "overdue_queue2")
    @RabbitHandler
    public void processAuto(String id) {
        logger.info("------- auto complete task ID: [{}]", id);
        int taskId = Integer.valueOf(id);
        taskService.processAutoComplete(taskId);
    }
}
