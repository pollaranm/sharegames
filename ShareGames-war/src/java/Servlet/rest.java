/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ejb.Amministratore;
import ejb.Listaeventiutente;
import ejb.Prezziario;
import ejb.Utente;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.GestoreAmministratoreLocal;
import manager.GestoreCampoLocal;
import manager.GestoreListaEventiLocal;
import manager.GestorePrezziarioLocal;
import manager.GestoreUtenteLocal;
import org.json.simple.*;

/**
 *
 * @author Alex
 */
public class rest extends HttpServlet {
    @EJB
    private GestoreListaEventiLocal gestoreListaEventi;
    @EJB
    private GestoreUtenteLocal gestoreUtente;
    @EJB
    private GestoreCampoLocal gestoreCampo;
    @EJB
    private GestorePrezziarioLocal gestorePrezziario;
    @EJB
    private GestoreAmministratoreLocal gestoreAmministratore;
    @PersistenceContext(unitName = "ShareGames-warPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String action = request.getParameter("action");
        
        if(action.equals("auth")){
            
            int id = Integer.parseInt(request.getParameter("id"));
            String psw  = request.getParameter("psw");
            
            if(gestoreAmministratore.checkAuthAmm(id,psw)){
                Amministratore a = gestoreAmministratore.getObjAmministratore(id);
                JSONObject json = new JSONObject();
            
                json.put("name", a.getNome());
                json.put("cognome", a.getCognome());
                json.put("impianto",a.getIdimpianto().getNome());
                json.put("id", a.getAmministratorePK().getIdamministratore());
                json.put("via", a.getIdimpianto().getIndirizzo());
                json.put("idimpianto",""+a.getIdimpianto().getIdimpianto());
                json.put("partitaiva", a.getIdimpianto().getPartitaiva());
                json.put("citta", a.getIdimpianto().getCitta());
                response.setContentType("application/json");
                try (PrintWriter out = response.getWriter()) {
                    out.print(json);
                    out.flush();
                } 
            }
            else{
                JSONObject json = new JSONObject();
                json.put("risultato", "Errore");
                response.setContentType("application/json");
                try (PrintWriter out = response.getWriter()) {
                    out.print(json);
                    out.flush();
                } 
                
            }
        }else if(action.equals("getPrezziario")){
            
                int id = Integer.parseInt(request.getParameter("idimpianto"));
                List<Prezziario> p = gestorePrezziario.getObjPrezziario(id);
                JSONObject json = new JSONObject();   
                
                JSONArray ja = new JSONArray();
                  
                    for(int i = 0; i<p.size();i++){
                        
                        JSONObject jo = new JSONObject();
                        
                        jo.put("id", i);
                        jo.put("campo", p.get(i).getCampo().getCampoPK().getIdcampo());
                        jo.put("prezzo", p.get(i).getPrezzo()); 
                        jo.put("sconto", p.get(i).getSconto()); 
                        
                        ja.add(i, jo);

                    }
                    
                json.put("prezziario",ja);
                response.setContentType("application/json");
                    try (PrintWriter out = response.getWriter()) {
                        out.print(json);
                        out.flush();
                    }
        }else if(action.equals("addCampo")){
                
                JSONObject json = new JSONObject();
                
                int id = Integer.parseInt(request.getParameter("id"));
                int idimpianto = Integer.parseInt(request.getParameter("idimpianto"));
                String tipologia = (request.getParameter("tipologia"));
                int numerogiocatori = Integer.parseInt(request.getParameter("numero"));
                float prezzo = Float.parseFloat(request.getParameter("prezzo"));
                int sconto = Integer.parseInt(request.getParameter("sconto"));
                BigDecimal bd = new BigDecimal(prezzo);
                
                try{
                    gestoreCampo.addCampo(id, idimpianto, tipologia, numerogiocatori);
                    gestorePrezziario.addPrezziario(id, idimpianto, bd, sconto);

                }catch(Exception e){
                    json.put("risultato","Errore");
                    response.setContentType("application/json");
                    try (PrintWriter out = response.getWriter()) {
                        out.print(json);
                        out.flush();
                    }
                }
                
                json.put("risultato","Eseguito");
                response.setContentType("application/json");
                    try (PrintWriter out = response.getWriter()) {
                        out.print(json);
                        out.flush();
                    }
        }else if(action.equals("removeCampo")){
                
                JSONObject json = new JSONObject();
                
                int id = Integer.parseInt(request.getParameter("idcampo"));
                int idimpianto = Integer.parseInt(request.getParameter("idimpianto"));
                
                try{
                    gestoreCampo.removeCampo(id, idimpianto);

                }catch(Exception e){
                    json.put("risultato","Errore");
                    response.setContentType("application/json");
                    try (PrintWriter out = response.getWriter()) {
                        out.print(json);
                        out.flush();
                    }
                }
                
                json.put("risultato","Eseguito");
                response.setContentType("application/json");
                    try (PrintWriter out = response.getWriter()) {
                        out.print(json);
                        out.flush();
                    }
        }else if(action.equals("removeCampo")){
                
                JSONObject json = new JSONObject();
                
                int id = Integer.parseInt(request.getParameter("idcampo"));
                int idimpianto = Integer.parseInt(request.getParameter("idimpianto"));
                String tipologia = (request.getParameter("tipologia"));
                int numerogiocatori = Integer.parseInt(request.getParameter("numerogiocatori"));
                
                try{
                    gestoreCampo.updateCampo(id, idimpianto, tipologia, numerogiocatori);

                }catch(Exception e){
                    json.put("risultato","Errore");
                    response.setContentType("application/json");
                    try (PrintWriter out = response.getWriter()) {
                        out.print(json);
                        out.flush();
                    }
                }
                
                json.put("risultato","Eseguito");
                response.setContentType("application/json");
                    try (PrintWriter out = response.getWriter()) {
                        out.print(json);
                        out.flush();
                    }
        } else if(action.equals("authAndroid")){

                JSONObject json = new JSONObject();
                JSONArray ja = new JSONArray();
                
                String id = request.getParameter("id");
                
                if (gestoreUtente.findFacebook(id)) {
                    Utente temp = gestoreUtente.getObjUtente(id, "facebook");
                    List<Listaeventiutente> lv = gestoreListaEventi.getEventoByUtente(temp.getIdutente());
                    
                    json.put("nome", temp.getNome());
                    for(int i = 0; i<lv.size();i++){
                        JSONObject jo = new JSONObject();
                        
                        jo.put("campo", lv.get(i).getEvento().getCampo().getCampoPK().getIdcampo());
                        jo.put("impianto", lv.get(i).getEvento().getImpianto().getNome()); 
                        jo.put("data", lv.get(i).getEvento().getEventoPK().getData()); 
                        jo.put("ora", lv.get(i).getEvento().getEventoPK().getOra());
                        jo.put("pagato", lv.get(i).getEvento().getPagato());
                        
                        ja.add(i, jo);
                    }
                    json.put("eventiUtente",ja);

                }else{
                    //gestoreUtente.addUser(name, email, "", id, phone);
                }
                json.put("eventiUtente",ja);
                response.setContentType("application/json");
                    try (PrintWriter out = response.getWriter()) {
                        out.print(json);
                        out.flush();
                    }
        }
        
 
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

}
