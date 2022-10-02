package com.template.wallpaper

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.template.R
import com.template.ui.screens.HomeWallpaperFragment

class WallpaperActivity : AppCompatActivity() {
    private var firstTimeTripleTouch = 0

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.primary)))
        window.statusBarColor = resources.getColor(R.color.primary_dark)

        supportFragmentManager.beginTransaction()
                .replace(R.id.frgView, HomeWallpaperFragment())
                .commit()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.pointerCount == 3 && firstTimeTripleTouch == 0) {
            firstTimeTripleTouch++
            Toast.makeText(
                this,
                "After 1 second the application will be closed",
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