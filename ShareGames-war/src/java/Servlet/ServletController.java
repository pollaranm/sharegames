/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ejb.Utente;
import java.io.*;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import manager.GestoreUtenteLocal;

/**
 *
 * @author Alex
 */
public class ServletController extends HttpServlet {
    @EJB
    private GestoreUtenteLocal gestoreUtente;

    String state = "index";
    HttpSession s;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext ctx = getServletContext();
        response.setContentType("text/html;charset=UTF-8");
        s = request.getSession();

        String action = request.getParameter("action");

        // refresh o accesso diretto 
        if (action == null) {
            try {
                if (s.isNew()) { // sessione nuova , caricamento index iniziale
                    RequestDispatcher rdErr = ctx.getRequestDispatcher("/index.jsp");
                    rdErr.forward(request, response);
                    // sessione gia aperta
                } else if (state.equals("homepageaccess")) {
                    // se ci troviamo in homepageaccess e carichiamo la pagina, il browser deve rimaner esulla stessa pagina
                    RequestDispatcher rdErr = ctx.getRequestDispatcher("/homepageaccess.jsp");
                    rdErr.forward(request, response);
                } else if (state.equals("index")) {
                    RequestDispatcher rdErr = ctx.getRequestDispatcher("/index.jsp");
                    rdErr.forward(request, response);
                } else if (state.equals("home")) {
                    RequestDispatcher rdErr = ctx.getRequestDispatcher("/homepage.jsp");
                    rdErr.forward(request, response);
                } else if (state.equals("personal")) {
                    RequestDispatcher rdErr = ctx.getRequestDispatcher("/personal.jsp");
                    rdErr.forward(request, response);
                }
            } catch (NullPointerException e) {
                RequestDispatcher rdErr = ctx.getRequestDispatcher("/errore.jsp");
                rdErr.forward(request, response);
            }
        } else if (action.equals("personal")) {
            doPersonal(request, response);
        } else if (action.equals("accesso")) {
            doAccesso(request, response);
        } else if (action.equals("loginFacebook")) {
            doLoginFacebook(request, response);
        } else if (action.equals("loginGoogle")) {
            doLoginGoogle(request, response);
        } else if (action.equals("removeUtente")) {
            doRemoveUtente(request, response);
        } else if (action.equals("logout")) {
            doLogout(request, response);
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

    /**
     * Così per ogni chiamata diversa del controller posso scrivere i Jdoc e
     * dire cosa fa !!!
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doPersonal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        state = "personal";
        request.getRequestDispatcher("/personal.jsp").forward(request, response);
    }

    private void doAccesso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        state = "home";
        request.getRequestDispatcher("/homepage.jsp").forward(request, response);
    }

    private void doLoginFacebook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nome = request.getParameter("name");
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String url = request.getParameter("url");
        if (gestoreUtente.findFacebook(id) == false) {
            gestoreUtente.AddUser(nome, email, "", id, "");
            System.out.println("inserito");
        } else {
            System.out.println("presente");
        }
        s.setAttribute("name", nome);
        s.setAttribute("id", id);
        s.setAttribute("tiposocial", "facebook");
        s.setAttribute("url", "<img src=" + url + ">");
//        Utente user = gestoreUtente.getObjUtente(id, "facebook");
        //metodo per loggare e controllare la persona nel database e linkarlo alla pagina nuova
        state = "homepageaccess";
        request.getRequestDispatcher("/homepageaccess.jsp").forward(request, response);

    }

    private void doLoginGoogle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nome = request.getParameter("name");
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String url = request.getParameter("url");
        if (gestoreUtente.findGoogle(id) == false) {
            gestoreUtente.AddUser(nome, email, id, "", "");
            System.out.println("inserito");
        } else {
            System.out.println("presente");
        }
        s.setAttribute("name", nome);
        s.setAttribute("id", id);
        s.setAttribute("tiposocial", "google");
        s.setAttribute("url", "<img src=" + url + ">");
//        Utente user = gestoreUtente.getObjUtente(id, "google");
        //metodo per loggare e controllare la persona nel database e linkarlo alla pagina nuova
        state = "homepageaccess";
        request.getRequestDispatcher("/homepageaccess.jsp").forward(request, response);

    }

    private void doRemoveUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        gestoreUtente.removeUtente((String) s.getAttribute("id"), (String) s.getAttribute("tiposocial"));
        state = "index";
        s.invalidate();
        request.getSession().invalidate();
        request.getRequestDispatcher("/homepage.jsp").forward(request, response);

    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        state = "index";
        s.invalidate();
        request.getSession().invalidate();
        request.getRequestDispatcher("/homepage.jsp").forward(request, response);
    }
}
