package com.example.vineet.myapplicationone;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.vineet.myapplicationone.Tab_Fragments.AllOrders_Fragment;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Vineet on 11/23/2016.
 */

public class Notificationpush extends FirebaseMessagingService {
    private static final String TAG = "PushMessageService";
    AllOrders_Fragment topitem ;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...
//        sendNotification(remoteMessage);
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("NEw-----", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("OK_____------", "Message data payload: " + remoteMessage.getData());
            String urlremote = remoteMessage.getData().get("picurl");
            try { URL url = new URL(urlremote);
                Bitmap bitimage = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                sendNotification(remoteMessage, bitimage);
            } catch (Exception e) {
                System.out.println(e);

            }

            Log.d("Url is ", " ------------"+urlremote);

        }

//        Check if message contains a notification payload.
       if (remoteMessage.getNotification() != null) {
            Log.d("Tag issssss ----------", "Message Notification Body: " + remoteMessage.getNotification().getBody());

       }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }





    private void sendNotification(RemoteMessage remoteMessage, Bitmap bitimage) {
        Intent intent = new Intent(this,Bob_main_activity.class );
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.mipmap.fangoord)
                .setContentTitle(remoteMessage.getData().get("title"))
                .setLargeIcon(bitimage)
                .setContentText(remoteMessage.getData().get("message"))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setPriority(Notification.PRIORITY_HIGH)
                .setStyle(new android.support.v4.app.NotificationCompat.BigTextStyle().bigText(remoteMessage.getData().get("subtitle")))
                .setStyle(new android.support.v4.app.NotificationCompat.BigPictureStyle().bigPicture(bitimage))
                .setContentIntent(pendingIntent);


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
       notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }



    public static Bitmap BitmapUrl (String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }


}

