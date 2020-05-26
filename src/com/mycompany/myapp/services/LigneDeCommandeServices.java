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
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.LigneDeCommandeProduit;
import com.mycompany.myapp.entities.Panier;
import com.mycompany.myapp.gui.AffichageCommande;
import com.mycompany.myapp.gui.AjoutCommandeForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hatem
 */
public class LigneDeCommandeServices {
    
    
    public ArrayList<LigneDeCommandeProduit> getLignesCommandeProduit(Integer id) {
        ConnectionRequest con = new ConnectionRequest();
        System.out.println("id"+id);
        String Url = "https://depo-pi.herokuapp.com/api/afflcJson/"+id;
        con.setUrl(Url);
        ArrayList<LigneDeCommandeProduit> mapPanier = new ArrayList<>();
        con.addResponseListener((e) -> {
            String jsonRes = new String(con.getResponseData());
            try {
                JSONParser j = new JSONParser();

                Map<String, Object> lcommandes = j.parseJSON(new CharArrayReader(jsonRes.toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) lcommandes.get("root");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                for (Map<String, Object> obj : list) {
                    

                    LigneDeCommandeProduit lcp = new LigneDeCommandeProduit();
                    lcp.setIdLigneDeCommande((int)Float.parseFloat(obj.get("id").toString()));
   
                    lcp.setPrixTotal((double)obj.get("prixTotal"));
  
                    lcp.setQuantiteCommander(obj.get("qte").toString());

                    
                    mapPanier.add(lcp);
                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        
        return mapPanier;
    }
    
     public void addCommandeAndLigneDeCommande(Integer idu) {
        try {
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
            Commande c =new Commande();
connReq.setUrl("https://depo-pi.herokuapp.com/api/ajoutCommandeJson/"+idu);  
connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });

            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }
        public void addCommande(Commande c,Integer idu) {
        try {
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
        
connReq.setUrl("https://depo-pi.herokuapp.com/api/ajoutCommandeJson/"+idu+"/"+c.getAddresse());  
connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });

            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }           

    public void addLigneDeCommande(Integer idu, Panier p) {
                    System.out.println("idUUUUUUUUU"+idu);

        try {
            
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
            PanierServices ps = new PanierServices();
            
                        System.out.println("idUUUUUUUUU"+idu);

      
connReq.setUrl("https://depo-pi.herokuapp.com/api/ajoutLigneCommandeJson/" + idu +"/"+ p.getId()+ 
                    "?prixtot=" + p.getPrixTot()+ 
                    "&qte=" + p.getQte()
                    
         );  

        
connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });

            NetworkManager.getInstance().addToQueue(connReq);
            
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }
    
    
    
    
    public void ajouterCommandeAndLigneDeCommande(int id, int produit, String adresse, int somme) {
        try {
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
            Commande c =new Commande();
connReq.setUrl("https://depo-pi.herokuapp.com/api/commande/"+id+"/"+produit+"/"+adresse+"/"+somme);  
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
    
    


