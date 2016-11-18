package com.vane.t05_test.utils.http;


/**
 * Created by Administrator on 2016/9/17.
 */
public class RequestBuilder {

    public static Request.Builder getBuilder(String authorization){
        return getBuilder().addHeader("Authorization", authorization);
    }
    public static Request.Builder getBuilder(){
        Request.Builder localBuilder = new Request.Builder()
                .addHeader("qd-manufacturer", "Xiaomi")
                .addHeader("qd-model", "MI 5")
                .addHeader("qd-os", "Android")
                .addHeader("qd-os-version", "6.0")
                .addHeader("qd-screen-width", "1080")
                .addHeader("qd-screen-height", "1920")
                .addHeader("qd-carrier", "%E4%B8%AD%E5%9B%BD%E8%81%94%E9%80%9A")
                .addHeader("qd-network-type", "WIFI")
                .addHeader("qd-app-id", "com.eqingdan")
                .addHeader("qd-app-version", "2.5")
                .addHeader("qd-app-channel", "mi")
                .addHeader("qd-track-device-id", "eb51c9b1f01ac05c32170fc4cf18d0e7")
                .addHeader("User-Agent", "EQingDan/2.5 (Android; okhttp/2.4.0)");
        return localBuilder;
    }

}
