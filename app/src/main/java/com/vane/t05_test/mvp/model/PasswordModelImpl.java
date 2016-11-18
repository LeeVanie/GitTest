package com.vane.t05_test.mvp.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.vane.t05_test.entity.ResponsePsw;
import com.vane.t05_test.utils.http.APIUtils;
import com.vane.t05_test.utils.http.FormBody;
import com.vane.t05_test.utils.http.HttpUtils;
import com.vane.t05_test.utils.http.Request;
import com.vane.t05_test.utils.http.RequestBody;

import java.util.HashMap;

/**
 * Created by Lee Vane.
 */

public class PasswordModelImpl implements PasswordModel {
    @Override
    public void loadDatas(String psw, String mobile, String verify_code, final Callback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("password", psw);
        map.put("mobile", mobile);
        map.put("verify_code", verify_code);
        RequestBody body = new FormBody.Builder()
                .add("password", psw)
                .add("mobile", mobile)
                .add("verify_code", verify_code)
                .build();
        Request.Builder builder = new Request.Builder()
                .url("https://passport.4c.cn/api/v1//signup?")
                .post(body)
                .addHeader("X-PASSPORT-APITOKEN", APIUtils.getXPassportApitoken(map));
        HttpUtils.getInstance().execute(builder.build(), new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Log.d("PasswordMedolImpl", response);
                ResponsePsw psw1 = JSON.parseObject(response, ResponsePsw.class);
                callback.loadDatasSuccess(psw1);
            }

            @Override
            public void onError() {
                callback.loadDatasError();
            }
        });
    }
}
