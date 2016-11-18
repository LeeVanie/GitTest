package com.vane.t05_test.mvp.presenter;

import com.vane.t05_test.entity.ResponseSignUp;
import com.vane.t05_test.mvp.model.SignUpModel;
import com.vane.t05_test.mvp.model.SignUpModelImpl;
import com.vane.t05_test.mvp.view.SignUpView;

/**
 * Created by Lee Vane.
 */

public class SignUpPresenterImpl implements SignUpPresenter {

    private SignUpModel model;
    private SignUpView view;

    public SignUpPresenterImpl(SignUpView view) {
        this.view = view;
        model = new SignUpModelImpl();
    }

    @Override
    public void loadData(String signup, String mobile) {
        model.loadDatas(signup, mobile, new SignUpModel.Callback() {
            @Override
            public void loadDatsSuccess(ResponseSignUp signUp) {
                view.showDatas(signUp);
            }

            @Override
            public void loadDatasError() {
                view.showFailed();
            }
        });
    }
}
