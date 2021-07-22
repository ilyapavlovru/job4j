package ru.job4j.springdicomponentprototype;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.springdicomponentprototype");
        context.refresh();

        StartUI ui = context.getBean(StartUI.class);
        ui.add("Petr Arsentev");
        ui.add("Ivan Ivanov");
        ui.addViaConsole();
        ui.print();

        StartUI anotherUI = context.getBean(StartUI.class);
        anotherUI.print();
    }
}
