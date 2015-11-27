/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejbFacade.ListaeventiutenteFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Alex
 */
@Stateless
public class GestoreListaEventi implements GestoreListaEventiLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    @EJB
    private ListaeventiutenteFacadeLocal listaeventiutenteFacade;
    
    

    @Override
    public List getEventoByUtente(int idutente) {
       return listaeventiutenteFacade.getEventoByUtente(idutente);
    }
    
    



    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List getEventoByPagato(int idutente) {
        return listaeventiutenteFacade.getEventoByPagato(idutente);
    }
    
    
}
