package ru.job4j.springdiautowiredset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartUI {

    private Store store;

    private ConsoleInput consoleInput;

    @Autowired
    public void setStore(Store store) {
        this.store = store;
    }

    @Autowired
    public void setConsoleInput(ConsoleInput consoleInput) {
        this.consoleInput = consoleInput;
    }

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
