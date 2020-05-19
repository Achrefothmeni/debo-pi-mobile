/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;
import com.codename1.io.CharArrayReader;
import com.mycompany.myapp.entities.Produit;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ProduitServices {
        public ArrayList<Produit> getListProduit(String json) {

        ArrayList<Produit> listProduits = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> produits = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(produits);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) produits.get("root");


            for (Map<String, Object> obj : list) {
                Produit p = new Produit();
 
                // System.out.println(obj.get("id"));
             
               
                  p.setName(obj.get("name").toString());
             
                  p.setPrix(Double.parseDouble(obj.get("price").toString()));
              
                //float promotion = Float.parseFloat(obj.get("promotion").toString());
           
         //  Float.parseFloat(obj.get("qte").toString());
               p.setDescription(obj.get("description").toString());
             
                p.setImage(obj.get("image").toString());
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
              
               
         
              //  p.setPromotion((int)promotion);
              
              
                
     
                System.out.println(p);
                listProduits.add(p);

            }

        } catch (IOException ex) {
        }
        System.out.println(listProduits);
        return listProduits;

    }
    
    
    ArrayList<Produit> listProduits = new ArrayList<>();
    
    public ArrayList<Produit> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("https://depo-pi.herokuapp.com/api/articles/");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitServices ser = new ProduitServices();
                
                listProduits = ser.getListProduit(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduits;
    }
    
       /* public void ajout(Produit p, Integer id) {
        try {
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
         //   connReq.setUrl("http://localhost/SoukLemdina/web/ecommerce/ajout-produitWS/" + id + "/"+ p.getLibelle()+"/"+p.getDescription()+"/"+p.getImage()+"/"+p.getPrix()+"/"+p.getQuqntite()+"/"+p.getPromotion());
            connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });
            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }*/
    
    
    
}
