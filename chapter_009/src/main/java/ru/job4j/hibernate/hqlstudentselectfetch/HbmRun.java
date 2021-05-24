package ru.job4j.hibernate.hqlstudentselectfetch;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            /*
            Book bookOne = Book.of("Двенадцать стульев", "АСТ");
            session.save(bookOne);
            Book bookTwo = Book.of("Одноэтажня Америка", "Текст");
            session.save(bookTwo);

            Account account = Account.of("root");
            account.addBook(session.load(Book.class, 1));
            account.addBook(session.load(Book.class, 2));
            session.save(account);

            Student one = Student.of("Иванов Иван", 20, "Москва");
            one.setAccount(account);
            session.save(one);

             */

            /*
            Query query = session.createQuery("from Account");
            for (Object st : ((org.hibernate.query.Query) query).list()) {
                System.out.println(st);
            }
            */

            Query query = session.createQuery(
                    "select distinct st from Student st "
                            + "join fetch st.account a "
                            + "join fetch a.books b "
                            + "where st.id = :sId", Student.class
            ).setParameter("sId", 1);
            System.out.println(((org.hibernate.query.Query) query).uniqueResult());


            /*
            Query query = session.createQuery(
                    "select s from Student s where s.id = :sId", Student.class)
                    .setParameter("sId", 1);
            System.out.println(((org.hibernate.query.Query) query).uniqueResult());

             */

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
