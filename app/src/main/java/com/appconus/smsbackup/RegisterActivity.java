package com.appconus.smsbackup;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appconus.smsbackup.abstracts.BaseActivity;
import com.appconus.smsbackup.utils.AccountHelper;
import com.appconus.smsbackup.utils.ActivityController;
import com.appconus.smsbackup.utils.FirebaseHelper;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private Button btnRegister;
    private String passWord;

    @Override
    protected void initData() {

    }

    @Override
    protected void initRootViews() {
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initUIComponents() {
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        edtConfirmPassword = (EditText) findViewById(R.id.edt_password_confirm);
        btnRegister = (Button) findViewById(R.id.btn_register);
    }

    @Override
    protected void initListeners() {
        btnRegister.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        String identity = AccountHelper.getUserIdentity(this);
        edtEmail.setText(identity);
    }

    @Override
    public void onClick(View v) {
        if (checkPassword()) {
            String email = edtEmail.getText().toString().trim();
            FirebaseHelper.INSTANCE.createUser(email, passWord, handleRegisterCallback());
        } else {
            Toast.makeText(this, getString(R.string.err_pass_not_match), Toast.LENGTH_SHORT).show();
        }
    }

    private Firebase.ValueResultHandler<Map<String, Object>> handleRegisterCallback() {
        return new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                ActivityController.navigateToActivity(RegisterActivity.this, MainActivity.class);
                finish();
            }

            @Override
            public void onError(FirebaseError firebaseError) {

            }
        };
    }

    private boolean checkPassword() {
        passWord = edtPassword.getText().toString().trim();
        String confirmPassWord = edtConfirmPassword.getText().toString().trim();
        return passWord.equals(confirmPassWord);
    }
}
