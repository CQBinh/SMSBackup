package com.appconus.smsbackup.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.appconus.smsbackup.R;
import com.appconus.smsbackup.abstracts.BaseArrayAdapter;
import com.appconus.smsbackup.utils.Objects.Message;
import com.appconus.smsbackup.utils.cells.MessageMasterCell;

import java.util.List;

/**
 * Copyright © 2015 Tubiapp inc.
 * Created by Justin on 26/02/2016.
 */
public class MessageMasterAdapter extends BaseArrayAdapter<Message> {
    public MessageMasterAdapter(Context context, List<Message> objects) {
        super(context, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageMasterCell cell;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_lv_message_master, parent, false);
            cell = new MessageMasterCell(getContext(), convertView);
            convertView.setTag(cell);
        } else {
            cell = (MessageMasterCell) convertView.getTag();
        }
        cell.setData(getItem(position));
        return convertView;
    }
}
