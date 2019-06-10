package com.kangaroo.backup.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kangaroo.backup.Domain.Task;
import com.kangaroo.backup.Domain.Transaction;
import com.kangaroo.backup.Utils.DateConvertUtils;
import org.springframework.beans.BeanUtils;

public class ShortTransactionOutputDTO {

    @JsonProperty("transaction_id")
    private int transactionId;

    @JsonProperty("task_id")
    private int taskId;

    @JsonProperty("task_title")
    private String taskTitle;

    @JsonProperty("task_short_description")
    private String taskShortContent;

    @JsonProperty("task_type")
    private int taskType;

    @JsonProperty("task_deadline")
    private String taskDeadLineDateString;

    @JsonProperty("task_price")
    private int taskPrice;

    @JsonProperty("state")
    private String transactionStateString;

    @JsonProperty("transaction_state_code")
    private int transactionState;

    @JsonProperty("transaction_start_time")
    private String transactionStartTimeString;

    @JsonProperty("transaction_complete_time")
    private String transactionCompleteTimeString;

    public ShortTransactionOutputDTO() {}

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

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskShortContent() {
        return taskShortContent;
    }

    public void setTaskShortContent(String taskShortContent) {
        this.taskShortContent = taskShortContent;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public String getTaskDeadLineDateString() {
        return taskDeadLineDateString;
    }

    public void setTaskDeadLineDateString(String taskDeadLineDateString) {
        this.taskDeadLineDateString = taskDeadLineDateString;
    }

    public int getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(int taskPrice) {
        this.taskPrice = taskPrice;
    }

    public int getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(int transactionState) {
        this.transactionState = transactionState;
    }

    public String getTransactionStartTimeString() {
        return transactionStartTimeString;
    }

    public void setTransactionStartTimeString(String transactionStartTimeString) {
        this.transactionStartTimeString = transactionStartTimeString;
    }

    public String getTransactionCompleteTimeString() {
        return transactionCompleteTimeString;
    }

    public void setTransactionCompleteTimeString(String transactionCompleteTimeString) {
        this.transactionCompleteTimeString = transactionCompleteTimeString;
    }

    public String getTransactionStateString() {
        return transactionStateString;
    }

    public void setTransactionStateString(String transactionStateString) {
        this.transactionStateString = transactionStateString;
    }

    public static ShortTransactionOutputDTO fromTransactionAndTask(Transaction transaction, Task task) {
        ShortTransactionOutputDTO dto = new ShortTransactionOutputDTO();
        BeanUtils.copyProperties(transaction, dto);
        BeanUtils.copyProperties(task, dto);
        dto.setTaskShortContent(task.getTaskContent().substring(0, 10));
        dto.setTaskDeadLineDateString(DateConvertUtils.dateToString(task.getTaskDeadLineDate()));
        dto.setTransactionStartTimeString(DateConvertUtils.dateToString(transaction.getTransactionStartTime()));
        dto.setTransactionCompleteTimeString(DateConvertUtils.dateToString(transaction.getTransactionCompleteTime()));
        dto.setTransactionStateString(Transaction.TransactionState.STATE_CHINESE_SET[dto.getTransactionState()]);
        return dto;
    }
}
