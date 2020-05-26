/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableModel;
import com.mycompany.myapp.entities.Categories;
import com.mycompany.myapp.services.ArticlesService;
import java.util.ArrayList;

public class ListCategoriesForm extends Form {

    public ListCategoriesForm(Form previous) {
        //Container cnt = new Container(new BoxLayout(BoxLayout.X_AXIS));

        setTitle("List all Categories");
        SpanLabel sp = new SpanLabel();
        sp.setText(ArticlesService.getInstance().getAllArticles().toString());
        ArrayList<Categories> categories = ArticlesService.getInstance().getAllCategories();
        Object[][] rows = new Object[categories.size()][];
        for (int iter = 0; iter < rows.length; iter++) {
            rows[iter] = new Object[]{
                categories.get(iter).getLabel()};

        }
        
        TableModel model = new DefaultTableModel(new String[]{"Label"}, rows);
        Table table = new Table(model) {
            @Override
            protected Component createCell(Object value, int row, int column, boolean editable) {
                Component cell = null;
                cell = super.createCell(value, row, column, editable);

                if (row > -1) {
                    cell.getAllStyles().setBgColor(ColorUtil.BLUE);
                    cell.getAllStyles().setFgColor(0x33FFFF);
                }
                return cell;
            }
        };
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
        add(table);
        table.setSortSupported(true);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
