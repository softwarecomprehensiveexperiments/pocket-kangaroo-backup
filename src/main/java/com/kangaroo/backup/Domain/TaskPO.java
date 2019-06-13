package com.kangaroo.backup.Domain;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

/**
 * PO
 */
@EntityScan
public class TaskPO extends BaseDomain {

    private static final long serialVersionUID = -4841653072498200773L;

    private int taskId;

    private String taskTitle;

    private String taskContent;

    private int taskType;

    private Date taskPublishDate;

    private Date taskDeadLineDate;

    private int maxReceiversCount;

    private int taskPrice;

    private int taskPublisherId;

    private Date taskCompleteDate;

    private int taskState;

    private int currentReceiversCount;

    private int currentCompleteCount;

    private String result;

    private String receivers;

    public TaskPO() {}

    public int getMaxReceiversCount() {
        return maxReceiversCount;
    }

    public void setMaxReceiversCount(int maxReceiversCount) {
        this.maxReceiversCount = maxReceiversCount;
    }

    public int getTaskPublisherId() {
        return taskPublisherId;
    }

    public void setTaskPublisherId(int taskPublisherId) {
        this.taskPublisherId = taskPublisherId;
    }

    public Date getTaskPublishDate() {
        return taskPublishDate;
    }

    public void setTaskPublishDate(Date taskPublishDate) {
        this.taskPublishDate = taskPublishDate;
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

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public int getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(int taskPrice) {
        this.taskPrice = taskPrice;
    }

    public Date getTaskDeadLineDate() {
        return taskDeadLineDate;
    }

    public void setTaskDeadLineDate(Date taskDeadLineDate) {
        this.taskDeadLineDate = taskDeadLineDate;
    }

    public Date getTaskCompleteDate() {
        return taskCompleteDate;
    }

    public void setTaskCompleteDate(Date taskCompleteDate) {
        this.taskCompleteDate = taskCompleteDate;
    }

    public int getTaskState() {
        return taskState;
    }

    public void setTaskState(int taskState) {
        this.taskState = taskState;
    }

    public int getCurrentReceiversCount() {
        return currentReceiversCount;
    }

    public void setCurrentReceiversCount(int currentReceiversCount) {
        this.currentReceiversCount = currentReceiversCount;
    }

    public int getCurrentCompleteCount() {
        return currentCompleteCount;
    }

    public void setCurrentCompleteCount(int currentCompleteCount) {
        this.currentCompleteCount = currentCompleteCount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReceivers() {
        return receivers;
    }

    public void setReceivers(String receivers) {
        this.receivers = receivers;
    }
}

