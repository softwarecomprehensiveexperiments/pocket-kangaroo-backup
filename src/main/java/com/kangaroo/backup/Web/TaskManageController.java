package com.kangaroo.backup.Web;

import com.kangaroo.backup.DTO.*;
import com.kangaroo.backup.Exception.NoCurrentUserException;
import com.kangaroo.backup.Exception.NoSuchTaskExpcetion;
import com.kangaroo.backup.Exception.NotEnoughBalanceException;
import com.kangaroo.backup.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 任务管理系统的控制层类
 */
@RestController
@RequestMapping(value = "/task")
public class TaskManageController extends BaseController{

    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * 获取广场任务
     */
    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public QueryResult<TaskSetResultDTO<PublicShortTaskDTO>> getPublicTasks() {
        QueryResult<TaskSetResultDTO<PublicShortTaskDTO>> result = new QueryResult<>();
        result.setSuccess(false);
        TaskSetResultDTO<PublicShortTaskDTO> dto = new TaskSetResultDTO<>();
        List<PublicShortTaskDTO> dtos = taskService.getPublicTasks();
        dto.setResultSet(dtos);
        dto.setCount(dtos.size());
        result.setT(dto);
        result.setSuccess(true);
        return result;
    }

    /**
     * 发布任务
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public PureStateDTO createTask(@RequestBody TaskCreateInputDTO dto, HttpServletRequest request) {
        PureStateDTO pureStateDTO = new PureStateDTO();
        pureStateDTO.setSuccess(false);
        try {
            int userId = getCurrentUserId(request);
            taskService.createTask(userId, dto);
            pureStateDTO.setSuccess(true);
        } catch (NoCurrentUserException e) {
            pureStateDTO.setDescription("认证未知错误");
            e.printStackTrace();
        } catch (NotEnoughBalanceException e) {
            pureStateDTO.setDescription("余额不足！");
            e.printStackTrace();
        }
        return pureStateDTO;
    }

    /**
     * 发布者确认完成任务
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/{taskId}", method = RequestMethod.PUT)
    public PureStateDTO checkTask(@PathVariable int taskId) {
        PureStateDTO pureStateDTO = new PureStateDTO();
        pureStateDTO.setSuccess(false);
        taskService.checkTask(taskId);
        pureStateDTO.setSuccess(true);
        return pureStateDTO;
    }

    /**
     * 取消任务
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/{taskId}", method = RequestMethod.DELETE)
    public PureStateDTO cancelTask(@PathVariable int taskId) {
        PureStateDTO pureStateDTO = new PureStateDTO();
        pureStateDTO.setSuccess(false);
        taskService.cancelTask(taskId);
        pureStateDTO.setSuccess(true);
        return pureStateDTO;
    }


    /**
     * 查询当前用户发布的"待领取、等待完成、等待确认"任务简介
     * @param request
     * @return
     */
    @RequestMapping(value = "/short/release_doing", method = RequestMethod.GET)
    public QueryResult<TaskSetResultDTO<ShortTaskOutputDTO>> queryReleaseDoing(HttpServletRequest request) {
        QueryResult<TaskSetResultDTO<ShortTaskOutputDTO>> result = new QueryResult<>();
        result.setSuccess(false);
        try {
            int userId = getCurrentUserId(request);
            List<ShortTaskOutputDTO> shortTaskOutputDTOS = taskService.queryShortTasksDoing(userId);
            TaskSetResultDTO<ShortTaskOutputDTO> resultDTO = new TaskSetResultDTO<>();
            resultDTO.setResultSet(shortTaskOutputDTOS);
            resultDTO.setCount(shortTaskOutputDTOS.size());
            result.setSuccess(true);
        } catch (NoCurrentUserException e) {
            result.setDescription("认证未知错误");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/short/release_completed", method = RequestMethod.GET)
    public QueryResult<TaskSetResultDTO<ShortTaskOutputDTO>> queryReleaseComplete(HttpServletRequest request) {
        QueryResult<TaskSetResultDTO<ShortTaskOutputDTO>> result = new QueryResult<>();
        result.setSuccess(false);
        try {
            int userId = getCurrentUserId(request);
            List<ShortTaskOutputDTO> shortTaskOutputDTOS = taskService.queryShortTasksComplete(userId);
            TaskSetResultDTO<ShortTaskOutputDTO> resultDTO = new TaskSetResultDTO<>();
            resultDTO.setResultSet(shortTaskOutputDTOS);
            resultDTO.setCount(shortTaskOutputDTOS.size());
            result.setSuccess(true);
        } catch (NoCurrentUserException e) {
            result.setDescription("认证未知错误");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/short/all", method = RequestMethod.GET)
    public QueryResult<TaskSetResultDTO<ShortTaskOutputDTO>> queryReleaseAll(HttpServletRequest request) {
        QueryResult<TaskSetResultDTO<ShortTaskOutputDTO>> result = new QueryResult<>();
        result.setSuccess(false);
        try {
            int userId = getCurrentUserId(request);
            List<ShortTaskOutputDTO> shortTaskOutputDTOS = taskService.queryShortTasksAll(userId);
            TaskSetResultDTO<ShortTaskOutputDTO> resultDTO = new TaskSetResultDTO<>();
            resultDTO.setResultSet(shortTaskOutputDTOS);
            resultDTO.setCount(shortTaskOutputDTOS.size());
            result.setSuccess(true);
        } catch (NoCurrentUserException e) {
            result.setDescription("认证未知错误");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/detail/{taskId}", method = RequestMethod.GET)
    public QueryResult<TaskDetailOutputDTO> queryTaskDetail(@PathVariable int taskId) {
        QueryResult<TaskDetailOutputDTO> result = new QueryResult<>();
        result.setSuccess(false);
        try {
            TaskDetailOutputDTO detailOutputDTO = taskService.queryTaskDetail(taskId);
            result.setT(detailOutputDTO);
            result.setSuccess(true);
        } catch (NoSuchTaskExpcetion noSuchTaskExpcetion) {
            result.setDescription("找不到该任务");
            noSuchTaskExpcetion.printStackTrace();
        }
        return result;
    }
}
