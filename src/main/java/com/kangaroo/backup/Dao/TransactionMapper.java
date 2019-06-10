package com.kangaroo.backup.Dao;

import com.kangaroo.backup.Domain.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionMapper {

    List<Transaction> getAllTransaction();

    Transaction getTransactionById(int transactionId);

    void insertTransaction(Transaction transaction);

    void updateTransaction(Transaction transaction);

    void deleteTransaction(int transactionId);



    //int getCountByTaskIdAndState(int taskId, int state);

    List<Transaction> getTransactionsByTaskId(int taskId);
    int getCountByReceiverIdAndTaskId(int receiverId, int taskId);
    int getCountByPublisherIdAndTaskId(int publisherId, int taskId);
}
