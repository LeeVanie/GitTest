package com.vane.t05_test.mvp.model;

import com.vane.t05_test.entity.ResponseSignUp;

/**
 * Created by Lee Vane.
 */

public interface SignUpModel {

    void loadDatas(String signup, String mobile, Callback callback);

    public interface Callback{
        void loadDatsSuccess(ResponseSignUp signUp);
        void loadDatasError();
    }

}
