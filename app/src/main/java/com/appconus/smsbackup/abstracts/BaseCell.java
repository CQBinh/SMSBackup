package com.appconus.smsbackup.abstracts;

import android.content.Context;
import android.view.View;

public abstract class BaseCell {
    public static int reUseIdentifier = 0;
    protected Context context;

    public BaseCell(Context context, View rootView) {
        super();
        this.context = context;
        initUiComponents(rootView);
    }

    public BaseCell(Context context) {
        this.context = context;
        initUiComponents(null);
    }

    protected abstract void initUiComponents(View rootView);

}