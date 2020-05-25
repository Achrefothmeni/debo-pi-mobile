/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author nouri
 */
public class Magazin {
    String idMagazin;
    int capacity;
    String location;
    String Category;
    int capacityRest ;
    User magazinier;

    public Magazin() {
        
    }

    public Magazin(String idMagazin, int capacity, String location, String Category,int capacityRest) {
        this.idMagazin = idMagazin;
        this.capacity = capacity;
        this.location = location;
        this.Category = Category;
        this.capacityRest = capacityRest;
    }

    public String getIdMagazin() {
        return idMagazin;
    }

    public User getMagazinier() {
        return magazinier;
    }

    public void setMagazinier(User magazinier) {
        this.magazinier = magazinier;
    }

    public void setIdMagazin(String idMagazin) {
        this.idMagazin = idMagazin;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public int getCapacityRest() {
        return capacityRest;
    }

    public void setCapacityRest(int capacityRest) {
        this.capacityRest = capacityRest;
    }

    @Override
    public String toString() {
        return "Magazin{" + "idMagazin=" + idMagazin + ", capacity=" + capacity + ", location=" + location + ", Category=" + Category + ", capacityRest=" + capacityRest + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.idMagazin.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Magazin other = (Magazin) obj;
        return !(this.idMagazin == null ? other.idMagazin != null : !this.idMagazin.equals(other.idMagazin));
    }
    
    
    
}
