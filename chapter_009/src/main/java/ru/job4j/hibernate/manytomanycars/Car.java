package ru.job4j.hibernate.manytomanycars;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "car", catalog = "job4jhiberdrivers")
public class Car implements java.io.Serializable {

    private Integer carId;
    private String name;
    private Set<Driver> drivers = new HashSet<Driver>(0);

    private Engine engine;

    public Car() {
    }

    public Car(String name) {
        this.name = name;
    }

    public Car(String name, Set<Driver> drivers) {
        this.name = name;
        this.drivers = drivers;
    }

    public Car(String name, Engine engine) {
        this.name = name;
        this.engine = engine;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "CAR_ID", unique = true, nullable = false)
    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    @Column(name = "NAME", nullable = false, length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cars")
    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    @ManyToOne
    @JoinColumn(name = "ENGINE_ID", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
