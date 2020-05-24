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
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Articles;
import com.mycompany.myapp.entities.Magazin;
import com.mycompany.myapp.services.MagazinServices;
import com.mycompany.myapp.utils.SessionUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;





/**
 *
 * @author nouri
 */
public class TestForm {
   Form fd;
    Resources theme = UIManager.initFirstTheme("/theme");
    Media media = MediaManager.createBackgroundMedia("file:///C:/Users/nouri/Downloads/WorkshopParsingJson/WorkshopOarsingJson/src/com/mycompany/myapp/gui/wrong-buzzer-sound-effect.mp3");
    Media med = MediaManager.createBackgroundMedia("file:///C:/Users/nouri/Downloads/WorkshopParsingJson/WorkshopOarsingJson/src/com/mycompany/myapp/gui/aaaa.mp3");
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
    return renderer;}
MagazinServices ms = new MagazinServices();
    
    public TestForm(Magazin m) throws IOException
            {
               ArrayList <String> names = new ArrayList<>();
               int[] values = new int[m.getCapacity()];
               int[] colors = new int[m.getCapacity()];
                fd = new Form("liste des articles du magazin "+m.getIdMagazin(), BoxLayout.y()); 
        ArrayList<Articles> e = ms.getListArticles(m.getIdMagazin());
        int k =0;
        Random rand = new Random();
        
            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            
            Label i = new Label("id");
            i.getAllStyles().setFgColor(ColorUtil.BLUE);
            i.getPreferredSize().setWidth(200);
            i.getAllStyles().setAlignment(CENTER);
            Label nom = new Label("name");
            nom.getAllStyles().setFgColor(ColorUtil.BLUE);
            nom.getPreferredSize().setWidth(500);
            Label capacity = new Label("capacity");
            capacity.getAllStyles().setFgColor(ColorUtil.BLUE);
            capacity.getPreferredSize().setWidth(250);
            Button deplacer = new Button("deplacer");
            deplacer.getAllStyles().setFgColor(ColorUtil.BLUE);
            Button supprimer = new Button("supprimer");
            supprimer.getAllStyles().setFgColor(ColorUtil.BLUE);
            c1.addAll(i,nom,capacity,deplacer,supprimer);
            if(!e.isEmpty())
            {
            fd.add(c1);}
        for (Articles aa : e) {
names.add(aa.getName());
values[k]=aa.getQuantity();
int r = rand.nextInt();
int g = rand.nextInt();
int b = rand.nextInt();
colors[k]=ColorUtil.rgb(r, g, b);
k++;



            Container c11 = new Container(new BoxLayout(BoxLayout.X_AXIS));
         
            //Container cy = new Container(BoxLayout.y());
            Label idx = new Label(aa.getId_article()+"");
            idx.getAllStyles().setAlignment(CENTER);
            idx.getAllStyles().setFgColor(ColorUtil.BLACK);
            idx.getPreferredSize().setWidth(200);
            Label name = new Label(aa.getName());
            name.getAllStyles().setFgColor(ColorUtil.BLACK);
            name.getPreferredSize().setWidth(500);
            Label quantity = new Label(aa.getQuantity()+"");
            quantity.getAllStyles().setFgColor(ColorUtil.BLACK);
            quantity.getPreferredSize().setWidth(250);
            Button det = new Button("déplacer");
            det.getAllStyles().setFgColor(ColorUtil.GREEN);
            det.setSize(new Dimension(5, 5));
             det.addActionListener((ev) -> {
    try {
        new Test2Form(aa.getId_article(),aa.getQuantity(),m);
    } catch (IOException ex) {
      
    }
}
);
                 Button supps = new Button("supprimer");
                 supps.getAllStyles().setFgColor(0xff0000);
            supps.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
             //Dialog.show("Alerte", "etes vous surz de vouloir supprimer", "Ok", null);
             med.play();
             Boolean x =Dialog.show("Attention!!!", "vous voulez supprimer le produit "+aa.getId_article(),"Oui", "Non");
             if(x)
             { ms.Delete(aa.getId_article(),m.getIdMagazin());
                 try {
                     TestForm mm = new TestForm(m);
                 } catch (IOException ex) {
                     
                 }
}

                                
                }
            });
                
                
               
                c11.addAll(idx,name,quantity,det,supps);
                
                  c11.getAllStyles().setBorder(Border.createInsetBorder(2, ColorUtil.BLACK));
                fd.add(c11);
                }
        names.add("capacité restante");
values[k]=m.getCapacityRest();
int r = rand.nextInt();
int g = rand.nextInt();
int b = rand.nextInt();
colors[k]=ColorUtil.GRAY;
        final DefaultRenderer renderer = buildCategoryRenderer(colors);
        
        
        for (SimpleSeriesRenderer s : renderer.getSeriesRenderers()) {
            s.setGradientEnabled(true);
            s.setGradientStart(0, shiftColor(s.getColor(), 0.8));
            s.setGradientStop(0, shiftColor(s.getColor(), 1.5));
        }
       renderer.setZoomButtonsVisible(true);
 renderer.setZoomEnabled(true);
 renderer.setChartTitleTextSize(70);
 //renderer.setLegendHeight(RIGHT);
 renderer.setDisplayValues(false);
 renderer.setShowLabels(true);
 renderer.setLabelsColor(ColorUtil.BLACK);
 renderer.setLabelsTextSize(40);
 renderer.setLegendTextSize(40);
 renderer.setChartTitle("MagazinCapacity");
 
 SimpleSeriesRenderer s = renderer.getSeriesRendererAt(0);
 s.setHighlighted(true);
        final CategorySeries seriesSet = buildCategoryDataset("Articles",names, values);
        
        final PieChart chart = new PieChart(seriesSet, renderer);
        
        ChartComponent c = new ChartComponent(chart);
        
        if (e.isEmpty())
        {
            String ss[] ={"iskanderregaieg3@gmail.com"};
        Message me = new Message("le debo est vide!!!!");
            
            
            
            Button b1 = new Button("envoyer email");
            Button b2 = new Button("envoyer sms");
             b1.getAllStyles().setFgColor(ColorUtil.MAGENTA);
         
            b2.getAllStyles().setFgColor(ColorUtil.YELLOW);
             b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt)
                {
                
                Display.getInstance().sendMessage(ss, "Debo "+m.getIdMagazin()+"est vide", me);
                //ms.send(m.getIdMagazin(),"iskanderregaieg3@gmail.com","ali");
                }});
              b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt)
                {
                
                    try {
                        Display.getInstance().sendSMS("50802348", "le Debo est vide!!!!");
                        ms.sms(m.getIdMagazin());
                        
                    } catch (IOException ex) {
                        
                    }
                }});
              Container cc = new Container(new FlowLayout(CENTER,CENTER));
          cc.addAll(b1,b2);
          fd.add(cc);
        }
        else {
           
            Label x = new Label("     ");
         Container cm = new Container(new BoxLayout(BoxLayout.X_AXIS));
         cm.add(x);
         fd.add(cm);
            fd.add(c);
        }
        
         
        fd.getAllStyles().setBgColor(ColorUtil.WHITE);
        fd.show();
        fd.getToolbar().addMaterialCommandToLeftSideMenu("accueil", FontImage.MATERIAL_HOME, ev -> {
                   try {
                       new HomeForm();
                   } catch (IOException ex) {
                      
                   }
               });
        fd.getToolbar().addMaterialCommandToLeftSideMenu("profil", FontImage.MATERIAL_PEOPLE, ev -> new ListTasksForm(22,"ali"));
        fd.getToolbar().addMaterialCommandToLeftSideMenu("magazins", FontImage.MATERIAL_HOME_WORK, ev -> new ListTasksForm(22,"ali"));
        fd.getToolbar().addMaterialCommandToLeftSideMenu("logout", FontImage.MATERIAL_LOGOUT, ev -> new LoginForm());
        fd.getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, ev -> new ListTasksForm(22,"ali"));
             }
    
}
