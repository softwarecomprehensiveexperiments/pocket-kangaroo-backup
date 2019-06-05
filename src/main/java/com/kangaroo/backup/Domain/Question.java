package com.kangaroo.backup.Domain;

import java.util.List;

/**
 * PO class
 */
public class Question extends BaseDomain{

    private static final long serialVersionUID = -5677371893965169309L;

    private int questionId;

    private String questionDescription;

    private Boolean ifMultipleSelect;

    private int optionsCount;

    private List<String> options;

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

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}