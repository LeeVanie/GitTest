package com.vane.t05_test.mvp.view;

import com.vane.t05_test.entity.ResponseSignUp;

/**
 * Created by Lee Vane.
 */

public interface SignUpView {
    void showDatas(ResponseSignUp signUp);
    void showFailed();
}
