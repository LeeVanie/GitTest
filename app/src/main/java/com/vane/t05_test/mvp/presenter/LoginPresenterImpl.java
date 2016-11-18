package com.vane.t05_test.mvp.presenter;

import com.vane.t05_test.entity.ResponseLogin;
import com.vane.t05_test.mvp.model.LoginMedolImpl;
import com.vane.t05_test.mvp.model.LoginModel;
import com.vane.t05_test.mvp.view.LoginView;

/**
 * Created by Lee Vane.
 */

public class LoginPresenterImpl implements LoginPresenter {
    private LoginModel model;
    private LoginView view;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        model = new LoginMedolImpl();
    }

    @Override
    public void loadDatas(String name, final String password) {
        model.loadDatas(name, password, new LoginModel.Callback() {

            @Override
            public void loadDatasSuccess(ResponseLogin login) {
                view.showDatas(login);
            }

            @Override
            public void loadDatasError() {
                view.showFailed();
            }
        });
    }
}
