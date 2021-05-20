package ru.job4j.hibernate.onetomanyautos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            CarModel one = CarModel.of("X5");
            session.save(one);

            CarModel two = CarModel.of("X6");
            session.save(two);

            CarModel three = CarModel.of("i8");
            session.save(three);

            CarModel four = CarModel.of("i3");
            session.save(four);

            CarModel five = CarModel.of("x3");
            session.save(five);

            CarBrand carBrand = CarBrand.of("BMW");
            carBrand.addCarModel(session.load(CarModel.class, 1));
            carBrand.addCarModel(session.load(CarModel.class, 2));
            carBrand.addCarModel(session.load(CarModel.class, 3));
            carBrand.addCarModel(session.load(CarModel.class, 4));
            carBrand.addCarModel(session.load(CarModel.class, 5));

            session.save(carBrand);

            session.getTransaction().commit();
            session.close();

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
