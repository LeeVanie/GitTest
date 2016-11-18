package com.vane.t05_test.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vane.t05_test.R;
import com.vane.t05_test.entity.ResponsePsw;
import com.vane.t05_test.mvp.presenter.PasswordPresenter;
import com.vane.t05_test.mvp.presenter.PasswordPresenterImpl;
import com.vane.t05_test.mvp.view.PasswordView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lee Vane.
 */

public class PasswordActivity extends Activity implements PasswordView,View.OnClickListener {
    @BindView(R.id.password_pass)
    EditText passwordPass;
    @BindView(R.id.password_qrpass)
    EditText passwordQrpass;
    @BindView(R.id.password_cancle)
    Button passwordCancle;
    @BindView(R.id.password_regist)
    Button passwordRegist;
    @BindView(R.id.password_cancle_topic)
    Button passwordCancleTopic;
    private PasswordPresenter presenter;
    private String mobile;
    private String verify_code;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password);
        ButterKnife.bind(this);
        mobile = getIntent().getStringExtra("mobile");
        verify_code = getIntent().getStringExtra("verify_code");
        passwordCancle.setOnClickListener(this);
        passwordRegist.setOnClickListener(this);
        passwordCancleTopic.setOnClickListener(this);
        presenter = new PasswordPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        String pass1 = passwordPass.getText().toString();
        String pass2 = passwordQrpass.getText().toString();
        switch (v.getId()) {
            case R.id.password_cancle:
                passwordPass.setText("");
                passwordQrpass.setText("");
                break;
            case R.id.password_regist:

                if (pass1.equals("") || pass2.equals("")) {
                    Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!pass1.equals(pass2)) {
                    Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.loadDatas(pass1,mobile,verify_code);
                break;
            case R.id.password_cancle_topic:
                Intent in = new Intent(this, MainActivity.class);
                startActivity(in);
        }
    }

    @Override
    public void showDatas(ResponsePsw psw) {
        Log.d("PasswordActivity", "psw.getStatus():" + psw.getStatus());
        if (psw.getStatus() == 1) {
            Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
            return ;
        }
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }
    @Override
    public void showFailed() {

    }
}
