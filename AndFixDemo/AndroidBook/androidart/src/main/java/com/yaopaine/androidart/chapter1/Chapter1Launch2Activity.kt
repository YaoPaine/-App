package com.yaopaine.androidart.chapter1

import android.content.Intent
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

        button3.setOnClickListener {
            val intent = Intent(this@Chapter1Launch2Activity, MainActivity::class.java)
            startActivity(intent)
        }

        button4.setOnClickListener {
            val intent = Intent(this@Chapter1Launch2Activity, this::class.java)
            startActivity(intent)
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e(TAG, "onNewIntent: " + intent?.extras.toString())
    }
}
