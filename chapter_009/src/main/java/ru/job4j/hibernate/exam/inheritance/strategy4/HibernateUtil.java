package ru.job4j.hibernate.exam.inheritance.strategy4;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory getSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}
