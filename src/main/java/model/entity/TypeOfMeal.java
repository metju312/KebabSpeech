package model.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "typeOfMeal")
public class TypeOfMeal implements Serializable {
    private int typeOfMealId;
    private String name;
    private float price;

    public TypeOfMeal() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getTypeOfMealId() {
        return typeOfMealId;
    }

    public void setTypeOfMealId(int typeOfMealId) {
        this.typeOfMealId = typeOfMealId;
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
