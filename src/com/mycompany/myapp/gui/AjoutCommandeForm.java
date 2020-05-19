/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import static com.codename1.io.rest.Rest.options;
import com.codename1.notifications.LocalNotification;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.util.regex.RE;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.User;

import com.mycompany.myapp.entities.Panier;
import com.mycompany.myapp.services.LigneDeCommandeServices;
import com.mycompany.myapp.services.PanierServices;
import com.mycompany.myapp.utils.SessionUser;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author hatem
 */
public class AjoutCommandeForm {
    private static final String apiKey = "AIzaSyBWeRU02YUYPdwRuMFyTKIXUbHjq6e35Gw";
    public static String adr;
 LigneDeCommandeServices lcs = new LigneDeCommandeServices();
   // public static InfoPersonnel ip;
   // public static Commande lp;
    final DefaultListModel<String> options = new DefaultListModel<>();
    
    private Form f;
    private boolean testTel;
    private boolean testcode;

    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }
    
    public boolean isNumber(String s) {
        RE r = new RE("(20|21|22|70|71|50|51)[0-9][0-9][0-9][0-9][0-9][0-9]$");
        boolean matcher = r.match(s);
     return matcher;
    } 
    public AjoutCommandeForm()
    {
                f = new Form("Informations personnels", new BoxLayout(BoxLayout.Y_AXIS));
                AutoCompleteTextField addresse = new AutoCompleteTextField(options){
             
            };
 addresse.setMinimumElementsShownInPopup(5);
    
 
 
                
                
addresse.setHint("Adresse");

                Button btnValider = new Button("Valider");
               f.add(addresse);
              
              ////  f.add(ville);
              //  f.add(codepostal);
              //  f.add(numtel);
                f.add(btnValider);
                
 
                
       
           
               
                btnValider.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Commande c =new Commande();
                         c.setAddresse(addresse.getText());

                                           //    lp = new Commande(adr);
                                          //   System.out.println("ajouterrrrr"+adr);
                                          //lcs.addCommande(c,SessionUser.getUser().getId());
                                          
                                               //AjouterLigneDeCommande acl = new AjouterLigneDeCommande();
                                               
                                               lcs.ajouterCommandeAndLigneDeCommande(SessionUser.getUser().getId(),1, "adresse", 100);
                                               Dialog.show("Success", "Votre commande a été effectuée avec succée", null, "ok");
                     
                         //      AjouterLigneDeCommande acl = new AjouterLigneDeCommande();

                    }
                });
                
       
                        f.show();

       
    }
    /*public InfoPersonnel getInfo()
    {
return ip;
    }*/
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
