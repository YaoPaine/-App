package com.yaopaine.androidart

import android.content.Intent
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
}
