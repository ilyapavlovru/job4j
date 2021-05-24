package ru.job4j.hibernate.hqlcandidate;

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

            // добавление кандидатов
            Candidate one = Candidate.of("Alex", 1, 100000);
            Candidate two = Candidate.of("Nikolay", 2, 180000);
            Candidate three = Candidate.of("Nikita", 3, 240000);

            session.save(one);
            session.save(two);
            session.save(three);

            // список всех кандидатов
            Query query = session.createQuery("from Candidate");
            for (Object st : ((org.hibernate.query.Query) query).list()) {
                System.out.println(st);
            }

            // поиск кандидата по id
            Query query2 = session.createQuery("from Candidate c where c.id = 1");
            System.out.println(((org.hibernate.query.Query) query2).uniqueResult());

            // поиск кандидата по имени
            Query query3 = session.createQuery("from Candidate c where c.name = 'Alex'");
            System.out.println(((org.hibernate.query.Query) query3).uniqueResult());

            // обновление записи кандидата
            Query query4 = session.createQuery(
                    "update Candidate c set c.experience = :newExperience, c.salary = :newSalary where c.id = :fId"
            );
            query4.setParameter("newExperience", 4);
            query4.setParameter("newSalary", 300000);
            query4.setParameter("fId", 1);
            query4.executeUpdate();

            // удаление записи кандидата
            session.createQuery("delete from Candidate where id = :fId")
                    .setParameter("fId", 3)
                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
