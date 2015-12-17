/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ejb.Impianto;
import ejb.Listaeventiutente;
import ejb.Utente;
import java.io.*;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import manager.GestoreCampoLocal;
import manager.GestoreImpiantoLocal;
import manager.GestoreListaEventiLocal;
import manager.GestoreUtenteLocal;


/**
 *
 * @author Alex
 */
public class ServletController extends HttpServlet {
    @EJB
    private GestoreListaEventiLocal gestoreListaEventi;
    @EJB
    private GestoreCampoLocal gestoreCampo;
    @EJB
    private GestoreImpiantoLocal gestoreImpianto;
    @EJB
    private GestoreUtenteLocal gestoreUtente;

    String state = "index";
    HttpSession s;
    Eventi e=new Eventi();

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
        } else if (action.equals("registration")) {
            doRegistration(request, response);
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
       
        //PRENDERE DA SESSIONE IDUTENTE!!!!
        
        s.setAttribute("lista", e.getListaEventiUtente(14));
        request.getRequestDispatcher("/personal.jsp").forward(request, response);
    }

    private void doAccesso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        state = "home";


        List<Impianto> l = gestoreImpianto.getImpiantoByCitta("Torino");
        String tmp = "<select id=selectimpianto>";
        for (int i = 0; i < l.size(); i++) {
            tmp += "<option value=" + l.get(i).getIdimpianto() + ">" + l.get(i).getNome() + "</option>";
        }
        tmp += "</select>";
        s.setAttribute("selectimpianto", tmp);

        request.getRequestDispatcher("/homepage.jsp").forward(request, response);
    }

    private void doLoginFacebook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String url = request.getParameter("url");
        String phone = request.getParameter("phone");

        s.setAttribute("id", id);
        s.setAttribute("social", "facebook");
        s.setAttribute("url", "<img src=" + url + ">");

        if (gestoreUtente.findFacebook(id)) {
            Utente temp = gestoreUtente.getObjUtente(id, "facebook");
            s.setAttribute("name", temp.getNome());
            s.setAttribute("email", temp.getEmail());
            s.setAttribute("phone", temp.getTelefono());
            state = "homepageaccess";
            request.getRequestDispatcher("/homepageaccess.jsp").forward(request, response);
        } else {
            state = "registration";
            s.setAttribute("name", name);
            s.setAttribute("email", email);
            s.setAttribute("phone", phone);
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
        }

    }

    private void doLoginGoogle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String url = request.getParameter("url");
        String phone = request.getParameter("phone");
       
        s.setAttribute("id", id);
        s.setAttribute("social", "google");
        s.setAttribute("url", "<img src=" + url + ">");
        
        if (gestoreUtente.findGoogle(id)) {
            Utente temp = gestoreUtente.getObjUtente(id, "google");
            s.setAttribute("name", temp.getNome());
            s.setAttribute("email", temp.getEmail());
            s.setAttribute("phone", temp.getTelefono());
            state = "homepageaccess";
            request.getRequestDispatcher("/homepageaccess.jsp").forward(request, response);
        } else {
            state = "registration";
            s.setAttribute("name", name);
            s.setAttribute("email", email);
            s.setAttribute("phone", phone);
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
        }

    }

    private void doRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String id = (String) s.getAttribute("id");
        if (s.getAttribute("social") == "facebook") {
            gestoreUtente.addUser(name, email, "", id, phone);
        } else if (s.getAttribute("social") == "google") {
            gestoreUtente.addUser(name, email, id, "", "");
        }
        s.setAttribute("name", name);
        s.setAttribute("email", email);
        s.setAttribute("phone", phone);
        state = "personal";
        request.getRequestDispatcher("/personal.jsp").forward(request, response);
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
