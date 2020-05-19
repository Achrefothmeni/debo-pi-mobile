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
import com.codename1.components.ImageViewer;
import com.codename1.components.MediaPlayer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.utils.SessionUser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
    Resources theme = UIManager.initFirstTheme("/theme");
Form f = new Form("Accueil", BoxLayout.y());
Date date = new Date();
public HomeForm() throws IOException{
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
               

ImageViewer v = new ImageViewer(theme.getImage("logo.png"));
Container cc = new Container(new FlowLayout(CENTER));
cc.add(v);
f.add(cc);
Button btnAddTask = new Button("Request article");
        Button btlListArticles = new Button("List Articles");
        Button btnListcategories = new Button("List Categories");

        btnAddTask.getAllStyles().setFgColor(0x33FFFF);
        btlListArticles.getAllStyles().setFgColor(0x33FFFF);
        btnListcategories.getAllStyles().setFgColor(0x33FFFF);
        
        btlListArticles.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        btnAddTask.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        btnListcategories.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);

        btnAddTask.addActionListener(e -> new RequestForm(f).show());
        btlListArticles.addActionListener(e -> new ListArticles(f).show());
        btnListcategories.addActionListener(e -> new ListCategoriesForm(f).show());
        f.addAll(btnAddTask, btlListArticles, btnListcategories);
                f.show();
                f.getToolbar().addMaterialCommandToLeftSideMenu("accueil", FontImage.MATERIAL_HOME, ev -> {
        try {
            new HomeForm();
        } catch (IOException ex) {
            
        }
    });
          f.getToolbar().addMaterialCommandToLeftSideMenu("profil", FontImage.MATERIAL_PEOPLE, ev -> new ListTasksForm(SessionUser.getUser().getId(),SessionUser.getUser().getUsername()));
        f.getToolbar().addMaterialCommandToLeftSideMenu("magazins", FontImage.MATERIAL_HOME_WORK, ev -> new ListTasksForm(SessionUser.getUser().getId(),SessionUser.getUser().getUsername()));
        f.getToolbar().addMaterialCommandToLeftSideMenu("logout", FontImage.MATERIAL_LOGOUT, ev -> new LoginForm());
}
    
}
