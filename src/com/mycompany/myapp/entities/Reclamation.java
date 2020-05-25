/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author bazinfo
 */
public class Reclamation {
    private int id;
    private String message;
    private String type;
    private String statut;
    private String objet;
    private String id_client;

    public Reclamation(int id, String message, String type, String statut, String objet, String id_client) {
        this.id = id;
        this.message = message;
        this.type = type;
        this.statut = statut;
        this.objet = objet;
        this.id_client = id_client;
    }

    public Reclamation(String message, String type, String objet, String id_client) {
        this.message = message;
        this.type = type;
        this.objet = objet;
        this.id_client = id_client;
    }

    

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }
}
