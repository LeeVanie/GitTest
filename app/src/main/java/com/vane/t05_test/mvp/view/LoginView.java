package com.vane.t05_test.mvp.view;

import com.vane.t05_test.entity.ResponseLogin;

/**
 * Created by Lee Vane.
 */

public interface LoginView {
    void showDatas(ResponseLogin login);
    void showFailed();
}
