package ru.job4j.hibernate.exam.inheritance.strategy2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(44411111);
        creditCard.setExpMonth("Jan");
        creditCard.setExpYear("2017");
        creditCard.setOwner("Bill Gates");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccount(111222333);
        bankAccount.setBankName("Goldman Sachs");
        bankAccount.setSwift("GOLDUS33");
        bankAccount.setOwner("Donald Trump");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction  = session.beginTransaction();
            session.persist(creditCard);
            session.persist(bankAccount);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }

        Session session1;
        Transaction transaction1 = null;
        try {
            session1 = sessionFactory.getCurrentSession();
            transaction1  = session1.beginTransaction();
            List billingDetails = session1.createQuery(
                    "select bd from ru.job4j.hibernate.exam.inheritance.strategy2.BillingDetails bd").list();
            for (int i = 0; i < billingDetails.size(); i++) {
                System.out.println(billingDetails.get(i));
            }
        } catch (Exception e) {
            transaction1.rollback();
            throw e;
        }


    }
}
