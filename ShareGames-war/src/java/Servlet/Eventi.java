/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ejb.Listaeventiutente;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import manager.GestoreListaEventiLocal;

/**
 *
 * @author Dario Gallo <your.name at your.org>
 */
public class Eventi  {
    GestoreListaEventiLocal gestoreListaEventi = lookupGestoreListaEventiLocal();

    public String getListaEventiUtente(int idutente){

        String lista=""; 
            
         
         //PRENDERE ID DA SESSIONE
           idutente=14;
   
           List<Listaeventiutente> listaeventi = gestoreListaEventi.getEventoByUtente(idutente);
           
           for(int i=0; i<=2; i++){
               
               
           
            lista+="<div class=4u>"+
                "<article class=item style= 'border:1px; border-style:solid; border-color:lightgrey;'>"
                    
                    +"<h3>Evento "+(i+1)+"</h3><header>"
                    +"<li>"
                        + "<span >Evento numero: "+listaeventi.get(i).getListaeventiutentePK().getIdevento()+"</span>"
                    + "</li>"
                    
                    +"<form id=formListaEventi>"
                    + "<input readonly type='text' id=data type=text name=data value="+listaeventi.get(i).getEvento().getEventoPK().getData()+"></input>"
                    + "<input readonly type='text'  id=ora  type=text name=ora value="+listaeventi.get(i).getEvento().getEventoPK().getOra()+"></input>"
                    + "<input readonly type='text' id=idcampo  type=text name=idcampo value="+listaeventi.get(i).getEvento().getEventoPK().getIdcampo()+"></input>"
                    + "<input readonly type='text' id=idimpianto  type=hidden name=idimpianto value="+listaeventi.get(i).getEvento().getEventoPK().getIdimpianto()+"></input>"
                    + "<input readonly type='text' id=idevento  type=hidden name=idevento value="+listaeventi.get(i).getListaeventiutentePK().getIdevento()+"></input>"
                    + "<input readonly type=hidden name=idproprietario value="+listaeventi.get(i).getProprietario()+"></input>"
                    + "</form>"
                    + "<li>Pagato: <span class=icon fa-remove></span></li>"
                    + "<span>Nome Impianto: "+listaeventi.get(i).getEvento().getImpianto().getNome()+"</span></br>"
                    //+ "<li> <img style=width:94%;height:auto src=http://maps.google.com/maps/api/staticmap?markers=size:mid|color:blue|Corso+regina+margherita+221+10144+torino&size=500x300&sensor=false&size=600x300&key=AIzaSyAbz8o3xVmsMTpHh3DRWO1kIW38K3zBVJ4></img></li></br>"
                    + "</header></article>";
            
            
            for(int j=i+3; j<listaeventi.size(); j+=3){
                
                
                 lista+=""+
                "<article class=item style= 'border:1px; border-style:solid; border-color:lightgrey;'>"
                    
                        + "<h3>Evento "+(j+1)+"</h3><header>"+

                    "<li>"
                        + "<span >Evento numero: "+listaeventi.get(j).getListaeventiutentePK().getIdevento()+"</span>"
                    + "</li>"
                    +"<form id=formListaEventi style=text-align:left>"
                    + "<input readonly type='text' id=data  type=text name=data value="+listaeventi.get(j).getEvento().getEventoPK().getData()+"></input>"
                    + "<input readonly type='text' id=ora  type=text name=ora value="+listaeventi.get(j).getEvento().getEventoPK().getOra()+"></input>"
                    + "<input readonly type='text' id=idcampo  type=text name=idcampo value="+listaeventi.get(j).getEvento().getEventoPK().getIdcampo()+"></input>"
                    + "<input readonly type='text' id=idimpianto  type=hidden name=idimpianto value="+listaeventi.get(j).getEvento().getEventoPK().getIdimpianto()+"></input>"
                    +"<input readonly type='text' id=idimpianto  type=hidden name=idevento value="+listaeventi.get(j).getListaeventiutentePK().getIdevento()+"></input>"
                    +"<input readonly type=hidden name=idproprietario value="+listaeventi.get(j).getProprietario()+"></input>"
                    + "</form>"
                    + "Pagato: <span class=icon fa-check></span></br>"
                    + "<span>Nome Impianto: "+listaeventi.get(j).getEvento().getImpianto().getNome()+"</span></br>"
                    //+" <li> <img style=width:94%;height:auto src=http://maps.google.com/maps/api/staticmap?markers=size:mid|color:blue|Corso+regina+margherita+221+10144+torino&size=500x300&sensor=false&size=600x300&key=AIzaSyAbz8o3xVmsMTpHh3DRWO1kIW38K3zBVJ4></img></li></br>"
                    +"</header></article>";
                 
                 if(j+2>listaeventi.size()) break;
   
            }
            
            lista+="</div>";
           
        }
           
       return lista;
    }
    
    
    
    
    
    public List<Integer> getidRegioni() throws ClassNotFoundException, SQLException{
        
        List<Integer> idregioni=new ArrayList<Integer>();
        
         try
    {
      // create our mysql database connection
      String myDriver = "com.mysql.jdbc.Driver";
      String myUrl = "jdbc:mysql://localhost:3306/newsharegames?zeroDateTimeBehavior=convertToNull";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "root");
       
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      String query = "SELECT * FROM regioni";
 
      // create the java statement
      java.sql.Statement st = conn.createStatement();
       
      ResultSet rs = st.executeQuery(query);
      int i=0;
      
      while (rs.next())
      {
        int id = rs.getInt("idRegione");
        idregioni.add(i++, id);
      }
      
      //regioni.set(0, "VUOTA");
       
        return idregioni; 

    }

         catch(Exception e){
             e.printStackTrace();
         }
         
         return idregioni;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public List<String> getRegioni() throws ClassNotFoundException, SQLException{
        
        List<String> regioni=new ArrayList<String>();
        
         try
    {
      // create our mysql database connection
      String myDriver = "com.mysql.jdbc.Driver";
      String myUrl = "jdbc:mysql://localhost:3306/newsharegames?zeroDateTimeBehavior=convertToNull";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "root");
       
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      String query = "SELECT * FROM regioni";
 
      // create the java statement
      java.sql.Statement st = conn.createStatement();
       
      ResultSet rs = st.executeQuery(query);
      int i=0;
      
      while (rs.next())
      {
        String nome = rs.getString("nomeRegione");
        regioni.add(i++, nome);
      }
      
      //regioni.set(0, "VUOTA");
       
        return regioni; 

    }

         catch(Exception e){
             e.printStackTrace();
         }
         
         return regioni;
    }
    
    
    
        public List<String> getCitta(int idregione) throws ClassNotFoundException, SQLException{
        
        List<String> citta=new ArrayList<String>();
        
         try
    {
      // create our mysql database connection
      String myDriver = "com.mysql.jdbc.Driver";
      String myUrl = "jdbc:mysql://localhost:3306/newsharegames?zeroDateTimeBehavior=convertToNull";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "root");
       
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      String query = "SELECT * FROM province";

      // create the java statement
      java.sql.Statement st = conn.createStatement();
       
      ResultSet rs = st.executeQuery(query);
      int i=0;
      
      while (rs.next())
      {
          if(rs.getInt("idRegione")==idregione){
                String nome = rs.getString("nomeProvincia");
                citta.add(i++, nome);
          }
      }
      
      //regioni.set(0, "VUOTA");
       
        return citta; 

    }

         catch(Exception e){
             e.printStackTrace();
         }
         
         return citta;
    }
        
        
        
        
        
        
        public List<String> getSiglaProvince(int idregione) throws ClassNotFoundException, SQLException{
        
        List<String> sigla=new ArrayList<String>();
        
         try
    {
      // create our mysql database connection
      String myDriver = "com.mysql.jdbc.Driver";
      String myUrl = "jdbc:mysql://localhost:3306/newsharegames?zeroDateTimeBehavior=convertToNull";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "root");
       
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      String query = "SELECT * FROM province";

      // create the java statement
      java.sql.Statement st = conn.createStatement();
       
      ResultSet rs = st.executeQuery(query);
      int i=0;
      
      while (rs.next())
      {
          if(rs.getInt("idRegione")==idregione){
                String nome = rs.getString("siglaProvincia");
                sigla.add(i++, nome);
          }
      }
      
      //regioni.set(0, "VUOTA");
       
        return sigla; 

    }

         catch(Exception e){
             e.printStackTrace();
         }
         
         return sigla;
    }
    
    
        
        
        
        
        
        
        
        
        
        
        
        
        
        public List<String> getProvince(String sigla) throws ClassNotFoundException, SQLException{
        
        List<String> province=new ArrayList<String>();
        
         try
    {
      // create our mysql database connection
      String myDriver = "com.mysql.jdbc.Driver";
      String myUrl = "jdbc:mysql://localhost:3306/newsharegames?zeroDateTimeBehavior=convertToNull";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "root");
       
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      String query = "SELECT * FROM comuni";

      // create the java statement
      java.sql.Statement st = conn.createStatement();
       
      ResultSet rs = st.executeQuery(query);
      int i=0;
      
      while (rs.next())
      {
          if(rs.getString("Provincia").equals(sigla)){
                String nome = rs.getString("Comune");
                province.add(i++, nome);
          }
      }
      
      //regioni.set(0, "VUOTA");
       
        return province; 

    }

         catch(Exception e){
             e.printStackTrace();
         }
         
         return province;
    }
    
    
    
    
        
         

    private GestoreListaEventiLocal lookupGestoreListaEventiLocal() {
        try {
            Context c = new InitialContext();
            return (GestoreListaEventiLocal) c.lookup("java:global/ShareGames/ShareGames-ejb/GestoreListaEventi!manager.GestoreListaEventiLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
