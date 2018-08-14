package com.example.gustavo.notificationexample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;

public class NotificationSender extends ContextWrapper {
    public static final String NOTIFICATION_CHANNEL = "default";
    private static final String NOTIFICATION_SEQUENCE = "sequence";

    private NotificationManager notificationManager;

    public NotificationSender(final Context context) {
        super(context);

        NotificationChannel notificationChannel =
                new NotificationChannel(
                        NOTIFICATION_CHANNEL,
                        NOTIFICATION_SEQUENCE,
                        NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getNotificationManager().createNotificationChannel(notificationChannel);

    }

    public NotificationManager getNotificationManager() {
        if (notificationManager == null) {
            notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

    public Notification.Builder getNotificationBuilder(String title, String body) {
        return new Notification.Builder(getApplicationContext(), NOTIFICATION_CHANNEL)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true);
    }

    public void send(int id, Notification.Builder notification) {
        getNotificationManager().notify(id, notification.build());
    }
}
