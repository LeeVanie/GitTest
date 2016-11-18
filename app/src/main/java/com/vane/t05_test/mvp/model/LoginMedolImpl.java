package com.vane.t05_test.mvp.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.vane.t05_test.entity.ResponseLogin;
import com.vane.t05_test.utils.http.APIUtils;
import com.vane.t05_test.utils.http.FormBody;
import com.vane.t05_test.utils.http.HttpUtils;
import com.vane.t05_test.utils.http.Request;
import com.vane.t05_test.utils.http.RequestBody;

import java.util.HashMap;

/**
 * Created by Lee Vane.
 */

public class LoginMedolImpl implements LoginModel {
    @Override
    public void loadDatas(String name, String password, final Callback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("username", name);
        map.put("password", password);
        RequestBody body = new FormBody.Builder()
                .add("username", name)
                .add("password", password)
                .build();
        Request.Builder builder = new Request.Builder()
                .url("https://passport.4c.cn/api/v1/login?")
                .post(body)
                .addHeader("X-PASSPORT-APITOKEN", APIUtils.getXPassportApitoken(map));
        HttpUtils.getInstance().execute(builder.build(), new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Log.d("LoginMedolImpl", response);
                ResponseLogin login = JSON.parseObject(response, ResponseLogin.class);
                callback.loadDatasSuccess(login);
            }

            @Override
            public void onError() {
                callback.loadDatasError();
            }
        });
    }
}
