package com.kangaroo.backup.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TransactionSetResultDTO<T> {

    @JsonProperty("total_count")
    private int count;

    @JsonProperty("transactions")
    private List<T> resultSet;

    public int getCount() {
        return count;
    }



    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getResultSet() {
        return resultSet;
    }

    public void setResultSet(List<T> resultSet) {
        this.resultSet = resultSet;
    }
}
