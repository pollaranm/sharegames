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
import ejbFacade.CampoFacadeLocal;
import ejbFacade.EventoFacadeLocal;
import ejbFacade.ImpiantoFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author PC-STUDIO
 */
public class EventiController extends HttpServlet {

    @EJB
    private GestoreListaEventiLocal gestoreListaEventi;
    @EJB
    private GestoreEventoLocal gestoreEvento;
    @EJB
    private GestoreCampoLocal gestoreCampo;
    HttpSession s;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        ServletContext ctx = getServletContext();
        response.setContentType("text/html;charset=UTF-8");
        s = request.getSession();
        String action = request.getParameter("action");

        if (action == null) {
            action = "fine";
        }

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
            System.out.print(prov + " - " +sport);
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
                        + "AND evento.sport = '" + sport + "'";
                // create the java statement
                java.sql.Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                String html = "inizio<br>";
                while (rs.next()) {
                    html += rs.getString("idevento") + " - " + rs.getString("evento.idimpianto");
                }
                try (PrintWriter out = response.getWriter()) {
                    out.write(html);
                    out.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
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
            int idutente = Integer.parseInt(iduser);
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
        } else if (action.equals("fine")) {

            int idimpianto = Integer.parseInt(request.getParameter("impianto"));
            int idcampo = Integer.parseInt((request.getParameter("campo")));
            String data = (String) request.getParameter("data");
            String ora = (String) request.getParameter("ora");
            String sport = (String) request.getParameter("sport");
            int idutente = Integer.parseInt((String) s.getAttribute("idutente"));

            sport = sport.substring(0, sport.length() - 1);

            gestoreEvento.addEvento(idimpianto, idcampo, data, ora, sport, "no", "no", 0, idutente);

            try {

                PrintWriter out = response.getWriter();

                out.write("Evento aggiunto con successo!!!");
                out.close();

            } catch (Exception e) {

                PrintWriter out = response.getWriter();
                out.write("Errore, questo evento esiste giÃ !");
            }
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

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Servlet;
//
//import ejb.Campo;
//import ejb.Evento;
//import ejb.EventoPK;
//import ejb.Impianto;
//import ejb.Listaeventiutente;
//import ejbFacade.CampoFacadeLocal;
//import ejbFacade.EventoFacadeLocal;
//import ejbFacade.ImpiantoFacadeLocal;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.ejb.EJB;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import manager.GestoreCampoLocal;
//import manager.GestoreEventoLocal;
//import manager.GestoreListaEventiLocal;
//
///**
// *
// * @author PC-STUDIO
// */
//public class EventiController extends HttpServlet {
//    @EJB
//    private GestoreListaEventiLocal gestoreListaEventi;
//    @EJB
//    private GestoreEventoLocal gestoreEvento;
//    @EJB
//    private GestoreCampoLocal gestoreCampo;
//    
//    HttpSession s;
//    
//
//    
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
//                
//        ServletContext ctx = getServletContext();
//        response.setContentType("text/html;charset=UTF-8");
//        s = request.getSession();
//        
//        
//        String action=request.getParameter("action");
//        
//        if(action==null){
//            action="fine";
//        }
//        
//        if(action.equals("getregioni")){
//            
//            
//            Eventi e=new Eventi();
//            List<Integer> id=e.getidRegioni(); //stessa dimensione delle liste
//            List<String> l=e.getRegioni();
//            
//            PrintWriter out=response.getWriter();
//            String regioni="";
//            
//             for(int i=0; i<l.size(); i++){
//                regioni+="<option value="+id.get(i)+">"+l.get(i)+"</option>";
//            }
//             
//              out.write(regioni);
//              out.close();
//            
//        }
//        
//        
//        if(action.equals("getcitta")){
//            
//            int idregione=Integer.parseInt(request.getParameter("idregione"));
//            Eventi e=new Eventi();
//            List<String> l=e.getCitta(idregione);
//            List<String> l2=e.getSiglaProvince(idregione);
//            
//            
//            PrintWriter out=response.getWriter();
//            String citta="";
//            
//
//             for(int i=0; i<l.size(); i++){
//                citta+="<option value="+l2.get(i)+">"+l.get(i)+"</option>";
//            }
//             
//              out.write(citta);
//              out.close();
//            
//        }
//        
//        
//        if(action.equals("getprovincia")){
//            
//            String citta=request.getParameter("citta");
//            Eventi e=new Eventi();
//            List<String> l=e.getProvince(citta);
//            
//            
//            PrintWriter out=response.getWriter();
//            String province="";
//
//             for(int i=0; i<l.size(); i++){
//                province+="<option value="+l.get(i)+">"+l.get(i)+"</option>";
//            }
//             
//              out.write(province);
//              out.close();
//            
//        }
//        
//        
//        if(action.equals("getsearchevento")){
//            
//           // String regione=request.getParameter("regione");
//            String provincia=request.getParameter("provincia");
//            String sport=request.getParameter("sport");
//            String result="";
//            
//            Eventi ev=new Eventi();
//            String citta=ev.getCittabySigla(request.getParameter("citta"));
//            
//            
//            System.out.println(citta);
//            System.out.println(provincia);
//            System.out.println(sport);
//            
//            //RECUPERARE DALLA SESSIONE IDUTENTE
//            //List <Listaeventiutente> lista=gestoreListaEventi.getEventoByUtente((Integer)request.getSession().getAttribute("idutente"));
//            
//            List <Listaeventiutente> lista=gestoreListaEventi.getEventoByUtente(14);
//            
//            for(int i=0; i<lista.size(); i++){
//                
//                if(/*lista.get(i).getEvento().getImpianto().getRegione().equals(regione)&&*/
//                        lista.get(i).getEvento().getImpianto().getCitta().equals(citta)&&
//                        lista.get(i).getEvento().getImpianto().getProvincia().equals(provincia) &&
//                        lista.get(i).getEvento().getSport().equals(sport)){
//                    Evento e=lista.get(i).getEvento();
//                    
//                    /*PrintWriter out=response.getWriter();
//                 
//                    out.write("Entra nell'IF");
//                    out.close();*/
//                    
//                    
//                   result+="<div class=4u>"+
//                "<article class=item style= 'border:1px; border-style:solid; border-color:lightgrey;'>"
//                    
//                    +"<h3>Evento "+(i+1)+"</h3><header>"
//                    +"<li>"
//                        + "<span >Evento numero: "+e.getEventoPK().getIdevento()+"</span>"
//                    + "</li>"
//                    
//                    +"<form id=formListaEventi>"
//                    + "<input readonly type='text' id=data type=text name=data value="+e.getEventoPK().getData()+"></input>"
//                    + "<input readonly type='text'  id=ora  type=text name=ora value="+e.getEventoPK().getOra()+"></input>"
//                    + "<input readonly type='text' id=idcampo  type=text name=idcampo value="+e.getEventoPK().getIdcampo()+"></input>"
//                    + "<input readonly type='text' id=idimpianto  type=hidden name=idimpianto value="+e.getEventoPK().getIdimpianto()+"></input>"
//                    + "<input readonly type='text' id=idevento  type=hidden name=idevento value="+e.getEventoPK().getIdevento()+"></input>"
//                    + "<input readonly type=hidden name=idproprietario value="+lista.get(i).getProprietario()+"></input>"
//                    + "</form>"
//                    + "<li>Pagato: <span class=icon fa-remove></span></li>"
//                    + "<span>Nome Impianto: "+e.getImpianto().getNome()+"</span></br>"
//                    //+ "<li> <img style=width:94%;height:auto src=http://maps.google.com/maps/api/staticmap?markers=size:mid|color:blue|Corso+regina+margherita+221+10144+torino&size=500x300&sensor=false&size=600x300&key=AIzaSyAbz8o3xVmsMTpHh3DRWO1kIW38K3zBVJ4></img></li></br>"
//                    + "</header></article>";
//                    
//                }
//                
//                
//            }
//            PrintWriter out=response.getWriter();
//            if(result.equals("")) result+="L'EVENTO CERCATO NON ESISTE!";
//            out.write(result);
//            out.close();
//            
//        }
//        
//        if(action.equals("getsport")){
//   
//            String sport="";
//
//
//                sport+="<option value=calcio>calcio</option>"
//                +"<option value=pallavolo>pallavolo</option>"
//                +"<option value=pallacanestro>pallacanestro</option>"
//                +"<option value=tennis>tennis</option>";
//    
//             
//              PrintWriter out=response.getWriter();
//              out.write(sport);
//              out.close();
//            
//        }
//
//        if(action.equals("getimpianto")){
//            int impianto=Integer.parseInt(request.getParameter("impianto"));
//            
//            PrintWriter out=response.getWriter();
//                
//            List<Campo> l=gestoreCampo.getCampoByImpianto(impianto);
//            String tmp="<select id=selectcampo>";
//            for(int i=0; i<l.size(); i++){
//                tmp+="<option value="+l.get(i).getCampoPK().getIdcampo()+">"+l.get(i).getCampoPK().getIdcampo()+"</option>";
//            }
//            
//                tmp+="</select>";
//                out.write(tmp);
//                
//                out.close();
//
//        }
//        
//        
//        else if(action.equals("geteventibyuser")){
//            
//            String eventi="IDEVENTO IDIMPIANTO IDCAMPO DATA ORA SPORT PAGATO COMPLETO"+
//                     " GIOCATORIPAGATO IDUTENTE";
//            
//            String iduser="";
//            iduser+= s.getAttribute("idutente");
//            
//            int idutente=Integer.parseInt(iduser);
//            List<Listaeventiutente> l=gestoreListaEventi.getEventoByUtente(idutente);
//            
//                        
//            PrintWriter out=response.getWriter();
//            
//            try{
//            out.write(eventi);
//            out.write("\n");
//            }
//            
//            catch(Exception e){
//                out.write("Errore nella risposta");
//            }
//           
//            
//            
//            for(int i=0; i<l.size(); i++){
//                
//                eventi="";
//                eventi+=l.get(i).getEvento().getEventoPK().getIdevento()+" "+
//                        l.get(i).getEvento().getImpianto().getIdimpianto()+" "+
//                        l.get(i).getEvento().getCampo().getCampoPK().getIdcampo()+ " "+
//                       
//                        l.get(i).getEvento().getEventoPK().getData()+ " "+
//                        l.get(i).getEvento().getEventoPK().getOra()+ " "+
//                        l.get(i).getEvento().getSport()+ " "+
//                        l.get(i).getEvento().getPagato()+ " "+
//                        l.get(i).getEvento().getCompleto()+ " "+
//                        l.get(i).getEvento().getGiocatoripagato()+ " "+
//                        l.get(i).getEvento().getIdutente().getIdutente()+
//                        
//                        "<button id="+i+" value="+Integer.toString(l.get(i).getEvento().getEventoPK().getIdevento())+
//                        " type=\"button\" class=\"rimuovievento\">Rimuovi evento</button>"+
//                
//                        "<button id=updateevento value="+Integer.toString(l.get(i).getEvento().getEventoPK().getIdevento())+
//                        " type=\"button\">Aggiorna evento</button>";
//                        
//                
//                try{
//                    out.write(eventi);
//                }
//                
//                catch(Exception e){
//                    out.write("Errore in scrittura");
//                }
//                
//
//            }
//
//            try{
//                
//                // out.write("\n"+eventi);
//                 out.close();
//            }
//            
//            catch(Exception e){
//                out.write("Errore in scrittura");
//            }
//            
//        }
//        
//        
////        else if(action.equals("getsport")){
////            
////            int idcampo=Integer.parseInt(request.getParameter("campo"));
////            int idimpianto=Integer.parseInt(request.getParameter("impianto"));
////            
////            Campo c=gestoreCampo.getObjectCampoById(idcampo, idimpianto);
////            
////            String tmp="<select id=selectsport>";
////            tmp+="<option value="+c.getTipologia()+">"+c.getTipologia()+"</option>";
////            
////            PrintWriter out=response.getWriter();
////            
////            out.write(tmp);
////            out.close();
////        }
//        
//        else if(action.equals("fine")){
//            
//            int idimpianto=Integer.parseInt(request.getParameter("impianto"));
//            int idcampo=Integer.parseInt((request.getParameter("campo")));
//            String data=(String)request.getParameter("data");
//            String ora=(String)request.getParameter("ora");
//            String sport=(String)request.getParameter("sport");
//            int idutente=Integer.parseInt((String)s.getAttribute("idutente"));
//            
//
//            sport=sport.substring(0, sport.length()-1);
//
//            
//            gestoreEvento.addEvento(idimpianto, idcampo, data, ora, sport, "no", "no", 0, idutente);
//            
//          try{  
//            
//          PrintWriter out=response.getWriter();
//            
//            
//          out.write("Evento aggiunto con successo!!!");
//          out.close();
//          
//          }
//          
//          catch(Exception e){
//              
//              PrintWriter out=response.getWriter();
//              out.write("Errore, questo evento esiste già!");
//          }
//        }
//        
//        else if(action.equals("rimuovi")){
//            
//            int idevento=Integer.parseInt(request.getParameter("idevento"));
//            gestoreEvento.removeEvento(idevento);
//            
//            PrintWriter out=response.getWriter();
//            try{ 
//                out.write("Evento rimosso!");
//                out.close();
//            }
//            
//            catch (Exception e){out.write("Errore AJAX");}
//        }
//        
//        
//        
//        else if(action.equals("aggiorna")){
//            /*
//            int idevento=Integer.parseInt(request.getParameter("idevento"));
//            int idimpianto=gestoreEvento.getEvento(idevento).getImpianto().getIdimpianto();
//            int idcampo=gestoreEvento.getEvento(idevento).getCampo().getCampoPK().getIdcampo();
//            /*String data=gestoreEvento.getEvento(idevento).getEventoPK().getData();
//            String ora=gestoreEvento.getEvento(idevento).getEventoPK().getOra();
//            String sport=gestoreEvento.getEvento(idevento).getSport();
//            int idutente=Integer.parseInt((String)s.getAttribute("idutente"));
//            String pagato=gestoreEvento.getEvento(idevento).getPagato();
//            String completo=gestoreEvento.getEvento(idevento).getCompleto();
//            int giocatoripagato=gestoreEvento.getGiocatoriPagato(idevento);
//            
//            
//            List<Campo> l=gestoreCampo.getCampoByImpianto(idimpianto);
//            String form=""+
//                   
//                    "Campo: <select id=selectcampo>";
//            
//            for(int i=0; i<l.size(); i++){
//                form+="<option value="+l.get(i).getCampoPK().getIdcampo()+">"+l.get(i).getCampoPK().getIdcampo()+"</option>";
//            }
//            
//                form+="</select></br>";
//            
//     
//            Campo c=gestoreCampo.getObjectCampoById(idcampo, idimpianto);
//            
//                form+="<label><span>Data:</span><script language=JavaScript>crea_data();</script></label><br>";
//            
//                form+="<select id=selectora name=selectora>"+
//                        "<option value=\"09\">09</option>"+
//                        "<option value=\"10\">10</option>"+
//                        "<option value=\"11\">11</option>"+
//                        "<option value=\"12\">12</option>"+
//                        "<option value=\"13\">13</option>"+
//                        "<option value=\"14\">14</option>"+
//                        "<option value=\"15\">15</option>"+
//                        "<option value=\"16\">16</option>"+
//                        "<option value=\"17\">17</option>"+
//                        "<option value=\"18\">18</option>"+
//                        "<option value=\"19\">19</option>"+
//                        "<option value=\"20\">20</option>"+
//                        "<option value=\"21\">21</option>"+
//                        "<option value=\"22\">22</option>"+
//                "</select> : 00</br>";
//            
//            
//            form+="<select id=selectsport>";
//            form+="<option value="+c.getTipologia()+">"+c.getTipologia()+"</option>";
//            
//            form+="Pagato: <select id=\"selectpagato\" name=\"selectpagato\">\n" +
//            "\n" +
//            "<option value=\"si\">si</option>\n" +
//            "<option value=\"no\">no</option>\n" +
//            "\n" +
//            "</select></br>";
//            
//            form+="Completo: <select id=\"selectcompleto\" name=\"selectcompleto\">\n" +
//            "\n" +
//            "<option value=\"si\">si</option>\n" +
//            "<option value=\"no\">no</option>     \n" +
//            "\n" +
//            "</select></br>\n" +
//            "\n" +
//            "Giocatori saldato: <select id=\"selectgiocatori\" name=\"selectgiocatori\">\n" +
//            "\n" +
//            "<option value=\"si\">si</option>\n" +
//            "<option value=\"no\">no</option>\n" +
//            "\n" +
//            "</select></br>";
//           
//            form+="";
//
//          try{  
//            
//          PrintWriter out=response.getWriter();
//            
//            
//          out.write(form);
//          out.close();
//          
//          }
//          
//          catch(Exception e){
//              
//              PrintWriter out=response.getWriter();
//              out.write("Errore caricaento dinamico");
//          }*/
//        }
//        
//        else if(action.equals("rimuovi")){
//            
//            int idevento=Integer.parseInt(request.getParameter("idevento"));
//            gestoreEvento.removeEvento(idevento);
//            
//            PrintWriter out=response.getWriter();
//            try{ 
//                out.write("Evento rimosso!");
//                out.close();
//            }
//            
//            catch (Exception e){out.write("Errore AJAX");}
//        }
//        
//        
//        
//        
//        
//        
//        
//    }
// 
//     
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            processRequest(request, response);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(EventiController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(EventiController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            processRequest(request, response);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(EventiController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(EventiController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//}
