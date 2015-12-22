/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ejb.Squadra;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.GestoreSquadraLocal;
import manager.GestoreUtenteLocal;

/**
 *
 * @author TTm
 */
@WebServlet(name = "SquadraController", urlPatterns = {"/SquadraController"})
public class SquadraController extends HttpServlet {

    @EJB
    private GestoreSquadraLocal gestoreSquadra;

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
        PrintWriter out = response.getWriter();

        String id = (String) s.getAttribute("id");
        String social = (String) s.getAttribute("social");

        if (action.equals("findTeams")) {
            String prov = request.getParameter("prov");
            String sport = request.getParameter("sport");
            Collection<Squadra> result = gestoreSquadra.getSquadraByCittaTipologia(prov, sport);
            String tmp = "";
            if (result.isEmpty()) {
                tmp += "<form>"
                        + "<div class='col-1'>"
                        + "<label>Non ci sono risultati per questa ricerca!</label>"
                        + "</div>"
                        + "</form>";
                out.write(tmp);
                out.close();
            } else {
                for (Squadra t : result) {
                    tmp += "<form action='SquadraController' method='POST'>"
                            + "<input type='hidden' name='idsquadra' value='" + t.getIdsquadra() + "'>"
                            + "<input type='hidden' name='namesquadra' value='" + t.getNomesquadra() + "'>"
                            + "<input type='hidden' name='action' value='joinTeam'>"
                            + "<div class='col-2'><label>Squadra<input type='text' value='" + t.getNomesquadra() + "' readonly='true'></label></div>"
                            + "<div class='col-2'><label>Membri<input type='text' value='" + t.getNumerocomponenti() + "' readonly='true'></label></div>"
                            + "<button class='submitbtn' onclick='joinTeam()'>Unisciti a questa squadra</button>"
                            + "</form>";
                }

                out.write(tmp);
                out.close();

            }

        }
        if (action.equals("joinTeam")) {
            String idsquadra = request.getParameter("idsquadra");
            String namesquadra = request.getParameter("namesquadra");
            gestoreUtente.joinSquadra(gestoreUtente.getObjUtente(id, social), new Integer(idsquadra));
            request.getSession().setAttribute("team",namesquadra);
            RequestDispatcher rdErr = ctx.getRequestDispatcher("/personal.jsp");
            rdErr.forward(request, response);
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
