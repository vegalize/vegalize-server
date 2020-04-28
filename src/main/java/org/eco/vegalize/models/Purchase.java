package org.eco.vegalize.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"category", "provider"})
    private List<Product> items;

    @ManyToOne(cascade = CascadeType.ALL)
    private User buyer;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> provider;

    @Column(columnDefinition = "Decimal(10,2) default '0.00'")
    private double amout;

    public Purchase() {
    }

    public double getAmout() {
        return amout;
    }

    public void setAmout(double amout) {
        this.amout = amout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public List<User> getProvider() {
        return provider;
    }

    public void setProvider(List<User> provider) {
        this.provider = provider;
    }
}
