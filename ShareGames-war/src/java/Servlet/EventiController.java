package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.GestoreEventoLocal;

@WebServlet(name = "EventiController", urlPatterns = {"/EventiController"})
public class EventiController extends HttpServlet {

    @EJB
    private GestoreEventoLocal gestoreEvento;

    HttpSession s;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        ServletContext ctx = getServletContext();
        response.setContentType("text/html;charset=UTF-8");
        s = request.getSession();
        Integer userid = (Integer) s.getAttribute("idnumber");
        String action = request.getParameter("action");

        if (action.equals("getregioni")) {
            Eventi e = new Eventi();
            List<Integer> id = e.getIdRegioni();
            List<String> l = e.getRegioni();
            try (PrintWriter out = response.getWriter()) {
                String regioni = "";
                for (int i = 0; i < l.size(); i++) {
                    regioni += "<option value=" + id.get(i) + ">" + l.get(i) + "</option>";
                }
                out.write(regioni);
            }
        }

        if (action.equals("getprovince")) {
            int idregione = Integer.parseInt(request.getParameter("idregione"));
            Eventi e = new Eventi();
            List<String> l = e.getProvince(idregione);
            try (PrintWriter out = response.getWriter()) {
                String citta = "";
                for (int i = 0; i < l.size(); i++) {
                    citta += "<option value=" + l.get(i) + ">" + l.get(i) + "</option>"; //metto come value il nome della città e non la sigla
                }
                out.write(citta);
            }
        }

        if (action.equals("getsport")) {
            String sport = "";
            sport += "<option value='Calcio5'>Calcio a 5</option>"
                    + "<option value='Calcio7'>Calcio a 7</option>"
                    + "<option value='Calcio11'>Calcio a 11</option>"
                    + "<option value='Pallavolo'>Pallavolo</option>"
                    + "<option value='Basket'>Basket</option>"
                    + "<option value='Tennis'>Tennis</option>";
            try (PrintWriter out = response.getWriter()) {
                out.write(sport);
                out.close();
            }
        }

        if (action.equals("getora")) {
            String orari = "";
            int i;
            for (i = 7; i < 10; i++) {
                orari += "<option value='0" + i + ":00'>0" + i + ":00</option>";
            }
            for (i = 10; i < 24; i++) {
                orari += "<option value='" + i + ":00'>" + i + ":00</option>";
            }
            try (PrintWriter out = response.getWriter()) {
                out.write(orari);
                out.close();
            }
        }

        if (action.equals("searchEvento")) {
            String prov = (String) request.getParameter("prov");
            String sport = (String) request.getParameter("sport");

            try {
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost:3306/newsharegames?zeroDateTimeBehavior=convertToNull";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "root", "root");
                String query = "SELECT * FROM evento, impianto, prezziario WHERE "
                        + "evento.idimpianto=impianto.idimpianto "
                        + "AND evento.idimpianto=prezziario.idimpianto "
                        + "AND evento.idcampo=prezziario.idcampo "
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
                        Double tempPrice = new Double(rs.getDouble("prezzo") * (Double) (100.00 - rs.getDouble("sconto")) / 100);
                        String actualPrice = String.format("%.2f", tempPrice);
                        temp = " <article class='item'>"
                                + "  <header>"
                                + "    <ul style='text - align: left;margin - left: 5%'>"
                                + "      <li><span>Data: " + rs.getString("data") + " - " + rs.getString("ora") + "</span></li>"
                                + "      <li><span>Campo: " + rs.getString("prezziario.idcampo") + " - " + actualPrice + " &euro;</span></li>"
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

        if (action.equals("deleteEvento")) {
            Integer idEvento = new Integer((String) request.getParameter("idEvento"));
            gestoreEvento.removeEvento(idEvento);
        }

        if (action.equals("searchFields")) {
            String provincia = (String) request.getParameter("provincia");
            String data = (String) request.getParameter("dataE");
            String ora = (String) request.getParameter("ora");
            String sport = (String) request.getParameter("sport");

            try {
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost:3306/newsharegames?zeroDateTimeBehavior=convertToNull";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "root", "root");
                String query = ""
                        + "SELECT * FROM impianto, campo, prezziario "
                        + "WHERE campo.idimpianto=impianto.idimpianto "
                        + "AND campo.idcampo = prezziario.idcampo "
                        + "AND campo.idimpianto = prezziario.idimpianto "
                        + "AND impianto.provincia = '" + provincia + "' "
                        + "AND campo.tipologia = '" + sport + "' "
                        + "AND NOT EXISTS "
                        + "(SELECT * FROM evento "
                        + "WHERE evento.idimpianto = campo.idimpianto "
                        + "AND evento.idcampo = campo.idcampo "
                        + "AND evento.data = '" + data + "' "
                        + "AND evento.ora = '" + ora + "') "
                        + "ORDER BY impianto.idimpianto ";
                java.sql.Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);

                String html = "<div id='wrapper' style='float:left;width: 50%'>"
                        + "  <form onsubmit='return false'>";
                int imp = -1;
                if (rs.isBeforeFirst()) {
                    rs.next();
                    String address = rs.getString("impianto.indirizzo") + " " + rs.getString("impianto.citta");
                    address = address.replace(" ", "+");
                    Double tempPrice = new Double(rs.getDouble("prezzo") * (Double) (100.00 - rs.getDouble("sconto")) / 100);
                    String actualPrice = String.format("%.2f", tempPrice);
                    html += ""
                            + "<div class='col-1'>"
                            + "    <label style='text-align: center'>"
                            + "        <input style='text-align: center' value='" + rs.getString("impianto.nome") + "' readonly='true'>"
                            + "        <input style='text-align: center' value='" + rs.getString("impianto.indirizzo") + " " + rs.getString("impianto.citta") + "' readonly='true'>"
                            + "        <img class='gmaps' style='width:94%;height:auto' "
                            + "        src='http://maps.google.com/maps/api/staticmap?markers=size:mid|color:blue|" + address + "&size=500x300&sensor=false&size=600x300&key=AIzaSyAbz8o3xVmsMTpHh3DRWO1kIW38K3zBVJ4'>"
                            + "        </img>"
                            + "        <input type='hidden' id='idImpianto' name='idImpianto' value='" + rs.getString("idimpianto") + "'>"
                            + "        <select id='idCampo' name='idCampo'>"
                            + "            <option value='" + rs.getString("idcampo") + "'>Campo n°" + rs.getString("idcampo") + " - " + actualPrice + "&euro; a testa";
                    imp = rs.getInt("idimpianto");
                    while (rs.next()) {
                        if (imp == rs.getInt("idimpianto")) {
                            tempPrice = new Double(rs.getDouble("prezzo") * (Double) (100.00 - rs.getDouble("sconto")) / 100);
                            actualPrice = String.format("%.2f", tempPrice);
                            html += "<option value='" + rs.getString("idcampo") + "'>Campo n°" + rs.getString("idcampo") + " - " + actualPrice + "&euro; a testa</option>";;
                        } else {
                            imp = rs.getInt("idimpianto");
                            html += ""
                                    + "</select>"
                                    + "<button class='button createE'>Crea evento</button>"
                                    + "</label>"
                                    + "</div>"
                                    + "</form>"
                                    + "</div>"
                                    + "<div id='wrapper' style='float:left;width: 50%'>"
                                    + "  <form onsubmit='return false'>";
                            address = rs.getString("impianto.indirizzo") + " " + rs.getString("impianto.citta");
                            address = address.replace(" ", "+");
                            tempPrice = new Double(rs.getDouble("prezzo") * (Double) (100.00 - rs.getDouble("sconto")) / 100);
                            actualPrice = String.format("%.2f", tempPrice);
                            html += ""
                                    + "<div class='col-1'>"
                                    + "    <label style='text-align: center'>"
                                    + "        <input style='text-align: center' value='" + rs.getString("impianto.nome") + "' readonly='true'>"
                                    + "        <input style='text-align: center' value='" + rs.getString("impianto.indirizzo") + " " + rs.getString("impianto.citta") + "' readonly='true'>"
                                    + "        <img class='gmaps' style='width:94%;height:auto' "
                                    + "        src='http://maps.google.com/maps/api/staticmap?markers=size:mid|color:blue|" + address + "&size=500x300&sensor=false&size=600x300&key=AIzaSyAbz8o3xVmsMTpHh3DRWO1kIW38K3zBVJ4'>"
                                    + "        </img>"
                                    + "        <input type='hidden' id='idImpianto' name='idImpianto' value='" + rs.getString("idimpianto") + "'>"
                                    + "        <select id='idCampo' name='idCampo'>"
                                    + "            <option value='" + rs.getString("idcampo") + "'>Campo n°" + rs.getString("idcampo") + " - " + actualPrice + "&euro; a testa";
                        }
                    }
                    html += "</select>"
                            + "<button class='button createE'>Crea evento</button>"
                            + "</label>"
                            + "</div>";
                } else {
                    html = "Nessun campo disponibile per questa richiesta!";
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

        if (action.equals("createEvento")) {
            Integer idimpianto = new Integer((String) request.getParameter("idI"));
            Integer idcampo = new Integer((String) request.getParameter("idC"));
            String data = (String) request.getParameter("data");
            String ora = (String) request.getParameter("ora");
            String sport = (String) request.getParameter("sport");
            gestoreEvento.addEvento(idimpianto, idcampo, data, ora, sport, "si", "no", 1, userid);
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
