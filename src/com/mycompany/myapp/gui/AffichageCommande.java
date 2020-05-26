/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.Accordion;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.LigneDeCommandeProduit;
import com.mycompany.myapp.entities.Produit;
import static com.mycompany.myapp.gui.AfficherLigneCommandes.X;
import com.mycompany.myapp.services.CommandeServices;
import com.mycompany.myapp.services.LigneDeCommandeServices;
import com.mycompany.myapp.utils.SessionUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


/**
 *
 * @author hatem
 */
public class AffichageCommande {
                    User fu = new User();

  /*  public static String adrm;
    public static String adr2m;
    public static String vilm;
    public static String codem;
    public static String numm;*/

    LigneDeCommandeServices lcs = new LigneDeCommandeServices();
    private Form f;
    private CommandeServices cs = new CommandeServices();
    public static Integer idC;
    Button btnModif;
   
        private Resources theme = UIManager.initFirstTheme("/theme");

    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }

    public AffichageCommande() {
        f = new Form("Mes Commandes", new BoxLayout(BoxLayout.Y_AXIS));
       // f.setScrollableX(true);
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
        
        tb1.addMaterialCommandToSideMenu("Détails articles", FontImage.MATERIAL_HOME, e -> {
                                new ListArticles(f).show();
                            });
        
        tb1.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_HOME, e -> {
                                new LoginForm();
                            });

        //SpanLabel lb = new SpanLabel("");
        Font smallBoldSystemFont = Font.createTrueTypeFont("native:ItalicBold", "native:ItalicBold").derive(Display.getInstance().convertToPixels(3), Font.STYLE_PLAIN);
        Font smallLightSystemFont = Font.createTrueTypeFont("native:ItalicLight", "native:ItalicLight").derive(Display.getInstance().convertToPixels(3), Font.STYLE_PLAIN);
        TableLayout layout = new TableLayout(1,3);
        TableLayout.Constraint constraint = layout.createConstraint();
        constraint.setWidthPercentage(200);
        Container c0 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label lh1 = new Label("Id");
        Label lh2 = new Label("Date Commande");
        Label lh3 = new Label("Date Max");
        Label lh4 = new Label("Adresse");
        c0.add(lh1);
        c0.add(lh2);
     //   c0.add(lh3);
        c0.add(lh4);
        f.add(c0);
              Toolbar tb = f.getToolbar();
            //  AfficherLigneWishlist affw = new AfficherLigneWishlist(fu.getId());
            //  AfficherPanier affp = new AfficherPanier().getF();
        tb.addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_ADD_A_PHOTO, e->{new AfficherPanier().getF().show();});
      //  tb.addMaterialCommandToSideMenu("Wishlist", FontImage.MATERIAL_AC_UNIT, e->{new AfficherLigneWishlist(fu.getId()).getF().show();});
        ArrayList<Commande> lstC = cs.getCommandes(SessionUser.getUser().getId());

        for (Commande c : lstC) {

            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

            Label l1 = new Label(c.getId().toString());
            l1.setAutoSizeMode(true);

            Label l2 = new Label(c.getDateCommande());
            l2.setAutoSizeMode(true);

            
            Label l4 = new Label(c.getAddresse().toString());
            l4.setAutoSizeMode(true);
            c1.add(createForFont(smallLightSystemFont, l1.getText()));
            c1.add(createForFont(smallLightSystemFont, l2.getText()));
              //   c1.add(createForFont(smallLightSystemFont,l3.getText()));
 c1.add(createForFont(smallLightSystemFont,l4.getText()));
            Button b1 = new Button("Details");
Button r1 = new Button("Reclamation");
        r1.addActionListener( e -> {try {
            new Reclamation(c.getId());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
});
            c1.add(b1);
            c1.add(r1);

            f.add(c1);
            b1.addActionListener(new ActionListener() {
                ArrayList<LigneDeCommandeProduit> lstlcp = lcs.getLignesCommandeProduit(c.getId());

                @Override
                public void actionPerformed(ActionEvent evt) {
                    /*jdid*/

                    Form f1 = new Form("Lignes de commande", new BorderLayout());
                    Accordion accr = new Accordion();
                    //             System.out.println("id commande"+c.getId());
                    System.out.println("liste lc : " + lstlcp);
                    //        Log.p("aaaasa"+lcs.getLignesCommandeProduit(c.getId()));
                    System.out.println("size : " + lstlcp.size());
                    for (LigneDeCommandeProduit lcp : lstlcp) {
                        btnModif = new Button("Modifier");
                     //   Label l1 = new Label("Produit : " + lcp.getLibelle());
                        createForFont(smallBoldSystemFont, l1.getText());
                        //System.out.println(l1);
                   Image placeholder = Image.createImage(f.getWidth() / 3 - 4, f.getWidth() / 3 - 4, 0xbfc9d2);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        Produit p=new Produit();
                        accr.addContent(l1, BoxLayout.encloseY(
                                new Label("   Prix Total : " + lcp.getPrixTotal()),
                                new Label("   Quantité achetée : " + lcp.getQuantiteCommander()),
                                btnModif)
        
                        );
                 
                        btnModif.setBlockLead(true);
                    //    System.out.println("addzzzzzz" + lcp.getAdresse());
                        btnModif.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                X = 1;
                                idC = lcp.getIdLigneDeCommande();
                                System.out.println("id ligne :p " + lcp.getIdLigneDeCommande());
             
                                AjoutCommandeForm acf = new AjoutCommandeForm();
                                

                            }
                        });

                    }

                    f1.getToolbar().addCommandToRightBar("", theme.getImage("back-command.png"), (b)->f.showBack());
              Toolbar tb = f1.getToolbar();
     
        tb.addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_ADD_A_PHOTO, e->{new AfficherPanier().getF().show();});
                    f1.add(BorderLayout.CENTER, accr);

                    f1.show();
                }
            });

        }
    
        f.show();

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
