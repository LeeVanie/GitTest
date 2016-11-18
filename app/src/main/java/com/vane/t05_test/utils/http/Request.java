package com.vane.t05_test.utils.http;


/**
 * Created by LG on 2016/10/16.
 * Tips:
 */

public class Request {
    private  String url;
    private  String method;
    private  Headers headers;
    private RequestBody body;

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public Headers getHeaders() {
        return headers;
    }

    public RequestBody getBody() {
        return body;
    }

    private Request(Builder builder){
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headersBuilder.build();
        this.body = builder.body;
    }
    public static class Builder{
        private  String url;
        private  String method;
        private  Headers.Builder headersBuilder;
        private RequestBody body;
        public Builder(){
            this.method = "GET";
            this.headersBuilder = new Headers.Builder();
        }
        public Builder url(String url){
            this.url = url;
            return this;
        }
        public Builder addHeader(String name,String value){
            if(headersBuilder == null){
                headersBuilder = new Headers.Builder();
            }
            this.headersBuilder.addHeader(name,value);
            return this;
        }
        public Builder get(){
            this.method = "GET";
            return this;
        }
        public Builder post(RequestBody body){
            this.method = "POST";
            this.body = body;
            addHeader("Content-type",body.getContentTpte());
            return this;
        }
        public Request build(){
            return new Request(this);
        }
    }
}
