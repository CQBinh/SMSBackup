package com.appconus.smsbackup;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.appconus.smsbackup.abstracts.BaseActivity;
import com.appconus.smsbackup.utils.AccountHelper;
import com.appconus.smsbackup.utils.ActivityController;
import com.appconus.smsbackup.utils.FirebaseHelper;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnRegister;
    private ProgressBar progress;


    @Override
    protected void initData() {

    }

    @Override
    protected void initRootViews() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initUIComponents() {
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);
        progress = (ProgressBar) findViewById(R.id.login_progress);
    }

    @Override
    protected void initListeners() {
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        String identity = AccountHelper.getUserIdentity(this);
        edtEmail.setText(identity);
        ActivityController.navigateToActivity(LoginActivity.this, MainActivity.class);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_register:
                ActivityController.navigateToActivity(this, RegisterActivity.class);
                finish();
                break;
        }
    }

    private void login() {
        String email = edtEmail.getText().toString().trim();
        String passWord = edtPassword.getText().toString().trim();
        progress.setVisibility(View.VISIBLE);
        FirebaseHelper.INSTANCE.login(email, passWord, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                ActivityController.navigateToActivity(LoginActivity.this, MainActivity.class);
                finish();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {

            }
        });
    }
}

