/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.mycompany.entities.Reclamation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bazinfo
 */
public class ReclamationService {
    public void add(String type, String objet, String message, int id_client) {
         try{
           
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
            connReq.setUrl("https://depo-pi.herokuapp.com/api/createReclamation/"+type+"/"+objet+"/"+message+"/"+id_client);
            System.out.println("succes");

        

            connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });
            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }   
    }
    
    public ArrayList<Reclamation> getReclamations(int id) {
         ConnectionRequest con = new ConnectionRequest();
        
        con.setUrl("https://depo-pi.herokuapp.com/api/showReclamation/"+id);

        ArrayList<Reclamation> listReclamations = new ArrayList<>();
        con.addResponseListener((e) -> {
            ReclamationService rs = new ReclamationService();
            String jsonr = new String(con.getResponseData());

            try {

                JSONParser j = new JSONParser();
                Map<String, Object> magazins = j.parseJSON(new CharArrayReader(jsonr.toCharArray()));
                System.out.println(magazins);
                List<Map<String, Object>> list = (List<Map<String, Object>>) magazins.get("root");
                
                for (Map<String, Object> obj : list) {
               
                    Reclamation rec = new Reclamation(
                            obj.get("type").toString(),
                            obj.get("message").toString(),
                            obj.get("objet").toString(),
                            obj.get("status").toString());
                    listReclamations.add(rec);

                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });
        System.out.println(listReclamations);
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReclamations;
    
    }
}
