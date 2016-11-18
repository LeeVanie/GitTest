package com.vane.t05_test.mvp.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.vane.t05_test.entity.ResponseRegister;
import com.vane.t05_test.utils.http.APIUtils;
import com.vane.t05_test.utils.http.FormBody;
import com.vane.t05_test.utils.http.HttpUtils;
import com.vane.t05_test.utils.http.Request;
import com.vane.t05_test.utils.http.RequestBody;

import java.util.HashMap;

/**
 * Created by Lee Vane.
 */

public class RegisterModelImpl implements RegisterMedol {
    @Override
    public void loadDatas(String phone, String key, final Callback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("field", phone);
        map.put("value", key);
        RequestBody body = new FormBody.Builder()
                .add("field", phone)
                .add("value", key)
                .build();
        Request.Builder builder = new Request.Builder()
                .url("https://passport.4c.cn/api/v1/exists?")
                .post(body)
                .addHeader("X-PASSPORT-APITOKEN", APIUtils.getXPassportApitoken(map));
        HttpUtils.getInstance().execute(builder.build(), new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Log.d("RegisterModelImpl", response);
                ResponseRegister responseRegister = JSON.parseObject(response, ResponseRegister.class);
                callback.loadDatasSuccess(responseRegister);
            }

            @Override
            public void onError() {
                callback.loadDatasFailed();
            }
        });

    }
}
