package com.yaopaine.javabase;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/14/18
 */
public class ThreadJoinTest {
    public static void main(String[] args) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start.");

        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2(thread1);
        try {
            /**
             * main start.
             Thread-0 start.
             Thread-0 loop at 0
             Thread-0 loop at 1
             main end!
             Thread-0 loop at 2
             Thread-1 start.
             Thread-0 loop at 3
             Thread-0 loop at 4
             Thread-0 end.
             Thread-1 end.
             */
            System.out.println("thread1.isAlive():\t" + thread1.isAlive());
            System.out.println("thread2.isAlive():\t" + thread2.isAlive());
            thread1.start();
            System.out.println("thread1.isAlive():\t" + thread1.isAlive());
            System.out.println("thread2.isAlive():\t" + thread2.isAlive());
            Thread.sleep(2000);
            thread2.start();
            System.out.println("thread1.isAlive():\t" + thread1.isAlive());
            System.out.println("thread2.isAlive():\t" + thread2.isAlive());
            thread2.join();
        } catch (Exception e) {

        }
        System.out.println(threadName + " end!");
        System.out.println("thread1.isAlive():\t" + thread1.isAlive());
        System.out.println("thread2.isAlive():\t" + thread2.isAlive());
    }

    static class Thread1 extends Thread {

        @Override
        public void run() {
            super.run();
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " start.");
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println(threadName + " loop at " + i);
                    sleep(1000);
                }
                System.out.println(threadName + " end.");
            } catch (Exception e) {

            }
        }

    }

    static class Thread2 extends Thread {

        private final Thread1 thread1;

        public Thread2(Thread1 thread1) {
            this.thread1 = thread1;
        }

        @Override
        public void run() {
            super.run();
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " start.");
            try {
                thread1.join();
                System.out.println(threadName + " end.");
            } catch (Exception e) {

            }
        }

    }
}
