package com.vane.t05_test.mvp.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.vane.t05_test.entity.ResponseVerify;
import com.vane.t05_test.utils.http.APIUtils;
import com.vane.t05_test.utils.http.FormBody;
import com.vane.t05_test.utils.http.HttpUtils;
import com.vane.t05_test.utils.http.Request;
import com.vane.t05_test.utils.http.RequestBody;

import java.util.HashMap;

/**
 * Created by Lee Vane.
 */

public class VerifyModelImpl implements VerifyModel {

    @Override
    public void loadDatas(String type, String mobile, String verify, final Callback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("mobile", mobile);
        map.put("verify_code", verify);
        RequestBody body = new FormBody.Builder()
                .add("type", type)
                .add("mobile", mobile)
                .add("verify_code", verify)
                .build();
        Request.Builder builder = new Request.Builder()
                .url("https://passport.4c.cn/api/v1/sms?")
                .post(body)
                .addHeader("X-PASSPORT-APITOKEN", APIUtils.getXPassportApitoken(map));
        HttpUtils.getInstance().execute(builder.build(), new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Log.d("VerifyModelImpl", response);
                ResponseVerify verify1 = JSON.parseObject(response, ResponseVerify.class);
                callback.loadDatasSuccess(verify1);
            }

            @Override
            public void onError() {
                callback.loadDatasError();
            }
        });
    }
}
