package com.kangaroo.backup.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * PO class
 */
public class Question extends BaseDomain{

    private static final long serialVersionUID = -5677371893965169309L;

    @JsonProperty("question_id")
    private int questionId;

    /*是否增加外键以便联合task查询*/
    //???感觉会出事
    private int taskId;

    @JsonProperty("question_description")
    private String questionDescription;

    @JsonProperty("if_multiple_select")
    private Boolean ifMultipleSelect;

    @JsonProperty("options_count")
    private int optionsCount;

//    private List<String> options;
    @JsonProperty("options")
    private String options;

    public Question() {
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public Boolean getIfMultipleSelect() {
        return ifMultipleSelect;
    }

    public void setIfMultipleSelect(Boolean ifMultipleSelect) {
        this.ifMultipleSelect = ifMultipleSelect;
    }

    public int getOptionsCount() {
        return optionsCount;
    }

    public void setOptionsCount(int optionsCount) {
        this.optionsCount = optionsCount;
    }

//    public List<String> getOptions() {
//        return options;
//    }
//
//    public void setOptions(List<String> options) {
//        this.options = options;
//    }
    public String getOptions(){return options;}
    public void setOptions(String options){ this.options = options; }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
