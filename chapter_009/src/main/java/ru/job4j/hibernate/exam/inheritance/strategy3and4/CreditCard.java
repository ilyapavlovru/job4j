package ru.job4j.hibernate.exam.inheritance.strategy3and4;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CC")
@SecondaryTable(name = "CREDIT_CARD",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "ID"))
public class CreditCard extends BillingDetails {

    @Column(table = "CREDIT_CARD", name = "card_number")
    private int cardNumber;

    @Column(table = "CREDIT_CARD", name = "exp_month")
    private String expMonth;

    @Column(table = "CREDIT_CARD", name = "exp_year")
    private String expYear;

    public CreditCard() {
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    @Override
    public String toString() {
        return "CreditCard{"
                + "cardNumber=" + cardNumber
                + ", expMonth='" + expMonth + '\''
                + ", expYear='" + expYear + '\''
                + '}';
    }
}
