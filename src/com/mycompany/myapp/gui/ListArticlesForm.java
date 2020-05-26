/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Component;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.util.AsyncResource;
import com.mycompany.myapp.entities.Articles;
import com.mycompany.myapp.services.ArticlesService;
import java.util.ArrayList;

public class ListArticlesForm extends Form {

    Container c = new Container(new FlowLayout(CENTER, CENTER));
    Container c1 = new Container(BoxLayout.y());
    Font font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
    ArrayList<Articles> articles = ArticlesService.getInstance().getAllArticles();
    Font largePlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);

    public ListArticlesForm(Form previous) {
//        Container c1 = new Container(new FlowLayout(BoxLayout.Y_AXIS));
//        add(c1);
        setTitle("List all articles");
        SpanLabel sp = new SpanLabel();

        sp.setText(ArticlesService.getInstance().getAllArticles().toString());
        Button OrderNewest = new Button("get the lastest Articles on top");
        String[] header = new String[]{"nom", "Image", "Description", "libelle", "quantité", "évaluation", "évaluer"};
        Object[][] rows = new Object[articles.size()][];
        for (int iter = 0; iter < rows.length; iter++) {
            rows[iter] = new Object[]{
                articles.get(iter).getName(),
                articles.get(iter).getId_article(),
                articles.get(iter).getDescription(),
                articles.get(iter).getLabel(),
                articles.get(iter).getQuantity(),
                articles.get(iter).getRating() + "/5",
                articles.get(iter).getId_article()
            };
        }

        TableModel model = new DefaultTableModel(header, rows);
        Table table = new Table(model) {
            @Override
            protected Component createCell(Object value, int row, int column, boolean editable) {
                Component cell = null;

                Button eval = new Button("Evaluate");
                Button img = new Button("See image");
                eval.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        System.out.println("RATE inside evalActionListener" + value.toString());
                        Dialog.show("please chose a rate", FlowLayout.encloseCenter(createStarRankSlider(value.toString())), new Command("Cancel"));
                    }
                });

                img.addActionListener((ActionListener) (ActionEvent evt) -> {
                    int id2 = Integer.parseInt(value.toString());
                    String urlimg = "http://localhost/debo-pi-master/web/images/", img3 = "";
                    String img2 = ArticlesService.getInstance().ReturnImage(id2);
                    Dialog dialog = new Dialog("Image :", BoxLayout.y());
                    dialog.add(new SpanLabel("this is an image of the product"));
                    img3 = img2.substring(1, img2.length() - 1);
                    urlimg = urlimg.concat(img3);
                    System.out.println(urlimg);
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(CN.convertToPixels(30), CN.convertToPixels(30), 0x00ffffff), true);
                    URLImage meditationIcon = URLImage.createToStorage(placeholder, "ArticleImage" + id2,
                            urlimg, URLImage.RESIZE_SCALE_TO_FILL);
                    meditationIcon.fetch();
                    dialog.add(FlowLayout.encloseCenter(new Label(meditationIcon)));
                    dialog.setDisposeWhenPointerOutOfBounds(true);
                    Button cancel = new Button("Cancel");
                    cancel.addActionListener(ll -> dialog.dispose());
                    dialog.add(cancel);
                    dialog.show();
//Dialog.show("Image:", images(value.toString()), new Command("Cancel"));
                });
                cell = super.createCell(value, row, column, editable);
                if (row == -1) { // (2)
                    cell.getAllStyles().setBgColor(ColorUtil.CYAN);
                    cell.getAllStyles().setFgColor(0x00FFFF);
                }
                if (row > -1 && row % 2 == 0) { // (5)
                    // pinstripe effect 
                    cell.getAllStyles().setBgColor(0xeeeeee);
                    cell.getAllStyles().setBgTransparency(255);
                }
                if (row > -1 && column == 1) {
                    //add(img);
                    return img;
                }
                if (row > -1 && column == 6) {
                    //add(eval);
                    return eval;
                }

                return cell;
            }
        };

       // add(OrderNewest);

        c1.add(table);
        c.add(c1);
        table.getAllStyles()
                .setFgColor(ColorUtil.BLUE);
        table.setDrawBorder(focusScrolling);

        table.getAllStyles()
                .setBackgroundGradientStartColor(8);
        table.getAllStyles()
                .setBackgroundType(Byte.MIN_VALUE);
        table.getStyle()
                .setBgTransparency(255);
        table.getAllStyles()
                .setMarginBottom(15);
        table.getAllStyles()
                .setMarginLeft(15);
        table.getAllStyles()
                .setMarginRight(15);
        table.getAllStyles()
                .setMarginTop(15);
        table.setSortSupported(
                true);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        add(c);
        OrderNewest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("click order new");
                c.removeAll();
                System.out.println("remove table");
                ArrayList<Articles> articlesLatest = ArticlesService.getInstance().getAllLatestArticles();
                for (int iter = 0; iter < rows.length; iter++) {
                    rows[iter] = new Object[]{
                        articlesLatest.get(iter).getName(), articlesLatest.get(iter).getDescription(), articlesLatest.get(iter).getLabel(), articlesLatest.get(iter).getQuantity(),
                        articlesLatest.get(iter).getRating()//, add(createStarRankSlider(articles.get(iter).getId_article()))
                    };
                }
                TableModel model2 = new DefaultTableModel(new String[]{"name", "description", "label", "quantity", "rating", "rate"}, rows);
                final Table table2 = new Table(model2);
                repaint();
                refreshTheme();
                c1.add(table2);
                c.add(c1);
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            }
        }
        );

    }

    public ImageViewer images(String id) {
        int id2 = Integer.parseInt(id);
        String img = ArticlesService.getInstance().ReturnImage(id2);
        Image placeholder = Image.createImage(getWidth() / 3 - 4, getWidth() / 3 - 4, 0xbfc9d2);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        ImageViewer img1 = new ImageViewer(URLImage.createToStorage(encImage, "file" + img,
                "http://localhost/debo-pi-master/web/images/" + img));

        return img1;
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider(String id) {
        int id2 = Integer.parseInt(id);
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
        int fontSize = Display.getInstance().convertToPixels(3);
        Font fnt = Font.createTrueTypeFont("Handlee", "Handlee-Regular.ttf").
                derive(fontSize, Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        starRank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int score = starRank.getProgress() / 2;
                ArticlesService.getInstance().UpdateRank(id2, score);
                System.out.println("getIncrements=" + score);
                Dialog.show("Success", "thank you for rating our product", new Command("OK"));
                repaint();
                refreshTheme();
            }
        });

        return starRank;
    }

}
