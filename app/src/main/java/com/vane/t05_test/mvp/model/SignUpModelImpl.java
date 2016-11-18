package com.vane.t05_test.mvp.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.vane.t05_test.entity.ResponseSignUp;
import com.vane.t05_test.utils.http.APIUtils;
import com.vane.t05_test.utils.http.FormBody;
import com.vane.t05_test.utils.http.HttpUtils;
import com.vane.t05_test.utils.http.Request;
import com.vane.t05_test.utils.http.RequestBody;

import java.util.HashMap;

/**
 * Created by Lee Vane.
 */

public class SignUpModelImpl implements SignUpModel {

    @Override
    public void loadDatas(String signup, String mobile, final Callback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", signup);
        map.put("mobile", mobile);
        RequestBody body = new FormBody.Builder()
                .add("type", signup)
                .add("mobile", mobile)
                .build();
        Request.Builder builder = new Request.Builder()
                .url("https://passport.4c.cn/api/v1/sms?")
                .post(body)
                .addHeader("X-PASSPORT-APITOKEN", APIUtils.getXPassportApitoken(map));
        HttpUtils.getInstance().execute(builder.build(), new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Log.d("SignUpModelImpl", response);
                ResponseSignUp signUp = JSON.parseObject(response, ResponseSignUp.class);
                callback.loadDatsSuccess(signUp);
            }

            @Override
            public void onError() {

            }
        });
    }
}
