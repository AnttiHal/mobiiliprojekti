package com.example.drinkwaterversion1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String CHANNEL_ID = "channel_id";
    static final String CHANNEL_NAME = "Notifikaatio kanava";
    static final int NOTIFICATION_ID = 123;



    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setSound(null, null);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.setVibrationPattern(new long[] { 500, 500, 500, 500 });
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.text_view_selected);


    }
    public void checkButton (View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
         radioButton = findViewById(radioId);
        textView.setText("Juomaväli: " + radioButton.getText());
        Toast.makeText(this, "Valittu juomaväli: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

    public void aloita(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        CharSequence text = radioButton.getText();
        String delay = (String) text.subSequence(0,2);
        int delayInMillis = Integer.parseInt(delay)*100;
        scheduleNotification(getNotification("Se auttaa jaksamaan."), delayInMillis);
        textView.setText("Juomaväli: " + radioButton.getText());

        Toast.makeText(this, "Ajastin käynnistetty!", Toast.LENGTH_SHORT).show();
    }


    public void lopeta(View view) {
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent(this, MyAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
        textView.setText("Ajastin pysäytetty.");
        Toast.makeText(this, "Hyvää päivänjatkoa!", Toast.LENGTH_SHORT).show();
    }


    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, MyAlarm.class);
        notificationIntent.putExtra(MyAlarm.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(MyAlarm.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        long timeNow = SystemClock.elapsedRealtime();

        alarmManager.setRepeating(alarmManager.RTC, timeNow, delay, pendingIntent);

    }

    private Notification getNotification(String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle("Juo vettä!");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.timericon);
        return builder.build();
    }


}
