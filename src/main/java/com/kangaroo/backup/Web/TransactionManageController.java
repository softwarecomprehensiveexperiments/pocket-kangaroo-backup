package com.kangaroo.backup.Web;

import com.kangaroo.backup.DTO.*;
import com.kangaroo.backup.Exception.DuplicateReceiveException;
import com.kangaroo.backup.Exception.NoCurrentUserException;
import com.kangaroo.backup.Exception.NoPlaceLeftException;
import com.kangaroo.backup.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionManageController extends BaseController{

    private TransactionService transactionService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
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

    @RequestMapping(value = "/{transactionId}", method = RequestMethod.PUT)
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

    @RequestMapping(value = "/{transactionId}", method = RequestMethod.DELETE)
    public PureStateDTO commitTransaction(@PathVariable int transactionId) {
        PureStateDTO dto = new PureStateDTO();
        dto.setSuccess(false);
        transactionService.cancelTransaction(transactionId);
        dto.setSuccess(true);
        return dto;
    }

    @RequestMapping(value = "/short/receive_doing", method = RequestMethod.GET)
    public QueryResult<TransactionSetResultDTO<ShortTransactionOutputDTO>> queryShortDoing(HttpServletRequest request) {
        QueryResult<TransactionSetResultDTO<ShortTransactionOutputDTO>> result = new QueryResult<>();
        result.setSuccess(false);
        try {
            int userId = getCurrentUserId(request);
            List<ShortTransactionOutputDTO> dtos = transactionService.queryShortTransactionDoing(userId);
            TransactionSetResultDTO<ShortTransactionOutputDTO> dto = new TransactionSetResultDTO<>();
            dto.setResultSet(dtos);
            dto.setCount(dtos.size());
            result.setT(dto);
        } catch (NoCurrentUserException e) {
            result.setDescription("认证未知错误");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/short/receive_completed", method = RequestMethod.GET)
    public QueryResult<TransactionSetResultDTO<ShortTransactionOutputDTO>> queryShortComplete(HttpServletRequest request) {
        QueryResult<TransactionSetResultDTO<ShortTransactionOutputDTO>> result = new QueryResult<>();
        result.setSuccess(false);
        try {
            int userId = getCurrentUserId(request);
            List<ShortTransactionOutputDTO> dtos = transactionService.queryShortTransactionComplete(userId);
            TransactionSetResultDTO<ShortTransactionOutputDTO> dto = new TransactionSetResultDTO<>();
            dto.setResultSet(dtos);
            dto.setCount(dtos.size());
            result.setT(dto);
        } catch (NoCurrentUserException e) {
            result.setDescription("认证未知错误");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/short/all", method = RequestMethod.GET)
    public QueryResult<TransactionSetResultDTO<ShortTransactionOutputDTO>> queryShortAll(HttpServletRequest request) {
        QueryResult<TransactionSetResultDTO<ShortTransactionOutputDTO>> result = new QueryResult<>();
        result.setSuccess(false);
        try {
            int userId = getCurrentUserId(request);
            List<ShortTransactionOutputDTO> dtos = transactionService.queryShortTransactionAll(userId);
            TransactionSetResultDTO<ShortTransactionOutputDTO> dto = new TransactionSetResultDTO<>();
            dto.setResultSet(dtos);
            dto.setCount(dtos.size());
            result.setT(dto);
        } catch (NoCurrentUserException e) {
            result.setDescription("认证未知错误");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/detail/{transactionId}", method = RequestMethod.GET)
    public QueryResult<TransactionDetailOutputDTO> queryDetail(@PathVariable int transactionId) {
        QueryResult<TransactionDetailOutputDTO> result = new QueryResult<>();
        result.setSuccess(false);
        result.setT(transactionService.queryDetails(transactionId));
        result.setSuccess(true);
        return result;
    }
}
