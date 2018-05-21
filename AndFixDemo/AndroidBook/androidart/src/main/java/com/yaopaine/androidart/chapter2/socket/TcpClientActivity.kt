package com.yaopaine.androidart.chapter2.socket

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import com.yaopaine.androidart.R
import kotlinx.android.synthetic.main.activity_tcp_client.*
import java.io.*
import java.lang.ref.WeakReference
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.*

class TcpClientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tcp_client)
        mHandler = MessageHandler(this)
        startService(Intent(this@TcpClientActivity, TcpServerService::class.java))

        Thread {
            connectTcpServer()
        }.start()

        button_send.setOnClickListener {
            var trim = edit_text.text.toString().trim()
            if (!TextUtils.isEmpty(trim)) {
                Thread {
                    mPrintWriter.println(trim)
                }.start()

                edit_text.setText("")

                val showMsg = "self ${formatDate(System.currentTimeMillis())}: $trim \n"
                var text = textView.text.toString().trim()
                textView.text = "$text,$showMsg"
            }
        }
    }

    private lateinit var mClientSocket: Socket
    private lateinit var mPrintWriter: PrintWriter

    companion object {
        private const val MSG_FROM_CLIENT = 1
        private const val MSG_RECEIVE_NEW_MESSGE = 10

        class MessageHandler(activity: TcpClientActivity) : Handler() {

            private val activityRef = WeakReference<TcpClientActivity>(activity)

            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                when (msg?.what) {
                    MSG_FROM_CLIENT -> {
                        val act = activityRef.get()
                        //act?.textView?.text = "欢迎来到聊天界面！"
                        act?.button_send?.isEnabled = true
                    }

                    MSG_RECEIVE_NEW_MESSGE -> {
                        val act = activityRef.get()
                        if (act != null) {
                            val text = act.textView.text.toString().trim()
                            act.textView.text = "$text,${msg.obj}"
                        }
                    }
                }
            }
        }
    }

    private lateinit var mHandler: Handler

    private fun connectTcpServer() {
        var socket: Socket? = null
        while (socket == null) {
            try {
                socket = Socket("localhost", 8788)
                mClientSocket = socket
                mPrintWriter = PrintWriter(BufferedWriter(
                        OutputStreamWriter(
                                mClientSocket.getOutputStream())), true)

                mHandler.sendEmptyMessage(MSG_FROM_CLIENT)
                Log.e("TAG", "connect server success")
            } catch (e: Exception) {
                Log.e("TAG", "connect tcp server failed ,retry...")
                SystemClock.sleep(10000)
            }
        }

        val bufferReader = BufferedReader(InputStreamReader(socket.getInputStream()))
        while (!this.isFinishing) {
            try {
                val message = bufferReader.readLine()
                Log.e("TAG", "receive: $message")
                if (!TextUtils.isEmpty(message)) {
                    val showMsg = "server time: ${formatDate(System.currentTimeMillis())}, message: $message"
                    mHandler.obtainMessage(MSG_RECEIVE_NEW_MESSGE, showMsg).sendToTarget()
                }
            } catch (e: Exception) {
                Log.e("TAG", e.toString())
            }
        }
        mPrintWriter.close()
        bufferReader.close()
        socket.close()
    }

    override fun onDestroy() {
        mClientSocket.shutdownInput()
        mClientSocket.close()
        super.onDestroy()
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(time: Long): String {
        return SimpleDateFormat("(HH:mm:ss)").format(Date(time))
    }
}
