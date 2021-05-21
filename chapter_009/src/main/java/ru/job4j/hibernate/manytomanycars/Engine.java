package ru.job4j.hibernate.manytomanycars;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "engine", catalog = "job4jhiberdrivers")
public class Engine implements java.io.Serializable {

    private Integer engineId;
    private String name;

    public Engine() {
    }

    public Engine(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ENGINE_ID", unique = true, nullable = false)
    public Integer getEngineId() {
        return engineId;
    }

    public void setEngineId(Integer engineId) {
        this.engineId = engineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Engine engine = (Engine) o;
        return Objects.equals(engineId, engine.engineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engineId);
    }
}
