package model.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ingredient")
public class Ingredient implements Serializable {
    private int ingredientId;
    private IngredientTemplate ingredientTemplate;
    private Dish dish;

    public Ingredient() {
    }

    public Ingredient(IngredientTemplate ingredientTemplate) {
        this.ingredientTemplate = ingredientTemplate;
    }

    public Ingredient(IngredientTemplate ingredientTemplate, Dish dish) {
        this.ingredientTemplate = ingredientTemplate;
        this.dish = dish;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    public IngredientTemplate getIngredientTemplate() {
        return ingredientTemplate;
    }

    public void setIngredientTemplate(IngredientTemplate ingredientTemplate) {
        this.ingredientTemplate = ingredientTemplate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
