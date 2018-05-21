package com.yaopaine.androidart.chapter2.socket

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.io.*
import java.net.ServerSocket
import java.net.Socket
import java.util.*

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/21/18
 */
class TcpServerService : Service() {

    private var mIsServerDestroyed = false
    private val mDefinedMessage = arrayOf("你好啊！哈哈哈",
            "请问你叫什么名字呀?",
            "今天天气还真的不错哟！",
            "你知道吗？我是可以多人聊天的",
            "给你讲个笑话吧，据说爱笑的人运气不会太差，不知道真的假的")

    override fun onCreate() {
        super.onCreate()
        Thread {
            val serverSocket = ServerSocket(8788)

            while (!mIsServerDestroyed) {
                val client = serverSocket.accept()
                Log.e("TAG", "accept客户端连接成功")
                Thread {
                    responseClient(client)
                }.start()
            }
        }.start()
    }

    private fun responseClient(client: Socket) {
        val bufferReader = BufferedReader(InputStreamReader(client.getInputStream()))

        val printWriter = PrintWriter(BufferedWriter(OutputStreamWriter(client.getOutputStream())))
        printWriter.println("欢迎来到聊天室！")
        while (!mIsServerDestroyed) {
            val readLine = bufferReader.readLine()
            Log.e("TAG", "msg from client:$readLine")
            if (readLine == null) {
                break
            }
            val i = Random().nextInt(mDefinedMessage.size)
            Log.e("TAG", "send: " + mDefinedMessage[i])
            printWriter.println("send: ${mDefinedMessage[i]}")
        }
        Log.e("TAG", "client quit.")
        printWriter.close()
        bufferReader.close()
        client.close()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        mIsServerDestroyed = true
        super.onDestroy()
    }
}