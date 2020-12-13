package com.dewaara.caavo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.dewaara.caavo.WelcomeActivity

class WelcomeActivity : AppCompatActivity(), AnimationListener {
    var imageView: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_welcome)
        imageView = findViewById<View>(R.id.imageView) as ImageView
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim)
        imageView!!.animation = animation
        animation.setAnimationListener(this)
        Handler().postDelayed({
            val homeIntent = Intent(this@WelcomeActivity, MainActivity::class.java)
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(homeIntent)
        }, SFLASH_TIME_OUT.toLong())
    }

    override fun onAnimationStart(animation: Animation) {
        //Toast.makeText(WelcomeActivity.this, "", Toast.LENGTH_SHORT).show();
    }

    override fun onAnimationEnd(animation: Animation) {
        // Toast.makeText(WelcomeActivity.this, "", Toast.LENGTH_SHORT).show();
    }

    override fun onAnimationRepeat(animation: Animation) {

        //Toast.makeText(WelcomeActivity.this, "", Toast.LENGTH_SHORT).show();
    }

    companion object {
        private const val SFLASH_TIME_OUT = 1500
    }
}