/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.entities.Magazin;
import com.mycompany.myapp.services.LivraisonService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class LivraisonsForm extends Form {
    
    LivraisonService ls = new LivraisonService();
    Form fe = new Form("liste des magazins", BoxLayout.y());
    public LivraisonsForm(){
        ArrayList<Livraison> lev = ls.getListLivraisons(118);
        Font smallBoldSystemFont = Font.createTrueTypeFont("native:ItalicBold", "native:ItalicBold").derive(Display.getInstance().convertToPixels(3), Font.STYLE_PLAIN);
        Font smallLightSystemFont = Font.createTrueTypeFont("native:ItalicLight", "native:ItalicLight").derive(Display.getInstance().convertToPixels((float) 2.2), Font.STYLE_ITALIC);

        
        
        
        //ArrayList<Magazin> lev = ls.getListMagazins(15);
       
Container cc = new Container(new BoxLayout(BoxLayout.X_AXIS));
            //Container cy = new Container(BoxLayout.y());
            Label idl = new Label("id Livraison");
            Label locationl = new Label("Date Depart");
             Label categoryy = new Label ("Date Arrivee");
            cc.addAll(idl,locationl,categoryy);
            fe.add(cc);
      
        
        for (Livraison mm : lev) {

            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            //Container cy = new Container(BoxLayout.y());
            Label id = new Label(mm.getId()+"          ");
            Label dateDepart = new Label(mm.getDateDepart()+"    ");
            Label dateArrivee = new Label(mm.getDateArrivee()+"          ");
            Button det = new Button("detail");
            det.setSize(new Dimension(5, 5));
            //det.addActionListener((ev) -> new TestForm(mm));
                
           
            
            c1.addAll(id,dateDepart,dateArrivee);
           
            fe.add(c1);
            
        }
        /*fe.getToolbar().addMaterialCommandToLeftBar("Mes livraisons",FontImage.MATERIAL_ARROW_BACK, ev -> {
            new HomeForm();
        });*/
        fe.show();

    }
    
    
}
