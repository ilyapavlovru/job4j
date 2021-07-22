package ru.job4j.di;

import java.util.Scanner;

public class ConsoleInput {

    public String askStr(String question) {
        System.out.println(question);
        return new Scanner(System.in).nextLine();
    }
}
