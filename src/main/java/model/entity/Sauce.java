package model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sauce")
public class Sauce implements Serializable {
    private int sauceid;
    private String name;

    public Sauce() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getSauceid() {
        return sauceid;
    }

    public void setSauceid(int sauceid) {
        this.sauceid = sauceid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
