/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;

import com.mycompany.myapp.entities.User;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author asus
 */
public class SessionUser {
    
    //private static final FosUserServices fs = new FosUserServices();

    private static SessionUser instance = null;
    private static User user = null;
    
    private SessionUser(User userConnected) {
        super();
        SessionUser.user = userConnected;
    }
    
    private SessionUser(User userConnected, Boolean b) {
        super();
        SessionUser.user = userConnected;
    }
    
    public final static SessionUser getInstance(User userConnected) {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet 
        //d'éviter un appel coûteux à synchronized, 
        //une fois que l'instanciation est faite.
        if (SessionUser.instance == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            //synchronized (DataSource.class) {
            //    if (DataSource.instance == null) {
            SessionUser.instance = new SessionUser(userConnected);
            //    }
            //}
        }
        return SessionUser.instance;
    }
    
    public final static SessionUser getFirstInstance(User userConnected) {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet 
        //d'éviter un appel coûteux à synchronized, 
        //une fois que l'instanciation est faite.
        if (SessionUser.instance == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            //synchronized (DataSource.class) {
            //    if (DataSource.instance == null) {
            SessionUser.instance = new SessionUser(userConnected,false);
            //    }
            //}
        }
        return SessionUser.instance;
    }

    /*public static FosUserServices getFs() {
        return fs;
    }*/

    public static SessionUser getInstance() {
        return instance;
    }

    public static User getUser() {
        return user;
    }


    public static void setUser(User user) {
        SessionUser.user = user;
    }

}
