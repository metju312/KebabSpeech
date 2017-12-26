package model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "meat")
public class TypeOfMeat implements Serializable {
    private int typeOfMeatId;
    private String name;
    private float price;

    public TypeOfMeat() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getTypeOfMeatId() {
        return typeOfMeatId;
    }

    public void setTypeOfMeatId(int typeOfMeatId) {
        this.typeOfMeatId = typeOfMeatId;
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
