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
import manager.GestioneEventoLocal;
import manager.GestoreUtenteLocal;

/**
 *
 * @author Alex
 */
public class Controller extends HttpServlet {
    @EJB
    private GestioneEventoLocal gestioneEvento;

    @EJB
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
            gestioneEvento.addEvento();
            out.println("andata");
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }

        //gestoreUtente.AddUser("123", "aa", "aa", "123", "123");
        //gestoreImpianto.insertImpianto("impianto_7_nuovo", "Italia", "Piemonte", "Torino", "Torino", "Corso", "333", "000", "08-22", "niente");
        //gestoreSquadra.aggiungiSquadra("123", "5", "calcio7");
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