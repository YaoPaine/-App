package com.yaopaine.androidart.chapter2

import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.yaopaine.androidart.IBookManager
import com.yaopaine.androidart.INewBookArrivalListener
import java.util.concurrent.CopyOnWriteArrayList

class BookManagerService : Service() {

    private val mBookList: CopyOnWriteArrayList<Book> = CopyOnWriteArrayList()
    private val mBinder: Binder = object : IBookManager.Stub() {
        override fun registerListener(listener: INewBookArrivalListener?) {
        }

        override fun unRegisterListener(listener: INewBookArrivalListener?) {
        }

        override fun getBookList(): MutableList<Book> {
//            var i: Long = 0
//            while (i < 1000000000000000000L) {
//                i++
//            }
            return mBookList
        }

        override fun addBook(book: Book?) {
            if (book != null) {
                mBookList.add(book)
            }
        }

    }

    override fun onCreate() {
        super.onCreate()
        Log.e("TAG", "onCreate")

        mBookList.add(Book(10010, "Android开发艺术探索"))
        mBookList.add(Book(20020, "Android源码情景分析"))
    }

    override fun onBind(intent: Intent): IBinder? {

        val permission = checkCallingOrSelfPermission("com.yaopaine.permission.ACCESS_BOOK_SERVICE")
        Log.e("TAG", "onBind ${permission == PackageManager.PERMISSION_GRANTED}")
        if (permission == PackageManager.PERMISSION_GRANTED) {
            return mBinder
        }

        return null
    }
}
