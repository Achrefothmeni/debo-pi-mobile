/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.Panier;
import com.mycompany.myapp.utils.SessionUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hatem
 */
public class PanierServices {

    Database db;
    boolean created;

  

    public void createDB() {
        created = Database.exists("panier");
        try {
            db = Database.openOrCreate("panier");
            if (created == false) {
                db.execute("create table `panier` ( id INTEGER PRIMARY KEY,idu integer,  prodImg varchar,  libelle varchar, qte varchar, prix number, prixTot number)");
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        System.out.println("créer");

    }

    public void addPanier(Panier p, Integer idu) {
        //  createDB();

        //  System.out.println("slt");
        if (!findPanier(idu, p.getId())) {
            try {
                db.execute("insert into `panier` (idu,prodImg,libelle,qte,prix,prixTot) values('" + idu + "','" + p.getProdImg() + "','" + p.getLibelle() + "','"
                        + p.getQte() + "'," + p.getPrix() + "," + Double.parseDouble(p.getQte()) * p.getPrix() + ")");
                System.out.println("jawou behy");
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
           
        }
    }

    public Boolean findPanier(Integer idu, Integer idp) {
        createDB();
        Boolean b = false;

        try {
            System.out.println("aaaaaaaaaaaaaaaaaaa");
            Cursor cs = db.executeQuery("select * from panier where idu=" + idu + " and id=" + idp);
            if (cs.next()) {
                b = true;
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        System.out.println("b " + b);

        return b;
    }

    public ArrayList<Panier> returnPanier(Integer idu) {
        ArrayList<Panier> lstp = new ArrayList();

        try {
            System.out.println("aaaaaaaaaaaaaaaaaaa");
            Cursor cs = db.executeQuery("select * from panier where idu=" + idu);
            System.out.println(idu);

            while (cs.next()) {
                Row r = cs.getRow();

                Panier p = new Panier(r.getInteger(0), r.getInteger(1), r.getString(2), r.getString(3), r.getString(4), r.getDouble(5), r.getDouble(6));
                System.out.println(p.getId() + " " + p.getIdu() + " " + p.getPrixTot());
                lstp.add(p);

            }
            db.close();
            System.out.println("panier" + lstp);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lstp;
    }

    public void DeleteFromPanier(Integer idu, Integer idp) {
        createDB();

        try {
           db.execute("delete from panier where id=" + idp + " and idu=" + idu);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void updateQtePanier(Panier p, Integer idu, String qte) {

        createDB();
        try {
            db.execute("update panier set qte=" + qte + " where id=" + p.getId() + " and idu=" + idu);

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        System.out.println("modifié");

    }
        /*public Integer getPrixPanier() {
        Integer prix;

        prix = 0;
     
        prix = SessionUser.getUser().getPanier().keySet().stream().map((key) -> {
                produit = ps.getP(key);

                return key;
            })  .map((key) -> produit.getPrix() * p.get(key)).reduce(prix, Integer::sum);

        return prix;
    }*/
}
