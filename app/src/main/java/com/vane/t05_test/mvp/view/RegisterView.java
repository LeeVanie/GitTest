package com.vane.t05_test.mvp.view;

import com.vane.t05_test.entity.ResponseRegister;

/**
 * Created by Lee Vane.
 */

public interface RegisterView {
    void showDatas(ResponseRegister responseRegister);
    void showFailed();
}
