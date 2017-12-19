package model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ingredient")
public class Ingredient implements Serializable{
    private int ingredientid;
    private String name;

    public Ingredient() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIngredientid() {
        return ingredientid;
    }

    public void setIngredientid(int ingredientid) {
        this.ingredientid = ingredientid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
