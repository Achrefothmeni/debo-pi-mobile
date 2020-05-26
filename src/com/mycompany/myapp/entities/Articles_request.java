/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author USER
 */
public class Articles_request {
    private int id_article,quantity;
    private String name,description;
    private Date req_date;
    public Articles_request(int id_article, int quantity, String name, String description, Date req_date) {
        this.id_article = id_article;
        this.quantity = quantity;
        this.name = name;
        this.description = description;
        this.req_date = req_date;
    }

    public Articles_request(int quantity, String name, String description, Date req_date) {
        this.quantity = quantity;
        this.name = name;
        this.description = description;
        this.req_date = req_date;
    }

    public Articles_request() {
    }

    public int getId_article() {
        return id_article;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getReq_date() {
        return req_date;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReq_date(Date req_date) {
        this.req_date = req_date;
    }

    @Override
    public String toString() {
        return "Articles_request{" + "id_article=" + id_article + ", quantity=" + quantity + ", name=" + name + ", description=" + description + ", req_date=" + req_date + '}';
    }
    
}
