package com.vane.t05_test.mvp.presenter;

import com.vane.t05_test.entity.ResponseRegister;
import com.vane.t05_test.mvp.model.RegisterMedol;
import com.vane.t05_test.mvp.model.RegisterModelImpl;
import com.vane.t05_test.mvp.view.RegisterView;

/**
 * Created by Lee Vane.
 */

public class RegisterPresenterImpl implements RegisterPresenter {
    private RegisterMedol model;
    private RegisterView view;

    public RegisterPresenterImpl(RegisterView view) {
        this.view = view;
        model = new RegisterModelImpl();
    }

    @Override
    public void loadData(String phone, String key) {
        model.loadDatas(phone, key, new RegisterMedol.Callback() {

            @Override
            public void loadDatasSuccess(ResponseRegister responseRegister) {
                view.showDatas(responseRegister);
            }

            @Override
            public void loadDatasFailed() {
                view.showFailed();
            }
        });
    }

}
