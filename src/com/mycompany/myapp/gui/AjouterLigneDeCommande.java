/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.LigneDeCommandeProduit;
import com.mycompany.myapp.entities.User;

import com.mycompany.myapp.entities.Panier;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.LigneDeCommandeServices;
import com.mycompany.myapp.services.PanierServices;

import com.mycompany.myapp.utils.SessionUser;
import java.util.ArrayList;

/**
 *
 * @author hatem
 */
public class AjouterLigneDeCommande {
    private Form f;

    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }
    public AjouterLigneDeCommande()
    {            
        //System.out.println("X = "+AfficherLigneCommandes.X);

                   // System.out.println("hédhi l'adresse: "+acf.);
   
              
                    
             
     User fu = new User();
               ArrayList<Panier> lstp= new ArrayList<>();
        PanierServices ps = new PanierServices();
 //LigneDeCommandeProduit lp = new LigneDeCommandeProduit(lp.getIdLigneDeCommande(),lp.getPrixTotal(),lp.getQuantiteCommander());
  Commande c= new Commande();
                     LigneDeCommandeServices lcs = new LigneDeCommandeServices();
     //  lcs.addCommandeAndLigneDeCommande(SessionUser.getUser().getId());
  ps.createDB();
       // InfoPersonnel ip = new InfoPersonnel();
       lstp=ps.returnPanier(SessionUser.getUser().getId());
       for(Panier p : lstp)
       {
          
            
            
        //   System.out.println("idprod "+p.getId());
          // System.out.println("iduuuuu "+p.getIdu());
    //       lcs.addCommande(c,SessionUser.getUser().getId());
                          lcs.addLigneDeCommande(SessionUser.getUser().getId(), p);
ps.DeleteFromPanier(SessionUser.getUser().getId(), p.getId());        
       }
       AffichageCommande ac = new AffichageCommande();
          
    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
