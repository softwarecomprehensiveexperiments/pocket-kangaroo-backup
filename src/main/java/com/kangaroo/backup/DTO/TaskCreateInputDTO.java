package com.kangaroo.backup.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kangaroo.backup.Domain.Question;
import com.kangaroo.backup.Domain.Task;
import com.kangaroo.backup.Utils.DateConvertUtils;

import java.util.Date;
import java.util.List;

public class TaskCreateInputDTO {

    @JsonProperty("task_title")
    private String taskTitle;

    @JsonProperty("task_description")
    private String taskContent;

    @JsonProperty("task_type")
    private int taskType;

    @JsonProperty("task_price")
    private int taskPrice;

    @JsonProperty("max_receivers_count")
    private int maxReceiversCount;

    @JsonProperty("task_deadline")
    private String taskDeadLineDate;

    @JsonProperty("questionnaire")
    private List<Question> questionnaire;

    public Task convertToTask() {
        Task task = new Task();
        task.setTaskTitle(taskTitle);
        task.setTaskContent(taskContent);
        task.setTaskType(taskType);
        task.setTaskPrice(taskPrice);
        task.setMaxReceiversCount(maxReceiversCount);
        task.setTaskDeadLineDate(DateConvertUtils.stringToDate(taskDeadLineDate));
        task.setQuestionnaire(questionnaire);
        return null;
    }

}
