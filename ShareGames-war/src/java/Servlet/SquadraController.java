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
        RequestDispatcher rdErr;
        //parametri per il recupero dell'utente estratti dalla sessione
        String id = (String) s.getAttribute("id");
        String social = (String) s.getAttribute("social");

        if (action.equals("findTeams")) {
            String prov = request.getParameter("prov");
            String sport = request.getParameter("sport");
            Collection<Squadra> result = gestoreSquadra.getSquadraByCittaTipologia(prov, sport);
            String tmp = "";
            if (result.isEmpty()) {
                tmp += "<form onsubmit='return false'>"
                        + "<div class='col-1'>"
                        + "    <label>Non ci sono risultati per questa ricerca!</label>"
                        + "</div>"
                        + "<div class='col-submit'>"
                        + "    <button type='submit' class='submitbtn' id='returnCreateBtn'>Creala tu!</button>"
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
                            + "<div class='col-submit'>"
                            + "<button class='button' onclick='joinTeam()'>Unisciti a questa squadra</button>"
                            + "</div>"
                            + "</form>";
                }
                tmp += "<div class='col-submit'>"
                        + "    <button type='submit' class='submitbtn' id='returnCreateBtn'>Oppure creala tu!</button>"
                        + "</div>";
                out.write(tmp);
                out.close();
            }
        }

        if (action.equals("returnCreateTeam")) {
            String html = "";
            html += "<form onsubmit='return false'>"
                    + "    <div class='col-4'>"
                    + "        <label>Scegli la regione: "
                    + "            <select id='geo3' name='geo3' tabindex='4'>"
                    + "                <option>Scegli una regione</option>"
                    + "            </select>"
                    + "        </label>"
                    + "    </div> "
                    + "    <div class='col-4'>"
                    + "        <label> Scegli la provincia:  "
                    + "            <select id='geo4' name='geo4' tabindex='5'>"
                    + "                <option>...</option>"
                    + "            </select>"
                    + "        </label>"
                    + "    </div>"
                    + "    <div class='col-4'>"
                    + "        <label> Scegli lo sport:"
                    + "            <select id='team_sport2' name='team_sport2' tabindex='6'></select>"
                    + "        </label>"
                    + "    </div>"
                    + "    <div class='col-4'>"
                    + "        <label> Scegli il nome:"
                    + "            <input placeholder='Libera la fantasia!' id='newteamname' name='newteamname' tabindex='7'>"
                    + "        </label>"
                    + "    </div>"
                    + "    <div class='col-submit'>"
                    + "        <button class='button' id='createTeamBtn'>Crea una squadra</button>"
                    + "    </div>"
                    + "</form>";
            out.write(html);
            out.close();
        }

        if (action.equals("joinTeam")) {
            String idsquadra = request.getParameter("idsquadra");
            String namesquadra = request.getParameter("namesquadra");
            gestoreUtente.joinSquadra(gestoreUtente.getObjUtente(id, social), new Integer(idsquadra));
            request.getSession().setAttribute("team", namesquadra);
            rdErr = ctx.getRequestDispatcher("/personal.jsp");
            rdErr.forward(request, response);
        }

        if (action.equals("createTeam")) {
            String newsport = request.getParameter("sport");
            String newname = request.getParameter("newname");
            String location = request.getParameter("prov");
            if (gestoreSquadra.checkNomeSquadra(newname)) {
                gestoreSquadra.addSquadra(newname, newsport, location);
                Integer newid = gestoreSquadra.getObjSquadraByName(newname).getIdsquadra();
                gestoreUtente.joinSquadra(gestoreUtente.getObjUtente(id, social), newid);
                request.getSession().setAttribute("team", newname);
                out.write("Squadra creata con successo!");
            } else {
                out.write("Il nome per la squadra è già stato scelto!");
                out.close();
            }
        }

        if (action.equals("getMyTeam")) {
            if (request.getSession().getAttribute("team") != null) {
                Squadra myTeam = gestoreSquadra.getObjSquadraByName((String) request.getSession().getAttribute("team"));
                String tipo = "";
                if (myTeam.getTipologia().equals("Calcio5")) {
                    tipo = "Calcio a 5";
                } else if (myTeam.getTipologia().equals("Calcio7")) {
                    tipo = "Calcio a 7";
                } else if (myTeam.getTipologia().equals("Calcio11")) {
                    tipo = "Calcio a 11";
                } else {
                    tipo = myTeam.getTipologia();
                }
                String tmp = "<div class='col-4'>"
                        + "    <label> Nome"
                        + "        <input value='" + myTeam.getNomesquadra() + "' id='myTeamName' name='myTeamName' tabindex='1' readonly='true'>"
                        + "    </label>"
                        + "</div>"
                        + "<div class='col-4'>"
                        + "    <label> Membri"
                        + "        <input value='" + myTeam.getNumerocomponenti() + "' id='myTeamMember' name='myTeamMember' tabindex='2' readonly='true'>"
                        + "    </label>"
                        + "</div> "
                        + "<div class='col-4'>"
                        + "    <label> Sport"
                        + "        <input value='" + tipo + "' id='myTeamSport' name='myTeamSport' tabindex='3' readonly='true'>"
                        + "    </label>"
                        + "</div>"
                        + "<div class='col-4'>"
                        + "    <label> Provincia"
                        + "        <input value='" + myTeam.getCitta() + "' id='myTeamLocation' name='myTeamLocation' tabindex='3' readonly='true'>"
                        + "    </label>"
                        + "</div>"
                        + "<div class='col-submit'>"
                        + "    <button type='submit' class='button' style='background-color: red' id='leaveTeamBtn'>Lascia la squadra</button>"
                        + "</div>"
                        + "<input type='hidden' name='action' value='leaveTeam'>";
                out.write(tmp);
                out.close();
            }
        }

        if (action.equals("leaveTeam")) {
            gestoreUtente.leaveSquadra(gestoreUtente.getObjUtente(id, social));
            request.getSession().setAttribute("team", null);

            request.getRequestDispatcher("/personal.jsp").forward(request, response);
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
