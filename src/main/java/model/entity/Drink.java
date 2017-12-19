package model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "drink")
public class Drink implements Serializable {
    private int drinkid;
    private String name;
    private int price;

    public Drink() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getDrinkid() {
        return drinkid;
    }

    public void setDrinkid(int drinkid) {
        this.drinkid = drinkid;
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

