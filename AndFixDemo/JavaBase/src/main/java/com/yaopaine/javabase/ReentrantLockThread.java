package com.yaopaine.javabase;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/14/18
 */
public class ReentrantLockThread extends Thread {

    private final ReentrantLock lock;
    private String name;
    private Condition condition1;
    private Condition condition2;
    private Condition condition3;

    public ReentrantLockThread(ReentrantLock lock, String name, Condition... args) {
        super();
        this.lock = lock;
        this.name = name;
        if (args != null) {
            this.condition1 = args[0];
            this.condition2 = args[1];
            this.condition3 = args[2];
        }
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                lock.lock();
                System.out.println(currentThread().getName() + ":\t" + name);
                if ("A".equals(name)) {
                    condition2.signal();
                    condition1.await();
                } else if ("B".equals(name)) {
                    condition3.signal();
                    condition2.await();
                } else if ("C".equals(name)) {
                    condition1.signal();
                    condition3.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


}
