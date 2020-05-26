/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.services.LivraisonService;
import com.mycompany.myapp.utils.SessionUser;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class ListLivraisonsForm extends Form{
    
    Form f = new Form ("Mes livraisons", BoxLayout.y());
    
    public ListLivraisonsForm(){
        LivraisonService ls = new LivraisonService();
        ArrayList<Livraison> livraisons = ls.getListLivraisons(SessionUser.getUser().getId());
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (Livraison liv : livraisons){
            MultiButton mb = new MultiButton("Livraison n°:"+String.valueOf(liv.getId()));
            mb.getStyle().setMarginLeft(5);
            mb.getStyle().setMarginBottom(10);
            mb.setTextLine2(liv.getDateDepart());
            mb.setTextLine3("Status: "+ liv.getStatut());
            mb.addActionListener(e -> {new UpdateLivraisonForm(liv, f);});
            list.add(mb);
        }
        f.add(list);
        f.show();
    }
    
    
}
