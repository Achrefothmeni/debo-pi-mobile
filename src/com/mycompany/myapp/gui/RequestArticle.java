/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.mycompany.myapp.entities.Articles_request;
import com.mycompany.myapp.services.ArticlesService;
import java.util.Date;

/**
 *
 * @author MedLah
 */
public class RequestArticle extends Form {
    public RequestArticle(Form previous) {

           Container c = new Container (new FlowLayout(CENTER,CENTER) ); 
            Container c1 = new Container(BoxLayout.y());
          c.setScrollableY(false);
        setTitle("Demander un Article");
        setLayout(BoxLayout.y());
        TextField article_name = new TextField("", "nom d'article");
        TextField article_quantity = new TextField("", "Quantité");
        TextComponent article_description = new TextComponent().multiline(true).rows(5).hint("Description");
        article_name.getAllStyles().setBorder(RoundBorder.create().rectangle(true).
                                        color(0xffffff).
                                        strokeColor(0x7EC0EE).strokeOpacity(80));
        article_quantity.getAllStyles().setBorder(RoundBorder.create().rectangle(true).
                                        color(0xffffff).
                                        strokeColor(0x7EC0EE).strokeOpacity(80));
        article_description.getAllStyles().setBorder(RoundBorder.create().rectangle(true).
                                        color(0xffffff).
                                        strokeColor(0x7EC0EE).strokeOpacity(80));
        Button btnValider = new Button("AJOUTER");
        
        btnValider.getAllStyles().setBgColor(ColorUtil.rgb(0, 255, 255));
        btnValider.getAllStyles().setFgColor(0x00FFFF);
        btnValider.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        
        Date req_date = new Date();
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((article_name.getText().length() == 0) || (article_quantity.getText().length() == 0 || (article_description.getText().length() == 0))) {
                    Dialog.show("Alert", "Remplir tous les champs svp!", new Command("OK"));
                } else {
                    try {
                        Articles_request a = new Articles_request(Integer.parseInt(article_quantity.getText()), article_name.getText(), article_description.getText(), req_date);
                        if (ArticlesService.getInstance().ReqArticle(a)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "quantity must be a number", new Command("OK"));
                    }

                }

            }
        });
        c1.addAll(article_name, article_quantity, article_description);
             c.add(c1);
           add(c);
              add(btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
