/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.FosUserServices;
import com.mycompany.myapp.utils.SessionUser;


/**
 *
 * @author dell
 */
public class UpdatePwdForm extends Form {
    
    public Form f;
    Font font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
    
    public UpdatePwdForm(){
        Form f = new Form("Changer mot de passe", BoxLayout.y());
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
        
        FosUserServices fs = new FosUserServices();
        User user = SessionUser.getUser();
        
        Resources theme = UIManager.initFirstTheme("/theme");
        ImageViewer icon = new ImageViewer(theme.getImage("user.png"));
        ImageViewer img = new ImageViewer(icon.getImage().scaled(500, 500));
        Label l1 = new Label("Mot de passe actuelle");
        TextField p1 = new TextField();
        Label l2 = new Label("Nouveau mot de passe");
        TextField p2 = new TextField();
        Button modifierButton = new Button("Modifier");
        modifierButton.addActionListener( e -> {
            Boolean x =Dialog.show("Attention!!!", "vous voulez modifier votre profile" ,"Oui", "Non");
            if(x)
             {   if(p1.getText().equals(SessionUser.getUser().getPassword())){
                    fs.modifierPwd(user.getId(), p2.getText());
                    Dialog.show("Success", "modification de votre mot de passe avec succéee" ,"Ok",null);
                    }
             else{
                 Dialog.show("Attention", "Verifier votre mot de passe" ,"Ok",null);
             }
                 
             }
            });
        
        //Container container = new Container(new FlowLayout(CENTER));
        
        f.add(img);
        f.add(l1);
        f.add(p1);
        f.add(l2);
        f.add(p2);
        f.add(modifierButton);
        f.show();
    }
    
}
