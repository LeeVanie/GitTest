package com.vane.t05_test.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vane.t05_test.R;
import com.vane.t05_test.entity.ResponseRegister;
import com.vane.t05_test.entity.ResponseSignUp;
import com.vane.t05_test.entity.ResponseVerify;
import com.vane.t05_test.mvp.presenter.RegisterPresenterImpl;
import com.vane.t05_test.mvp.presenter.SignUpPresenter;
import com.vane.t05_test.mvp.presenter.SignUpPresenterImpl;
import com.vane.t05_test.mvp.presenter.VerifyPresenter;
import com.vane.t05_test.mvp.presenter.VerifyPresenterImpl;
import com.vane.t05_test.mvp.view.RegisterView;
import com.vane.t05_test.mvp.view.SignUpView;
import com.vane.t05_test.mvp.view.VerifyView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lee Vane.
 */

public class RegistActivity extends Activity implements RegisterView,SignUpView,VerifyView,View.OnClickListener {
    @BindView(R.id.regist_regist)
    EditText registRegist;
    @BindView(R.id.regist_yanzhengma)
    EditText registYanzhengma;
    @BindView(R.id.btn_yanzhengma)
    Button btnYanzhengma;
    @BindView(R.id.register_cancle)
    Button registerCancle;
    @BindView(R.id.register_regist)
    Button registerRegist;
    private RegisterPresenterImpl presenter;
    private SignUpPresenter signUpPresenter;
    private VerifyPresenter verifyPresenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
        ButterKnife.bind(this);
        btnYanzhengma.setOnClickListener(this);
        registerCancle.setOnClickListener(this);
        registerRegist.setOnClickListener(this);
        presenter = new RegisterPresenterImpl(this);
        signUpPresenter = new SignUpPresenterImpl(this);
        verifyPresenter = new VerifyPresenterImpl(this);
        time = new TimeCount(60000, 1000);
    }
    private String registphone;
    private String yanzhengma;
    private boolean isTimer;
    @Override
    public void onClick(View v) {
        registphone = registRegist.getText().toString();
        yanzhengma = registYanzhengma.getText().toString();
        switch (v.getId()) {
            case R.id.register_regist:

                if (registphone.equals("") || yanzhengma.equals("")) {
                    Toast.makeText(this, "请输入完整", Toast.LENGTH_SHORT).show();
                    registerRegist.getBackground().setAlpha(50);
                    return;
                }
                verifyPresenter.loadData("verify", registphone, yanzhengma);

                break;
            case R.id.register_cancle:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_yanzhengma:
                presenter.loadData("mobile", registphone);
                time.start();
        }
    }
    private boolean isChecked;
    public void isChecked(){
        signUpPresenter.loadData("signup", registphone);
        isChecked = true;
    }

    private TimeCount time;
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish() {
            btnYanzhengma.setText("重新验证");
            btnYanzhengma.setClickable(true);
        }
        @Override
        public void onTick(long millisUntilFinished){
            btnYanzhengma.setClickable(false);
            btnYanzhengma.setText(millisUntilFinished /1000+"秒");
        }
    }



    private boolean isExist;
    @Override
    public void showDatas(ResponseRegister responseRegister) {
        Log.d("RegistActivity", "responseRegister.getStatus():" + responseRegister.getStatus());
        if(responseRegister.getStatus() == 1){
            Toast.makeText(this, "手机号码错误，请重新填写", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showDatas(ResponseSignUp signUp) {
        Log.d("RegistActivity", "signUp.getStatus():" + signUp.getStatus());

    }

    @Override
    public void showDatas(ResponseVerify verify) {
        Log.d("RegistActivity", "verify.getStatus():" + verify.getStatus());
        if(verify.getStatus() == 0){
            registerRegist.setText("");
            Toast.makeText(this, "验证码错误，请重新获取", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, PasswordActivity.class);
        intent.putExtra("mobile",registphone);
        intent.putExtra("verify_code", yanzhengma);
        startActivity(intent);
    }

    @Override
    public void showFailed() {

    }

}
