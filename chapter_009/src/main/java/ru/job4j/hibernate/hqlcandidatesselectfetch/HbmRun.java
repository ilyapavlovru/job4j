package ru.job4j.hibernate.hqlcandidatesselectfetch;

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

            Vacancy vacancyOne = Vacancy.of("Вакансия 1", "Epam");
            session.save(vacancyOne);
            Vacancy vacancyTwo = Vacancy.of("Вакансия 2", "Luxsoft");
            session.save(vacancyTwo);

            VacancyBase vacancyBase = VacancyBase.of("vacancyBase");
            vacancyBase.addVacancy(session.load(Vacancy.class, 1));
            vacancyBase.addVacancy(session.load(Vacancy.class, 2));
            session.save(vacancyBase);

            Candidate one = Candidate.of("Иванов Иван", 1, 100000);
            one.setVacancyBase(vacancyBase);
            session.save(one);

            Query query = session.createQuery(
                    "select distinct can from Candidate can "
                            + "join fetch can.vacancyBase vb "
                            + "join fetch vb.vacancies v "
                            + "where can.id = :sId", Candidate.class
            ).setParameter("sId", 1);
            System.out.println(((org.hibernate.query.Query) query).uniqueResult());

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
