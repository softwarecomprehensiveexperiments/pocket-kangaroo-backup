package com.kangaroo.backup.DTO;

import com.kangaroo.backup.Domain.Question;
import com.kangaroo.backup.Domain.Task;
import com.kangaroo.backup.Utils.DateConvertUtils;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class TaskDetailOutputDTO {

    private int taskId;

    private String taskTitle;

    private String taskContent;

    private int taskType;

    private String taskPublishDateString;

    private String taskDeadLineDateString;

    private int maxReceiversCount;

    private List<Question> questionnaire;

    private int taskPrice;

    private int taskPublisherId;

    private String publisherName;

    private String taskCompleteDateString;

    private int taskState;

    private String taskStateString;

    private int currentReceiversCount;

    private int currentCompleteCount;

    private String result;

    private String receivers;

    public static TaskDetailOutputDTO fromNameAndTask(String publisherName, Task task) {
        TaskDetailOutputDTO detailOutputDTO = new TaskDetailOutputDTO();
        BeanUtils.copyProperties(task, detailOutputDTO);
        detailOutputDTO.setTaskPublishDateString(DateConvertUtils.dateToString(task.getTaskPublishDate()));
        detailOutputDTO.setTaskDeadLineDateString(DateConvertUtils.dateToString(task.getTaskDeadLineDate()));
        detailOutputDTO.setPublisherName(publisherName);
        detailOutputDTO.setTaskCompleteDateString(DateConvertUtils.dateToString(task.getTaskCompleteDate()));
        detailOutputDTO.setTaskStateString(Task.TaskState.STATE_CHINESE_SET[task.getTaskState()]);
        return detailOutputDTO;
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

    public String getTaskPublishDateString() {
        return taskPublishDateString;
    }

    public void setTaskPublishDateString(String taskPublishDateString) {
        this.taskPublishDateString = taskPublishDateString;
    }

    public String getTaskDeadLineDateString() {
        return taskDeadLineDateString;
    }

    public void setTaskDeadLineDateString(String taskDeadLineDateString) {
        this.taskDeadLineDateString = taskDeadLineDateString;
    }

    public int getMaxReceiversCount() {
        return maxReceiversCount;
    }

    public void setMaxReceiversCount(int maxReceiversCount) {
        this.maxReceiversCount = maxReceiversCount;
    }

    public List<Question> getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(List<Question> questionnaire) {
        this.questionnaire = questionnaire;
    }

    public int getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(int taskPrice) {
        this.taskPrice = taskPrice;
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

    public String getTaskCompleteDateString() {
        return taskCompleteDateString;
    }

    public void setTaskCompleteDateString(String taskCompleteDateString) {
        this.taskCompleteDateString = taskCompleteDateString;
    }

    public int getTaskState() {
        return taskState;
    }

    public void setTaskState(int taskState) {
        this.taskState = taskState;
    }

    public String getTaskStateString() {
        return taskStateString;
    }

    public void setTaskStateString(String taskStateString) {
        this.taskStateString = taskStateString;
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
