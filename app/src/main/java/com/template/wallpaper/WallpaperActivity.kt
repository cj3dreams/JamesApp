package com.template.wallpaper

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.Toast
import com.template.R
import com.template.model.WallpaperModel
import com.template.notification.NotificationReceiver
import com.template.ui.screens.ApplyFragment
import com.template.ui.screens.HomeWallpaperFragment

class WallpaperActivity : AppCompatActivity() {
    private lateinit var sharedPrefFirstStart: SharedPreferences
    private var firstTimeTripleTouch = 0

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)

        sharedPrefFirstStart = getSharedPreferences("isFirstStart", MODE_PRIVATE)
        if (sharedPrefFirstStart.getBoolean("isFirstStart", true)) {
            sharedPrefFirstStart.edit().putBoolean("isFirstStart", false).apply()

            val currentSystemTime = System.currentTimeMillis()

            val intent = Intent(this, NotificationReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                this,
                200, intent, PendingIntent.FLAG_UPDATE_CURRENT
            )

            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManager.set(AlarmManager.RTC_WAKEUP, currentSystemTime + 60000, pendingIntent)
        }

        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.primary)))
        window.statusBarColor = resources.getColor(R.color.primary_dark)

        if (intent.hasExtra("notification")) {
            if (intent.getStringExtra("notification") != null) {
                val data = intent.getStringExtra("notification")!!
                intent.removeExtra("notification")
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.frgView, ApplyFragment.getWallpaperModel(
                            WallpaperModel(null, data)
                        )
                    )
                    .commit()
            }
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frgView, HomeWallpaperFragment())
                .commit()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.pointerCount == 3 && firstTimeTripleTouch == 0) {
            firstTimeTripleTouch++
            Toast.makeText(
                this, "After 1 second you will exit the application",
                Toast.LENGTH_SHORT
            ).show()
            (object : CountDownTimer(1000, 1000) {
                override fun onTick(p0: Long) {}
                override fun onFinish() {
                    this@WallpaperActivity.finish()
                    cancel()
                }
            }).start()
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}