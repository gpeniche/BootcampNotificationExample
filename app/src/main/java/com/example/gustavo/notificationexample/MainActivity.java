package com.example.gustavo.notificationexample;

import android.app.Notification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final int NOTIFICATION_ID = 123;

    public static NotificationSender notificationSender;
    private TextView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationSender = new NotificationSender(this);
        editText = findViewById(R.id.edit_title);
        Button sendButton = findViewById(R.id.send_button);
        sendButton.setOnClickListener(getSendButtonClick());


    }

    private String getEditText() {
        if (editText != null) {
            return editText.getText().toString();
        }
        return "";
    }

    private View.OnClickListener getSendButtonClick() {
        return new View.OnClickListener() {
            public void onClick(View v) {

                Notification.Builder notification =
                        notificationSender.getNotificationBuilder("From the button", getEditText());

                if (notification != null) {
                    notificationSender.send(NOTIFICATION_ID, notification);
                }
            }
        };
    }
}
