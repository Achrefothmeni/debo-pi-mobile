/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.FosUserServices;
import com.mycompany.myapp.utils.SessionUser;

/**
 *
 * @author dell
 */
public class ProfileForm {
    
    private Form f;
    Font font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
    
    public ProfileForm(){
        Form f = new Form("Profile", BoxLayout.y());
        
        
                            
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
        tb1.addMaterialCommandToSideMenu("Mes reclamations", FontImage.MATERIAL_HOME, e -> {
                                new AfficheRecForm();
                            });
        tb1.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_HOME, e -> {
                                new LoginForm();
                            });
        FosUserServices fs = new FosUserServices();
        User user = SessionUser.getUser();
        Resources theme = UIManager.initFirstTheme("/theme");
        ImageViewer icon = new ImageViewer(theme.getImage("user.png"));
        ImageViewer img = new ImageViewer(icon.getImage().scaled(500, 500));
        Label l1 = new Label("Email");
        TextField email = new TextField(user.getEmail());
        Label l2 = new Label("Numero de téléphone");
        TextField num = new TextField(String.valueOf(user.getNumTel()));
        Button modifierButton = new Button("Modifier");
        modifierButton.getStyle().setMarginBottom(18);
        modifierButton.addActionListener( e -> {
            Boolean x =Dialog.show("Attention!!!", "vous voulez modifier votre profile" ,"Oui", "Non");
            if(x)
             { 
                 fs.modifierProfile(user.getId(), email.getText(), num.getText());
                 Dialog.show("Success", "modification de votre profile avec succéee" ,"Ok",null);
             }
            });
        
        Button pwd = new Button("Changer mot de passe");
        pwd.addActionListener( e -> {
             new UpdatePwdForm();
            });
        
        pwd.setGap(10);
        f.add(img);
        f.add(l1);
        f.add(email);
        f.add(l2);
        f.add(num);
        f.add(modifierButton);
        f.add(pwd);
        f.show();
    }

public Form getF(){
return f;
}
}
