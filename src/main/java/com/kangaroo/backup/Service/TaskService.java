package com.kangaroo.backup.Service;

import com.kangaroo.backup.DTO.ShortTaskOutputDTO;
import com.kangaroo.backup.DTO.TaskCreateInputDTO;
import com.kangaroo.backup.Dao.TaskMapper;
import com.kangaroo.backup.Domain.Task;
import com.kangaroo.backup.Domain.Transaction;
import com.kangaroo.backup.Exception.NoPlaceLeftException;
import com.kangaroo.backup.Exception.NotEnoughBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class TaskService {

    private TaskMapper taskMapper;

    private AccountService accountService;

    @Autowired
    public void setTaskMapper(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    private TransactionService transactionService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public void createTask(int userId, TaskCreateInputDTO taskCreateInputDTO) throws NotEnoughBalanceException {
        Task task = taskCreateInputDTO.convertToTask();
        task.setTaskPublisherId(userId);
        task.setTaskPublishDate(new Date());
        task.setTaskState(Task.TaskState.WAITTING_FOR_RECEIVED);
        accountService.pay(userId, task.getTaskPrice() * task.getMaxReceiversCount());
        taskMapper.insert(task);
    }

    /**
     * 查询进行中的任务（包括"待领取、等待完成、等待确认"）
     * @param userId
     * @return
     */
    public List<ShortTaskOutputDTO> queryReleaseDoingTask(int userId) {
        List<Task> tasks = taskMapper.loadByPublisherIdAndStates(userId, Task.TaskState.DOING_STATE_SET);
        List<ShortTaskOutputDTO> res = new LinkedList<>();
        tasks.forEach(e -> res.add(ShortTaskOutputDTO.fromTask(e)));
        return res;
    }

    /**
     * 为某个任务添加接受者
     * @param taskId
     * @throws NoPlaceLeftException
     */
    void addReceiver(int taskId) throws NoPlaceLeftException {
        Task task = taskMapper.loadById(taskId);
        if(task.getCurrentCompleteCount() + task.getCurrentReceiversCount() >= task.getMaxReceiversCount()) {
            throw new NoPlaceLeftException();
        }
        task.setCurrentReceiversCount(task.getCurrentReceiversCount() + 1);
        if(task.getCurrentReceiversCount() == task.getMaxReceiversCount()) {
            task.setTaskState(Task.TaskState.WAITTING_FOR_COMPLETED);
        }
        taskMapper.update(task);
    }

    /**
     * 为一个任务添加新的提交者
     * @param taskId
     */
    void addCommitter(int taskId, String committion) {
        Task task = taskMapper.loadById(taskId);
        task.setCurrentCompleteCount(task.getCurrentCompleteCount() + 1);
        if(task.getCurrentCompleteCount() == task.getMaxReceiversCount()) {
            task.setTaskState(Task.TaskState.WAITTING_FOR_CHECKED);
        }
        task.setResult(task.getResult() + "^^" + committion);
        taskMapper.update(task);
    }

    public void checkTask(int taskId){
        Task task = taskMapper.loadById(taskId);
        task.setTaskState(Task.TaskState.COMPLETED_NORMALLY);
        transactionService.afterTaskCheck(taskId);
    }

    public void cancelTask(int taskId) {
        Task task = taskMapper.loadById(taskId);
        if(task.getTaskState() != Task.TaskState.WAITTING_FOR_RECEIVED ||
                task.getTaskState() != Task.TaskState.WAITTING_FOR_COMPLETED) {
            return;
        }
        transactionService.afterTaskCancel(taskId);
    }

    int getAmount(int taskId) {
        return taskMapper.loadById(taskId).getTaskPrice();
    }

    void removeReceiver(int taskId) {
        Task task = taskMapper.loadById(taskId);
        if(task.getCurrentReceiversCount() == task.getMaxReceiversCount()) {
            task.setTaskState(Task.TaskState.WAITTING_FOR_RECEIVED);
        }
        task.setCurrentReceiversCount(task.getCurrentReceiversCount() - 1);
    }

    int[] getUserStatisticsData(int userId) {
        int[] res = new int[2];
        List<Task> tasks = taskMapper.getTaskListByPublisher(userId);
        tasks.forEach(e -> {
            if(e.getTaskState() == Task.TaskState.WAITTING_FOR_RECEIVED ||
                    e.getTaskState() == Task.TaskState.WAITTING_FOR_COMPLETED ||
                    e.getTaskState() == Task.TaskState.WAITTING_FOR_CHECKED) {
                res[0]++;
            }
            if(e.getTaskState() == Task.TaskState.COMPLETED_NORMALLY) {
                res[1]++;
            }
        });
        return res;
    }
}
