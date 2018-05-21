package com.yaopaine.androidart.chapter1

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.yaopaine.androidart.R
import kotlinx.android.synthetic.main.activity_flag.*

class FlagActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flag)
        Log.e("FlagActivity", "onCreate")
        button21.setOnClickListener {
            val intent = Intent(this@FlagActivity, Chapter1LaunchActivity::class.java)
            startActivity(intent)
        }

        button22.setOnClickListener {
            val intent = Intent()
            intent.action = "action"
            intent.addCategory("category1")
            intent.addCategory("category2")
            intent.data

        }
    }

    override fun onStop() {
        super.onStop()
        Log.e("FlagActivity", "onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.e("FlagActivity", "onPause")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("FlagActivity", "onNewIntent: " + intent?.toString())
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("FlagActivity", "onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.e("FlagActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("FlagActivity", "onResume")
    }
}
