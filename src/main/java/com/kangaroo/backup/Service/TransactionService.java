package com.kangaroo.backup.Service;

import com.kangaroo.backup.DTO.ShortTransactionOutputDTO;
import com.kangaroo.backup.DTO.TransactionDetailOutputDTO;
import com.kangaroo.backup.Dao.TransactionMapper;
import com.kangaroo.backup.Domain.Task;
import com.kangaroo.backup.Domain.Transaction;
import com.kangaroo.backup.Exception.DuplicateReceiveException;
import com.kangaroo.backup.Exception.NoPlaceLeftException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 交易管理系统服务层
 */
@Service
public class TransactionService {

    private TransactionMapper transactionMapper;

    private AccountService accountService;

    private TaskService taskService;

    private UserService userService;

    @Autowired
    public void setTransactionMapper(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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
        if(transactionMapper.getTransactionsByUserIdAndTaskId(userId, taskId) != null) {
            throw new DuplicateReceiveException();
        }
        String userName = userService.getName(userId);
        taskService.addReceiver(taskId, userName);
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
        transactionMapper.updateTransaction(transaction);
        String userName = userService.getName(transaction.getUserId());
        taskService.removeReceiver(transaction.getTaskId(), userName);
    }

    public TransactionDetailOutputDTO queryDetails(int transactionId) {
        Transaction transaction = transactionMapper.getTransactionById(transactionId);
        Task task = taskService.getById(transaction.getTaskId());
        String pName = userService.getName(task.getTaskPublisherId());
        return TransactionDetailOutputDTO.fromPNameAndTransAndTask(pName, transaction, task);
    }

    public List<ShortTransactionOutputDTO> queryShortTransactionDoing(int userId) {
        return queryShortTransactionByStates(userId, Transaction.TransactionState.DOING_STATE_SET);
    }

    public List<ShortTransactionOutputDTO> queryShortTransactionComplete(int userId) {
        return queryShortTransactionByStates(userId, Transaction.TransactionState.COMPLETED_STATE_SET);
    }

    public List<ShortTransactionOutputDTO> queryShortTransactionAll(int userId) {
        return queryShortTransactionByStates(userId, Transaction.TransactionState.ALL_STATE_SET);
    }

    private List<ShortTransactionOutputDTO> queryShortTransactionByStates(int userId, List<Integer> states) {
        List<Transaction> transactions = transactionMapper.getTransactionsByUserId(userId);
        List<ShortTransactionOutputDTO> dtos = new LinkedList<>();
        transactions.forEach(e -> {
            if(states.contains(e.getTransactionState())) {
                Task task = taskService.getById(e.getTaskId());
                dtos.add(ShortTransactionOutputDTO.fromTransactionAndTask(e, task));
            }
        });
        return dtos;
    }

    void afterTaskCheck(int taskId) {
        List<Transaction> transactions = transactionMapper.getTransactionsByTaskId(taskId);
        transactions.forEach(e -> {
            if(e.getTransactionState() == Transaction.TransactionState.WAITTING_FOR_PUBLISHER_CHECK) {
                settle(e);
                transactionMapper.updateTransaction(e);
            }
        });
    }

    void afterTaskCancel(int taskId) {
        List<Transaction> transactions = transactionMapper.getTransactionsByTaskId(taskId);
        transactions.forEach(e -> {
            if(e.getTransactionState() == Transaction.TransactionState.WAITTING_FOR_PUBLISHER_CHECK) {
                settle(e);
            }
            else {
                e.setTransactionState(Transaction.TransactionState.CANCELED_BY_PUBLISHER);
            }
            transactionMapper.updateTransaction(e);
        });
    }

    private void settle(Transaction transaction) {
        transaction.setTransactionState(Transaction.TransactionState.COMPLETED_NORMALLY);
        accountService.addCredit(transaction.getUserId(), taskService.getAmount(transaction.getTaskId()));
    }

    int[] getUserStatisticsData(int userId) {
        int[] res = new int[2];
        List<Transaction> transactions = transactionMapper.getTransactionsByUserId(userId);
        transactions.forEach(e -> {
            if(Transaction.TransactionState.DOING_STATE_SET.contains(e.getTransactionState())) {
                res[0]++;
            }
            if(Transaction.TransactionState.COMPLETED_STATE_SET.contains(e.getTransactionState())) {
                res[1]++;
            }
        });
        return res;
    }
}
