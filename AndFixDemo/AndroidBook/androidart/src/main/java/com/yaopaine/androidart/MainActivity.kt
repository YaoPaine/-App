package com.yaopaine.androidart

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.res.Configuration
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.yaopaine.androidart.chapter1.Chapter1LaunchActivity
import com.yaopaine.androidart.chapter1.FlagActivity
import com.yaopaine.androidart.chapter2.MessageService
import kotlinx.android.synthetic.main.activity_main_art.*

class MainActivity : AppCompatActivity() {


    companion object {
        const val MSG_FROM_CLIENT = 999
        const val TAG: String = "MainActivity"
        var sUserId: Int = 1

        class ClientHandler : Handler() {
            override fun handleMessage(msg: Message?) {
                when (msg?.what) {
                    MessageService.MSG_FROM_SERVICE -> {
                        var bundle = msg.data
                        Log.e(TAG, bundle.getString("msg_client"))
                    }
                }
                super.handleMessage(msg)
            }
        }
    }

    private lateinit var mServer: Messenger

    private val conn: ServiceConnection = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.e(TAG, "onServiceDisconnected")

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.e(TAG, "onServiceConnected")
            mServer = Messenger(service)
            val message = Message.obtain(null, MSG_FROM_CLIENT)
            val bundle = Bundle()
            bundle.putString("msg_server", "这台IPhone X送给我可以吗")
            message.data = bundle

            message.replyTo = mClient

            mServer.send(message)
        }
    }

    private lateinit var mClient: Messenger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_art)

        mClient = Messenger(ClientHandler())

        button1.setOnClickListener {
            val intent = Intent(this@MainActivity, Chapter1LaunchActivity::class.java)
            startActivity(intent)
        }

        button11.setOnClickListener {
            val intent = Intent(this@MainActivity, FlagActivity::class.java)
            startActivity(intent)
        }

        MainActivity.sUserId = 10
        Log.e("MainActivity", "sUserId:" + MainActivity.sUserId)

        button13.setOnClickListener {
            val intent = Intent(this@MainActivity, MessageService::class.java)
            bindService(intent, conn, Context.BIND_AUTO_CREATE)
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
        unbindService(conn)
        Log.e(MainActivity.TAG, "onDestroy: ")
    }
}
