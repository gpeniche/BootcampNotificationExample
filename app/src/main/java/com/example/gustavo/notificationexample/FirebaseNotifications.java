package com.example.gustavo.notificationexample;

import android.app.Notification;
import android.util.Log;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FirebaseNotifications extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0) {

            // Schedule job
            FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
            Job myJob = dispatcher.newJobBuilder()
                    .setService(NotifJob.class)
                    .setTag("my-job-tag")
                    .build();
            dispatcher.schedule(myJob);
        }


        if (remoteMessage.getNotification() != null) {
            Log.d("FirebaseNotifications", remoteMessage.getNotification().getBody());
            NotificationSender notificationSender = MainActivity.notificationSender;
            Notification.Builder notification =
                    notificationSender.getNotificationBuilder(
                            "From firebase",
                            remoteMessage.getNotification().getBody());
            notificationSender.send(222, notification);
        }
    }
}
