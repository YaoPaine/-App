package com.yaopaine.javabase;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/14/18
 */
public class SynchronizedThread extends Thread {

    private final Object lock;
    private String name;

    protected SynchronizedThread(Object lock, String name) {
        super();
        this.lock = lock;
        this.name = name;
    }

    @Override
    public void run() {
        super.run();
        synchronized (this.lock) {
            while (true) {
                System.out.println(currentThread().getName() + ":\t" + name);
                try {
//                    lock.notifyAll();
                    lock.notify();
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
