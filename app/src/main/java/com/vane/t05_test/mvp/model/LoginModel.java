package com.vane.t05_test.mvp.model;

import com.vane.t05_test.entity.ResponseLogin;

/**
 * Created by Lee Vane.
 */

public interface LoginModel {

    void loadDatas(String name, String password, Callback callback);
    public interface Callback{
        void loadDatasSuccess(ResponseLogin login);
        void loadDatasError();
    }
}
