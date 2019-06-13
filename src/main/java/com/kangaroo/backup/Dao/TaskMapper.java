package com.kangaroo.backup.Dao;

import com.kangaroo.backup.Domain.Task;
import com.kangaroo.backup.Domain.TaskPO;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskMapper {

    /*查询，task与questionnarie联合查询*/
    List<TaskPO> getTaskList();

    List<TaskPO> getTaskListByTitle(String taskTitle);

    //已更正，taskType是int类型
    List<TaskPO> getTaskListByType(int taskType);

    List<TaskPO> getTaskListByPublisher(int taskPublisherId);

    List<TaskPO> getTaskListByCompleteDate(Date taskCompleteDate);

    void insert(TaskPO taskpo);

    void update(TaskPO taskpo);

    void delete(int taskId);

    /**
     * New
     */
    List<TaskPO> loadById(int taskId);

}
