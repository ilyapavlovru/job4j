package ru.job4j.springdicomponent.springdi;

import org.springframework.stereotype.Component;

@Component
public class StartUI {

    private Store store;

    private ConsoleInput consoleInput;

    public StartUI(Store store, ConsoleInput consoleInput) {
        this.store = store;
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
