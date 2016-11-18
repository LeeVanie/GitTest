package com.vane.t05_test.mvp.model;

import com.vane.t05_test.entity.ResponseVerify;

/**
 * Created by Lee Vane.
 */

public interface VerifyModel {

    void loadDatas(String type, String mobile, String verify_code, Callback callback);
    public interface Callback{
        void loadDatasSuccess(ResponseVerify verify);
        void loadDatasError();
    }
}
