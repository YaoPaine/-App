package com.yaopaine.androidart.chapter2

import android.os.Binder
import android.os.IBinder
import android.os.IInterface

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/19/18
 */
interface IJobManager : IInterface {

    companion object {
        abstract class Stub : Binder(), IJobManager {

            val DESCRIPTOR: String = "com.yaopaine.androidart.chapter2.IJobManager"

            init {
                this.attachInterface(this@Stub, DESCRIPTOR)
            }

            override fun asBinder(): IBinder {
                return this@Stub
            }
        }
    }

    fun getJobList(): List<Job>

    fun offerJob(job: Job)

}