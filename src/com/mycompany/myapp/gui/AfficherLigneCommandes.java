/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.Accordion;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.myapp.entities.LigneDeCommandeProduit;
import com.mycompany.myapp.services.LigneDeCommandeServices;
import java.util.ArrayList;

/**
 *
 * @author hatem
 */
public class AfficherLigneCommandes {

    LigneDeCommandeServices lcs = new LigneDeCommandeServices();
    private Form f;
    Button btnModif;
    public static Integer X =0;
    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }

    public AfficherLigneCommandes(Integer id) {
        f = new Form("Lignes de commande", new BorderLayout());
        Toolbar tb1 = f.getToolbar();
        tb1.addMaterialCommandToSideMenu("Articles", FontImage.MATERIAL_HOME, e -> {
                                new ArticleForm();
                            });
        tb1.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_HOME, e -> {
                                new ProfileForm();
                            });
        tb1.addMaterialCommandToSideMenu("Mes commandes", FontImage.MATERIAL_HOME, e -> {
                                new AffichageCommande();
                            });
        tb1.addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_HOME, e -> {
                                new AfficherPanier();
                            });
        tb1.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_HOME, e -> {
                                new LoginForm();
                            });
        Accordion accr = new Accordion();
        ArrayList<LigneDeCommandeProduit> lstlcp = lcs.getLignesCommandeProduit(id);
        System.out.println(id);
        System.out.println(lstlcp);
      

        f.add(BorderLayout.CENTER, accr);
        f.show();

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
