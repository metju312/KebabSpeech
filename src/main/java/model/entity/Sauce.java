package model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sauce")
public class Sauce implements Serializable {
    private int sauceId;
    private String name;

    public Sauce() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getSauceId() {
        return sauceId;
    }

    public void setSauceId(int sauceId) {
        this.sauceId = sauceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
