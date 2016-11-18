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
import com.vane.t05_test.entity.ResponseLogin;
import com.vane.t05_test.mvp.presenter.LoginPresenter;
import com.vane.t05_test.mvp.presenter.LoginPresenterImpl;
import com.vane.t05_test.mvp.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements LoginView,View.OnClickListener {

    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_regist)
    Button loginRegist;
    @BindView(R.id.login_login)
    Button loginLogin;
    @BindView(R.id.login_account)
    EditText loginAccount;

    private LoginPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        int i = 5;
        loginLogin.setOnClickListener(this);
        loginRegist.setOnClickListener(this);
        presenter = new LoginPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        String account = loginAccount.getText().toString();
        String password = loginPassword.getText().toString();
        switch (v.getId()) {
            case R.id.login_login:
                if (account.equals("") && password.equals("")) {
                    Toast.makeText(this, "请输入账号或者密码", Toast.LENGTH_SHORT).show();
                    loginLogin.getBackground().setAlpha(50);
                    return;
                }
                presenter.loadDatas(account, password);
                break;
            case R.id.login_regist:
                Intent intent = new Intent(this, RegistActivity.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    public void showDatas(ResponseLogin login) {
        Log.d("MainActivity", "login:" + login);
        if(login.getStatus() == 1){
            loginAccount.setText("");
            loginPassword.setText("");
            Toast.makeText(this, "输入错误，重新登录", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailed() {

    }
}
