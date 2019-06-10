package com.kangaroo.backup.Domain;

import com.kangaroo.backup.Constant.DateConstant;

import java.util.Date;

/**
 * PO/DO class
 */
public class Transaction extends BaseDomain{

    private static final long serialVersionUID = -9154939714950317847L;

    private int transactionId;

    private int taskId;

    private int userId;

    /**
     * New
     * 存放问卷填写结果或者任务完成反馈
     */
    private String committion;

    private int transactionState;

    private Date transactionStartTime;

    private Date transactionCompleteTime;

    public static class TransactionState {
        public static final String[] STATE_CHINESE_SET = {"进行中", "等待发布者确认", "已完成", "已逾期", "已取消"};
        public static final int DOING = 0;
        public static final int WAITTING_FOR_PUBLISHER_CHECK = 1;
        public static final int COMPLETED_NORMALLY = 2;
        public static final int OVERDUE = 3;
        public static final int CANCELED = 4;
    }

    public Transaction() {
        transactionStartTime = DateConstant.NULL_DATE;
        transactionCompleteTime = DateConstant.NULL_DATE;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCommittion() {
        return committion;
    }

    public void setCommittion(String committion) {
        this.committion = committion;
    }

    public int getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(int transactionState) {
        this.transactionState = transactionState;
    }

    public Date getTransactionStartTime() {
        return transactionStartTime;
    }

    public void setTransactionStartTime(Date transactionStartTime) {
        this.transactionStartTime = transactionStartTime;
    }

    public Date getTransactionCompleteTime() {
        return transactionCompleteTime;
    }

    public void setTransactionCompleteTime(Date transactionCompleteTime) {
        this.transactionCompleteTime = transactionCompleteTime;
    }

    //    public int getState() {
//        if(transactionCancelTime != DateConstant.NULL_DATE) {
//            return TransactionState.CANCELED;
//        }
//        if(transactionCompleteTime != DateConstant.NULL_DATE) {
//            return TransactionState.COMPLETED_NORMALLY;
//        }
//        if(transactionCommitTime != DateConstant.NULL_DATE) {
//            return TransactionState.WAITTING_FOR_PUBLISHER_CHECK;
//        }
//        if(System.currentTimeMillis() > transactionOverDueTime.getTime()) {
//            return TransactionState.OVERDUE;
//        }
//        return TransactionState.DOING;
//    }
}
