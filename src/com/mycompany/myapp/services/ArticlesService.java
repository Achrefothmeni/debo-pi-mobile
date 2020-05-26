/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.ui.geom.Dimension;
import com.codename1.ui.Font;
import com.codename1.ui.Slider;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.Display;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Border;
import com.mycompany.myapp.entities.Articles_request;
import com.mycompany.myapp.entities.Articles;
import com.mycompany.myapp.entities.Categories;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ArticlesService {

    public ArrayList<Articles> articles;
    public ArrayList<Categories> categories;
    String bb = "";
    public static ArticlesService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ArticlesService() {
        req = new ConnectionRequest();
    }

    public static ArticlesService getInstance() {
        if (instance == null) {
            instance = new ArticlesService();
        }
        return instance;
    }

    public boolean ReqArticle(Articles_request a) {
        String url = Statics.BASE_URL + "req_article?name=" + a.getName() + "&quantity=" + a.getQuantity() + "&description=" + a.getDescription();

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean AjoutArticle(Articles a) {
        String url = Statics.BASE_URL + "article/new?name=" + a.getName() + "&quantity=" + a.getQuantity() + "&description=" + a.getDescription()
                + "&label=" + a.getLabel() + "&rating=" + a.getRating();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Categories> parseCategories(String jsonText) {
        try {
            categories = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Categories c = new Categories();
                float id_category = Float.parseFloat(obj.get("id").toString());

                c.setId_category((int) id_category);
                c.setLabel(obj.get("label").toString());

                categories.add(c);
            }

        } catch (IOException ex) {
            Dialog.show("ERROR", "ERREURR dans parseArticles", new Command("OK"));
        }
        return categories;
    }

        public ArrayList<Articles> parseArticles(String jsonText) {
        try {
            articles = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {

                Articles a = new Articles();

                float id_article = Float.parseFloat(obj.get("id").toString());
                float rating = Float.parseFloat(obj.get("rating").toString());
                float quantity = Float.parseFloat(obj.get("qte").toString());
                double price = Double.parseDouble(obj.get("price").toString());
                a.setId_article((int) id_article);
                a.setName(obj.get("name").toString());
                a.setDescription(obj.get("description").toString());
                a.setLabel(obj.get("label").toString());
                a.setQuantity((int) quantity);
                a.setRating((int) rating);
                a.setPrice(price);
                articles.add(a);
            }

        } catch (IOException ex) {
            Dialog.show("ERROR", "ERREURR dans parseArticles", new Command("OK"));
        }
        return articles;
    }

    public ArrayList<Categories> getAllCategories() {
        String url = Statics.BASE_URL + "all_categories";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categories = parseCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }

    public ArrayList<Articles> getAllArticles() {
        String url = Statics.BASE_URL + "all_articles";
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseArticles(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }

    public ArrayList<Articles> getAllLatestArticles() {
        String url = Statics.BASE_URL + "Allbylatest_articles";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseArticles(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }
    
    public ArrayList<Articles> getAllNameArticles() {
        String url = Statics.BASE_URL + "Allbyname_articles";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseArticles(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }

    public ArrayList<Articles> getAllPriceArticles() {
        String url = Statics.BASE_URL + "AllbyPrice_articles";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseArticles(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }
//      ----------

    public ArrayList<Articles> getAllRateArticles() {
        String url = Statics.BASE_URL + "AllbyRate_articles";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseArticles(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }

//    ----------------------------------------------------------------------------

    public void UpdateRank(int id, int rate) {
        String url = Statics.BASE_URL + "Maj_article/" + id + "/" + rate;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public String ReturnImage(int id) {
        String url = Statics.BASE_URL + "all_images/" + id;
        req.setUrl(url);

        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            bb = str;
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return bb;
    }

//    ----------------------------------------------------------
}
