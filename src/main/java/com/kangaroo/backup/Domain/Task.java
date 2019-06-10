package com.kangaroo.backup.Domain;

import com.kangaroo.backup.Constant.DateConstant;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * PO/DO class.
 */
@EntityScan
public class Task extends BaseDomain {

    private static final long serialVersionUID = -4841653072498200773L;

    private int taskId;

    private String taskTitle;

    private String taskContent;

    private int taskType;

    private Date taskPublishDate;

    private Date taskDeadLineDate;

    private int maxReceiversCount;

    private List<Question> questionnaire;

    private int taskPrice;

    private int taskPublisherId;

    private Date taskCompleteDate;

    private int taskState;

    /**
     * new
     */
    private int currentReceiversCount;

    /**
     * new
     */
	private int currentCompleteCount;

    /**
     * new
     */
	private String result;

    /**
     * new
     */
    private String receivers;

    public static class TaskType {

        public static final String[] TYPE_CHINESE_SET = {"跑腿", "分享", "调查问卷"};

        public static final int RUN_ERRANDS = 0;

        public static final int RESOURCE_SHARE = 1;

        public static final int SURVEY = 2;
    }

    public static class TaskState {

        public static final String[] STATE_CHINESE_SET = {"待领取", "等待完成", "等待确认", "已完成", "已取消"};

        public static final int WAITTING_FOR_RECEIVED = 0;

        public static final int WAITTING_FOR_COMPLETED = 1;

        public static final int WAITTING_FOR_CHECKED = 2;

        public static final int COMPLETED_NORMALLY = 3;

        public static final int CANCELED = 4;

        public static final List<Integer> DOING_STATE_SET;

        public static final List<Integer> COMPLETED_STATE_SET;

        public static final List<Integer> ALL_STATE_SET;

        static {
            DOING_STATE_SET = new LinkedList<>();
            DOING_STATE_SET.add(TaskState.WAITTING_FOR_RECEIVED);
            DOING_STATE_SET.add(TaskState.WAITTING_FOR_COMPLETED);
            DOING_STATE_SET.add(TaskState.WAITTING_FOR_CHECKED);

            COMPLETED_STATE_SET = new LinkedList<>();
            COMPLETED_STATE_SET.add(TaskState.COMPLETED_NORMALLY);

            ALL_STATE_SET = new LinkedList<>();
            ALL_STATE_SET.add(TaskState.WAITTING_FOR_RECEIVED);
            ALL_STATE_SET.add(TaskState.WAITTING_FOR_COMPLETED);
            ALL_STATE_SET.add(TaskState.WAITTING_FOR_CHECKED);
            ALL_STATE_SET.add(TaskState.COMPLETED_NORMALLY);
            ALL_STATE_SET.add(TaskState.CANCELED);

        }
    }

    public Task() {}

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
