package com.example.trainingretrofit.data_source_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;

@JsonIgnoreProperties(
        ignoreUnknown = true
)

public class BaseResults<T>{
    @JsonProperty("result")
    public T result;

    @JsonProperty("errors")
    public LinkedHashMap error;

    @JsonProperty("status")
    public String status;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public LinkedHashMap getError() {
        return error;
    }

    public void setError(LinkedHashMap error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
