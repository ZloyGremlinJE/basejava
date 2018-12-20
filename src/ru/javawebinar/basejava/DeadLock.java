package ru.javawebinar.basejava;

public class DeadLock {
    public static final TheLock Lock1 = new TheLock("Lock1");
    public static final TheLock Lock2 = new TheLock("Lock2");

    public static void main(String args[]) {
        Runnable threadFirst = new ThreadFirst();
        Runnable threadSecond = new ThreadSecond();

        Thread first = new Thread(threadFirst);
        first.setName("Thread1");
        Thread second = new Thread(threadSecond);
        second.setName("Thread2");

        first.start();
        second.start();
    }

    private static class TheLock{
        String name;
        public TheLock(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static class ThreadFirst implements Runnable {
        public void run() {
            getDeadLock(Lock1, Lock2);
        }
    }

    private static class ThreadSecond implements Runnable {
        public void run() {
            getDeadLock(Lock2, Lock1);
        }
    }

    protected static void sleepThread() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
        }
    }

    protected static void getDeadLock(Object firstLock, Object secondLock) {
        synchronized (firstLock) {
            System.out.println("thread: " + Thread.currentThread().getName()+", the lock: " + firstLock.toString());
            sleepThread();
            System.out.println("thread: " + Thread.currentThread().getName()+", try the lock: " + secondLock.toString());
            synchronized (secondLock) {
                System.out.println("thread: " + Thread.currentThread().getName()+",  the lock: " + firstLock.toString() + " "
                        + secondLock.toString());
            }
        }
    }
}



