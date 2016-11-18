package com.vane.t05_test.utils.http;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;

/**
 * Created by LG on 2016/10/16.
 * Tips:
 */

public class HttpClient {
    private static String authorization;
    private static long validTime;
    public static void excute(final Request.Builder request, final HttpUtils.Callback callback){
        if(!checkAuthorization()){
            Log.d("HttpClient", "token为空，去申请token咯");
            Credential credential = new Credential();
            credential.setGrant_type("client_credentials");
            credential.setClient_id("herEv4O9tg4PuviM");
            credential.setClient_secret("v20ulmkZP5ykQMn9uUwNbyEiuTkzFfPn");
            String json = JSON.toJSONString(credential);
            RequestBody body = RequestBody.create(RequestBody.jsonContentType,json);
            Request requestToken = new Request.Builder()
                    .url("http://api.eqingdan.com/v1/oauth2/access-token")
                    .post(body)
                    .build();
            HttpUtils.getInstance().execute(requestToken, new HttpUtils.Callback() {
                @Override
                public void onResponse(String response) {
                    Log.d("HttpClient", response);
                    AuthoriaztionObj authoriaztionObj = JSON.parseObject(response,AuthoriaztionObj.class);
                    AuthoriaztionObj.DataBean data = authoriaztionObj.getData();
                    authorization = data.getToken_type()+" " + data.getAccess_token();
                    validTime = System.currentTimeMillis()+data.getExpires_in()*1000;
                    request.addHeader("authorization",authorization);
                    HttpUtils.getInstance().execute(request.build(),callback);
                }
                @Override
                public void onError() {

                }
            });
        }else{
            Log.d("HttpClient", "Token为：" + authorization);
            request.addHeader("authorization",authorization);
            HttpUtils.getInstance().execute(request.build(),callback);
        }
    }

    /**
     * 检测authorization是否有效
     * @return
     */
    private static boolean checkAuthorization() {
        if(TextUtils.isEmpty(authorization) || isTokenInvalid()){
            return false;
        }
        return true;
    }

    /**
     * 判断authorization是否已失效
     * @return
     */
    private static boolean isTokenInvalid(){
        return System.currentTimeMillis() > validTime;
    }
}
