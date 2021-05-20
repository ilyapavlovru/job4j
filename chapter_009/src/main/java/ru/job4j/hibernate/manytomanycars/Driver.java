package ru.job4j.hibernate.manytomanycars;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "driver", catalog = "job4jhiberdrivers", uniqueConstraints = {
        @UniqueConstraint(columnNames = "DRIVER_NAME")})
public class Driver implements java.io.Serializable {
    private Integer driverId;
    private String driverName;
    private Set<Car> cars = new HashSet<Car>(0);

    public Driver() {
    }

    public Driver(String driverName) {
        this.driverName = driverName;
    }

    public Driver(String driverName, Set<Car> cars) {
        this.driverName = driverName;
        this.cars = cars;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "DRIVER_ID", unique = true, nullable = false)
    public Integer getDriverId() {
        return this.driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    @Column(name = "DRIVER_NAME", unique = true, nullable = false, length = 20)
    public String getDriverName() {
        return this.driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner", catalog = "job4jhiberdrivers", joinColumns = {
            @JoinColumn(name = "DRIVER_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "CAR_ID",
                    nullable = false, updatable = false)})
    public Set<Car> getCars() {
        return this.cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
