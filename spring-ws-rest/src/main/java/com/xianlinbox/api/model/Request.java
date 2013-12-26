package com.xianlinbox.api.model;

public class Request {
    private String userId;
    private int requestId;
    private String requestType;
    private String message;

    public Request(String userId, int requestId, String requestType) {
        this.userId = userId;
        this.requestId = requestId;
        this.requestType = requestType;
    }

    public String getUserId() {
        return userId;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
