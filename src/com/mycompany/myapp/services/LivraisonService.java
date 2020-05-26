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
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.entities.Magazin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dell
 */
public class LivraisonService {
    
    public ArrayList<Livraison> getListLivraisons(int id) {
         ConnectionRequest con = new ConnectionRequest();
        
        con.setUrl("https://depo-pi.herokuapp.com/api/livraisons/"+id);

        ArrayList<Livraison> listLivraisons = new ArrayList<>();
        con.addResponseListener((e) -> {
            MagazinServices ms = new MagazinServices();
            String jsonr = new String(con.getResponseData());

            try {

                JSONParser j = new JSONParser();
                Map<String, Object> magazins = j.parseJSON(new CharArrayReader(jsonr.toCharArray()));
                System.out.println(magazins);
                List<Map<String, Object>> list = (List<Map<String, Object>>) magazins.get("root");
                
                for (Map<String, Object> obj : list) {
               
                    Livraison ev = new Livraison(
                            (int) Float.parseFloat(obj.get("id").toString()),
                            obj.get("dateDepart").toString().substring(6, 16),
                            "ND",
                            obj.get("status").toString()
                          );
                    listLivraisons.add(ev);

                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });
        System.out.println(listLivraisons);
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listLivraisons;
    }
    
    public void modifierLivraison(int id, String status) {
         try{
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
             connReq.setUrl("http://depo-pi.herokuapp.com/api/livraison/"+id+"/"+status);
            connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });
            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }
    
}
