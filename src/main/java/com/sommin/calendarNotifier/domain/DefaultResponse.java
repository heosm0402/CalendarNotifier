package com.sommin.calendarNotifier.domain;

import org.springframework.http.HttpStatus;

public class DefaultResponse {
    private HttpStatus status;
    private String msg;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
