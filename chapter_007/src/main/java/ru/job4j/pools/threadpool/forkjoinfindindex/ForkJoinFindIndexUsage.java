package ru.job4j.pools.threadpool.forkjoinfindindex;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinFindIndexUsage {
    public static void main(String[] args) {
        User[] users = {
                new User("Ilya", "ilya@mail.ru"),
                new User("Petr", "petr@mail.ru"),
                new User("Matvey", "matvey@mail.ru"),
                new User("Lena", "lena@mail.ru")};
        int searchIndex = 2;
        ForkJoinTask<Integer> task = new ForkJoinFindIndex<>(users, searchIndex, 0, users.length);
        System.out.println(new ForkJoinPool().invoke(task));
    }
}
