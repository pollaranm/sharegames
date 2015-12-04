/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ejb.Amministratore;
import ejb.Prezziario;
import ejb.Squadra;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.GestoreAmministratoreLocal;
import manager.GestoreCampoLocal;
import manager.GestoreEventoLocal;

import manager.GestoreImpiantoLocal;
import manager.GestoreListaEventiLocal;
import manager.GestorePrezziarioLocal;
import manager.GestoreSquadraLocal;
import manager.GestoreUtenteLocal;

/**
 *
 * @author Alex
 */
public class Controller extends HttpServlet {
    @EJB
    private GestoreUtenteLocal gestoreUtente;
    @EJB
    private GestoreSquadraLocal gestoreSquadra;
    @EJB
    private GestoreEventoLocal gestoreEvento;
    @EJB
    private GestorePrezziarioLocal gestorePrezziario;
    @EJB
    private GestoreAmministratoreLocal gestoreAmministratore;
    @EJB
    private GestoreCampoLocal gestoreCampo;
    @EJB
    private GestoreImpiantoLocal gestoreImpianto;
    
    
    
    

    
    


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controller</title>");
            
            
            //gestoreImpianto.addImpianto();
            
            
            //gestoreImpianto.addImpianto("ImpiantoProva1", "Italia", "Piemonte", "Torino", "Torino", "Corso Regina", "333", "000000000", "08-22", "nessuno");
            
//            for(int i=0;i<gestoreImpianto.getImpiantoByCitta("Torino").size();i++){
//                
//                String nome = gestoreImpianto.getImpiantoByCitta("Torino").get(i).getNome();
//                int    id   = gestoreImpianto.getImpiantoByCitta("Torino").get(i).getIdimpianto();
//                out.println("<p>Nome: "+nome + ", Id: "+id+"<p>");
//            }
            
//            for(int i=0;i<gestoreImpianto.getImpiantoByProvincia("Torino").size();i++){
//                
//                String nome = gestoreImpianto.getImpiantoByProvincia("Torino").get(i).getCitta();
//                int    id   = gestoreImpianto.getImpiantoByProvincia("Torino").get(i).getIdimpianto();
//                out.println("<p>Nome: "+nome + ", Id: "+id+"<p>");
//            }
            
//            out.println(gestoreImpianto.getObjectImpiantoById(25));

            //gestoreImpianto.updateImpianto("ImpiantoProva1", "Italia", "Piemonte", "Vercelli", "Torino", "Corso Regina", "333", "000000000", "08-22", "nessuno",25);

//            gestoreCampo.addCampo(2,20,"calcio7",7);
//            gestoreCampo.addCampo(2,22,"calcio7",7);
//            gestoreCampo.addCampo(1,20,"calcio7",7);
            //out.println(gestoreCampo.getObjectCampoById(3, 19).getIdimpianto());
            //gestoreCampo.updateCampo(50, 19, "calcio5", 5);
            //gestoreCampo.removeCampo(50, 19);
            
            //out.println(gestoreCampo.getCampoByImpianto(19));
            //out.println(gestoreCampo.getCampoByTipologia("calcio5"));
            
              /* TEST AMMINISTRATORE */
            
            //gestoreAmministratore.addAmministratore(24, "Mario", "Rossi");
            //Amministratore a=gestoreAmministratore.getObjAmministratore(7);
            // out.println("Nome: " + a.getNome()+ "Cognome: " + a.getCognome() + "  Id Amministratore: " + a.getIdamministratore() + "  ID_IMPIANTO" + a.getIdimpianto().getIdimpianto());
            //gestoreAmministratore.removeAmministratore(7);
             
            /*TEST EVENTO*/
            
            //gestoreEvento.updateEvento(20,1,1,"2012","02");
            //gestoreEvento.updateEvento(20, 1, 1, "2015/2025", "20154");
            
            /*TEST PREZZIARIO*/
            
            //BigDecimal val;
            //float prezzo=(float)8.85;
            //int sconto=21;
            //float prezzo_scontato=(float)((prezzo)/((float)1+((float)(sconto)/(float)100)));
            //val= new BigDecimal(prezzo_scontato);
            
            //gestorePrezziario.addPrezziario(5,25, val, sconto);
            //gestorePrezziario.removePrezziario(5,25);
            //Prezziario p;
            //p=gestorePrezziario.getObjPrezziario(5, 25);
            //out.println("ID impianto: " + p.getImpianto().getNome() + "  Prezzo: " + p.getPrezzo()+ "  Sconto: " + p.getSconto() );
            //gestorePrezziario.removePrezziario(5,25);
            //gestorePrezziario.updatePrezziario(5, 25, val, sconto);
            
            /* TEST UPDATE SQUADRA */
            //gestoreSquadra.updateSquadra(4, "New s1", 5, "calcio11", "Pisa");
            
            /* TEST UPDATE UTENTE */
            //gestoreUtente.updateUtente("10206904148233460", "facebook", "Nicolò Mattia Pollara","lamia@mail.it" , "+393338");
            
            /* TEST getSquadraBy */
            //for( int i = 1 ; i < 6 ; i++) {
            //    gestoreSquadra.addSquadra("Gianduiotti" + i, "calcio5", "Torino");
            //}
            //for(Squadra temp : gestoreSquadra.getSquadraByCittaTipologia("Torino", "calcio5")){
            //    out.println(temp.getNomesquadra() + "-" + temp.getTipologia() + "-" + temp.getCitta() + "<br>");
            //}
              //gestoreEvento.removeEvento(1);
            
                
            //out.println(gestoreEvento.getEventoCompletoByProvincia("Vercelli")+"\n");
            //out.println(gestoreListaEventi.getEventoByPagato(14)+"\n");
            //out.println(gestoreListaEventi.getListaEventiUtenti()+"\n");
            
            //gestoreEvento.addEvento(20, 1, "30/11/2016", "18", "calcio", "no", "no", 2, 14);
            
            /*int idimpianto=(Integer.parseInt(request.getParameter("idimpianto")));
            int idcampo=(Integer.parseInt(request.getParameter("idcampo")));
            String data=request.getParameter("data");
            String ora=request.getParameter("ora");
            String sport=request.getParameter("sport");*/
//            int idutente=(Integer.parseInt(request.getParameter("idutente")));
            
            //gestoreEvento.addEvento(idimpianto, idcampo, data, ora, sport, "no", "no", 0, 14);

            
            //out.println("Hai inserito un evento mbare.");
            
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }

    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
