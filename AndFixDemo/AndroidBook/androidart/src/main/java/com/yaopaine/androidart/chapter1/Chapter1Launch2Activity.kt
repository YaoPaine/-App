package com.yaopaine.androidart.chapter1

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.yaopaine.androidart.MainActivity
import com.yaopaine.androidart.R
import kotlinx.android.synthetic.main.activity_chapter1_launch2.*

const val TAG = "Chapter1Launch2Activity"

class Chapter1Launch2Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter1_launch2)
        Log.e("Chapter1Launch2Activity", "sUserId:" + MainActivity.sUserId)
        button3.setOnClickListener {
            val intent = Intent(this@Chapter1Launch2Activity, MainActivity::class.java)
            startActivity(intent)
        }

        button4.setOnClickListener {
            val intent = Intent(this@Chapter1Launch2Activity, this::class.java)
            startActivity(intent)
        }

        button5.setOnClickListener {
            val intent = Intent(this@Chapter1Launch2Activity, FlagActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e(TAG, "onNewIntent: " + intent?.extras.toString())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val timeMillis = System.currentTimeMillis()
        outState.putLong("time", timeMillis)
        Log.e(TAG, "onSaveInstanceState: timeMillis: $timeMillis")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val time = savedInstanceState.getLong("time")
        Log.e(TAG, "onRestoreInstanceState: time: $time")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.e(TAG, "onConfigurationChanged: $newConfig")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart: ")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
    }
}
