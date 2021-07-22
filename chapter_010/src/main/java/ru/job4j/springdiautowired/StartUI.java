package ru.job4j.springdiautowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartUI {

    @Autowired
    private Store store;

    @Autowired
    private ConsoleInput consoleInput;

    public void add(String value) {
        store.add(value);
    }

    public void addViaConsole() {
        String answer = consoleInput.askStr("Enter last name and first name:");
        store.add(answer);
    }

    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }
}
