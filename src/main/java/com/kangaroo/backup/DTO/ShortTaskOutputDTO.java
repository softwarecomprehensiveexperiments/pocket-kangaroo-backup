package com.kangaroo.backup.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.kangaroo.backup.Domain.Task;
import com.kangaroo.backup.Utils.DateConvertUtils;
import org.springframework.beans.BeanUtils;

public class ShortTaskOutputDTO {

    @JsonProperty("task_id")
    private int taskId;

    @JsonProperty("task_title")
    private String taskTitle;

    @JsonProperty("task_short_description")
    private String taskShortDescription;

    @JsonProperty("task_type")
    private int taskType;

    @JsonProperty("task_release_time")
    private String taskPublishDateString;

    @JsonProperty("task_deadline")
    private String taskDeadlineDateString;

    @JsonProperty("max_receivers_count")
    private int maxReceiversCount;

    @JsonProperty("task_price")
    private int taskPrice;

    @JsonProperty("current_receivers_count")
    private int currentReceiversCount;

    @JsonProperty("current_complete_count")
    private int currentCompleteCount;

    @JsonProperty("overdue_count")
    private int overdueCount;

    @JsonProperty("task_state")
    private String taskState;

    @JsonProperty("task_state_code")
    private int taskStateCode;

    public ShortTaskOutputDTO() {
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

    public String getTaskShortDescription() {
        return taskShortDescription;
    }

    public void setTaskShortDescription(String taskShortDescription) {
        this.taskShortDescription = taskShortDescription;
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

    public void setTaskPublishDateString(String taskPublishDateS) {
        this.taskPublishDateString = taskPublishDateString;
    }

    public String getTaskDeadlineDateString() {
        return taskDeadlineDateString;
    }

    public void setTaskDeadlineDateString(String taskDeadlineDate) {
        this.taskDeadlineDateString = taskDeadlineDateString;
    }

    public int getMaxReceiversCount() {
        return maxReceiversCount;
    }

    public void setMaxReceiversCount(int maxReceiversCount) {
        this.maxReceiversCount = maxReceiversCount;
    }

    public int getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(int taskPrice) {
        this.taskPrice = taskPrice;
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

    public int getOverdueCount() {
        return overdueCount;
    }

    public void setOverdueCount(int overdueCount) {
        this.overdueCount = overdueCount;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    public int getTaskStateCode() {
        return taskStateCode;
    }

    public void setTaskStateCode(int taskStateCode) {
        this.taskStateCode = taskStateCode;
    }

    public static ShortTaskOutputDTO fromTask(Task task) {
        ShortTaskOutputDTO dto = new ShortTaskOutputDTO();
        BeanUtils.copyProperties(task, dto);
        dto.setTaskShortDescription(task.getTaskContent().substring(0, 10) + "...");
        dto.setTaskPublishDateString(DateConvertUtils.dateToString(task.getTaskPublishDate()));
        dto.setTaskDeadlineDateString(DateConvertUtils.dateToString(task.getTaskDeadLineDate()));
        dto.setTaskState(Task.TaskState.STATE_CHINESE_SET[task.getTaskState()]);
        dto.setOverdueCount(task.getCurrentReceiversCount() - task.getCurrentCompleteCount());
        return dto;
    }
}
