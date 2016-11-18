package com.vane.t05_test.mvp.view;

import com.vane.t05_test.entity.ResponsePsw;

/**
 * Created by Lee Vane.
 */

public interface PasswordView {
    void showDatas(ResponsePsw psw);
    void showFailed();
}
