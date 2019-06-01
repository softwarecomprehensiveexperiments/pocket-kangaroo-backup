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
    private Date taskDeadLineDate;
    private String taskContent;
    private int taskPrice;
    private int taskPublisherId;
    //taskReceiversId由Vector类型改为String类型，类似："12354&448936$65484"，便于数据库存储操作
//    private Vector<Integer> taskReceiversId;
    private String taskReceiversId;
    private Date taskPublishDate;
    private int taskType;
    private Date taskCompleteDate;
    private int taskState;

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

    public Task(String taskTitle, int taskType, String taskContent, int taskPrice, int taskPublisherId, String taskReceiversId,
                Date taskPublishDate, Date taskDeadLineDate) {
        this.taskTitle = taskTitle;
        this.taskType = taskType;
        this.taskContent = taskContent;
        this.taskPrice = taskPrice;
        this.taskPublisherId = taskPublisherId;
//        this.taskReceiversId = new Vector<>();
        this.taskReceiversId = taskReceiversId;
        this.taskPublishDate = taskPublishDate;
        this.taskCompleteDate = DateConstant.NULL_DATE;
        this.taskDeadLineDate = taskDeadLineDate;
        this.taskState = TaskState.NOT_RECEIVED;
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

//    public Vector<Integer> getTaskReceiversId() {
//        return taskReceiversId;
//    }

//    public void setTaskReceiversId(Vector<Integer> taskReceiversId) {
//        this.taskReceiversId = taskReceiversId;
//    }

    public String getTaskReceiversId() {
        return taskReceiversId;
    }
    public void setTaskReceiversId(String taskReceiversId) {
        this.taskReceiversId = taskReceiversId;
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
