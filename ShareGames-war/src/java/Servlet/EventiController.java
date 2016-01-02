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
            
    int idutente;
    String tiposocial="";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        ServletContext ctx = getServletContext();
        response.setContentType("text/html;charset=UTF-8");
        s = request.getSession();
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
            // List<String> l2=e.getSiglaProvince(idregione);
            PrintWriter out = response.getWriter();
            String citta = "";
            for (int i = 0; i < l.size(); i++) {
                citta += "<option value=" + l.get(i) + ">" + l.get(i) + "</option>"; //metto come value il nome della città e non la sigla
            }
            out.write(citta);
            out.close();
        }

        //Questo metodo potrebbe poi essere rivisto per prendere le città di una provincia ma per il momento
        //NON VIENE UTILIZZATO
//        if (action.equals("getcitta")) { // stesso discorso di prima, cercava le citta ma si chiamava getprovincia
//            String citta = request.getParameter("citta");
//            Eventi e = new Eventi();
//            List<String> l = e.getProvince(citta);
//            PrintWriter out = response.getWriter();
//            String province = "";
//
//            for (int i = 0; i < l.size(); i++) {
//                province += "<option value=" + l.get(i) + ">" + l.get(i) + "</option>";
//            }
//            out.write(province);
//            out.close();
//        }
        if (action.equals("getsport")) {
            String sport = "";
            sport += "<option value='calcio5'>calcio5</option>"
                    + "<option value='calcio7'>calcio7</option>"
                    + "<option value='calcio11'>calcio11</option>"
                    + "<option value='pallavolo'>pallavolo</option>"
                    + "<option value='pallacanestro'>pallacanestro</option>"
                    + "<option value='tennis'>tennis</option>";
            try (PrintWriter out = response.getWriter()) {
                out.write(sport);
                out.close();
            }
        }

        if (action.equals("searchEvento")) {
            String prov = (String) request.getParameter("prov");
            String sport = (String) request.getParameter("sport");
            System.out.print(prov + " - " + sport);
            List<Evento> eventoList = new ArrayList<Evento>();
            try {
                // create our mysql database connection
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost:3306/newsharegames?zeroDateTimeBehavior=convertToNull";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "root", "root");
                String query = "SELECT * FROM evento, impianto WHERE "
                        + "evento.idimpianto=impianto.idimpianto "
                        + "AND evento.data >= curdate() "
                        + "AND impianto.provincia = '" + prov + "' "
                        + "AND evento.sport = '" + sport + "' "
                        + "ORDER BY evento.data";
                // create the java statement
                java.sql.Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                
                
                //TOGLIERE I SEGUENTI COMMENTI NELLA VERSIONE DEFINITIVA!!!
                
                /*if(s.getAttribute("social").equals("facebook")){
                

                Utente u=gestoreUtente.getObjUtente((String)s.getAttribute("social"), "facebook");
                idutente=u.getIdutente();
                tiposocial="facebook;
                List<Evento> listaeventiutente=gestoreListaEventi..getEventoByUtente(Integer.parseInt((String)s.getAttribute("idutente")));
                }
                
                else if(s.getAttribute("social").equals("google")){
                

                Utente u=gestoreUtente.getObjUtente((String)s.getAttribute("social"), "google");
                idutente=u.getIdutente();
                tiposocial="google";
                List<Evento> listaeventiutente=gestoreListaEventi..getEventoByUtente(Integer.parseInt((String)s.getAttribute("idutente")));
                }*/
                
                
                
                //da cancellare queste due righe nella versione definitiva
                idutente=27;
                List<Listaeventiutente> listaeventiutente=gestoreListaEventi.getEventoByUtente(idutente);
                
                //----------------------------------------
                
                
                boolean esiste=false;
                
                String html = "";
                int count = 1;
                String col1 = "<div class='4u' id='col1'>";
                String col2 = "<div class='4u' id='col2'>";
                String col3 = "<div class='4u' id='col3'>";
                String temp = "";
                if (rs.first()) {
                    while (rs.next()) {
                        String address = rs.getString("impianto.indirizzo") + " " + rs.getString("impianto.citta");
                        address = address.replace(" ", "+");
                        //System.out.println(address);
                        temp = " <article class='item'>"
                                + "     <h3>Evento n°" + rs.getString("idevento") + "</h3>"
                                + "     <header>"
                                + "        <ul style='text - align: left;margin - left: 5%'>"
                                + "            <li><span>Data: " + rs.getString("data")+"</span></li>"
                                + "            <li><span>Ora: " + rs.getString("ora")+"</span></li>"
                                + "            <li><span>Giocatori: " + rs.getString("giocatoripagato") + "</span></li>"
                                + "            <li><span>Impianto:" + rs.getString("nome") + "</span></li>"
                                + "            <li><span>" + rs.getString("indirizzo") + ", " + rs.getString("citta") + "</span></li>"
                                + "            <li> <img style='width:94%;height:auto' "
                                + "            src='http://maps.google.com/maps/api/staticmap?markers=size:mid|color:blue|" + address + "&size=500x300&sensor=false&size=600x300&key=AIzaSyAbz8o3xVmsMTpHh3DRWO1kIW38K3zBVJ4'>"
                                + "            </img>"
                                + "            </li>"
                                + "         </ul>";
                     
                     for(int i=0; i<listaeventiutente.size(); i++){
                         Evento e=listaeventiutente.get(i).getEvento();
                         if(rs.getString("idevento").equals(e.getEventoPK().getIdevento())){
                             esiste=true;
                             break;
                         }
                     }
                         
                        if(!esiste){
                        
                                temp+= "    <form action='EventiController' style='border-style:none'>"
                                + "       <input type='hidden' name='action' value='joinEvento'>"
                                + "       <input type='hidden' name='idEvento' value='" + rs.getString("idevento") + "'>"
                                + "       <button class='button'>Partecipa</button>"
                                + "    </form>";
                        }
                     
                                temp+= "    </header>"
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
            //boh tutta la roba che devi fare per registrare una partecipazione ad un evento
            String id = (String) s.getAttribute("id");
            String social = (String) s.getAttribute("social");
            String idevento = (String) request.getParameter("idEvento");
            Evento evento = gestoreEvento.getEvento(new Integer(idevento));
            Utente utente = gestoreUtente.getObjUtente(id, social);
            
            //Bisogna controllare che non esista già?
            
            gestoreListaEventi.addEventoUtente(evento.getEventoPK().getIdevento(), utente.getIdutente());
            
            //Mando una richiesta a ServletController per rimanere nello stesso Controller.
            response.sendRedirect("ServletController");

        }

        //--------------------------------------------------------------------
        if (action.equals("getimpianto")) {
            int impianto = Integer.parseInt(request.getParameter("impianto"));
            PrintWriter out = response.getWriter();
            List<Campo> l = gestoreCampo.getCampoByImpianto(impianto);
            String tmp = "<select id=selectcampo>";
            for (int i = 0; i < l.size(); i++) {
                tmp += "<option value=" + l.get(i).getCampoPK().getIdcampo() + ">" + l.get(i).getCampoPK().getIdcampo() + "</option>";
            }
            tmp += "</select>";
            out.write(tmp);
            out.close();
        }

        if (action.equals("geteventibyuser")) {
            String eventi = "IDEVENTO IDIMPIANTO IDCAMPO DATA ORA SPORT PAGATO COMPLETO"
                    + " GIOCATORIPAGATO IDUTENTE";
            String iduser = "";
            iduser += s.getAttribute("idutente");
            idutente = Integer.parseInt(iduser);
            List<Listaeventiutente> l = gestoreListaEventi.getEventoByUtente(idutente);
            PrintWriter out = response.getWriter();
            try {
                out.write(eventi);
                out.write("\n");
            } catch (Exception e) {
                out.write("Errore nella risposta");
            }
            for (int i = 0; i < l.size(); i++) {
                eventi = "";
                eventi += l.get(i).getEvento().getEventoPK().getIdevento() + " "
                        + l.get(i).getEvento().getImpianto().getIdimpianto() + " "
                        + l.get(i).getEvento().getCampo().getCampoPK().getIdcampo() + " "
                        + l.get(i).getEvento().getEventoPK().getData() + " "
                        + l.get(i).getEvento().getEventoPK().getOra() + " "
                        + l.get(i).getEvento().getSport() + " "
                        + l.get(i).getEvento().getPagato() + " "
                        + l.get(i).getEvento().getCompleto() + " "
                        + l.get(i).getEvento().getGiocatoripagato() + " "
                        + l.get(i).getEvento().getIdutente().getIdutente()
                        + "<button id=" + i + " value=" + Integer.toString(l.get(i).getEvento().getEventoPK().getIdevento())
                        + " type=\"button\" class=\"rimuovievento\">Rimuovi evento</button>"
                        + "<button id=updateevento value=" + Integer.toString(l.get(i).getEvento().getEventoPK().getIdevento())
                        + " type=\"button\">Aggiorna evento</button>";
                try {
                    out.write(eventi);
                } catch (Exception e) {
                    out.write("Errore in scrittura");
                }
            }
            try {
                out.write("\n" + eventi);
                out.close();
            } catch (Exception e) {
                out.write("Errore in scrittura");
            }

        } else if (action.equals("getsport")) {

            int idcampo = Integer.parseInt(request.getParameter("campo"));
            int idimpianto = Integer.parseInt(request.getParameter("impianto"));

            Campo c = gestoreCampo.getObjectCampoById(idcampo, idimpianto);

            String tmp = "<select id=selectsport>";
            tmp += "<option value=" + c.getTipologia() + ">" + c.getTipologia() + "</option>";

            PrintWriter out = response.getWriter();

            out.write(tmp);
            out.close();
        } 
        
        else if (action.equals("insertevento")) {

            int idimpianto = Integer.parseInt(request.getParameter("impianto"));
            int idcampo = Integer.parseInt((request.getParameter("campo")));
            String data = request.getParameter("data");
            String ora =request.getParameter("ora");
            String minuti=request.getParameter("minuti");
            String sport =request.getParameter("sport");
            String oraeminuti=ora+":"+minuti;
            
            if(s.getAttribute("social").equals("facebook")){
                
                Utente u=gestoreUtente.getObjUtente(s.getAttribute("id").toString(), "facebook");
                idutente=u.getIdutente();
            }
            
            else if(s.getAttribute("social").equals("google")){
                Utente u=gestoreUtente.getObjUtente((String)s.getAttribute("id"), "google");
                idutente=u.getIdutente();
            }

            try {
                
                gestoreEvento.addEvento(idimpianto, idcampo, data, oraeminuti, sport, "no", "no", 0, idutente);
                PrintWriter out = response.getWriter();

                out.write("Evento aggiunto con successo!!!");
                out.close();

            } catch (Exception e) {

                PrintWriter out = response.getWriter();
                out.write("Errore, questo evento esiste gia'!");
            }
        } 
        else if (action.equals("rimuovi")) {

            int idevento = Integer.parseInt(request.getParameter("idevento"));
            gestoreEvento.removeEvento(idevento);

            PrintWriter out = response.getWriter();
            try {
                out.write("Evento rimosso!");
                out.close();
            } catch (Exception e) {
                out.write("Errore AJAX");
            }
        } else if (action.equals("aggiorna")) {

            int idevento = Integer.parseInt(request.getParameter("idevento"));
            int idimpianto = Integer.parseInt(request.getParameter("idimpianto"));
            int idcampo = Integer.parseInt(request.getParameter("idcampo"));
            String data = request.getParameter("data");
            String ora = request.getParameter("ora");
            String pagato = gestoreEvento.getEvento(idevento).getPagato();
            int giocatoripagato = gestoreEvento.getEvento(idevento).getGiocatoripagato();
            String sport = gestoreEvento.getEvento(idevento).getSport();

            gestoreEvento.updateEvento(idimpianto, idcampo, idevento, data, ora, pagato, sport, giocatoripagato);
            Eventi e = new Eventi();
            s.setAttribute("lista", e.getListaEventiUtente(14));

        } else if (action.equals("rimuovi")) {

            int idevento = Integer.parseInt(request.getParameter("idevento"));
            gestoreEvento.removeEvento(idevento);

            PrintWriter out = response.getWriter();
            try {
                out.write("Evento rimosso!");
                out.close();
            } catch (Exception e) {
                out.write("Errore AJAX");
            }
        }
        
        //  CARICO LO STORICO EVENTI (EVENTI PRENOTATI)
        //  CORREGGERE: FUNZIONA CON IL <= currentDate()? Dovrebbe essere solo < e aggiungere ORDER BY
        
        
        else if(action.equals("getstoricoeventi")){
            try {
                // create our mysql database connection
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost:3306/newsharegames?zeroDateTimeBehavior=convertToNull";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "root", "root");
                String query = "SELECT * FROM evento, impianto WHERE "
                        + "evento.idimpianto=impianto.idimpianto "
                        + "AND evento.data <= curdate() "
                        + "AND evento.idutente = '" + idutente + "' ";
                        //+ "ORDER BY evento.data";
                
                
                // create the java statement
                java.sql.Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                
                String html = "";
                int count = 1;
                String col1 = "<div class='4u' id='col1'>";
                String col2 = "<div class='4u' id='col2'>";
                String col3 = "<div class='4u' id='col3'>";
                String temp = "";
                if (rs.first()) {
                    while (rs.next()) {
                        String address = rs.getString("impianto.indirizzo") + " " + rs.getString("impianto.citta");
                        address = address.replace(" ", "+");
                        System.out.println(address);
                        temp = " <article class='item'>"
                                + "     <h3>Evento n°" + rs.getString("idevento") + "</h3>"
                                + "     <header>"
                                + "        <ul style='text - align: left;margin - left: 5%'>"
                                + "            <li><span>Data: " + rs.getString("data") + "</span></li>"
                                + "            <li><span>Ora: " + rs.getString("ora")+"</span></li>"
                                + "            <li><span>Giocatori: " + rs.getString("giocatoripagato") + "</span></li>"
                                + "            <li><span>Impianto:" + rs.getString("nome") + "</span></li>"
                                + "            <li><span>" + rs.getString("indirizzo") + ", " + rs.getString("citta") + "</span></li>"
                                + "            <li> <img style='width:94%;height:auto' "
                                + "            src='http://maps.google.com/maps/api/staticmap?markers=size:mid|color:blue|" + address + "&size=500x300&sensor=false&size=600x300&key=AIzaSyAbz8o3xVmsMTpHh3DRWO1kIW38K3zBVJ4'>"
                                + "            </img>"
                                + "            </li>"
                                + "         </ul>"
                                + "    </header>"
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
                }
                

                    PrintWriter out = response.getWriter(); 
                    out.write(html);
                    out.close();
            }
         
            catch(Exception e2){
                e2.printStackTrace();;
            }
             
        }
        
        
        
        
        
        else if(action.equals("selectora")){
            String ora="<option value='20'>20</option>" +
                       "<option value='21'>21</option>" +
                        "<option value='22'>22</option>";
            
            try{
                 PrintWriter out = response.getWriter(); 
                    out.write(ora);
                    out.close();
            }
         
            catch(Exception e2){
                e2.printStackTrace();;
            }
        }
        
        
        
        else if(action.equals("selectminuti")){
            String minuti="<option value='00'>00</option>" +
                       "<option value='15'>15</option>" +
                       "<option value='30'>30</option>" +
                       "<option value='45'>45</option>";
            
            try{
                 PrintWriter out = response.getWriter(); 
                    out.write(minuti);
                    out.close();
            }
         
            catch(Exception e2){
                e2.printStackTrace();;
            }
        }
        
        
        
        
        
        
        
        else if(action.equals("caricaimpianti")){
            
            String impianti=(String)s.getAttribute("selectimpianto");
            
             try{
                 PrintWriter out = response.getWriter(); 
                    out.write(impianti);
                    out.close();
            }
         
            catch(Exception e2){
                e2.printStackTrace();;
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
