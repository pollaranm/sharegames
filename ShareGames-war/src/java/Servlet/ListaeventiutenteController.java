package Servlet;

import ejb.Campo;
import ejb.Evento;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.GestoreEventoLocal;
import manager.GestoreListaEventiLocal;


@WebServlet(name = "ListaeventiutenteController", urlPatterns = {"/ListaeventiutenteController"})
public class ListaeventiutenteController extends HttpServlet {

    @EJB
    private GestoreListaEventiLocal gestoreListaEventi;

    @EJB
    private GestoreEventoLocal gestoreEvento;

    HttpSession s;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext ctx = getServletContext();
        response.setContentType("text/html;charset=UTF-8");
        s = request.getSession();
        Integer userid = (Integer) s.getAttribute("idnumber");
        String action = request.getParameter("action");
        
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
                    html = "Non hai ancora partecipato a nessun evento...";
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
