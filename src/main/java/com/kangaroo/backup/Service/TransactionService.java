package com.kangaroo.backup.Service;

import com.kangaroo.backup.Dao.TaskMapper;
import com.kangaroo.backup.Dao.TransactionMapper;
import com.kangaroo.backup.Domain.Transaction;
import com.kangaroo.backup.Exception.DuplicateReceiveException;
import com.kangaroo.backup.Exception.NoPlaceLeftException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 交易管理系统服务层
 */
@Service
public class TransactionService {

    private TransactionMapper transactionMapper;

    private TaskService taskService;

    @Autowired
    public void setTransactionMapper(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * 领取任务，创建交易单
     * @param userId
     * @param taskId
     * @throws DuplicateReceiveException
     * @throws NoPlaceLeftException
     */
    public void createTransaction(int userId, int taskId) throws DuplicateReceiveException, NoPlaceLeftException {
        if(transactionMapper.getCountByReceiverIdAndTaskId(userId, taskId) > 0) {
            throw new DuplicateReceiveException();
        }
        taskService.addReceiver(taskId);
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setTaskId(taskId);
        transaction.setTransactionStartTime(new Date());
        transaction.setTransactionState(Transaction.TransactionState.DOING);
        transactionMapper.insertTransaction(transaction);
    }

    /**
     * 提交交易单
     * @param transactionId
     * @param committion
     */
    public void commitTransaction(int transactionId, String committion) {
        Transaction transaction = transactionMapper.getTransactionById(transactionId);
        if(transaction.getTransactionState() != Transaction.TransactionState.DOING) {
            return;
        }
        transaction.setCommittion(committion);
        transaction.setTransactionState(Transaction.TransactionState.WAITTING_FOR_PUBLISHER_CHECK);
        transaction.setTransactionCompleteTime(new Date());
        taskService.addCommitter(transaction.getTaskId(), committion);
        transactionMapper.updateTransaction(transaction);
    }

    public void afterTaskCheck(int taskId) {
        transactionMapper.getTransactionsByTaskId(taskId);
    }
}
