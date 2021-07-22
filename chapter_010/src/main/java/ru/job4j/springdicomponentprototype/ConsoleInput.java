package ru.job4j.springdicomponentprototype;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Scope("prototype")
public class ConsoleInput {

    public String askStr(String question) {
        System.out.println(question);
        return new Scanner(System.in).nextLine();
    }
}
