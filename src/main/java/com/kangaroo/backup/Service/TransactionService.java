package com.kangaroo.backup.Service;

import com.kangaroo.backup.Dao.TransactionMapper;
import com.kangaroo.backup.Domain.Transaction;
import com.kangaroo.backup.Exception.DuplicateReceiveException;
import com.kangaroo.backup.Exception.NoPlaceLeftException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * 交易管理系统服务层
 */
@Service
public class TransactionService {

    private TransactionMapper transactionMapper;

    private AccountService accountService;

    private TaskService taskService;

    @Autowired
    public void setTransactionMapper(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
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
        if(transactionMapper.getByUserIdAndTaskId(userId, taskId) != null) {
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

    /**
     * 用户取消交易单
     * @param transactionId
     */
    public void cancelTransaction(int transactionId) {
        Transaction transaction = transactionMapper.getTransactionById(transactionId);
        if (transaction.getTransactionState() != Transaction.TransactionState.DOING) {
            return;
        }
        transaction.setTransactionState(Transaction.TransactionState.CANCELED);
        transaction.setTransactionCompleteTime(new Date());
        taskService.removeReceiver(transaction.getTaskId());
    }

    void afterTaskCheck(int taskId) {
        List<Transaction> transactions = transactionMapper.getTransactionsByTaskId(taskId);
        transactions.forEach(e -> {
            if(e.getTransactionState() == Transaction.TransactionState.WAITTING_FOR_PUBLISHER_CHECK) {
                settle(e);
            }
        });
    }

    void afterTaskCancel(int taskId) {
        List<Transaction> transactions = transactionMapper.getTransactionsByTaskId(taskId);
        transactions.forEach(e -> {
            if(e.getTransactionState() == Transaction.TransactionState.WAITTING_FOR_PUBLISHER_CHECK) {
                settle(e);
            }
        });
    }

    private void settle(Transaction transaction) {
        transaction.setTransactionState(Transaction.TransactionState.COMPLETED_NORMALLY);
        accountService.addCredit(transaction.getUserId(), taskService.getAmount(transaction.getTaskId()));
    }

    int[] getUserStatisticsData(int userId) {
        int[] res = new int[2];
        List<Transaction> transactions = transactionMapper.getAllByUserId(userId);
        transactions.forEach(e -> {
            if(e.getTransactionState() == Transaction.TransactionState.DOING ||
                    e.getTransactionState() == Transaction.TransactionState.WAITTING_FOR_PUBLISHER_CHECK) {
                res[0]++;
            }
            if(e.getTransactionState() == Transaction.TransactionState.COMPLETED_NORMALLY ||
                    e.getTransactionState() == Transaction.TransactionState.OVERDUE) {
                res[1]++;
            }
        });
        return res;
    }
}
