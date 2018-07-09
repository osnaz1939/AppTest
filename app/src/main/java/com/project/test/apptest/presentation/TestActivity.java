package com.project.test.apptest.presentation;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.project.test.apptest.R;
import com.project.test.apptest.data.NotificationModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {
    
    private TestPresenter mPresenter;
    private NotificationManager notificationManager;
    
    @BindView(R.id.subjectOfNotification)
    TextView notificationMessage;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = TestPresenter.getInstance();
        mPresenter.bindView(this);
        mPresenter.startInterval();
    }
    
    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        notificationMessage.setText(intent.getStringExtra("text"));
    }
    
    void updateNotification(List<NotificationModel> notificationsList) {
        Log.e("TEST", "TEST");
        notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        int i = 0;
        
        for (NotificationModel model : notificationsList) {
            i++;
            Notification builder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(android.R.drawable.ic_dialog_email)
                            .setContentTitle(model.getSubject())
                            .setContentText(model.getText())
                            .setAutoCancel(true)
                            .setContentIntent(createPendingIntent(model.getText(), i))
                            .getNotification();
            notificationManager.notify(i, builder);
        }
        
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        notificationManager.cancelAll();
    }
    
    private PendingIntent createPendingIntent(String message, int number) {
        Intent resultIntent = new Intent(this, TestActivity.class);
        resultIntent.putExtra("text", message);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent resultPendingIntent = PendingIntent
                .getActivity(this, number, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        return resultPendingIntent;
    }
}
