package com.template.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.template.R
import com.template.data.WallpapersData
import android.app.NotificationChannel
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager.TYPE_NOTIFICATION
import android.media.RingtoneManager.getDefaultUri
import android.os.Build
import com.template.wallpaper.WallpaperActivity

class NotificationReceiver: BroadcastReceiver() {
    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onReceive(context: Context?, intent: Intent?) {
        if(context != null) {
            val random = (0..WallpapersData.getAllWallpapers(context).size).random()
            val randomElement = WallpapersData.getAllWallpapers(context)[random]

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val intentByClick = Intent(context, WallpaperActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .putExtra("notification", randomElement.fileName)

            val pendingIntent = PendingIntent.getActivity(
                context, 200, intentByClick,
                PendingIntent.FLAG_UPDATE_CURRENT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannel = NotificationChannel(
                    "200",
                    "wallpapers",
                    NotificationManager.IMPORTANCE_DEFAULT)

                notificationChannel.description = "Wallpapers showing once"
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.RED
                notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
                notificationChannel.setSound(getDefaultUri(TYPE_NOTIFICATION),
                    AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build())
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)
            }

            val notification = NotificationCompat.Builder(context, "200")
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(context.resources.getString(R.string.app_name))
                .setContentTitle("Apply this wallpaper right now :)")
                .setLargeIcon(
                    BitmapFactory.decodeStream(
                        context.resources.assets
                            .open("wallpapers/${randomElement.fileName}")))
                .setAutoCancel(true)

            notificationManager.notify(200, notification.build())
        }
    }
}