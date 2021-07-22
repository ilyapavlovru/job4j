package ru.job4j.springdiautowiredset;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInput {

    public String askStr(String question) {
        System.out.println(question);
        return new Scanner(System.in).nextLine();
    }
}
