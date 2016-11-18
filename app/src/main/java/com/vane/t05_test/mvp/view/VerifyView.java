package com.vane.t05_test.mvp.view;

import com.vane.t05_test.entity.ResponseVerify;

/**
 * Created by Lee Vane.
 */

public interface VerifyView {
    void showDatas(ResponseVerify verify);
    void showFailed();
}
