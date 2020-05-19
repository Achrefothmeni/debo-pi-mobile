/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;


import com.mycompany.myapp.entities.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.SessionUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hatem
 */
public class FosUserServices {
    
public boolean login(String username,String password){
        ArrayList<User> listuser= new ArrayList<>();
          ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://depo-pi.herokuapp.com/api/get-user/"+username);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json=new String(con.getResponseData());
               try {
            //System.out.println(json);
            JSONParser j = new JSONParser();

                   Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) user.get("root");
                   System.out.println(user);
            System.out.println("##############################################"+list.get(0).get("password").toString());
            if(list.get(0).get("password").toString().equals(password)){
            for (Map<String, Object> obj : list) {
                User u = new User();
                //u.setId(Integer.parseInt(obj.get("id").toString()));
                u.setUsername(obj.get("username").toString());
                u.setEmail(obj.get("email").toString());
                u.setPassword(obj.get("password").toString());
                u.setNom(obj.get("nom").toString());
                u.setPrenom(obj.get("prenom").toString());
                listuser.add(u);
                SessionUser.setUser(u);
            }
            }

        } catch (IOException ex) {
                   System.out.println(ex.getMessage());
        }
            }
        });
        return listuser.isEmpty();
    }

    public User getUser(String username){
        ArrayList<User> listuser= new ArrayList<>();
        User u = new User();
          ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://depo-pi.herokuapp.com/api/get-user/"+username);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json=new String(con.getResponseData());
            try {
            //System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
            //System.out.println(user);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) user.get("root");
                
               System.out.println(list);
                
                
                u.setId((int) Float.parseFloat(list.get(0).get("id").toString()));
                //u.setId(Math.round(Integer.valueOf(list.get(0).get("id").toString())));
                //u.setId((int)Float.parseFloat(list.get(0).get("id").toString()));
                u.setUsername(list.get(0).get("username").toString());
                u.setEmail(list.get(0).get("email").toString());                
                u.setPassword(list.get(0).get("password").toString());
                u.setNom(list.get(0).get("nom").toString());
                u.setPrenom(list.get(0).get("prenom").toString());
                u.setNumTel((int) Float.parseFloat(list.get(0).get("num").toString()));
                u.setRoles((list.get(0).get("roles").toString()));
                System.out.println(u.getRoles());
        } catch (IOException ex) {
                   System.out.println(ex.getMessage());
                   System.out.println("erreur");
        }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        
        return u;
    }
    
    public void modifierProfile(int id, String email,String num) {
         try{
           
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
             connReq.setUrl("http://depo-pi.herokuapp.com/api/update-profile/"+id+"/"+email+"/"+Math.round(Float.parseFloat(num))
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
    
     public void modifierPwd(int id, String pwd) {
         try{
           
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
             connReq.setUrl("http://depo-pi.herokuapp.com/api/update-pwd/"+id+"/"+pwd); 

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
