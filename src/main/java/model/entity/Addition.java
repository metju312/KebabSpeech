package model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "addition")
public class Addition implements Serializable {
    private int additionid;
    private String name;
    private int price;

    public Addition() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getAdditionid() {
        return additionid;
    }

    public void setAdditionid(int additionid) {
        this.additionid = additionid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
