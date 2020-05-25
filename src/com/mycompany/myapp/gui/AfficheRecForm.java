/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Reclamation;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.services.LivraisonService;
import com.mycompany.myapp.utils.SessionUser;
import com.mycompany.services.ReclamationService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author bazinfo
 */
public class AfficheRecForm {
    private static final String apiKey = "AIzaSyBWeRU02YUYPdwRuMFyTKIXUbHjq6e35Gw";

    Resources theme = UIManager.initFirstTheme("/theme");
        boolean tprix;
        boolean tnb;


        Form fd;
        private Label createForFont(Font fnt, String s) {
            Label l = new Label(s);
            l.getUnselectedStyle().setFont(fnt);
            return l;
        }

        private int shiftColor(int color, double factor) {
            return ColorUtil.rgb(
                    (int)Math.min(ColorUtil.red(color) * factor, 255) , 
                    (int)Math.min(ColorUtil.green(color) * factor, 255) ,
                    (int)Math.min(ColorUtil.blue(color) * factor, 255)
            );
        }
    protected CategorySeries buildCategoryDataset(String title,ArrayList<String> names, int[] values) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (int i=0; i<names.size();i++) {
            series.add(names.get(i), values[i]);
        }

        return series;
    }
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        //renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }
    ReclamationService rs = new ReclamationService();
    public AfficheRecForm() {
        Font smallBoldSystemFont = Font.createTrueTypeFont("native:ItalicBold", "native:ItalicBold").derive(Display.getInstance().convertToPixels(3), Font.STYLE_PLAIN);
        Font smallLightSystemFont = Font.createTrueTypeFont("native:ItalicLight", "native:ItalicLight").derive(Display.getInstance().convertToPixels((float) 2.2), Font.STYLE_ITALIC);
        Form fe = new Form("Mes Reclamations ", BoxLayout.y());
        Toolbar tb1 = fe.getToolbar();
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
        
        ArrayList<Reclamation> rec = rs.getReclamations(SessionUser.getUser().getId());
        /*Container cc = new Container(new BoxLayout(BoxLayout.X_AXIS));
            //Container cy = new Container(BoxLayout.y());
        Label type = new Label("                 "+ "Type");
        type.getAllStyles().setFgColor(ColorUtil.BLUE);
        Label objet = new Label("                 "+"Objet");
        objet.getAllStyles().setFgColor(ColorUtil.BLUE);
        Label message = new Label ("                 "+"Message");
        message.getAllStyles().setFgColor(ColorUtil.BLUE);
        Label statut = new Label ("                      "+"Statut");
        statut.getAllStyles().setFgColor(ColorUtil.BLUE);
        cc.addAll(type, objet, message, statut);
        if(!rec.isEmpty())
        {
            fe.add(cc);
        }
        for (Reclamation mm : rec) {

            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            //Container cy = new Container(BoxLayout.y());
            Label type1 = new Label("                 "+mm.getType());
            type1.getAllStyles().setFgColor(ColorUtil.BLACK);
            Label objet1 = new Label("                     "+mm.getObjet());
            objet1.getAllStyles().setFgColor(ColorUtil.BLACK);
            Label message1 = new Label("                     "+mm.getMessage());
            message1.getAllStyles().setFgColor(ColorUtil.BLACK);
            Label statut1 = new Label ("                              "+mm.getStatut());
            statut1.getAllStyles().setFgColor(ColorUtil.BLACK);
            c1.addAll(type1, objet1, message1, statut1);
            fe.add(c1);
        } */
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (Reclamation liv : rec){
            MultiButton mb = new MultiButton("Reclamation n°:"+String.valueOf(liv.getId()) +" | "+String.valueOf(liv.getType()));
            mb.getStyle().setMarginLeft(5);
            mb.getStyle().setMarginBottom(10);
            mb.setTextLine2(liv.getMessage());
            mb.setTextLine3("Status: "+ liv.getStatut());
            list.add(mb);
        }
        fe.add(list);
        fe.show();
    }
    
}
