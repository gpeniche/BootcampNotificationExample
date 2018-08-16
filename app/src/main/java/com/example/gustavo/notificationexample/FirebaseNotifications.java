package com.example.gustavo.notificationexample;

import android.os.Bundle;
import android.util.Log;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseNotifications extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage message) {
        Log.d("Firebase", message.getNotification().getBody().toString());

        Bundle extras = new Bundle();
//        extras.putInt("key", 0);
        extras.putString("message", message.getNotification().getBody().toString());
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job myJob = dispatcher.newJobBuilder()
                .setService(JobNotification.class)
                .setExtras(extras)
                .setTag("my-job-tag")
                .build();
        dispatcher.schedule(myJob);

    }

    @Override
    public void onNewToken(String token) {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("TAG", "Refreshed token: " + refreshedToken);
    }
}
