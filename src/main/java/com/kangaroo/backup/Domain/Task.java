package com.kangaroo.backup.Domain;

import com.kangaroo.backup.Constant.DateConstant;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.Vector;

/**
 * PO/DO class.
 */
@EntityScan
public class Task extends BaseDomain {
    private static final long serialVersionUID = -4841653072498200773L;
    private int taskId;
    private String taskTitle;
    private int taskType;
    private String taskContent;
    private int price;
    private int publisherId;
    private Vector<Integer> receiversId;
    private Date publishDate;
    private Date deadLineDate;
    private Date completeDate;
    private int state;

    public class TaskType {

        public static final int RUN_ERRANDS = 0;
        public static final int RESOURCE_SHARE = 1;
        public static final int SURVEY = 2;
    }

    public class TaskState {

        public static final int NOT_RECEIVED = 0;
        public static final int DOING = 1;
        public static final int COMPLETED_NORMALLY = 2;
        public static final int COMPLETED_OUT_OF_TIME = 3;
    }

    public Task(String taskTitle, int taskType, String taskContent, int price, int publisherId,
                Date publishDate, Date deadLineDate) {
        this.taskTitle = taskTitle;
        this.taskType = taskType;
        this.taskContent = taskContent;
        this.price = price;
        this.publisherId = publisherId;
        this.receiversId = new Vector<>();
        this.publishDate = publishDate;
        this.completeDate = DateConstant.NULL_DATE;
        this.deadLineDate = deadLineDate;
        this.state = TaskState.NOT_RECEIVED;
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


    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public Vector<Integer> getReceiversId() {
        return receiversId;
    }

    public void setReceiversId(Vector<Integer> receiversId) {
        this.receiversId = receiversId;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDeadLineDate() {
        return deadLineDate;
    }

    public void setDeadLineDate(Date deadLineDate) {
        this.deadLineDate = deadLineDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
