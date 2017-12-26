package model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "addition")
public class Addition implements Serializable {
    private int additionId;
    private String name;
    private float price;

    public Addition() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getAdditionId() {
        return additionId;
    }

    public void setAdditionId(int additionId) {
        this.additionId = additionId;
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
}
