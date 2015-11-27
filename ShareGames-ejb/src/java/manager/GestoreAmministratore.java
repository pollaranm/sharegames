/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import javax.ejb.Stateless;

import ejb.Amministratore;
import ejb.Impianto;
import ejbFacade.AmministratoreFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Alex
 */
@Stateless
public class GestoreAmministratore implements GestoreAmministratoreLocal {

    @EJB
    private AmministratoreFacadeLocal amministratoreFacade;

    
    @Override
    public void addAmministratore(int idImpianto, String nome, String cognome) {
        
        Impianto i= new Impianto();
        i.setIdimpianto(idImpianto);
        
        Amministratore a = new Amministratore();
        a.setIdimpianto(i);
        a.setNome(nome);
        a.setCognome(cognome);
        
        amministratoreFacade.create(a);
        
    }

    @Override
    public Amministratore getObjAmministratore(int idAmministratore) {
        return amministratoreFacade.getObjAmministratore(idAmministratore);
    }

    @Override
    public boolean removeAmministratore(int idAmministratore) {
        
        Amministratore a= amministratoreFacade.getObjAmministratore(idAmministratore);
        
        if( a == null ) {
            return false;
        } else {
            amministratoreFacade.remove(a);
            return true;
        }
        
    }
    
    

   
    
}
