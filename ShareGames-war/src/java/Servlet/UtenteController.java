/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ejb.Utente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.GestoreUtenteLocal;

/**
 *
 * @author TTm
 */
@WebServlet(name = "UtenteController", urlPatterns = {"/UtenteController"})
public class UtenteController extends HttpServlet {

    @EJB
    private GestoreUtenteLocal gestoreUtente;

    HttpSession s;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext ctx = getServletContext();
        response.setContentType("text/html;charset=UTF-8");
        s = request.getSession();
        String action = request.getParameter("action");

        String id = (String) s.getAttribute("id");
        String social = (String) s.getAttribute("social");
        if (action.equals("updateUtente")) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            gestoreUtente.updateUtente(id, social, name, email, phone);
            s.setAttribute("name", name);
            s.setAttribute("email", email);
            s.setAttribute("phone", phone);
        }

        if (action.equals("getMyPersonal")) {
            String teamPersonal = "Sei ancora senza squadra? Cercane una o creala!";
            if (request.getSession().getAttribute("team") != null) {
                teamPersonal = (String) request.getSession().getAttribute("team");
            }
            String html = ""
                    + "<div class='col-2'>"
                    + "    <label> Nome"
                    + "        <input value='" + request.getSession().getAttribute("name") + "' id='namePersonal' name='namePersonal' tabindex='1' readonly='true'>"
                    + "    </label>"
                    + "</div>"
                    + "<div class='col-2'>"
                    + "    <label> Email"
                    + "        <input value='" + request.getSession().getAttribute("email") + "' id='emailPersonal' name='emailPersonal' tabindex='2' readonly='true'>"
                    + "    </label>"
                    + "</div> "
                    + "<div class='col-2'>"
                    + "    <label> Telefono "
                    + "        <input value='" + request.getSession().getAttribute("phone") + "' id='phonePersonal' name='phonePersonal' tabindex='3' readonly='true'>"
                    + "    </label>"
                    + "</div>"
                    + "<div class='col-2'>"
                    + "    <label> Squadra "
                    + "         <input placeholder='" + teamPersonal + "' id='teamPersonal' name='teamPersonal' tabindex='5' readonly='true'>"
                    + "    </label>"
                    + "</div>"
                    + "<div class='col-submit'>"
                    + "    <button class='button' id='buttonPersonal' onclick='editableForm()'>Aggiorna i tuoi dati</button>"
                    + "</div>";
            try (PrintWriter out = response.getWriter()) {
                out.write(html);
                out.close();
            } 
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

}
