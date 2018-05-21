package com.yaopaine.androidart.chapter2

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteCallbackList
import android.os.SystemClock
import android.util.Log
import com.yaopaine.androidart.IBookManager
import com.yaopaine.androidart.INewBookArrivalListener
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/21/18
 */
class BookCallBackService : Service() {

    private val TAG = "BookCallBackService"

    private val mIsServiceDestroyed: AtomicBoolean = AtomicBoolean(false)

    private val mBookList: CopyOnWriteArrayList<Book> = CopyOnWriteArrayList()

//    private val mListenerList: CopyOnWriteArrayList<INewBookArrivalListener> = CopyOnWriteArrayList()

    private val mListenerList: RemoteCallbackList<INewBookArrivalListener> = RemoteCallbackList()

    private val mBinder = object : IBookManager.Stub() {
        override fun getBookList(): MutableList<Book> {
//            SystemClock.sleep(10000)
            return mBookList
        }

        override fun addBook(book: Book?) {
            mBookList.add(book)
        }

        override fun registerListener(listener: INewBookArrivalListener?) {
            mListenerList.register(listener)
            val N = mListenerList.beginBroadcast()
            mListenerList.finishBroadcast()
            Log.e(TAG, "registerListener, after registerListener, size: $N")
//            if (!mListenerList.contains(listener)) {
//                mListenerList.add(listener)
//            } else {
//                Log.e(TAG, "已经存在了")
//            }
//
//            Log.e(TAG, "registerListener, registerListener, size" + mListenerList.size)
        }

        override fun unRegisterListener(listener: INewBookArrivalListener?) {
            mListenerList.unregister(listener)
            val N = mListenerList.beginBroadcast()
            mListenerList.finishBroadcast()
            Log.e(TAG, "unRegisterListener, registerListener, size: $N")
//            if (mListenerList.contains(listener)) {
//                mListenerList.remove(listener)
//            } else {
//                Log.e(TAG, "not found, can not unregister")
//            }
//            Log.e(TAG, "unRegisterListener, registerListener, size" + mListenerList.size)
        }

    }

    override fun onCreate() {
        super.onCreate()
        mBookList.add(Book(1, "数据结构和算法"))
        mBookList.add(Book(2, "计算机网络"))
        mBookList.add(Book(3, "操作系统"))

        val thread: Thread = object : Thread() {
            override fun run() {
                super.run()
                while (!mIsServiceDestroyed.get()) {
                    Thread.sleep(5000)
                    val bookId = mBookList.size + 1
                    onNewArrival(Book(bookId, "Android神功第 <$bookId> 层"))
                }
            }
        }
        thread.start()
    }

    fun onNewArrival(book: Book) {
        mBookList.add(book)
        val N = mListenerList.beginBroadcast()
        var i = 0
        while (i < N) {
            val listener = mListenerList.getBroadcastItem(i)
            listener.onNewBookArrived(book)
            i++
        }
        mListenerList.finishBroadcast()
//        for (listener in mListenerList) {
//            listener.onNewBookArrived(book)
//        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return mBinder
    }

    override fun onDestroy() {
        mIsServiceDestroyed.set(true)
        super.onDestroy()
    }
}
