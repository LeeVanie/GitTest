package com.vane.t05_test.mvp.presenter;

import com.vane.t05_test.entity.ResponsePsw;
import com.vane.t05_test.mvp.model.PasswordModel;
import com.vane.t05_test.mvp.model.PasswordModelImpl;
import com.vane.t05_test.mvp.view.PasswordView;

/**
 * Created by Lee Vane.
 */

public class PasswordPresenterImpl implements PasswordPresenter {
    private PasswordModel model;
    private PasswordView view;

    public PasswordPresenterImpl(PasswordView view) {
        this.view = view;
        model = new PasswordModelImpl();
    }

    @Override
    public void loadDatas(String psw, String mobile, final String verify_code) {
        model.loadDatas(psw, mobile, verify_code, new PasswordModel.Callback() {
            @Override
            public void loadDatasSuccess(ResponsePsw psw1) {
                view.showDatas(psw1);
            }

            @Override
            public void loadDatasError() {
                view.showFailed();
            }
        });

    }
}
