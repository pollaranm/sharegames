/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.Utente;
import ejbFacade.UtenteFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Alex
 */
@Stateless
public class gestoreUtente implements gestoreUtenteLocal {
    @EJB
    private UtenteFacadeLocal utenteFacade;

    @Override
    public void AddUser(String name, String email, String idgoogle, String idfacebook, String telefono) {
        
        Utente u = new Utente();
        u.setEmail(email);
        u.setIdfacebook(idfacebook);
        u.setIdgoogle(idgoogle);
        u.setNome(name);
        u.setTelefono(telefono);
        
        utenteFacade.create(u);
        
    }

    @Override
    public boolean findFacebook(String idfacebook) {
        
//        
//        boolean b = utenteFacade.findbyface(idfacebook);
//        
//        if(b){
//            return true;
//        }else return false;
//        
        return utenteFacade.findbyface(idfacebook);
        
        
    }

    @Override
    public boolean findGoogle(String idgoogle) {
        
//        boolean b= utenteFacade.findbygoogle(idgoogle);
//        
//        if(b){
//            return true;
//        }else return false;
        
        return utenteFacade.findbygoogle(idgoogle);
    }

    @Override
    public boolean removeUtente(String idsocial, String tipo) {
        
        utenteFacade.remove(utenteFacade.getObjUtente(idsocial,tipo));

        return true;
    }



    
    
    
}
