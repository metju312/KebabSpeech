package model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "drink")
public class Drink implements Serializable {
    private int drinkId;
    private String name;
    private float price;

    public Drink() {
    }

    public Drink(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

