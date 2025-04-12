package com.example.leafiihc.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.leafiihc.DetalleAreaVerdeActivity;
import com.example.leafiihc.R;
import com.example.leafiihc.AreaVerde;

import java.util.Calendar;
import java.util.Date;

public class GreenAreaNotificationService {
    private static final String CHANNEL_ID = "green_area_notifications";
    private static final int NOTIFICATION_ID = 1;
    private Context context;

    public GreenAreaNotificationService(Context context) {
        this.context = context;
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Green Area Notifications";
            String description = "Notifications for green area monitoring";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void checkAndSendNotifications(AreaVerde areaVerde) {
        // Check humidity levels
        if (areaVerde.getNivelHumedad() < 30) {
            sendNotification(
                "Nivel de humedad bajo",
                "El área " + areaVerde.getNombre() + " necesita riego urgente",
                areaVerde
            );
        }

        // Check light levels
        if (areaVerde.getNivelLuz() < 40) {
            sendNotification(
                "Nivel de luz insuficiente",
                "El área " + areaVerde.getNombre() + " necesita más exposición a la luz",
                areaVerde
            );
        }

        // Check last revision date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7); // 7 days ago
        if (areaVerde.getUltimaRevision().before(calendar.getTime())) {
            sendNotification(
                "Revisión pendiente",
                "El área " + areaVerde.getNombre() + " necesita una revisión",
                areaVerde
            );
        }
    }

    private void sendNotification(String title, String message, AreaVerde areaVerde) {
        Intent intent = new Intent(context, DetalleAreaVerdeActivity.class);
        intent.putExtra("CODIGO_AREA", areaVerde.getCodigo());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        
        PendingIntent pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_plants)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
} 