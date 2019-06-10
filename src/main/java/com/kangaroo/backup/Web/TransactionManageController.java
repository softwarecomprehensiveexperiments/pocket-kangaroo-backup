package com.kangaroo.backup.Web;

import com.kangaroo.backup.DTO.PureStateDTO;
import com.kangaroo.backup.Exception.DuplicateReceiveException;
import com.kangaroo.backup.Exception.NoCurrentUserException;
import com.kangaroo.backup.Exception.NoPlaceLeftException;
import com.kangaroo.backup.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@RestController
public class TransactionManageController extends BaseController{

    private TransactionService transactionService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public PureStateDTO createTransaction(@RequestParam int task_id, HttpServletRequest request) {
        PureStateDTO dto = new PureStateDTO();
        dto.setSuccess(false);
        try {
            int userId = getCurrentUserId(request);
            transactionService.createTransaction(userId, task_id);
        } catch (NoCurrentUserException e) {
            e.printStackTrace();
        } catch (NoPlaceLeftException e) {
            dto.setDescription("当前任务已被领取完");
            e.printStackTrace();
        } catch (DuplicateReceiveException e) {
            dto.setDescription("你已领取过该任务");
            e.printStackTrace();
        }
        return dto;
    }

    @RequestMapping(value = "transaction/{transactionId}", method = RequestMethod.PUT)
    public PureStateDTO commitTransaction(@PathVariable int transactionId, @RequestParam String committion, HttpServletRequest request) {
        PureStateDTO dto = new PureStateDTO();
        dto.setSuccess(false);
        try {
            int userId = getCurrentUserId(request);
            transactionService.commitTransaction(userId, committion);
            dto.setSuccess(true);
        } catch (NoCurrentUserException e) {
            dto.setDescription("认证未知错误");
            e.printStackTrace();
        }
        return dto;
    }
}
