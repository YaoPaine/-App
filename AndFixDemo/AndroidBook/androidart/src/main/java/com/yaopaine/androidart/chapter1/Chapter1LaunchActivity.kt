package com.yaopaine.androidart.chapter1

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.yaopaine.androidart.IBookManager
import com.yaopaine.androidart.INewBookArrivalListener
import com.yaopaine.androidart.MainActivity
import com.yaopaine.androidart.R
import com.yaopaine.androidart.chapter2.Book
import com.yaopaine.androidart.chapter2.BookManagerService
import kotlinx.android.synthetic.main.activity_chapter1_launch.*
import java.lang.ref.WeakReference

class Chapter1LaunchActivity : AppCompatActivity() {

    private var conn: ServiceConnection = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.e(TAG, "onServiceDisconnected ${Thread.currentThread().name}")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val iBookManager: IBookManager = IBookManager.Stub.asInterface(service)
            service?.linkToDeath(mDeathRecipient, 0)
            Log.e(TAG, iBookManager.bookList.toString())
            iBookManager.addBook(Book(30030, "深入理解Android系统"))
            Log.e(TAG, iBookManager.bookList.toString())
        }
    }

    private val mDeathRecipient = IBinder.DeathRecipient {
        Log.e(TAG, "binderDied ${Thread.currentThread().name}")
    }

    private val MSG_BOOK_ARRIVAL = 100

    private var isBound: Boolean = false
    private var mRemoteService: IBookManager? = null

    private var connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            mRemoteService = null
            Log.e(TAG, "onServiceDisconnected ${Thread.currentThread().name}")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            isBound = true
            mRemoteService = IBookManager.Stub.asInterface(service)

            val bookList = mRemoteService?.bookList
            Log.e(TAG, "query book list, list type: ${bookList!!::class.java.canonicalName}")
            Log.e(TAG, "query book list:$bookList")

            val book = Book(1000, "深入理解Android系统")
            mRemoteService?.addBook(book)
            mRemoteService?.registerListener(mBookListener)
            Log.e(TAG, "query book list:$bookList")
        }
    }

    private var mBookListener: INewBookArrivalListener? = object : INewBookArrivalListener.Stub() {
        override fun onNewBookArrived(book: Book?) {
            Log.e(TAG, "current thread name: " + Thread.currentThread().name)
            val message = Message.obtain()
            message.what = MSG_BOOK_ARRIVAL
            message.obj = book
            mHandler.sendMessage(message)
        }
    }

    private val mHandler = BookHandler(this)

    private class BookHandler constructor(activity: Chapter1LaunchActivity) : Handler() {

        private var activityRef: WeakReference<Chapter1LaunchActivity> = WeakReference(activity)

        override fun handleMessage(msg: Message?) {
            val launchActivity = activityRef.get()
            if (launchActivity == null || launchActivity.isFinishing) {
                super.handleMessage(msg)
                return
            }
            when (msg?.what) {
                launchActivity.MSG_BOOK_ARRIVAL -> {
                    Log.e(TAG, "receive new book :${msg.obj}")
                }
                else -> {
                    super.handleMessage(msg)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter1_launch)

        button2.setOnClickListener {
            val intent = Intent(this@Chapter1LaunchActivity, Chapter1Launch2Activity::class.java)
            startActivity(intent)

            Log.e("Chapter1LaunchActivity", "sUserId:" + MainActivity.sUserId)
            MainActivity.sUserId = 100
        }

        button22.setOnClickListener {
            val intent = Intent(this@Chapter1LaunchActivity, BookManagerService::class.java)
            bindService(intent, conn, Context.BIND_AUTO_CREATE)
        }

        button23.setOnClickListener {
            val intent = Intent()
            intent.setClassName("com.yaopaine.andfix", "com.yaopaine.androidart.chapter2.BookCallBackService")
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onDestroy() {
        unbindService(conn)
        if (mRemoteService != null && mRemoteService?.asBinder()?.isBinderAlive!!) {
            mRemoteService?.unRegisterListener(mBookListener)
            mBookListener = null
        }
        if (isBound) {
            unbindService(connection)
        }
        super.onDestroy()
    }
}
