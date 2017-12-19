package model.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "meal")
public class Meal implements Serializable {
    private int mealid;
    private String name;
    private int price;

    public Meal() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getMealid() {
        return mealid;
    }

    public void setMealid(int mealid) {
        this.mealid = mealid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
