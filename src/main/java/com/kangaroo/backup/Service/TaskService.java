package com.kangaroo.backup.Service;

import com.kangaroo.backup.DTO.PublicShortTaskDTO;
import com.kangaroo.backup.DTO.ShortTaskOutputDTO;
import com.kangaroo.backup.DTO.TaskCreateInputDTO;
import com.kangaroo.backup.DTO.TaskDetailOutputDTO;
import com.kangaroo.backup.Dao.TaskMapper;
import com.kangaroo.backup.Domain.Task;
import com.kangaroo.backup.Exception.NoPlaceLeftException;
import com.kangaroo.backup.Exception.NoSuchTaskExpcetion;
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

    private UserService userService;

    @Autowired
    public void setTaskMapper(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private TransactionService transactionService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public List<PublicShortTaskDTO> getPublicTasks() {
        List<Task> tasks = taskMapper.getTaskListByType(Task.TaskState.WAITTING_FOR_RECEIVED);
        List<PublicShortTaskDTO> shortTaskDTOS = new LinkedList<>();
        tasks.forEach(e -> {shortTaskDTOS.add(PublicShortTaskDTO.fromTask(e));});
        return shortTaskDTOS;
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
     * 为某个任务添加接受者
     * @param taskId
     * @throws NoPlaceLeftException
     */
    void addReceiver(int taskId, String userName) throws NoPlaceLeftException {
        Task task = taskMapper.loadById(taskId);
        if(task.getCurrentCompleteCount() + task.getCurrentReceiversCount() >= task.getMaxReceiversCount()) {
            throw new NoPlaceLeftException();
        }
        task.setCurrentReceiversCount(task.getCurrentReceiversCount() + 1);
        if(task.getCurrentReceiversCount() == task.getMaxReceiversCount()) {
            task.setTaskState(Task.TaskState.WAITTING_FOR_COMPLETED);
        }
        task.setReceivers(task.getReceivers() + "@@" + userName);
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
        task.setTaskCompleteDate(new Date());
        taskMapper.update(task);
        transactionService.afterTaskCheck(taskId);
    }

    public void cancelTask(int taskId) {
        Task task = taskMapper.loadById(taskId);
        if(task.getTaskState() != Task.TaskState.WAITTING_FOR_RECEIVED ||
                task.getTaskState() != Task.TaskState.WAITTING_FOR_COMPLETED) {
            return;
        }
        int left = task.getTaskPrice() * (task.getMaxReceiversCount() - task.getCurrentCompleteCount());
        accountService.addCredit(task.getTaskPublisherId(), left);
        taskMapper.update(task);
        transactionService.afterTaskCancel(taskId);
    }

    public List<ShortTaskOutputDTO> queryShortTasksAll(int userId) {
        return queryShortTasksByStates(userId, Task.TaskState.ALL_STATE_SET);
    }

    public List<ShortTaskOutputDTO> queryShortTasksDoing(int userId) {
        return queryShortTasksByStates(userId, Task.TaskState.DOING_STATE_SET);
    }

    public List<ShortTaskOutputDTO> queryShortTasksComplete(int userId) {
        return queryShortTasksByStates(userId, Task.TaskState.COMPLETED_STATE_SET);
    }

    public TaskDetailOutputDTO queryTaskDetail(int taskId) throws NoSuchTaskExpcetion {
        Task task = taskMapper.loadById(taskId);
        if(task == null) {
            throw new NoSuchTaskExpcetion();
        }
        String userName = userService.getName(task.getTaskPublisherId());
        TaskDetailOutputDTO dto = TaskDetailOutputDTO.fromNameAndTask(userName, task);
        return dto;
    }

    /**
     * 查询某些状态下的任务
     * @param userId 发布用户
     * @param states 存放所需请求状态的列表
     * @return 所请求的任务列表
     */
    private List<ShortTaskOutputDTO> queryShortTasksByStates(int userId, List<Integer> states) {
        List<Task> tasks = taskMapper.getTaskListByPublisher(userId);
        List<ShortTaskOutputDTO> res = new LinkedList<>();
        tasks.forEach(e -> {
            if(states.contains(e.getTaskState())) {
                res.add(ShortTaskOutputDTO.fromTask(e));
            }
        });
        return res;
    }

    int getAmount(int taskId) {
        return taskMapper.loadById(taskId).getTaskPrice();
    }

    void removeReceiver(int taskId, String userName) {
        Task task = taskMapper.loadById(taskId);
        if(task.getCurrentReceiversCount() == task.getMaxReceiversCount()) {
            task.setTaskState(Task.TaskState.WAITTING_FOR_RECEIVED);
        }
        task.setCurrentReceiversCount(task.getCurrentReceiversCount() - 1);
        task.setReceivers(task.getReceivers().replaceFirst("@@" + userName, ""));
        taskMapper.update(task);
    }

    int[] getUserStatisticsData(int userId) {
        int[] res = new int[2];
        List<Task> tasks = taskMapper.getTaskListByPublisher(userId);
        tasks.forEach(e -> {
            if(Task.TaskState.DOING_STATE_SET.contains(e.getTaskState())) {
                res[0]++;
            }
            if(Task.TaskState.COMPLETED_STATE_SET.contains(e.getTaskState())) {
                res[1]++;
            }
        });
        return res;
    }

    Task getById(int taskId) {
        return taskMapper.loadById(taskId);
    }

}
