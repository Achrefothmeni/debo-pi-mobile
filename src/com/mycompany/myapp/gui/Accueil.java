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
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("choisis une option"));
        Button btnAddTask = new Button("Demander un article");
        Button btlListArticles = new Button("Liste tous les articles");
        Button btnListcategories = new Button("Liste tous les catégories");

        btnAddTask.getAllStyles().setFgColor(0X0095F9);
        btlListArticles.getAllStyles().setFgColor(0X0095F9);
        btnListcategories.getAllStyles().setFgColor(0X0095F9);
        
        btlListArticles.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        btnAddTask.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        btnListcategories.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);

        btnAddTask.addActionListener(e -> new RequestArticle(current).show());
        btlListArticles.addActionListener(e -> new ListArticlesForm(current).show());
        btnListcategories.addActionListener(e -> new ListCategoriesForm(current).show());
        addAll(btnAddTask, btlListArticles, btnListcategories);

    }

}
