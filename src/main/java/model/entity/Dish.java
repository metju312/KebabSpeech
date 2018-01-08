package model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dish")
public class Dish implements Serializable {
    private int dishId;
    private String name;
    private float price;
    private List<Sauce> sauces = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private List<Addition> additions = new ArrayList<>();
    private List<TypeOfMeal> typeOfMeals = new ArrayList<>();
    private List<TypeOfMeat> typeOfMeats = new ArrayList<>();

    public Dish() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
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

    @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    public List<Sauce> getSauces() {
        return sauces;
    }

    public void setSauces(List<Sauce> sauces) {
        this.sauces = sauces;
    }

    @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    public List<Addition> getAdditions() {
        return additions;
    }

    public void setAdditions(List<Addition> additions) {
        this.additions = additions;
    }

    @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    public List<TypeOfMeal> getTypeOfMeals() {
        return typeOfMeals;
    }

    public void setTypeOfMeals(List<TypeOfMeal> typeOfMeals) {
        this.typeOfMeals = typeOfMeals;
    }

    @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    public List<TypeOfMeat> getTypeOfMeats() {
        return typeOfMeats;
    }

    public void setTypeOfMeats(List<TypeOfMeat> typeOfMeats) {
        this.typeOfMeats = typeOfMeats;
    }
}
