package vn.poly.ungdungnghenhacoffline;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import vn.poly.ungdungnghenhacoffline.service.NotificationActionService;


public class CreateNotification {
    public static final String CHANNEL_ID = "CHANNEL";
    public static final String ACTION_NEXT = "NEXT";
    public static final String ACTION_PREVIOUS = "PREVIOUS";
    public static final String ACTION_PLAY = "PLAY";

    public static Notification notification;
    public static int drw_play;


    public static void createNotification(Context context, Song song, int playButton, int pos, int size) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            //MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, "tag");
            PendingIntent pendingIntentPreviouse;
            //Notification.MediaStyle style = new Notification.MediaStyle();
            int drw_previous;
            Intent intentPrevious = new Intent(context, NotificationActionService.class)
                    .setAction(ACTION_PREVIOUS);
            pendingIntentPreviouse = PendingIntent.getBroadcast(context,
                    0, intentPrevious, PendingIntent.FLAG_UPDATE_CURRENT);
            drw_previous = R.drawable.previous;
            Bitmap icon = BitmapFactory.decodeResource(context.getResources(), song.getImgSong());

            Intent intentPlay = new Intent(context, NotificationActionService.class)
                    .setAction(ACTION_PLAY);
            PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(context, 0,
                    intentPlay, PendingIntent.FLAG_UPDATE_CURRENT);
            drw_play = R.drawable.play;

            PendingIntent pendingIntentNext;
            int drw_next;
            Intent intentNext = new Intent(context, NotificationActionService.class)
                    .setAction(ACTION_NEXT);
            pendingIntentNext = PendingIntent.getBroadcast(context,
                    0, intentNext, PendingIntent.FLAG_UPDATE_CURRENT);
            drw_next = R.drawable.next;

            Intent resultIntent = new Intent(context, ListNhacActivity.class);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.logo2)
                    .setContentTitle(song.getTenBaiHat())
                    .setContentText(song.getTenCaSy())
                    .setOnlyAlertOnce(true)
                    .setShowWhen(false)
                    .setContentIntent(resultPendingIntent)
                    .addAction(drw_previous, "Previous", pendingIntentPreviouse)
                    .addAction(drw_play, "Play", pendingIntentPlay)
                    .addAction(drw_next, "Next", pendingIntentNext)
                    .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                            .setShowActionsInCompactView(0, 1, 2))
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .build();
            notificationManagerCompat.notify(1, notification);


        }
    }
}

