package model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dish")
public class Dish implements Serializable {
    private int dishId;
    private DishTemplate dishTemplate;
    private List<Ingredient> ingredients = new ArrayList<>();

    public Dish() {
    }

    public Dish(DishTemplate dishTemplate) {
        this.dishTemplate = dishTemplate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    public DishTemplate getDishTemplate() {
        return dishTemplate;
    }

    public void setDishTemplate(DishTemplate dishTemplate) {
        this.dishTemplate = dishTemplate;
    }

    @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
