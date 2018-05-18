package com.yaopaine.androidart.chapter1

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yaopaine.androidart.R
import kotlinx.android.synthetic.main.activity_chapter1_launch.*

class Chapter1LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter1_launch)

        button2.setOnClickListener {
            val intent = Intent(this@Chapter1LaunchActivity, Chapter1Launch2Activity::class.java)
            startActivity(intent)
        }
    }
}
