/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.SeriesSelection;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.charts.views.AbstractChart;

import com.codename1.charts.views.RoundChart;
import com.codename1.charts.views.PieChart;
import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.components.MediaPlayer;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.utils.SessionUser;
import com.mycompany.services.ReclamationService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author bhk
 */
public class Reclamation extends Form{
    Resources theme = UIManager.initFirstTheme("/theme");
    Form f = new Form("Reclamation", BoxLayout.y());
    ReclamationService rs = new ReclamationService();
    Date date = new Date();
    public Reclamation(int commande) throws IOException{
        
        Container c = new Container(new FlowLayout(CENTER));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Label i = new Label(formatter.format(date)+"");
        i.getAllStyles().setFgColor(ColorUtil.BLACK);
        i.getAllStyles().setAlignment(CENTER);
        System.out.println(formatter.format(date));  
            Timer myTimer=new Timer();
                    TimerTask task=new TimerTask(){
                        @Override
                        public void run()
                        {
                            date = new Date(); 
                            i.setText(formatter.format(date)+"");
                        }    
                    };
                    myTimer.scheduleAtFixedRate(task, 1000, 1000);
                    f.getAllStyles().setBgColor(ColorUtil.WHITE);
                    c.add(i);
                    f.add(c);

TextField type = new TextField(String.valueOf(commande), "",20,TextArea.UNEDITABLE);
type.setEditable(false);
TextField objet = new TextField("", "Objet");
TextComponent message = new TextComponent().multiline(true).rows(5).hint("Description");

        Style loginStyle = objet.getAllStyles();
            Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
            loginStyle.setBorder(RoundRectBorder.create().
            strokeColor(0).
            strokeOpacity(120).
            stroke(borderStroke));
            loginStyle.setBgColor(0xffffff);
            loginStyle.setBgTransparency(255);
            loginStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
            loginStyle.setMargin(Component.BOTTOM, 3); 
        Style loginStyle1 = type.getAllStyles();
            Stroke borderStroke1 = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
            loginStyle1.setBorder(RoundRectBorder.create().
            strokeColor(0).
            strokeOpacity(120).
            stroke(borderStroke));
            loginStyle1.setBgColor(0xffffff);
            loginStyle1.setBgTransparency(255);
            loginStyle1.setMarginUnit(Style.UNIT_TYPE_DIPS);
            loginStyle1.setMargin(Component.BOTTOM, 3); 
        Style loginStyle2 = message.getAllStyles();
            Stroke borderStroke2 = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
            loginStyle2.setBorder(RoundRectBorder.create().
            strokeColor(0).
            strokeOpacity(120).
            stroke(borderStroke));
            loginStyle2.setBgColor(0xffffff);
            loginStyle2.setBgTransparency(255);
            loginStyle2.setMarginUnit(Style.UNIT_TYPE_DIPS);
            loginStyle2.setMargin(Component.BOTTOM, 3); 
                         
        Button sendButton = new Button("Envoyée");
        FontImage.setMaterialIcon(sendButton, FontImage.MATERIAL_DONE);
        sendButton.addActionListener(e -> {
            LocalNotification n = new LocalNotification();
            n.setId("debo-notification");
            n.setAlertBody("votre Reclamation a été trairée");
            n.setAlertTitle("Merci!!");
            Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
            );
            System.out.println("notif succes");
            String ss[] ={"dhiaeddine.jemaiel@esprit.tn"};
            Message me = new Message("Reclamation envoyée");
            rs.add(type.getText(), objet.getText(), message.getText(), SessionUser.getUser().getId());
            Display.getInstance().sendMessage(ss, "Reclamation Debo", me);
            
            
        });
                Container cc = new Container(new FlowLayout(CENTER));
                cc.add(type);
                cc.add(objet);
                cc.add(message);
                cc.add(sendButton);
                f.add(cc);
                f.show();
                
           
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


    } 
}
