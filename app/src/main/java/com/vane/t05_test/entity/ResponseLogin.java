package com.vane.t05_test.entity;

/**
 * Created by Lee Vane.
 */

public class ResponseLogin {

    /**
     * status : 1
     * message : child "username" fails because ["username" is not allowed to be empty]
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