package model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "meat")
public class Meat implements Serializable {
    private int meatid;
    private String name;
    private int price;

    public Meat() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getMeatid() {
        return meatid;
    }

    public void setMeatid(int meatid) {
        this.meatid = meatid;
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
