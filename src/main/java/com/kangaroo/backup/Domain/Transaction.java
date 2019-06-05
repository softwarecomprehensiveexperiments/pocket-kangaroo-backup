package com.kangaroo.backup.Domain;

import java.util.Date;

/**
 * PO/DO class
 */
public class Transaction extends BaseDomain{

    private static final long serialVersionUID = -9154939714950317847L;

    private int transactionId;

    private int taskId;

    private int userId;

    private int transactionState;

    private Date transactionStartTime;

    private Date transactionCompleteTime;

    public static class TaskState {
        public static final String[] STATE_CHINESE_SET = {"进行中", "已取消", "已逾期", "等待发布者确认", "已完成"};
        public static final int WAITTING_FOR_RECEIVED = 0;
        public static final int WAITTING_FOR_COMPLETED = 1;
        public static final int WAITTING_FOR_CHECKED = 2;
        public static final int COMPLETED_NORMALLY = 3;
        public static final int CANCELED = 4;
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
}
