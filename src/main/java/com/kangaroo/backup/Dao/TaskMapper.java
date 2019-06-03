package com.kangaroo.backup.Dao;

import com.kangaroo.backup.Domain.Task;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskMapper {

    List<Task> getTaskList();

    List<Task> getTaskListByTitle(String taskTitle);

    List<Task> getTaskListByType(String taskType);

    List<Task> getTaskListByPublisher(int taskPublisherId);

    List<Task> getTaskListByCompleteDate(Date taskCompleteDate);

    void insert(Task task);

    void update(Task task);

    void delete(int taskId);

}
