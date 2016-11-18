package com.vane.t05_test.utils.http;

import java.io.UnsupportedEncodingException;

/**
 * Created by LG on 2016/10/16.
 * Tips:
 */

public abstract class RequestBody {

    public static final String formContentType = "application/x-www-form-urlencoded";
    public static final String jsonContentType = "application/json; Charset=utf-8";
    public RequestBody(String contentType){
        this.contentType = contentType;
    }
    private String contentType;
    public String getContentTpte(){
        return contentType;
    }
    public static RequestBody create(String type, final String body){

        return new RequestBody(type) {

            @Override
            public byte[] getBodyBytes() {
                return getUTFBodyBytes(body);
            }
        };
    }
    public abstract byte[] getBodyBytes();
    protected byte[] getUTFBodyBytes(String body){
        try {
            return body.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
