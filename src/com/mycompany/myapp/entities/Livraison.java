/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;



/**
 *
 * @author syrina
 */
public class Livraison {
    private int id;
    private String statut;
    private String dateDepart;
    private String dateArrivee;
    private int livreurdId;
    private String flotte;
    //private ArrayList <Commande> listecommandes;

  



    public Livraison(int id, String dateDepart, String dateArrivee, String status) {
        this.id = id;
        this.dateDepart = dateDepart;
        this.livreurdId = livreurdId;
        this.statut = status;
    }
    

    

    public Livraison() {
      
       
    }
   

    public int getId() {
        return id;
    }


    public String getStatut() {
        return statut;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public String getDateArrivee() {
        return dateArrivee;
    }

    public int getLivreurdId() {
        return livreurdId;
    }

    public String getFlotte() {
        return flotte;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public void setDateArrivee(String dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public void setLivreurdId(int livreurdId) {
        this.livreurdId = livreurdId;
    }

    public void setFlotte(String flotte) {
        this.flotte = flotte;
    }


    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", statut=" + statut + ", dateDepart=" + dateDepart + ", dateArrivee=" + dateArrivee + ", livreurdId=" + livreurdId + ", flotte=" + flotte + '}';
    }

}
