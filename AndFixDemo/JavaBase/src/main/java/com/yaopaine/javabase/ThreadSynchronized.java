package com.yaopaine.javabase;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSynchronized {

    public static void main(String[] args) {
        //采用这种方式，两个进程竞争还是可以依次打印，但是三个进程就会出现紊乱
        /*Object lock = new Object();
        SynchronizedThread a = new SynchronizedThread(lock, "A");
        SynchronizedThread b = new SynchronizedThread(lock, "B");
        SynchronizedThread c = new SynchronizedThread(lock, "C");
        a.start();
        b.start();
        c.start();*/

        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();
        ReentrantLockThread thread1 = new ReentrantLockThread(lock, "A", condition1, condition2, condition3);
        ReentrantLockThread thread2 = new ReentrantLockThread(lock, "B", condition1, condition2, condition3);
        ReentrantLockThread thread3 = new ReentrantLockThread(lock, "C", condition1, condition2, condition3);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}



