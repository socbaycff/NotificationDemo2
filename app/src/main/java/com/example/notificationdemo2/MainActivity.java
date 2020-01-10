package com.example.notificationdemo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taoKenh();
    }

    public static String idKenhTinNhan = "kenhTinNhan";

    public void taoKenh() {


        NotificationChannel kenh_tin_nhan = new NotificationChannel(idKenhTinNhan, "Kenh tin nhan", NotificationManager.IMPORTANCE_HIGH);
        kenh_tin_nhan.setDescription("kenh thong bao lien quan tin nhan");
        kenh_tin_nhan.setShowBadge(true);


        NotificationManager systemService = getSystemService(NotificationManager.class);

        systemService.createNotificationChannel(kenh_tin_nhan);

    }


    public void normalNoti(View view) throws InterruptedException {

        // tao pending intent cho su kien setContentIntent
        Intent intent = new Intent(this, Main2Activity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        // tao intent phat broadcast
        Intent intentBroadcast = new Intent(this, MyReceiver.class);
        PendingIntent broadcastpend = PendingIntent.getBroadcast(getApplicationContext(), 0, intentBroadcast, 0);


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ltl_foreground); // lay bitmap tu png

        // khoi tao , tuy bien noti
        Notification noti = new NotificationCompat.Builder(getApplicationContext(), idKenhTinNhan)
                .setSmallIcon(R.drawable.ic_arrow_downward)
                .setContentTitle("Tua de thong bao")
                .setContentText("Noi dung thong bao")
                .setLargeIcon(bitmap)
                .addAction(R.drawable.ic_arrow_downward,"Chay Broadcast", broadcastpend)
                .setContentIntent(pendingIntent) // su kien mo acti khi cham noti
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                .setNumber(5)
                .setColor(Color.RED)
                .setProgress(100,50,false)
                .build();

        // chay noti
          NotificationManager systemService = getSystemService(NotificationManager.class);
          systemService.notify(0,noti);// id cho phep xoa, update noti

        Thread.sleep(2000);


        // khoi tao , tuy bien noti
        Notification noti2 = new NotificationCompat.Builder(getApplicationContext(), idKenhTinNhan)
                .setSmallIcon(R.drawable.ic_arrow_downward)
                .setContentTitle("Tua de thong bao")
                .setContentText("Noi dung thong bao")
                .setLargeIcon(bitmap)
                .addAction(R.drawable.ic_arrow_downward,"Chay Broadcast", broadcastpend)
                .setContentIntent(pendingIntent) // su kien mo acti khi cham noti
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                .setNumber(5)
                .setColor(Color.RED)
                .setProgress(100,70,false)
                .build();

        // chay noti

        systemService.notify(0,noti2);// id cho phep xoa, update noti
    }
}
