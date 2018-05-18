package com.yaopaine.androidart

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.yaopaine.androidart.chapter1.Chapter1LaunchActivity
import kotlinx.android.synthetic.main.activity_main_art.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG: String = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_art)

        button1.setOnClickListener {
            val intent = Intent(this@MainActivity, Chapter1LaunchActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e(MainActivity.TAG, "onNewIntent: " + intent?.extras.toString())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val timeMillis = System.currentTimeMillis()
        outState.putLong("time", timeMillis)
        Log.e(MainActivity.TAG, "onSaveInstanceState: timeMillis: $timeMillis")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val time = savedInstanceState.getLong("time")
        Log.e(MainActivity.TAG, "onRestoreInstanceState: time: $time")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.e(MainActivity.TAG, "onConfigurationChanged: $newConfig")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(MainActivity.TAG, "onRestart: ")
    }

    override fun onStart() {
        super.onStart()
        Log.e(MainActivity.TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.e(MainActivity.TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.e(MainActivity.TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.e(MainActivity.TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(MainActivity.TAG, "onDestroy: ")
    }
}
