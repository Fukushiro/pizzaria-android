package com.example.models;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pizzaria.MainActivity;

public class NotificationManagerM{
    private    String CHANNEL_ID = "id";
    private AppCompatActivity a ;
    private NotificationManagerCompat notificationManager;

    public NotificationManagerM( ){


    }
    public void send(Context c, int drawable, String content, String title){


        NotificationCompat.Builder builder = new NotificationCompat.Builder(c, CHANNEL_ID)
                .setSmallIcon(drawable)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        this.notificationManager = NotificationManagerCompat.from(c);
        notificationManager.notify(1, builder.build());

    }
    public void createNotificationChannel(AppCompatActivity app){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = ("nome_canal");
            String description = ("Canal");
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = app.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
