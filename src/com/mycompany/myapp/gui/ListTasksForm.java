/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.processing.Result;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.EAST;
import static com.codename1.ui.CN.SOUTH;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.DateTimeSpinner;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.regex.RE;
import com.mycompany.myapp.entities.Articles;
import com.mycompany.myapp.entities.Magazin;
import com.mycompany.myapp.services.MagazinServices;

import com.mycompany.myapp.utils.SessionUser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;




/**
 *
 * @author bhk
 */
public class ListTasksForm {
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

   

    MagazinServices ms = new MagazinServices();

    public ListTasksForm(int idU,String name) {
        Font smallBoldSystemFont = Font.createTrueTypeFont("native:ItalicBold", "native:ItalicBold").derive(Display.getInstance().convertToPixels(3), Font.STYLE_PLAIN);
        Font smallLightSystemFont = Font.createTrueTypeFont("native:ItalicLight", "native:ItalicLight").derive(Display.getInstance().convertToPixels((float) 2.2), Font.STYLE_ITALIC);
Form fe = new Form("liste des magazins du magazinier "+name, BoxLayout.y());


        
       
        
        ArrayList<Magazin> lev = ms.getListMagazins(idU);
       Container rc = new Container(new BorderLayout());  
     TextField t = new TextField("", "recherche");
     Button rec = new Button("Rechercher");
     Button all = new Button("All");
     rec.addActionListener((ev) -> {
         if(t.getText().equals(""))
         {
              //media.play();
    Dialog.show("Attention!!", "champs recherche vide", "Ok", null);
         }
         else{
             new ListTasksForm(22, "ali", t.getText());
       }});
     all.addActionListener((ev) -> {
         new ListTasksForm(22, "ali");
       });
     rc.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER,t);
     rc.addComponent(EAST, all);
     rc.addComponent(SOUTH, rec);
     fe.add(rc);
Container cc = new Container(new BoxLayout(BoxLayout.X_AXIS));
            //Container cy = new Container(BoxLayout.y());
            Label idl = new Label("id Magazin");
    
         idl.getAllStyles().setFgColor(ColorUtil.BLUE);
           
            Label locationl = new Label("Location");
              locationl.getAllStyles().setFgColor(ColorUtil.BLUE);
              
             Label categoryy = new Label ("Category");
               categoryy.getAllStyles().setFgColor(ColorUtil.BLUE);
            Label capacityl = new Label ("Capacity");
              capacityl.getAllStyles().setFgColor(ColorUtil.BLUE);
            Label capacityRestl = new Label ("Capacity restante");
              capacityRestl.getAllStyles().setFgColor(ColorUtil.BLUE);
            Button capacityRestt = new Button ("détail");
            
              capacityRestt.getAllStyles().setFgColor(ColorUtil.BLUE);
            cc.addAll(idl,locationl,categoryy,capacityl,capacityRestl,capacityRestt);
         if(!lev.isEmpty())
         {
            fe.add(cc);}
      
        
        for (Magazin mm : lev) {

            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            //Container cy = new Container(BoxLayout.y());
            Label id = new Label(mm.getIdMagazin()+"");
            Label id1 = new Label("Id Magazin");
            id.getAllStyles().setFgColor(ColorUtil.BLACK);
            id.getPreferredSize().setWidth(id1.getPreferredSize().getWidth());
            id.getAllStyles().setAlignment(CENTER);
            Label location = new Label(mm.getLocation()+"");
             Label location1 = new Label("Location");
            location.getAllStyles().setFgColor(ColorUtil.BLACK);
            location.getPreferredSize().setWidth(location1.getPreferredSize().getWidth());
            Label category = new Label(mm.getCategory()+""); 
if(mm.getCategory().equals("cold"))
{
    category.getAllStyles().setFgColor(ColorUtil.MAGENTA);
}
else if(mm.getCategory().equals("brittle"))
{
    category.getAllStyles().setFgColor(ColorUtil.CYAN);
}
else{
            category.getAllStyles().setFgColor(ColorUtil.GRAY);}
            category.getPreferredSize().setWidth(categoryy.getPreferredSize().getWidth());
            Label capacity = new Label (mm.getCapacity()+"");
            Label capacity1 = new Label ("Capacity");
            capacity.getAllStyles().setFgColor(ColorUtil.BLACK);
            capacity.getPreferredSize().setWidth(capacity1.getPreferredSize().getWidth());
            Label capacityRest = new Label (mm.getCapacityRest()+"");
            Label capacityRest1 = new Label ("Capacity restante");
            int a = mm.getCapacity()/3;
            if(mm.getCapacityRest()==mm.getCapacity())
            {
                capacityRest.getAllStyles().setFgColor(ColorUtil.rgb(34, 139, 34));
            }
            
            else if(mm.getCapacityRest()<=a)
            {
                capacityRest.getAllStyles().setFgColor(ColorUtil.rgb(255,0,0));
            }
                    else{
            capacityRest.getAllStyles().setFgColor(ColorUtil.rgb(255,165,0));}
            capacityRest.getPreferredSize().setWidth(capacityRest1.getPreferredSize().getWidth());
            Button det = new Button("détail");
            det.getAllStyles().setFgColor(ColorUtil.GREEN);
            det.setSize(new Dimension(5, 5));
             det.addActionListener((ev) -> {
                try {
                    new TestForm(mm);
                } catch (IOException ex) {
                    
                }
            }
);
                
           
            
            c1.addAll(id,location,category,capacity,capacityRest,det);
           c1.getAllStyles().setBorder(Border.createInsetBorder(2, ColorUtil.BLACK));
    
           

            //fe.getAllStyles().setBgTransparency(200);
            fe.add(c1);
            //fe.add(c1);
            /*fe.add(det); 
            add(supp);*/
            //fd.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new ListTasksForm().show());
                  
//fe.show();
        }
        
        
      fe.getAllStyles().setBgColor(ColorUtil.WHITE);
      fe.getToolbar().addMaterialCommandToLeftSideMenu("accueil", FontImage.MATERIAL_HOME, ev -> {
            try {
                new HomeForm();
            } catch (IOException ex) {
                
            }
        });
       fe.getToolbar().addMaterialCommandToLeftSideMenu("profil", FontImage.MATERIAL_PEOPLE, ev -> new ListTasksForm(22,"ali"));
        fe.getToolbar().addMaterialCommandToLeftSideMenu("magazins", FontImage.MATERIAL_HOME_WORK, ev -> new ListTasksForm(22,"ali"));
        fe.getToolbar().addMaterialCommandToLeftSideMenu("logout", FontImage.MATERIAL_LOGOUT, ev -> new LoginForm());
        fe.show();
        
    }
public ListTasksForm(int idU,String name,String rech)  {
       //Media media = MediaManager.createBackgroundMedia("file:///C:/Users/nouri/Downloads/WorkshopParsingJson/WorkshopOarsingJson/src/com/mycompany/myapp/gui/wrong-buzzer-sound-effect.mp3");
   // Media med = MediaManager.createBackgroundMedia("file:///C:/Users/nouri/Downloads/WorkshopParsingJson/WorkshopOarsingJson/src/com/mycompany/myapp/gui/aaaa.mp3");
Form fe = new Form("liste des magazins du magazinier "+name, BoxLayout.y());

        ArrayList<Magazin> lev = ms.recherche(idU,rech);
        if(lev.isEmpty())
         {
              //med.play();
    Dialog.show("Attention!!", "résultat vide", "Ok", null);
         }
       Container rc = new Container(new BorderLayout());  
     TextField t = new TextField("", "recherche");
     Button rec = new Button("Rechercher");
     Button all = new Button("All");
     rec.addActionListener((ev) -> {
         new ListTasksForm(22, "ali", t.getText());
       });
     all.addActionListener((ev) -> {
         new ListTasksForm(22, "ali");
       });
   
 rc.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER,t);
     rc.addComponent(EAST, all);
     rc.addComponent(SOUTH, rec);
     fe.add(rc);
Container cc = new Container(new BoxLayout(BoxLayout.X_AXIS));
            //Container cy = new Container(BoxLayout.y());
            Label idl = new Label("id Magazin");
    
         idl.getAllStyles().setFgColor(ColorUtil.BLUE);
           
            Label locationl = new Label("Location       ");
              locationl.getAllStyles().setFgColor(ColorUtil.BLUE);
              
             Label categoryy = new Label ("Category");
               categoryy.getAllStyles().setFgColor(ColorUtil.BLUE);
            Label capacityl = new Label ("Capacity");
              capacityl.getAllStyles().setFgColor(ColorUtil.BLUE);
            Label capacityRestl = new Label ("Capacity restante");
              capacityRestl.getAllStyles().setFgColor(ColorUtil.BLUE);
            Button capacityRestt = new Button ("détail");
            
              capacityRestt.getAllStyles().setFgColor(ColorUtil.BLUE);
            cc.addAll(idl,locationl,categoryy,capacityl,capacityRestl,capacityRestt);
         if(!lev.isEmpty())
         {
            fe.add(cc);}
      
        
        for (Magazin mm : lev) {

            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            //Container cy = new Container(BoxLayout.y());
            Label id = new Label(mm.getIdMagazin()+"");
            Label id1 = new Label("Id Magazin");
            id.getAllStyles().setFgColor(ColorUtil.BLACK);
            id.getPreferredSize().setWidth(id1.getPreferredSize().getWidth());
            id.getAllStyles().setAlignment(com.codename1.ui.Component.CENTER);
            Label location = new Label(mm.getLocation()+"");
             Label location1 = new Label("Location       ");
            location.getAllStyles().setFgColor(ColorUtil.BLACK);
            location.getPreferredSize().setWidth(location1.getPreferredSize().getWidth());
            Label category = new Label(mm.getCategory()+""); 
if(mm.getCategory().equals("cold"))
{
    category.getAllStyles().setFgColor(ColorUtil.MAGENTA);
}
else if(mm.getCategory().equals("brittle"))
{
    category.getAllStyles().setFgColor(ColorUtil.CYAN);
}
else{
            category.getAllStyles().setFgColor(ColorUtil.GRAY);}
            category.getPreferredSize().setWidth(categoryy.getPreferredSize().getWidth());
            Label capacity = new Label (mm.getCapacity()+"");
            Label capacity1 = new Label ("Capacity");
            capacity.getAllStyles().setFgColor(ColorUtil.BLACK);
            capacity.getPreferredSize().setWidth(capacity1.getPreferredSize().getWidth());
            Label capacityRest = new Label (mm.getCapacityRest()+"");
            Label capacityRest1 = new Label ("Capacity restante");
            int a = mm.getCapacity()/3;
            if(mm.getCapacityRest()==mm.getCapacity())
            {
                capacityRest.getAllStyles().setFgColor(ColorUtil.rgb(34, 139, 34));
            }
            
            else if(mm.getCapacityRest()<=a)
            {
                capacityRest.getAllStyles().setFgColor(ColorUtil.rgb(255,0,0));
            }
                    else{
            capacityRest.getAllStyles().setFgColor(ColorUtil.rgb(255,165,0));}
            capacityRest.getPreferredSize().setWidth(capacityRest1.getPreferredSize().getWidth());
            Button det = new Button("détail");
            det.getAllStyles().setFgColor(ColorUtil.GREEN);
            det.setSize(new Dimension(5, 5));
             det.addActionListener((ev) -> {
                try {
                    new TestForm(mm);
                } catch (IOException ex) {
                    
                }
            }
);
                
           
            
            c1.addAll(id,location,category,capacity,capacityRest,det);
           c1.getAllStyles().setBorder(Border.createInsetBorder(2, ColorUtil.BLACK));
    
           

            //fe.getAllStyles().setBgTransparency(200);
            fe.add(c1);
            //fe.add(c1);
            /*fe.add(det); 
            add(supp);*/
            //fd.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new ListTasksForm().show());
                  
//fe.show();
        }
        
        
      fe.getAllStyles().setBgColor(ColorUtil.WHITE);
      fe.getToolbar().addMaterialCommandToLeftSideMenu("accueil", FontImage.MATERIAL_HOME, ev -> {
            try {
                new HomeForm();
            } catch (IOException ex) {
                
            }
        });
       fe.getToolbar().addMaterialCommandToLeftSideMenu("profil", FontImage.MATERIAL_PEOPLE, ev -> {
           new ListTasksForm(22,"ali");
       });
        fe.getToolbar().addMaterialCommandToLeftSideMenu("magazins", FontImage.MATERIAL_HOME_WORK, ev -> {
            new ListTasksForm(22,"ali");
       });
        fe.getToolbar().addMaterialCommandToLeftSideMenu("logout", FontImage.MATERIAL_LOGOUT, ev -> new LoginForm());
        fe.show();
        
    }

    public boolean isNumber(String s) {
        RE ra = new RE("(20|21|22|70|71|50|51)[0-9][0-9][0-9][0-9][0-9][0-9]$");
        boolean matcher = ra.match(s);
        return matcher;
    }

    public Form getFd() {
        return fd;
    }

    public void setFd(Form fd) {
        this.fd = fd;
    }

    
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    

