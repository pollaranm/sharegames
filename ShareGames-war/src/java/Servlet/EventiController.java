/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ejb.Campo;
import ejb.CampoPK;
import ejb.Evento;
import ejb.EventoPK;
import ejb.Impianto;
import ejb.Listaeventiutente;
import ejb.Utente;
import ejbFacade.CampoFacadeLocal;
import ejbFacade.EventoFacadeLocal;
import ejbFacade.ImpiantoFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UpdateModelException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.GestoreCampoLocal;
import manager.GestoreEventoLocal;
import manager.GestoreListaEventiLocal;
import manager.GestoreUtenteLocal;

/**
 *
 * @author PC-STUDIO
 */
public class EventiController extends HttpServlet {

    @EJB
    private GestoreListaEventiLocal gestoreListaEventi;

    @EJB
    private GestoreUtenteLocal gestoreUtente;

    @EJB
    private GestoreEventoLocal gestoreEvento;
    @EJB
    private GestoreCampoLocal gestoreCampo;

    HttpSession s;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        ServletContext ctx = getServletContext();
        response.setContentType("text/html;charset=UTF-8");
        s = request.getSession();
        Integer userid = (Integer) s.getAttribute("idnumber");
        String action = request.getParameter("action");

        if (action.equals("getregioni")) {
            Eventi e = new Eventi();
            List<Integer> id = e.getIdRegioni(); //stessa dimensione delle liste
            List<String> l = e.getRegioni();

            PrintWriter out = response.getWriter();
            String regioni = "";
            for (int i = 0; i < l.size(); i++) {
                regioni += "<option value=" + id.get(i) + ">" + l.get(i) + "</option>";
            }
            out.write(regioni);
            out.close();
        }

        if (action.equals("getprovince")) {
            int idregione = Integer.parseInt(request.getParameter("idregione"));
            Eventi e = new Eventi();
            List<String> l = e.getProvince(idregione);
            PrintWriter out = response.getWriter();
            String citta = "";
            for (int i = 0; i < l.size(); i++) {
                citta += "<option value=" + l.get(i) + ">" + l.get(i) + "</option>"; //metto come value il nome della città e non la sigla
            }
            out.write(citta);
            out.close();
        }

        if (action.equals("getsport")) {
            String sport = "";
            sport += "<option value='calcio5'>Calcio a 5</option>"
                    + "<option value='calcio7'>Calcio a 7</option>"
                    + "<option value='calcio11'>Calcio a 11</option>"
                    + "<option value='pallavolo'>Pallavolo</option>"
                    + "<option value='pallacanestro'>Pallacanestro</option>"
                    + "<option value='tennis'>Tennis</option>";
            try (PrintWriter out = response.getWriter()) {
                out.write(sport);
                out.close();
            }
        }

        if (action.equals("searchEvento")) {
            String prov = (String) request.getParameter("prov");
            String sport = (String) request.getParameter("sport");
            System.out.print(prov + " - " + sport);
            try {

                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost:3306/newsharegames?zeroDateTimeBehavior=convertToNull";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "root", "root");
                String query = "SELECT * FROM evento, impianto WHERE "
                        + "evento.idimpianto=impianto.idimpianto "
                        + "AND evento.data >= curdate() "
                        + "AND impianto.provincia = '" + prov + "' "
                        + "AND evento.sport = '" + sport + "' "
                        + "AND evento.completo = 'no' "
                        + "AND evento.idevento NOT IN "
                        + "(SELECT idevento FROM listaeventiutente WHERE idutente = '" + userid.toString() + "')"
                        + "ORDER BY evento.data, evento.ora";

                java.sql.Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);

                String html = "";
                int count = 1;
                String col1 = "<div class='4u' id='col1'>";
                String col2 = "<div class='4u' id='col2'>";
                String col3 = "<div class='4u' id='col3'>";
                String temp = "";

                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        String address = rs.getString("impianto.indirizzo") + " " + rs.getString("impianto.citta");
                        address = address.replace(" ", "+");
                        System.out.println(address);
                        temp = " <article class='item'>"
                                + "  <h3>Evento n°" + rs.getString("idevento") + "</h3>"
                                + "  <header>"
                                + "    <ul style='text - align: left;margin - left: 5%'>"
                                + "      <li><span>Data: " + rs.getString("data") + "</span></li>"
                                + "      <li><span>Ora: " + rs.getString("ora") + "</span></li>"
                                + "      <li><span>Giocatori: " + rs.getString("giocatoripagato") + "</span></li>"
                                + "      <li><span>" + rs.getString("nome") + "</span></li>"
                                + "      <li><span>" + rs.getString("indirizzo") + ", " + rs.getString("citta") + "</span></li>"
                                + "      <li> <img class='gmaps' style='width:94%;height:auto' "
                                + "        src='http://maps.google.com/maps/api/staticmap?markers=size:mid|color:blue|" + address + "&size=500x300&sensor=false&size=600x300&key=AIzaSyAbz8o3xVmsMTpHh3DRWO1kIW38K3zBVJ4'>"
                                + "        </img></li>"
                                + "    </ul>"
                                + "    <form onsubmit='return false' style='border-style:none'>"
                                + "      <input type='hidden' name='action' value='joinEvento'>"
                                + "      <input type='hidden' name='idEvento' id='idEvento' value='" + rs.getString("idevento") + "'>"
                                + "      <button class='button partecipa'>Partecipa</button>"
                                + "    </form>"
                                + "  </header>"
                                + "</article>";
                        if (count == 1) {
                            col1 += temp;
                        } else if (count == 2) {
                            col2 += temp;
                        } else {
                            col3 += temp;
                        }
                        if (count < 3) {
                            count++;
                        } else {
                            count = 1;
                        }
                    }
                    html = "<div class='row'>" + col1 + "</div>" + col2 + "</div>" + col3 + "</div></div>";
                } else {
                    html = "Nessun evento presente con queste caratteristiche!";
                }

                try (PrintWriter out = response.getWriter()) {
                    out.write(html);
                    out.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (action.equals("joinEvento")) {
            Integer idEvento = new Integer((String) request.getParameter("idEvento"));
            gestoreListaEventi.addEventoUtente(idEvento, userid);
            Evento tempE = gestoreEvento.getEvento(idEvento);
            tempE.setGiocatoripagato(tempE.getGiocatoripagato() + 1);
            Campo tempC = tempE.getCampo();
            if (tempE.getGiocatoripagato() == tempC.getNumerogiocatori()) {
                tempE.setCompleto("si");
            }
            gestoreEvento.updateEvento(tempE);

        }

        if (action.equals("getstorico")) {
            try {
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost:3306/newsharegames?zeroDateTimeBehavior=convertToNull";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "root", "root");
                String query = "SELECT * FROM evento, impianto, listaeventiutente "
                        + "WHERE evento.idimpianto = impianto.idimpianto "
                        + "AND evento.idevento = listaeventiutente.idevento "
                        + "AND evento.data < curdate() "
                        + "AND evento.completo = 'si' "
                        + "AND listaeventiutente.idutente = '" + userid.toString() + "' "
                        + "ORDER BY evento.sport, evento.data, evento.ora";

                java.sql.Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);

                String html = "<div id='wrapper'>"
                        + "  <form id='mystorico' onsubmit='return false'>";

                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        html += "   <div class='col-3'>"
                                + "    <label>"
                                + "        <input value='" + rs.getString("data") + " " + rs.getString("sport") + "' readonly='true'>"
                                + "    </label>"
                                + "</div>"
                                + "<div class='col-3'>"
                                + "    <label>"
                                + "        <input value='" + rs.getString("impianto.nome") + "' readonly='true'>"
                                + "    </label>"
                                + "</div>"
                                + "<div class='col-3'>"
                                + "    <label>"
                                + "        <input value='" + rs.getString("impianto.indirizzo") + " - " + rs.getString("impianto.provincia") + "' readonly='true'>"
                                + "    </label>"
                                + "</div>";
                    }

                } else {
                    html = "Nessun evento presente con queste caratteristiche!";
                }

                html += "</form></div>";

                try (PrintWriter out = response.getWriter()) {
                    out.write(html);
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (action.equals("getnextevents")) {
            try {
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost:3306/newsharegames?zeroDateTimeBehavior=convertToNull";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "root", "root");
                String query = "SELECT * FROM evento, impianto, listaeventiutente, prezziario "
                        + "WHERE evento.idimpianto = impianto.idimpianto "
                        + "AND evento.idevento = listaeventiutente.idevento "
                        + "AND evento.idcampo = prezziario.idcampo "
                        + "AND evento.idimpianto = prezziario.idimpianto "
                        + "AND evento.data >= curdate() "
                        + "AND listaeventiutente.idutente = '" + userid.toString() + "' "
                        + "ORDER BY evento.data, evento.ora";

                java.sql.Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);

                String html = "";
                int count = 1;
                String col1 = "<div class='4u' id='col1'>";
                String col2 = "<div class='4u' id='col2'>";
                String col3 = "<div class='4u' id='col3'>";
                String temp = "";

                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        String address = rs.getString("impianto.indirizzo") + " " + rs.getString("impianto.citta");
                        address = address.replace(" ", "+");
                        Double tempPrice = new Double(rs.getDouble("prezzo") * (Double) (100.00 - rs.getDouble("sconto")) / 100);
                        //BigDecimal bg = new BigDecimal();
                        String actualPrice = String.format("%.2f", tempPrice);
                        temp = " <article class='item'>"
                                + "  <h3>Evento n°" + rs.getString("idevento") + "<br>" + rs.getString("sport") + "</h3>"
                                + "  <header>"
                                + "    <ul style='text - align: left;margin - left: 5%'>"
                                + "      <li><span>Data: " + rs.getString("data") + " - " + rs.getString("ora") + "</span></li>"
                                + "      <li><span>Giocatori: " + rs.getString("giocatoripagato") + "</span></li>"
                                + "      <li><span>Costo: " + actualPrice + " &euro;</span></li>"
                                + "      <li><span>" + rs.getString("nome") + "</span></li>"
                                + "      <li><span>" + rs.getString("indirizzo") + ", " + rs.getString("citta") + "</span></li>"
                                + "      <li> <img class='gmaps' style='width:94%;height:auto' "
                                + "        src='http://maps.google.com/maps/api/staticmap?markers=size:mid|color:blue|" + address + "&size=500x300&sensor=false&size=600x300&key=AIzaSyAbz8o3xVmsMTpHh3DRWO1kIW38K3zBVJ4'>"
                                + "        </img></li>"
                                + "    </ul>";
                        if (rs.getString("proprietario").equals("si")) {
                            temp += "    <form onsubmit='return false' style='border-style:none'>"
                                    + "      <input type='hidden' name='action' value='deleteEvento'>"
                                    + "      <input type='hidden' name='idEvento' id='idEvento' value='" + rs.getString("idevento") + "'>"
                                    + "      <button class='button cancellaE' style='background-color: red'>Cancella</button>"
                                    + "    </form>";
                        } else {
                            temp += "    <form onsubmit='return false' style='border-style:none'>"
                                    + "      <input type='hidden' name='action' value='withdrawEvento'>"
                                    + "      <input type='hidden' name='idEvento' id='idEvento' value='" + rs.getString("idevento") + "'>"
                                    + "      <button class='button ritiratiE'>Ritirati</button>"
                                    + "    </form>";
                        }
                        temp += "     </header>"
                                + "</article>";
                        if (count == 1) {
                            col1 += temp;
                        } else if (count == 2) {
                            col2 += temp;
                        } else {
                            col3 += temp;
                        }
                        if (count < 3) {
                            count++;
                        } else {
                            count = 1;
                        }
                    }
                    html = "<div class='row'>" + col1 + "</div>" + col2 + "</div>" + col3 + "</div></div>";
                } else {
                    html = "Nessun evento in programma al momento.";
                }

                try (PrintWriter out = response.getWriter()) {
                    out.write(html);
                    out.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (action.equals("withdrawEvento")) {
            Integer idEvento = new Integer((String) request.getParameter("idEvento"));
            gestoreListaEventi.removeEventoUtente(idEvento, userid);
            Evento tempE = gestoreEvento.getEvento(idEvento);          
            tempE.setCompleto("no");
            tempE.setGiocatoripagato(tempE.getGiocatoripagato() - 1);
            gestoreEvento.updateEvento(tempE);

        }
        
        if (action.equals("deleteEvento")) {
            Integer idEvento = new Integer((String) request.getParameter("idEvento"));
            gestoreEvento.removeEvento(idEvento);

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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EventiController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EventiController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
