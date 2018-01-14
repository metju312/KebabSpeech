package model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dishTemplate")
public class DishTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int dishTemplateId;
    private String name;
    private float price;
    @OneToMany(mappedBy = "dishTemplate", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    private List<IngredientTemplate> ingredientTemplates = new ArrayList<>();
    @OneToMany(mappedBy = "dishTemplate", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Dish> dishes = new ArrayList<>();

    public DishTemplate() {
        super();
    }

    public DishTemplate(String name, float price) {
        this.name = name;
        this.price = price;
        this.ingredientTemplates = new ArrayList<>();
    }

    public DishTemplate(String name, float price, List<IngredientTemplate> ingredientTemplates) {
        this.name = name;
        this.price = price;
        this.ingredientTemplates = ingredientTemplates;
    }

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

    public List<IngredientTemplate> getIngredientTemplates() {
        return ingredientTemplates;
    }

    public void setIngredientTemplates(List<IngredientTemplate> ingredientTemplates) {
        this.ingredientTemplates = ingredientTemplates;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
