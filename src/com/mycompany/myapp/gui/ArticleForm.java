/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Form;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
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
import com.mycompany.myapp.entities.Panier;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.FosUserServices;
import com.mycompany.myapp.services.PanierServices;
import com.mycompany.myapp.services.ProduitServices;
import com.mycompany.myapp.utils.SessionUser;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author dell
 */
public class ArticleForm extends Form {
     private Form f;
    Font font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
     public ArticleForm() {

        
           // f = new Form("Login", BoxLayout.y());
            Resources theme = UIManager.initFirstTheme("/theme");

 
            Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);

        
             //   private String newfilePath = "";
                ProduitServices ps = new ProduitServices();        
                        try {
                            Container cn = new Container(new FlowLayout(Component.CENTER));

                            SpanLabel l = new SpanLabel(SessionUser.getUser().getPrenom() + " " + SessionUser.getUser().getNom());
                            l.getAllStyles().setFgColor(0xd03958);
                            cn.add(l);

                            Form home = new Form("Page d'accueil", BoxLayout.y());
                            
                            Toolbar tb1 = home.getToolbar();
        
        
        

                            ImageViewer logo = new ImageViewer(Image.createImage("/app.jpg"));
                            logo.getImage().scale(1000, 400);
                            logo.getAllStyles().setMarginBottom(20);
                            logo.getAllStyles().setMarginTop(20);
                            home.add(logo);
                            Container vc2 = new Container(BoxLayout.y());

                            home.add(vc2);

                            Form about = new Form("A propos", new FlowLayout(CENTER, CENTER));

                            Form souk = new Form("Les produits", BoxLayout.y());
 
          
                           
        tb1.addMaterialCommandToSideMenu("Mes reclamations", FontImage.MATERIAL_HOME, e -> {
                                new AfficheRecForm();
                            });
        
        tb1.addMaterialCommandToSideMenu("Détails articles", FontImage.MATERIAL_HOME, e -> {
                                new ListArticles(home).show();
                            });
        

                            ArrayList<Produit> listProduits = ps.getList2();

                            //Toolbar tb1 = home.getToolbar();
                            about.getToolbar().addCommandToOverflowMenu("Logout", theme.getImage("back-command.png"), b -> f.showBack());

                            tb1.addMaterialCommandToSideMenu("Accueil", FontImage.MATERIAL_HOME, e -> {
                                home.show();
                            });
                    
                     
                 
                            tb1.addMaterialCommandToSideMenu("Les produits", FontImage.MATERIAL_SHOP, e -> {
                                souk.show();
                            });
                      
                       tb1.addMaterialCommandToSideMenu("Mon panier", FontImage.MATERIAL_SHOPPING_BASKET, e -> {
                LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
                                       AfficherPanier ap = new AfficherPanier();
                                
                       
                                ap.getF().getToolbar().addCommandToLeftBar("", theme.getImage("back-command.png"), b -> home.showBack());
                            });
                                          tb1.addMaterialCommandToSideMenu("Mes commandes", FontImage.MATERIAL_SHOPPING_CART, e -> {
                                AffichageCommande ac = new AffichageCommande();
                                ac.getF().show();
                                Image icon = theme.getImage("back-command.png");
                                ac.getF().getToolbar().addCommandToLeftBar("", icon, e1 -> {
                                    home.showBack();
                                });
                            });
                            tb1.addMaterialCommandToSideMenu("Mon profile", FontImage.MATERIAL_PERSON, e -> {
                                new ProfileForm();
                            });
                            tb1.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_PERSON, e -> {
                                new LoginForm();
                            });
                            
                            home.show();

                            for (Produit t : listProduits) {
                                Toolbar tb = getToolbar();
                                Image placeholder = Image.createImage(tb.getWidth() / 3 - 4, tb.getWidth() / 3 - 4, 0xbfc9d2);
                                EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                              ImageViewer img = new ImageViewer(URLImage.createToStorage(encImage, "file" + t.getImage(), "http://localhost/debo-pi-master/web/images/" + t.getImage()));

// ImageViewer img = new ImageViewer(res.getImage(t.getImage()));
                                Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                c1.setScrollableY(false);
                                c1.getAllStyles().setBorder(RoundBorder.create().rectangle(true).
                                        color(0xffffff).
                                        strokeColor(0x7EC0EE).
                                        strokeOpacity(80).
                                        stroke(borderStroke));
                                c1.getAllStyles().setBackgroundType(Byte.MIN_VALUE);
//                            c1.getStyle().setBgColor(0xe6e5f4);
                                c1.getStyle().setBgTransparency(255);
                                c1.getAllStyles().setMarginBottom(15);
                                c1.getAllStyles().setMarginLeft(15);
                                c1.getAllStyles().setMarginRight(15);
                                c1.getAllStyles().setMarginTop(15);

                                Label name = new Label(t.getName());
                                name.setUIID("Label");

                                name.getAllStyles().setFgColor(0x3958d0);
                                name.getAllStyles().setFont(font);
                                name.setAlignment(CENTER);

                                name.addPointerPressedListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {

                                        System.out.println(".actionPerformed()");
                                        Form product = new Form(t.getName(), BoxLayout.y());

                                        Image placeholder = Image.createImage(tb.getWidth() / 3 - 4, tb.getWidth() / 3 - 4, 0xbfc9d2);
                                        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                                       ImageViewer pImg = new ImageViewer(URLImage.createToStorage(encImage, "file" + t.getImage(), "http://localhost/debo-pi-master/web/images/" + t.getImage()));
                                       pImg.getAllStyles().setMarginTop(15);
                                      Container cImg = new Container(BoxLayout.x());
                                        Container cName = new Container(BoxLayout.x());
                                        Container cDescription = new Container(BoxLayout.x());
                                        Container cPrix = new Container(BoxLayout.x());
                                        Container cQuantite = new Container(BoxLayout.x());
                                       
                                      
                                    //    Container cCategorie = new Container(BoxLayout.x());
                                        Container cbtn = new Container(BoxLayout.x());

                                        Button bnt1 = new Button("Ajouter au panier");
                                               bnt1.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent evt) {
                                                PanierServices ps = new PanierServices();
                                                Panier p = new Panier();
                                                p.setId(t.getId());
                                                p.setIdu(SessionUser.getUser().getId());
                                                p.setLibelle(t.getName());
                                                p.setPrix(t.getPrix());
                                                p.setQte("1");
                                                p.setProdImg(t.getImage());
                                                ps.addPanier(p, SessionUser.getUser().getId());
                                                                         
                                            }
                                        });
                                     //   Button bnt2 = new Button("Wishlist");
                                       

                                        cbtn.add(bnt1);
                                 //       cbtn.add(bnt2);

                                        cName.getAllStyles().setMarginTop(15);

                                        Label lbPname = new Label("Libelle : ");
                                        Label lbPdescription = new Label("Description : ");
                                        Label lbPprice = new Label("Prix : ");
                                        Label lbPquantite = new Label("Quantité disponible : ");
                                
                                     //   Label lbPcategorie = new Label("Catégorie : ");

                                        lbPname.setUIID("Label");
                                        lbPname.getAllStyles().setFgColor(0x3958d0);
                                        lbPname.getAllStyles().setFont(font);

                                        lbPdescription.setUIID("Label");
                                        lbPdescription.getAllStyles().setFgColor(0x3958d0);
                                        lbPdescription.getAllStyles().setFont(font);

                                        lbPprice.setUIID("Label");
                                        lbPprice.getAllStyles().setFgColor(0x3958d0);
                                        lbPprice.getAllStyles().setFont(font);

                                     
                               
                                    

                                  

                                      //  lbPcategorie.setUIID("Label");
                                     //   lbPcategorie.getAllStyles().setFgColor(0x3958d0);
                                       // lbPcategorie.getAllStyles().setFont(font);

                                        lbPquantite.setUIID("Label");
                                        lbPquantite.getAllStyles().setFgColor(0x3958d0);
                                        lbPquantite.getAllStyles().setFont(font);

                                        Label Pname = new Label(t.getName());
                                        Label Pdescription = new Label(t.getDescription());
                                        Label Pprice = new Label(Double.toString(t.getPrix()) + "DT");
                                      //  Label Pquantite = new Label(Integer.toString(t.getQte()));
                             
                           
                                     //   Label Pcategorie = new Label(t.getCategorie());

                                        cName.add(lbPname);
                                        cName.add(Pname);

                                        cDescription.add(lbPdescription);
                                        cDescription.add(Pdescription);

                                        cPrix.add(lbPprice);
                                        cPrix.add(Pprice);

                                        cQuantite.add(lbPquantite);
                                      //  cQuantite.add(Pquantite);

                                 

                                 

                                      //  cCategorie.add(lbPcategorie);
                                       // cCategorie.add(Pcategorie);

                                   //    cImg.add(pImg);
                                     product.add(pImg);
                                        product.add(cName);
                                        product.add(cDescription);
                                        product.add(cPrix);
                                   product.add(cQuantite);
                                       
                                       // product.add(cCategorie);
                                        product.add(cbtn);

                                        product.getToolbar().addCommandToLeftBar("Retour", theme.getImage("back-command.png"), b -> souk.showBack());

                                        product.show();

                                    }
                                });

                                c1.add(name);
                               c1.add(img);

                                Label price = new Label(String.valueOf(t.getPrix()) + " DT");

                                price.setUIID("label");
                                price.getAllStyles().setFgColor(0xa1a0aa);
                                price.setAlignment(CENTER);
                                c1.add(price);
                                souk.add(c1);

                               // int id = t.getId();
                            }
    Form mesProduits = new Form("Mon Souk", BoxLayout.y());

                  
                            Button btnOpen = new Button("Choisir Image");

                    

                          

                            Button aj = new Button("Ajouter");
                    

                     

                            //cn.add(logo);
                            about.add(cn);

                            //about.add(l);
                            about.getToolbar().addCommandToLeftBar("Retour", theme.getImage("back-command.png"), b -> home.showBack());
souk.getToolbar().addCommandToLeftBar("Retour", theme.getImage("back-command.png"), b -> home.showBack());

                        } catch (IOException ex) {
                            System.out.println("Error" + ex.getMessage());
                        }

                  

                

            
           


    

    }
       public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
