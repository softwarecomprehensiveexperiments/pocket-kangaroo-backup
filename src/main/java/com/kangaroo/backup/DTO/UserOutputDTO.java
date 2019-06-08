package com.kangaroo.backup.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UserOutputDTO implements Serializable {

    private static final long serialVersionUID = -3387275890406870339L;

    @JsonProperty(value = "user_id")
    private int userId;

    @JsonProperty(value = "user_phone")
    private String phone;

    @JsonProperty(value = "user_name")
    private String name;

    @JsonProperty(value = "user_sex")
    private int sex;

    @JsonProperty(value = "user_properties")
    private int properties;

    @JsonProperty(value = "user_icon")
    private String icon;

    @JsonProperty(value = "user_completed_receive_task_count")
    private int userDoingReceiveTaskCount;

    @JsonProperty(value = "user_completed_release_task_count")
    private int userCompletedReceiveTaskCount;

    @JsonProperty(value = "user_doing_receive_task_count")
    private int userCompletedReleaseTaskCount;

    @JsonProperty(value = "user_doing_release_task_count")
    private int userDoingReleaseTaskCount;

    public UserOutputDTO() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getProperties() {
        return properties;
    }

    public void setProperties(int properties) {
        this.properties = properties;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getUserCompletedReceiveTaskCount() {
        return userCompletedReceiveTaskCount;
    }

    public void setUserCompletedReceiveTaskCount(int userCompletedReceiveTaskCount) {
        this.userCompletedReceiveTaskCount = userCompletedReceiveTaskCount;
    }

    public int getUserCompletedReleaseTaskCount() {
        return userCompletedReleaseTaskCount;
    }

    public void setUserCompletedReleaseTaskCount(int userCompletedReleaseTaskCount) {
        this.userCompletedReleaseTaskCount = userCompletedReleaseTaskCount;
    }

    public int getUserDoingReceiveTaskCount() {
        return userDoingReceiveTaskCount;
    }

    public void setUserDoingReceiveTaskCount(int userDoingReceiveTaskCount) {
        this.userDoingReceiveTaskCount = userDoingReceiveTaskCount;
    }

    public int getUserDoingReleaseTaskCount() {
        return userDoingReleaseTaskCount;
    }

    public void setUserDoingReleaseTaskCount(int userDoingReleaseTaskCount) {
        this.userDoingReleaseTaskCount = userDoingReleaseTaskCount;
    }
}
