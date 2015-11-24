/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.GestoreCampoLocal;
import manager.GestoreEventoLocal;
import manager.GestoreImpiantoLocal;
import manager.GestoreUtenteLocal;

/**
 *
 * @author Alex
 */
public class Controller extends HttpServlet {
    @EJB
    private GestoreCampoLocal gestoreCampo;
    private GestoreImpiantoLocal gestoreImpianto;
    private GestoreEventoLocal gestioneEvento;
    private GestoreUtenteLocal gestoreUtente;
    
    
    


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controller</title>");
            //gestoreImpianto.addImpianto("ImpiantoProva1", "Italia", "Piemonte", "Torino", "Torino", "Corso Regina", "333", "000000000", "08-22", "nessuno");
            
//            for(int i=0;i<gestoreImpianto.getImpiantoByCitta("Torino").size();i++){
//                
//                String nome = gestoreImpianto.getImpiantoByCitta("Torino").get(i).getNome();
//                int    id   = gestoreImpianto.getImpiantoByCitta("Torino").get(i).getIdimpianto();
//                out.println("<p>Nome: "+nome + ", Id: "+id+"<p>");
//            }
            
//            for(int i=0;i<gestoreImpianto.getImpiantoByProvincia("Torino").size();i++){
//                
//                String nome = gestoreImpianto.getImpiantoByProvincia("Torino").get(i).getCitta();
//                int    id   = gestoreImpianto.getImpiantoByProvincia("Torino").get(i).getIdimpianto();
//                out.println("<p>Nome: "+nome + ", Id: "+id+"<p>");
//            }
            
//            out.println(gestoreImpianto.getObjectImpiantoById(25));

//            gestoreImpianto.updateImpianto("ImpiantoProva1", "Italia", "Piemonte", "Vercelli", "Torino", "Corso Regina", "333", "000000000", "08-22", "nessuno");

            //gestoreCampo.addCampo(2,20,"calcio7",7);
            gestoreCampo.addCampo(2,22,"calcio7",7);
            //gestoreCampo.addCampo(1,20,"calcio7",7);
            //out.println(gestoreCampo.getObjectCampoById(3, 19).getIdimpianto());
            //gestoreCampo.updateCampo(50, 19, "calcio5", 5);
            //gestoreCampo.removeCampo(50, 19);
            
            //out.println(gestoreCampo.getCampoByImpianto(19));
            //out.println(gestoreCampo.getCampoByTipologia("calcio5"));
            
            out.println("andata");
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }

    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}