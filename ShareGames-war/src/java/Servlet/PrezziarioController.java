/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ejb.Campo;
import ejb.Prezziario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.GestoreCampoLocal;
import manager.GestorePrezziarioLocal;

/**
 *
 * @author Ivan
 */
public class PrezziarioController extends HttpServlet {
    @EJB
    private GestoreCampoLocal gestoreCampo;
    @EJB
    private GestorePrezziarioLocal gestorePrezziario;
    
     HttpSession s;
     String state = "index";


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ServletContext ctx = getServletContext();
        response.setContentType("text/html;charset=UTF-8");
        
        
        String action=request.getParameter("action");
        
        if(action.equals("getimpianti")){
            int impianto=Integer.parseInt(request.getParameter("impianto"));
            
            PrintWriter out=response.getWriter();
                
            List<Campo> l=gestoreCampo.getCampoByImpianto(impianto);
            String tmp="<select id=selectcampo>";
            for(int i=0; i<l.size(); i++){
                tmp+="<option value="+l.get(i).getCampoPK().getIdcampo()+">"+l.get(i).getCampoPK().getIdcampo()+"</option>";
            }
            
                tmp+="</select>";
                out.write(tmp);
     
                out.close();

        }
        
        
        else if(action.equals("getprezziario")){
            
            
            int impianto=Integer.parseInt(request.getParameter("impianto"));
            int campo=Integer.parseInt(request.getParameter("campo"));
            
            PrintWriter out=response.getWriter();
            
            Prezziario p = gestorePrezziario.getObjPrezziario(campo, impianto);
           
            
            
            if(p != null){
                
                String tmp="<div id=selectprezzo>";
                tmp+="Prezzo: "+p.getPrezzo()+"<br>";
                tmp+="Sconto: "+p.getSconto();
                tmp+="</div>";
                
                
                out.write(tmp);
            }
            
            else{
                String tmp="<div id=selectprezzo>";
                tmp+="<br> Attenzione: Nessun prezzo inserito per l'Impianto: "+impianto +" e per il Campo: "+campo;
                tmp+="</div>";
                out.write(tmp);
                
            }
            
            out.close();

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
