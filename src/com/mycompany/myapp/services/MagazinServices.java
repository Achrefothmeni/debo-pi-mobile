/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.mycompany.myapp.entities.Articles;
import com.mycompany.myapp.entities.Magazin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nouri
 */
public class MagazinServices {

    public ArrayList<Magazin> getListMagazins(int id) {
         ConnectionRequest con = new ConnectionRequest();
        
        con.setUrl("http://depo-pi.herokuapp.com/api/afficherMM/"+id);

        ArrayList<Magazin> listMagazins = new ArrayList<>();
        con.addResponseListener((e) -> {
            MagazinServices ms = new MagazinServices();
            String jsonr = new String(con.getResponseData());

            try {

                JSONParser j = new JSONParser();
                Map<String, Object> magazins = j.parseJSON(new CharArrayReader(jsonr.toCharArray()));
                System.out.println(magazins);
                List<Map<String, Object>> list = (List<Map<String, Object>>) magazins.get("root");
                
                for (Map<String, Object> obj : list) {
               
                    Magazin ev = new Magazin(
                            obj.get("id").toString(),
                            (int) Float.parseFloat(obj.get("capacity").toString()),
                            obj.get("location").toString(),
                            obj.get("category").toString(),
                            (int) Float.parseFloat(obj.get("capacityRest").toString())
                          );
                    listMagazins.add(ev);

                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });
        System.out.println(listMagazins);
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listMagazins;
    
    }

    public ArrayList<Articles> getListArticles(String id) {
         ConnectionRequest con = new ConnectionRequest();
        
        con.setUrl("http://depo-pi.herokuapp.com/api/afficherAA/"+id);

        ArrayList<Articles> listArticles = new ArrayList<>();
        con.addResponseListener((e) -> {
            MagazinServices ms = new MagazinServices();
            String jsonr = new String(con.getResponseData());

            try {

                JSONParser j = new JSONParser();
                Map<String, Object> articles = j.parseJSON(new CharArrayReader(jsonr.toCharArray()));
                System.out.println(articles);
                List<Map<String, Object>> list = (List<Map<String, Object>>) articles.get("root");
                
                for (Map<String, Object> obj : list) {
               
                    Articles ev = new Articles(
                            (int) Float.parseFloat(obj.get("id").toString()),
                            obj.get("name").toString(),
                            (int) Float.parseFloat(obj.get("quantity").toString())
                          );
                    listArticles.add(ev);

                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });
        System.out.println(listArticles);
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listArticles;
    
    }

    public void Delete(int id_article, String idMagazin) {
        try{
           
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
             connReq.setUrl("http://depo-pi.herokuapp.com/api/deleteAA/" +id_article+"/"+idMagazin
            );
        

            connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });
            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }

    public void add(int id, String x,String idM) {
         try{
           
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
             connReq.setUrl("http://depo-pi.herokuapp.com/api/changerAA/" +id+"/"+x+"/"+idM
            );
        

            connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });
            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
        
    }

    public void send(String idMagazin) {
        try{
           
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
             connReq.setUrl("http://depo-pi.herokuapp.com/api/sendAA/" +idMagazin
            );
        

            connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });
            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
        
    }

    public void sms(String idMagazin) {
        try{
           
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
             connReq.setUrl("http://depo-pi.herokuapp.com/api/smsAA/" +idMagazin
            );
        

            connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });
            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
        
        
    }
    public ArrayList<String> getAdmin() {
         ConnectionRequest con = new ConnectionRequest();
        
        con.setUrl("http://depo-pi.herokuapp.com/api/admin/");

        ArrayList<String> s = new ArrayList<>();
        con.addResponseListener((e) -> {
            MagazinServices ms = new MagazinServices();
            String jsonr = new String(con.getResponseData());

            try {

                JSONParser j = new JSONParser();
                Map<String, Object> strings = j.parseJSON(new CharArrayReader(jsonr.toCharArray()));
                System.out.println(strings);
                List<Map<String, Object>> list = (List<Map<String, Object>>) strings.get("root");
                
                for (Map<String, Object> obj : list) {
               
                     s.add(obj.get("num").toString());
                              
                    s.add(obj.get("email").toString());

                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });
        System.out.println(s);
        NetworkManager.getInstance().addToQueueAndWait(con);
        return s;
    
    }
    
    
}
