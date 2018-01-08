package model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sauce")
public class Sauce implements Serializable {
    private int sauceId;
    private String name;
    private List<Dish> dishList = new ArrayList<>();

    public Sauce() {
    }

    public Sauce(String name, List<Dish> dishList) {
        this.name = name;
        this.dishList = dishList;
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

    @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }
}
