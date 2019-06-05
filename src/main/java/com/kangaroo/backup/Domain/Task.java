package com.kangaroo.backup.Domain;

import com.kangaroo.backup.Constant.DateConstant;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.List;
import java.util.Vector;

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

    /**
     * new
     */
    private int maxReceiversCount;

    /**
     * new
     * can be null
     */
    private List<Question> questionnaire;

    private int taskPrice;

    private int taskPublisherId;

    private Date taskCompleteDate;

    private int taskState;

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
    }

    public Task(String taskTitle, int taskType, String taskContent, int taskPrice, int taskPublisherId,
                Date taskPublishDate, Date taskDeadLineDate) {
        this.taskTitle = taskTitle;
        this.taskType = taskType;
        this.taskContent = taskContent;
        this.taskPrice = taskPrice;
        this.taskPublisherId = taskPublisherId;
        this.taskPublishDate = taskPublishDate;
        this.taskCompleteDate = DateConstant.NULL_DATE;
        this.taskDeadLineDate = taskDeadLineDate;
        this.taskState = TaskState.WAITTING_FOR_RECEIVED;
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

    //移动到服务层
//    public boolean addReceiver(User user) {
//        if(user == null) {
//            return false;
//        }
//        if(receiversId.isEmpty()) {
//            this.state = TaskState.DOING;
//        }
//        receiversId.add(user.getId());
//        return true;
//    }
//
//    public boolean removeReceiver(User user) {
//        if(user == null || receiversId.isEmpty()) {
//            return false;
//        }
//        receiversId.remove(user.getId());
//        if(receiversId.isEmpty()) {
//            this.state = TaskState.NOT_RECEIVED;
//        }
//        return true;
//    }
//    public void completeNormally() {
//        this.completeDate = new Date();
//        this.state = TaskState.COMPLETED_NORMALLY;
//    }
//
//    public void completeOutOfTime() {
//        this.completeDate = new Date();
//        this.state = TaskState.COMPLETED_OUT_OF_TIME;
//    }


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

}
