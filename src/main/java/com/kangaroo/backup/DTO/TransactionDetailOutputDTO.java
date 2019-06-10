package com.kangaroo.backup.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kangaroo.backup.Domain.Question;
import com.kangaroo.backup.Domain.Task;
import com.kangaroo.backup.Domain.Transaction;
import com.kangaroo.backup.Utils.DateConvertUtils;
import org.springframework.beans.BeanUtils;
import java.util.List;

public class TransactionDetailOutputDTO {

    @JsonProperty("transaction_id")
    private int transactionId;

    @JsonProperty("task_id")
    private int taskId;

    @JsonProperty("committion")
    private String committion;

    @JsonProperty("publisher_id")
    private int taskPublisherId;

    @JsonProperty("publisher_name")
    private String publisherName;

    @JsonProperty("transaction_state_code")
    private int transactionState;

    @JsonProperty("state")
    private String transactionStateString;

    @JsonProperty("transaction_start_time")
    private String transactionStartTimeString;

    @JsonProperty("transaction_complete_time")
    private String transactionCompleteTimeString;

    @JsonProperty("task_deadline")
    private String taskDeadLineDateString;

    @JsonProperty("task_title")
    private String taskTitle;

    @JsonProperty("task_description")
    private String taskContent;

    @JsonProperty("task_type")
    private int taskType;

    @JsonProperty("task_price")
    private int taskPrice;

    @JsonProperty("questionnaire")
    private List<Question> questionnaire;

    public static TransactionDetailOutputDTO fromPNameAndTransAndTask(String publisherName, Transaction transaction, Task task) {
        TransactionDetailOutputDTO dto = new TransactionDetailOutputDTO();
        BeanUtils.copyProperties(transaction, dto);
        BeanUtils.copyProperties(task, dto);
        dto.setPublisherName(publisherName);
        dto.setTransactionStartTimeString(DateConvertUtils.dateToString(transaction.getTransactionStartTime()));
        dto.setTransactionCompleteTimeString(DateConvertUtils.dateToString(transaction.getTransactionCompleteTime()));
        dto.setTaskDeadLineDateString(DateConvertUtils.dateToString(task.getTaskDeadLineDate()));
        dto.setTransactionStateString(Transaction.TransactionState.STATE_CHINESE_SET[transaction.getTransactionState()]);
        return dto;
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

    public String getTransactionStateString() {
        return transactionStateString;
    }

    public void setTransactionStateString(String transactionStateString) {
        this.transactionStateString = transactionStateString;
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

    public String getTaskDeadLineDateString() {
        return taskDeadLineDateString;
    }

    public void setTaskDeadLineDateString(String taskDeadLineDateString) {
        this.taskDeadLineDateString = taskDeadLineDateString;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public int getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(int taskPrice) {
        this.taskPrice = taskPrice;
    }

    public List<Question> getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(List<Question> questionnaire) {
        this.questionnaire = questionnaire;
    }

    public int getTaskPublisherId() {
        return taskPublisherId;
    }

    public void setTaskPublisherId(int taskPublisherId) {
        this.taskPublisherId = taskPublisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}
