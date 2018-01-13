package model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ingredientTemplate")
public class IngredientTemplate implements Serializable {
    private int ingredientTemplateId;
    private String name;
    private float price;
    private DishTemplate dishTemplate;
    private List<Ingredient> ingredients = new ArrayList<>();

    public IngredientTemplate() {
    }

    public IngredientTemplate(DishTemplate dishTemplate) {
        this.dishTemplate = dishTemplate;
    }

    public IngredientTemplate(DishTemplate dishTemplate, List<Ingredient> ingredients) {
        this.dishTemplate = dishTemplate;
        this.ingredients = ingredients;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIngredientTemplateId() {
        return ingredientTemplateId;
    }

    public void setIngredientTemplateId(int ingredientTemplateId) {
        this.ingredientTemplateId = ingredientTemplateId;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    public DishTemplate getDishTemplate() {
        return dishTemplate;
    }

    public void setDishTemplate(DishTemplate dishTemplate) {
        this.dishTemplate = dishTemplate;
    }

    @OneToMany(mappedBy = "ingredientTemplate", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
