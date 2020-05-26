/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.FosUserServices;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.services.LivraisonService;
import com.mycompany.myapp.utils.SessionUser;

/**
 *
 * @author dell
 */
public class UpdateLivraisonForm extends Form {
    
    private Form f;
    
    public UpdateLivraisonForm(Livraison liv, Form form){
        FosUserServices fs = new FosUserServices();
        LivraisonService ls = new LivraisonService();
        Form f = new Form("Modifier Livraison n°:"+String.valueOf(liv.getId()), BoxLayout.y());
        Resources theme = UIManager.initFirstTheme("/theme");
        ImageViewer icon = new ImageViewer(theme.getImage("livv.png"));
        ImageViewer img = new ImageViewer(icon.getImage().scaled(500, 500));
        Label titre = new Label("Modifier status");
        MultiButton b = new MultiButton("Modifier le status...");
        Button modifier = new Button("Modifier");
        b.addActionListener(e -> {
            Dialog d = new Dialog();
            d.setLayout(BoxLayout.y());
            d.getContentPane().setScrollableY(true);
                MultiButton mb = new MultiButton("En cours");
                MultiButton mb2 = new MultiButton("Terminée");
                MultiButton mb3 = new MultiButton("Annulée");
                d.addAll(mb, mb2, mb3);
                mb.addActionListener(ee -> {
                    b.setTextLine1(mb.getTextLine1());
                    d.dispose();
                    b.revalidate();
                    });
                mb2.addActionListener(ee -> {
                    b.setTextLine1(mb2.getTextLine1());
                    d.dispose();
                    b.revalidate();
                    });
                mb3.addActionListener(ee -> {
                    b.setTextLine1(mb3.getTextLine1());
                    d.dispose();
                    b.revalidate();
                    });
            d.showPopupDialog(b);
            });
        
        modifier.addActionListener( e -> {
            Boolean x =Dialog.show("Attention!!!", "vous voulez modifier la livraison" ,"Oui", "Non");
            if(x)
             {   
                    ls.modifierLivraison(liv.getId(), b.getTextLine1());
                    Dialog.show("Success", "modification effectuée avec succès" ,"Ok",null);
                 Message m = new Message("La livraison n°" + liv.getId() + " est " + liv.getStatut());
                 String receipent[] ={SessionUser.getUser().getEmail()};
                 Display.getInstance().sendMessage(receipent, "Alerte de sécurité", m);
             }
            });
        f.addAll(img, titre, b, modifier);
        f.show();
    }
}
