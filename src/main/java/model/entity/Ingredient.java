package model.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ingredient")
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ingredientId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private IngredientTemplate ingredientTemplate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Dish dish;

    public Ingredient() {
        super();
    }

    public Ingredient(IngredientTemplate ingredientTemplate) {
        this.ingredientTemplate = ingredientTemplate;
    }

    public Ingredient(IngredientTemplate ingredientTemplate, Dish dish) {
        this.ingredientTemplate = ingredientTemplate;
        this.dish = dish;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public IngredientTemplate getIngredientTemplate() {
        return ingredientTemplate;
    }

    public void setIngredientTemplate(IngredientTemplate ingredientTemplate) {
        this.ingredientTemplate = ingredientTemplate;
    }
    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
