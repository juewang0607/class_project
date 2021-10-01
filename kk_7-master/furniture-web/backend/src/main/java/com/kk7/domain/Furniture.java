package com.kk7.domain;

import javax.persistence.*;


@Table(name = "furnitures")
@Entity
public class Furniture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int quantity;
    private String img_url;
    private String description;
    private double price;

    public Furniture() {};

    public Furniture(String name) {
        this.name = name;
        this.description = "";
        this.img_url = "";
        this.quantity = 1;
        this.price = 0;
    }

    public Furniture(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.quantity = 1;
        this.img_url = "";
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
