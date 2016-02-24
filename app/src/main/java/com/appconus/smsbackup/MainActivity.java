package com.appconus.smsbackup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.appconus.smsbackup.apis.APIHelper;
import com.appconus.smsbackup.apis.MessageService;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView = (TextView) findViewById(R.id.tvMain);
//        FirebaseHelper.INSTANCE.loadMessage(textView);

        MessageService messageService = new APIHelper<MessageService>().createService(MessageService.class);
        messageService.getAllMessages("7ba59d3e-ff10-4363-b0c5-28910639eb33.json", new Callback<JSONObject>() {
            @Override
            public void success(JSONObject s, Response response) {
                BufferedReader reader = null;
                StringBuilder sb = new StringBuilder();
                try {

                    reader = new BufferedReader(new InputStreamReader(response.getBody().in()));

                    String line;

                    try {
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


                String result = sb.toString();
                try {
                    JSONObject object = new JSONObject(result);
                    Iterator<?> iterator = object.keys();
                    while (iterator.hasNext()) {
                        JSONObject json = (JSONObject) object.get(String.valueOf(iterator.next()));
                        Log.w("Foo", json.toString());
                    }
                } catch (JSONException e) {
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
