package com.vane.t05_test.entity;

/**
 * Created by Lee Vane.
 */

public class ResponseRegister {

    /**
     * status : 1
     * message : child "field" fails because ["field" must be one of [mobile, nickname]]
     */

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
