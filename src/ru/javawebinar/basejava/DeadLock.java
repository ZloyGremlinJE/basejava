package ru.javawebinar.basejava;

public class DeadLock {
    public static final Object Lock1 = new Object();
    public static final Object Lock2 = new Object();

    public static void main(String args[]) {
        Runnable threadFirst = new ThreadFirst();
        Runnable threadSecond = new ThreadSecond();

        Thread first = new Thread(threadFirst);
        Thread second = new Thread(threadSecond);

        first.start();
        second.start();
    }

    private static class ThreadFirst implements Runnable  {
        public void run() {
            synchronized (Lock1) {
                System.out.println("Thread 1: the lock 1...");

                try { Thread.sleep(5); }
                catch (InterruptedException e) {}

                System.out.println("Thread 1: try the lock 2...");

                synchronized (Lock2) {
                    System.out.println("Thread 1:the lock 1 & 2...");
                }
            }
        }
    }
    private static class ThreadSecond implements Runnable  {
        public void run() {
            synchronized (Lock2) {
                System.out.println("Thread 2: the lock 2...");
                try { Thread.sleep(20); }
                catch (InterruptedException e) {}

                System.out.println("Thread 2: try the lock 1...");

                synchronized (Lock1) {
                    System.out.println("Thread 2: the lock 1 & 2...");
                }
            }
        }
    }
}



