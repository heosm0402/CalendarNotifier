package com.sommin.calendarNotifier.http;

import org.springframework.http.HttpStatus;

public class ApiResponse {

    private HttpStatus status;
    private String message;
    private Object data;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ApiResponse() {
        this.status = HttpStatus.OK;
        this.message = null;
        this.data = null;
    }

}
