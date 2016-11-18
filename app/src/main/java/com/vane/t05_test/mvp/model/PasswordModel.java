package com.vane.t05_test.mvp.model;

import com.vane.t05_test.entity.ResponsePsw;

/**
 * Created by Lee Vane.
 */

public interface PasswordModel {

    void loadDatas(String psw, String mobile, String verify_code, Callback callback);
    public interface Callback{
        void loadDatasSuccess(ResponsePsw psw);
        void loadDatasError();
    }
}
