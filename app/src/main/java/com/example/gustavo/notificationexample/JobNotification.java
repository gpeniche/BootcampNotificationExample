package com.example.gustavo.notificationexample;

import android.app.Notification;
import android.os.Bundle;

import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class JobNotification extends JobService {

    @Override
    public boolean onStartJob(JobParameters job) {
        Bundle extras = job.getExtras();
        String message = extras.getString("message");

        Notification.Builder notif = MainActivity.notificationSender.getNotificationBuilder("from job", message);
        MainActivity.notificationSender.send(1, notif);

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }
}
