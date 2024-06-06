package com.vshpynta.concurrency;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ExecutorServicePlayground {


    private static <T> List<T> silentlyGetAll(List<Future<T>> futures) {
        return futures.stream()
                .map(ExecutorServicePlayground::silentlyGet)
                .collect(Collectors.toList());
    }

    private static <T> T silentlyGet(Future<T> future) {
        try {
            return future.get();
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            return null;
        }
    }

    public static void main(String[] args) {
        var executorService = Executors.newFixedThreadPool(10);
        Callable<Integer> task1 = () -> {
            try {
                Thread.sleep(3000L);
                System.out.println("Returning result 1");
                return 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Callable<Integer> task2 = () -> {
            throw new RuntimeException("Simulated");
        };
        Callable<Integer> task3 = () -> {
            Thread.sleep(1500);
            System.out.println("Returning result 3");
            return 3;
        };
//        var res1 = executorService.submit(task1);
//        var res2 = executorService.submit(task2);
//        System.out.println("res1: " + silentlyGet(res1));
//        System.out.println("res2: " + silentlyGet(res2));

        try {
            var futures = executorService.invokeAll(List.of(task1, task2, task3), 2, TimeUnit.SECONDS);
            System.out.println(silentlyGetAll(futures));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
    }
}
