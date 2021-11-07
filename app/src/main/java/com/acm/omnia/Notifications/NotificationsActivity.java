package com.acm.omnia.Notifications;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.acm.omnia.databinding.ActivityNotificationsBinding;

import com.google.firebase.messaging.FirebaseMessaging;

public class NotificationsActivity extends AppCompatActivity {
    ActivityNotificationsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        getSupportActionBar().hide();

        FirebaseMessaging.getInstance().subscribeToTopic("all");
        binding.title.setText("Hello from ACM Omnia");
        binding.sendToAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.title.getText().toString().isEmpty() && !binding.message.getText().toString().isEmpty()){

                    FcmNotificationsSender notificationsSender = new FcmNotificationsSender("/topics/all",binding.title.getText().toString(),
                            binding.message.getText().toString(),getApplicationContext(),NotificationsActivity.this);
                    notificationsSender.SendNotifications();
                }else{
                    Toast.makeText(NotificationsActivity.this, "Please write some text to send notification", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}