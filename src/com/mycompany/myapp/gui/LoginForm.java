/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;

import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.FosUserServices;
import com.mycompany.myapp.services.MagazinServices;
import com.mycompany.myapp.utils.SessionUser;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author asus
 */
public class LoginForm extends Form {

    public Form f;
    Font font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);

    public LoginForm(){
        
        f = new Form("Login", BoxLayout.y());
        Resources theme = UIManager.initFirstTheme("/theme");
        ImageViewer logo = new ImageViewer(theme.getImage("logo.png"));
        logo.getImage().scale(300, 300);
        
        TextField login = new TextField("", "Tapez votre nom d'utilisateur");
        TextField password = new TextField("", "Tapez votre mot de passe", 20, TextField.PASSWORD);
        Style loginStyle = login.getAllStyles();
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        loginStyle.setBorder(RoundBorder.create().
                rectangle(true).
                color(0xffffff).
                strokeColor(0x7EC0EE).
                strokeOpacity(80).
                stroke(borderStroke));
        loginStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        loginStyle.setMargin(Component.BOTTOM, 5);
        loginStyle.setMargin(Component.TOP, 5);
        Style passwordStyle = password.getAllStyles();
        passwordStyle.setBorder(RoundBorder.create().
                rectangle(true).
                color(0xffffff).
                strokeColor(0x7EC0EE).
                strokeOpacity(80).
                stroke(borderStroke));
        passwordStyle.setMargin(Component.BOTTOM, 5);
        Button loginButton = new Button("Login");
        loginButton.getStyle().setPadding(Component.LEFT, 10);
        loginButton.getStyle().setPadding(Component.RIGHT, 10);
        loginButton.getStyle().setBgColor(0x99CCCC);
        loginButton.addActionListener(new ActionListener() {
            private String newfilePath = "";
            //ProduitServices ps = new ProduitServices();
            
            @Override
            public void actionPerformed(ActionEvent evt) {  
                
                FosUserServices us = new FosUserServices();
                if ((login.getText().equals("")) || ((password.getText().equals("")))) {
                    Dialog.show("Vérifiez Vos Champs", "Vérifiez Vos Champs", null, "ok");
                }
                //System.out.println("Connected User: ");
                User user = us.getUser(login.getText());
                if(user == null) {
                    Dialog.show("Information ivalide", "Information ivalide", null, "ok");
                }
                else if(!user.getPassword().equals(password.getText())){
                    Dialog.show("Information ivalide", "Information ivalide", null, "ok");
                }
                else{
                    System.out.println("Connected User: "+ user);
                    SessionUser.getInstance(user);
                    if(SessionUser.getUser().getRoles().equals("[ROLE_DELIVERY_MANAGER, ROLE_USER]")){
                        new ListLivraisonsForm();
                    try {
                        Media m = MediaManager.createBackgroundMedia("C:\\sound.mp3");
                        m.play();
                    } catch (IOException err) {
                        Log.e(err);
                    }
                    }
                    else if (SessionUser.getUser().getRoles().equals("[ROLE_CLIENT, ROLE_USER]")){
                        new ArticleForm();
                    try {
                        Media m = MediaManager.createBackgroundMedia("C:\\sound.mp3");
                        m.play();
                    } catch (IOException err) {
                        Log.e(err);
                    }
                    }
                    else{
                        MagazinServices ms = new MagazinServices();
                        try {
                            new HomeForm();
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    try {
                        Media m = MediaManager.createBackgroundMedia("C:\\sound.mp3");
                        m.play();
                    } catch (IOException err) {
                        Log.e(err);
                    }
                    }
                    
                }

            }
        }
        );
        f.add(logo);
        f.add(login);
        f.add(password);
        f.add(loginButton);
        f.show();
        
    }

   

}
