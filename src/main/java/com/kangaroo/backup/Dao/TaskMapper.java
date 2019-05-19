package com.kangaroo.backup.Dao;

import com.kangaroo.backup.Domain.Task;

import java.util.Date;
import java.util.List;

public interface TaskMapper {

    List<Task> getTaskList();

    List<Task> getTaskListByTitle(String taskTitle);

    List<Task> getTaskListByType(String taskType);

    List<Task> getTaskListByPublisher(int publisherId);

    List<Task> getTaskListByCompleteDate(Date completeDate);

    void insertTask(Task task);

    void updateTask(Task task);

    void deleteTask(int taskId);

}
