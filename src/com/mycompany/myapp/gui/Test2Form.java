/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Magazin;
import com.mycompany.myapp.services.MagazinServices;
import com.mycompany.myapp.utils.SessionUser;


import java.io.IOException;
import java.util.ArrayList;




/**
 *
 * @author nouri
 */
public class Test2Form {
    Form fd;
    Resources theme = UIManager.initFirstTheme("/theme");
    MagazinServices ms = new MagazinServices();
    
    public Test2Form(int id,int quantite,Magazin mm) throws IOException{
        ArrayList<Magazin> lev = ms.getListMagazins(SessionUser.getUser().getId());
       fd = new Form("changer le produit "+id, BoxLayout.y()); 
Container cc = new Container(new BoxLayout(BoxLayout.Y_AXIS));
ComboBox<String> c = new ComboBox<>();
c.getAllStyles().setBgColor(ColorUtil.WHITE);
c.getAllStyles().setFgColor(ColorUtil.BLACK);

for (Magazin m:lev)
{
     if (m.getCapacityRest()>=quantite && !(m.getIdMagazin().equals(mm.getIdMagazin())) && (m.getCategory().equals(mm.getCategory())) )
    {
        c.addItem(m.getIdMagazin());
    }
}
if(c.size()==0)
{
    Media media = MediaManager.createBackgroundMedia("C:\\wrong-buzzer-sound-effect.mp3");
    media.play();
    Dialog.show("Attention!!", "pas de maagazin supporte le produit avec l'identifiant "+id, "Ok", null);
}
String x = c.getSelectedItem();
        System.out.println(x);
Button supps = new Button("changer");
            supps.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    String x = c.getSelectedItem();
                    Media media = null;
                    try {
                        media = MediaManager.createBackgroundMedia("C:\\wrong-buzzer-sound-effect.mp3");
                    } catch (IOException ex) {
                        System.out.println("erreur");
                    }
    media.play();
             Dialog.show("Attention", "etes vous sure de changer le produit "+id+" vers "+x, "Ok", null);
                    ms.add(id,x,mm.getIdMagazin());
                    ListTasksForm m = new ListTasksForm(SessionUser.getUser().getId(),SessionUser.getUser().getUsername());

                                
                }
            });
 ArrayList<String> s = ms.getAdmin();
            String ss[] ={s.get(1)};
        Message me = new Message("pas de debo vide!!!!");
            
            
            
            Button b1 = new Button("envoyer email");
            Button b2 = new Button("envoyer sms");
             b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt)
                {
                
                Display.getInstance().sendMessage(ss, "Article "+id, me);
                }});
              b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt)
                {
                
                    try {
                        Display.getInstance().sendSMS(s.get(0), "Pas de debo vide pour l'article "+id);
                    } catch (IOException ex) {
                        
                    }
                }});
              
        if(c.size()==0)
        {
            cc.addAll(b1,b2);
        }
        else
        {cc.addAll(c,supps);}
        fd.add(cc);
       
         fd.getAllStyles().setBgColor(ColorUtil.WHITE);
         fd.show();
          fd.getToolbar().addMaterialCommandToLeftSideMenu("accueil", FontImage.MATERIAL_HOME, ev -> {
            try {
                new HomeForm();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
          //fd.getToolbar().addMaterialCommandToLeftSideMenu("profil", FontImage.MATERIAL_PEOPLE, ev -> new ListTasksForm(22,"ali"));
        fd.getToolbar().addMaterialCommandToLeftSideMenu("magazins", FontImage.MATERIAL_HOME_WORK, ev -> new ListTasksForm(SessionUser.getUser().getId(),SessionUser.getUser().getUsername()));
        fd.getToolbar().addMaterialCommandToLeftSideMenu("logout", FontImage.MATERIAL_LOGOUT, ev -> new LoginForm());
          fd.getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, ev -> {
            try {
                new TestForm(mm);
            } catch (IOException ex) {
                
            }
        });
    }
    
    
}
