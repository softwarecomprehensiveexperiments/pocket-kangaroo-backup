package com.kangaroo.backup.Dao;

import com.kangaroo.backup.Domain.Task;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskMapper {

    /*查询，task与questionnarie联合查询*/
    List<Task> getTaskList();

    List<Task> getTaskListByTitle(String taskTitle);

    //错了，taskType是int类型
    //List<Task> getTaskListByType(String taskType);

    List<Task> getTaskListByPublisher(int taskPublisherId);

    List<Task> getTaskListByCompleteDate(Date taskCompleteDate);

    void insert(Task task);

    void update(Task task);

    void delete(int taskId);


    /**
     * New
     */
    List<Task> getTaskListByType(int taskType);
    Task loadById(int taskId);

}
