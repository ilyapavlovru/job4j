/**
 * Future and FutureTask in java
 * https://www.geeksforgeeks.org/future-and-futuretask-in-java/?ref=rp
 *
 * Java program do two Future task
 * using Runnable Interface
 */
package ru.job4j.pools.threadpool;

import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class MyRunnable implements Runnable {

    private final long waitTime;

    public MyRunnable(int timeInMillis) {
        this.waitTime = timeInMillis;
    }

    @Override
    public void run() {
        System.out.printf("%s задача started... \n", Thread.currentThread().getName());
        try {
            // sleep for user given millisecond
            // before checking again
                Thread.sleep(waitTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(MyRunnable.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        System.out.printf("%s задача finished... Execution time is %s ms\n", Thread.currentThread().getName(), waitTime);
    }
}

public class FutureTaskExample {

    public static void main(String[] args) {
        // create two object of MyRunnable class
        // for FutureTask and sleep 1000, 2000
        // millisecond before checking again
        MyRunnable myRunnableObject1 = new MyRunnable(1000);
        MyRunnable myRunnableObject2 = new MyRunnable(2000);

        FutureTask<String> futureTask1 = new FutureTask<>(myRunnableObject1, "FutureTask1 is complete");
        FutureTask<String> futureTask2 = new FutureTask<>(myRunnableObject2, "FutureTask2 is complete");

        // create thread pool of 2 size for Executor Service
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // submit futureTask1 to ExecutorService
        executor.submit(futureTask1);

        // submit futureTask2 to ExecutorService
        executor.submit(futureTask2);

        while (true) {
            try {

                // if both future task complete
                if (futureTask1.isDone() && futureTask2.isDone()) {
                    System.out.println("Both FutureTask Complete");

                    executor.shutdown();
                    return;
                }

                if (!futureTask1.isDone()) {

                    // wait indefinitely for future task to complete
                    System.out.println("FutureTask1 output = " + futureTask1.get());
                }

                System.out.println("Waiting for FutureTask2 to complete");

                // wait if necessary for the computation to complete,
                // and then retrieves its result
                String s = futureTask2.get(250, TimeUnit.MILLISECONDS);

                if (s != null) {
                    System.out.println("FutureTask2 output = " + s);
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
