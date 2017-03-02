package com.jartcorp.hnoss.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by yeh on 15/9/3.
 */
public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    private static final String[] TOPICS = {"Jart.Hnoss"};
    private static final String SENDER_ID = "643735582603";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            synchronized (TAG) {

                //向GCM註冊(產生Token)
                InstanceID instanceID = InstanceID.getInstance(this);
                String token = instanceID.getToken(SENDER_ID, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                Log.i(TAG, "GCM Registration Token: " + token);

                //App Server註冊(回傳Token)
                sendRegistrationToServer(token);
                //訂閱發怖主題
                subscribeTopics(token);
            }
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
        }
    }

    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
    }

    private void subscribeTopics(String token) throws IOException {
        for (String topic : TOPICS) {
            GcmPubSub pubSub = GcmPubSub.getInstance(this);
            //訂閱主題
            pubSub.subscribe(token, "/topics/" + topic, null);
            //取消訂閱
            //pubSub.unsubscribe(token,"/topics/" + "你的主題名稱");
        }
    }

}