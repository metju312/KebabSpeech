package model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dishTemplate")
public class DishTemplate {
    private int dishTemplateId;
    private String name;
    private float price;
    private List<IngredientTemplate> ingredientTemplates = new ArrayList<>();
    private List<Dish> dishes = new ArrayList<>();

    public DishTemplate() {
    }

    public DishTemplate(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public DishTemplate(String name, float price, List<IngredientTemplate> ingredientTemplates) {
        this.name = name;
        this.price = price;
        this.ingredientTemplates = ingredientTemplates;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getDishTemplateId() {
        return dishTemplateId;
    }

    public void setDishTemplateId(int dishTemplateId) {
        this.dishTemplateId = dishTemplateId;
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

    @OneToMany(mappedBy = "dishTemplate", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    public List<IngredientTemplate> getIngredientTemplates() {
        return ingredientTemplates;
    }

    public void setIngredientTemplates(List<IngredientTemplate> ingredientTemplates) {
        this.ingredientTemplates = ingredientTemplates;
    }

    @OneToMany(mappedBy = "dishTemplate", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
