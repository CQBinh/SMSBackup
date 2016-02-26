package com.appconus.smsbackup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.appconus.smsbackup.adapters.MessageMasterAdapter;
import com.appconus.smsbackup.apis.APIHelper;
import com.appconus.smsbackup.apis.MessageService;
import com.appconus.smsbackup.utils.FirebaseHelper;
import com.appconus.smsbackup.utils.JsonHelper;
import com.appconus.smsbackup.utils.Objects.Message;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private List<Message> messages;
    private MessageMasterAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        messages = new ArrayList<>();
        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        adapter = new MessageMasterAdapter(this, messages);
        lvMain.setAdapter(adapter);
        loadMessages();
    }

    private void loadMessages() {
        MessageService messageService = new APIHelper<MessageService>().createService(MessageService.class);
        String apiPath = String.format("%s.json", FirebaseHelper.INSTANCE.getUserUid());
        messageService.getAllMessages(apiPath, new Callback<JSONObject>() {
            @Override
            public void success(JSONObject s, Response response) {
                BufferedReader reader;
                StringBuilder sb = new StringBuilder();
                try {
                    reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    String result = sb.toString();
                    JSONObject object = new JSONObject(result);
                    Iterator<?> iterator = object.keys();
                    Message message;
                    while (iterator.hasNext()) {
                        JSONObject json = (JSONObject) object.get(String.valueOf(iterator.next()));
                        message = JsonHelper.fromJson(json, Message.class);
                        messages.add(message);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Foo", error.getMessage());
            }
        });
    }
}
