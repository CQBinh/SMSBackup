package com.appconus.smsbackup.abstracts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected ACActivityCallback activityCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initRootViews();
        initUIComponents();
        initListeners();
        loadData();
    }

    protected abstract void initData();

    protected abstract void initRootViews();

    protected abstract void initUIComponents();

    protected abstract void initListeners();

    protected abstract void loadData();

    public void startActivityForResult(Intent intent, int requestCode,
                                       ACActivityCallback activityCallback) {
        startActivityForResult(intent, requestCode);
        this.activityCallback = activityCallback;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (activityCallback != null) {
            activityCallback.onReceiveData(requestCode, resultCode, data);
        }
    }

    public interface ACActivityCallback {
        void onReceiveData(int requestCode, int resultCode, Intent data);
    }
}