/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author dell
 */
public class User {
    
    protected int id;
    protected String username;
    protected String usernameCanonical;
    protected String email;
    protected String emailCanonical;
    protected int enabled = 1;
    protected String password;
    protected String roles;
    protected String nom;
    protected String prenom;
    private String status;
    private String matFiscal;
    private int numTel;
    
    public User(){}

    public User(String username, String usernameCanonical, String email, String emailCanonical, String password, String roles, String nom, String prenom, String status, String matFiscal, int numTel) {
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.password = password;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.status = status;
        this.matFiscal = matFiscal;
        this.numTel = numTel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMatFiscal() {
        return matFiscal;
    }

    public void setMatFiscal(String matFiscal) {
        this.matFiscal = matFiscal;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "username=" + username + ", usernameCanonical=" + usernameCanonical + ", email=" + email + ", emailCanonical=" + emailCanonical + ", enabled=" + enabled + ", password=" + password + ", roles=" + roles + ", nom=" + nom + ", prenom=" + prenom + ", status=" + status + ", matFiscal=" + matFiscal + ", numTel=" + numTel + '}';
    }

    
    
    
    
}
