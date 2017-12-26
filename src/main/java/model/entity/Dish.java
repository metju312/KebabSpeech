package model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dish")
public class Dish implements Serializable {
    private int dishId;
    private String name;
    private Sauce sauce;

    public Dish() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    public Sauce getSauce() {
        return sauce;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }
}
