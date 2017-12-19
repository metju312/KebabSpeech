package model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dish")
public class Dish implements Serializable {
    private int dishid;
    private String name;

    public Dish() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getDishid() {
        return dishid;
    }

    public void setDishid(int dishid) {
        this.dishid = dishid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
