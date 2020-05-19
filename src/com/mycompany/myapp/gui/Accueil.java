/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.Font;
import com.codename1.ui.Slider;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.Display;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;

/**
 *
 * @author bhk
 */
public class Accueil extends Form {

    Form current;

    public Accueil() {
        Resources theme=UIManager.initFirstTheme("/theme");
        ImageViewer v = new ImageViewer(theme.getImage("LOGODEBO.png"));
        Container cc = new Container(new FlowLayout(CENTER));
        cc.add(v);
        add(cc);
        current = this;
        Toolbar tb1 = current.getToolbar();
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
        tb1.addMaterialCommandToSideMenu("Demander un article", FontImage.MATERIAL_HOME, e -> {
                                new RequestForm(current).show();
                            });
        tb1.addMaterialCommandToSideMenu("Détails articles", FontImage.MATERIAL_HOME, e -> {
                                new ListArticles(current).show();
                            });
        tb1.addMaterialCommandToSideMenu("Liste Categories", FontImage.MATERIAL_HOME, e -> {
                                new ListCategoriesForm(current).show();
                            });
        tb1.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_HOME, e -> {
                                new LoginForm();
                            });
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Request article");
        Button btlListArticles = new Button("List Articles");
        Button btnListcategories = new Button("List Categories");

        btnAddTask.getAllStyles().setFgColor(0x33FFFF);
        btlListArticles.getAllStyles().setFgColor(0x33FFFF);
        btnListcategories.getAllStyles().setFgColor(0x33FFFF);
        
        btlListArticles.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        btnAddTask.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        btnListcategories.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);

        btnAddTask.addActionListener(e -> new RequestForm(current).show());
        btlListArticles.addActionListener(e -> new ListArticles(current).show());
        btnListcategories.addActionListener(e -> new ListCategoriesForm(current).show());
        addAll(btnAddTask, btlListArticles, btnListcategories);

    }

}
