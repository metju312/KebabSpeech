package model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ingredientTemplate")
public class IngredientTemplate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ingredientTemplateId;
    private String name;
    private float price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private DishTemplate dishTemplate;
    @OneToMany(mappedBy = "ingredientTemplate", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Ingredient> ingredients = new ArrayList<>();

    public IngredientTemplate() {
        super();
    }

    public IngredientTemplate(DishTemplate dishTemplate) {
        this.dishTemplate = dishTemplate;
    }

    public IngredientTemplate(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public IngredientTemplate(String name, float price, DishTemplate dishTemplate) {
        this.name = name;
        this.price = price;
        this.dishTemplate = dishTemplate;
    }

    public IngredientTemplate(DishTemplate dishTemplate, List<Ingredient> ingredients) {
        this.dishTemplate = dishTemplate;
        this.ingredients = ingredients;
    }

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

    public DishTemplate getDishTemplate() {
        return dishTemplate;
    }

    public void setDishTemplate(DishTemplate dishTemplate) {
        this.dishTemplate = dishTemplate;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
