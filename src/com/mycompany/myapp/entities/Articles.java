/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;


import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author USER
 */
public class Articles {
private int id_article;
private String name;
private double price;
private int quantity;
private String description;
private String label;
private String image;
private int id_category;
private int rating;
private Date reg_date;
private String magazin_id;

    public Articles(int id_article, String name, double price, int quantity, String description, String label, String image, int id_category, int rating, Date reg_date, String magazin_id) {
        this.id_article = id_article;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.label = label;
        this.image = image;
        this.id_category = id_category;
        this.rating = rating;
        this.reg_date = reg_date;
        this.magazin_id = magazin_id;
    }

    public Articles(String name, double price, int quantity, String description, String label, String image, int id_category, int rating, Date reg_date, String magazin_id) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.label = label;
        this.image = image;
        this.id_category = id_category;
        this.rating = rating;
        this.reg_date = reg_date;
        this.magazin_id = magazin_id;
    }

    public Articles(int id_article, String name, int quantity) {
        this.id_article = id_article;
        this.name = name;
        this.quantity = quantity;
    }

    
    public Articles(int quantity, int id_category, int rating, String name, String description, String label, String magazin_id, Date reg_date, double price) {
        this.quantity = quantity;
        this.id_category = id_category;
        this.rating = rating;
        this.name = name;
        this.description = description;
        this.label = label;
        this.magazin_id = magazin_id;
        this.reg_date = reg_date;
        this.price = price;
    }

    public Articles(int id_article, int quantity, int id_category, int rating, String name, String description, String label, String magazin_id, Date reg_date, double price) {
        this.id_article = id_article;
        this.quantity = quantity;
        this.id_category = id_category;
        this.rating = rating;
        this.name = name;
        this.description = description;
        this.label = label;
        this.magazin_id = magazin_id;
        this.reg_date = reg_date;
        this.price = price;
    }

    public Articles(int quantity, int rating, String name,String image, String description, String label, Date reg_date, double price) {
        this.quantity = quantity;
        this.rating = rating;
        this.name = name;
        this.image=image;
        this.description = description;
        this.label = label;
        this.reg_date = reg_date;
        this.price = price;
    }
    
    public Articles(){}
    
    public int getId_article() {
        return id_article;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getLabel() {
        return label;
    }

    public String getImage() {
        return image;
    }

    public int getId_category() {
        return id_category;
    }

    public int getRating() {
        return rating;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public String getMagazin_id() {
        return magazin_id;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public void setMagazin_id(String magazin_id) {
        this.magazin_id = magazin_id;
    }

    @Override
    public String toString() {
        return "Articles{" + "id_article=" + id_article + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", description=" + description + ", label=" + label + ", image=" + image + ", id_category=" + id_category + ", rating=" + rating + ", reg_date=" + reg_date + ", magazin_id=" + magazin_id + '}';
    }


}
