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
public class LigneDeCommandeProduit {
      private Integer idLigneDeCommande;
    private Integer idCommande;

    
    private double prixTotal;
    private String quantiteCommander;

    private int idProduit;
    private int id_user;

    private String image;


    public Integer getIdLigneDeCommande() {
        return idLigneDeCommande;
    }


    public void setIdLigneDeCommande(Integer idLigneDeCommande) {
        this.idLigneDeCommande = idLigneDeCommande;
    }

    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String getQuantiteCommander() {
        return quantiteCommander;
    }

    public void setQuantiteCommander(String quantiteCommander) {
        this.quantiteCommander = quantiteCommander;
    }


    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }









    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }





  

    
    
}
