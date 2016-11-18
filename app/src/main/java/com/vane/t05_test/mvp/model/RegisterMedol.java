package com.vane.t05_test.mvp.model;

import com.vane.t05_test.entity.ResponseRegister;

/**
 * Created by Lee Vane.
 */

public interface RegisterMedol {

    void loadDatas(String mobile, String key, Callback callback);
    public interface Callback{
        void loadDatasSuccess(ResponseRegister responseRegister);
        void loadDatasFailed();
    }
}
