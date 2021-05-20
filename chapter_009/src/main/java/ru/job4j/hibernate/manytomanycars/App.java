package ru.job4j.hibernate.manytomanycars;

import org.hibernate.Session;
import ru.job4j.hibernate.manytomanyexample.HibernateUtil;

import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Driver driver = new Driver();
        driver.setDriverName("Schumacher");

        Car car1 = new Car("Ferrari");
        Car car2 = new Car("Mercedes");

        Set<Car> cars = new HashSet<Car>();
        cars.add(car1);
        cars.add(car2);

        driver.setCars(cars);
        session.save(driver);

        session.getTransaction().commit();
    }
}
