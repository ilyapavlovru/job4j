package ru.job4j.hibernate.exam.inheritance.strategy1;

import javax.persistence.*;

@MappedSuperclass
public class BillingDetails {

    private String owner;

    public BillingDetails() {
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "BillingDetails{"
                + "owner='" + owner + '\''
                + '}';
    }
}
