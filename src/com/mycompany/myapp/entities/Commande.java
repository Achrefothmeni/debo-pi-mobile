/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author hatem
 */
public class Commande {
    private Integer id;
    
    private String dateCommande;
    
    private String dateMax;
    private String addresse;
    private Integer idUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getDateMax() {
        return dateMax;
    }

    public void setDateMax(String dateMax) {
        this.dateMax = dateMax;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Commande(Integer id, String dateCommande, String dateMax,String addresse) {
        this.id = id;
        this.dateCommande = dateCommande;
        this.dateMax = dateMax;
        this.addresse = addresse;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public Commande(Integer id) {
        this.id = id;
    }
    
    public Commande() {
       
    }
     public Commande(String addresse) {
       this.addresse = addresse;
    }
    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", Date Commande=" + dateCommande + ", Date Max=" + dateMax + '}';
    }
}
