package com.kangaroo.backup.Web;

import com.kangaroo.backup.DTO.*;
import com.kangaroo.backup.Exception.NoCurrentUserException;
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

    @RequestMapping(value = "/{taskId}", method = RequestMethod.PUT)
    public PureStateDTO checkTask(@PathVariable int taskId, HttpServletRequest request) {
        PureStateDTO pureStateDTO = new PureStateDTO();
        pureStateDTO.setSuccess(false);
        try {
            int userId = getCurrentUserId(request);

        } catch (NoCurrentUserException e) {
            pureStateDTO.setDescription("认证未知错误");
            e.printStackTrace();
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
            List<ShortTaskOutputDTO> shortTaskOutputDTOS = taskService.queryReleaseDoingTask(userId);
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

}
