package com.example.leafiihc.services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.leafiihc.AreaVerde;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GreenAreaMonitoringService extends Service {
    private static final String TAG = "GreenAreaMonitoringService";
    private static final long CHECK_INTERVAL = 6 * 60 * 60 * 1000; // 6 hours
    private Timer timer;
    private GreenAreaNotificationService notificationService;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationService = new GreenAreaNotificationService(this);
        startMonitoring();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    private void startMonitoring() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkGreenAreas();
            }
        }, 0, CHECK_INTERVAL);
    }

    private void checkGreenAreas() {
        SharedPreferences prefs = getSharedPreferences("AreasVerdesPrefs", MODE_PRIVATE);
        String areasJson = prefs.getString("areasVerdes", "");

        if (!areasJson.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<AreaVerde>>(){}.getType();
            List<AreaVerde> areasVerdes = gson.fromJson(areasJson, type);

            for (AreaVerde area : areasVerdes) {
                notificationService.checkAndSendNotifications(area);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
} 