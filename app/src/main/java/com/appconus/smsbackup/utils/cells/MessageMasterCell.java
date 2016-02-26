package com.appconus.smsbackup.utils.cells;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.appconus.smsbackup.R;
import com.appconus.smsbackup.abstracts.BaseCell;
import com.appconus.smsbackup.utils.Objects.Message;

/**
 * Copyright © 2015 Tubiapp inc.
 * Created by Justin on 26/02/2016.
 */
public class MessageMasterCell extends BaseCell{
    private TextView tvSenderName;
    private TextView tvMessageContent;

    public MessageMasterCell(Context context, View rootView) {
        super(context, rootView);
    }

    @Override
    protected void initUiComponents(View rootView) {
        tvSenderName = (TextView) rootView.findViewById(R.id.tvSenderName);
        tvMessageContent = (TextView) rootView.findViewById(R.id.tvMessageContent);
    }

    public void setData(Message message) {
        tvSenderName.setText(message.getFrom());
        tvMessageContent.setText(message.getMessage());
    }
}
