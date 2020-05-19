/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author USER
 */
public class Categories {

    private int id_category;
    private String label;

    public Categories() {
    }

    public Categories(int id_category, String label) {
        this.id_category = id_category;
        this.label = label;
    }

    public Categories(String label) {
        this.label = label;
    }

    public int getId_category() {
        return id_category;
    }

    public String getLabel() {
        return label;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Categories{" + "id_category=" + id_category + ", label=" + label + '}';
    }
    

}
