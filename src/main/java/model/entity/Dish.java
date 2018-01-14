package model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dish")
public class Dish implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int dishId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private DishTemplate dishTemplate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Invoice invoice;

    @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Ingredient> ingredients = new ArrayList<>();

    public Dish() {
        super();
    }

    public Dish(DishTemplate dishTemplate) {
        this.dishTemplate = dishTemplate;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public DishTemplate getDishTemplate() {
        return dishTemplate;
    }

    public void setDishTemplate(DishTemplate dishTemplate) {
        this.dishTemplate = dishTemplate;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
