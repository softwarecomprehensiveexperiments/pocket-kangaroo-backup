package com.kangaroo.backup.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StateDTO {
    @JsonProperty(value = "is_success")
    private boolean success;
    @JsonProperty(value = "error_code")
    private int errorCode;
    @JsonProperty(value = "description")
    private String description;
}
