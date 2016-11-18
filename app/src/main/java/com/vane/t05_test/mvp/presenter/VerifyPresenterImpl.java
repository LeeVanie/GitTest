package com.vane.t05_test.mvp.presenter;

import com.vane.t05_test.entity.ResponseVerify;
import com.vane.t05_test.mvp.model.VerifyModel;
import com.vane.t05_test.mvp.model.VerifyModelImpl;
import com.vane.t05_test.mvp.view.VerifyView;

/**
 * Created by Lee Vane.
 */

public class VerifyPresenterImpl implements VerifyPresenter {

    private VerifyModel model;
    private VerifyView view;

    public VerifyPresenterImpl(VerifyView view) {
        this.view = view;
        model = new VerifyModelImpl();
    }

    @Override
    public void loadData(String verify, String mobile, String verify_code) {
        model.loadDatas(verify, mobile, verify_code, new VerifyModel.Callback() {
            @Override
            public void loadDatasSuccess(ResponseVerify verify1) {
                view.showDatas(verify1);
            }

            @Override
            public void loadDatasError() {
                view.showFailed();
            }
        });
    }
}
