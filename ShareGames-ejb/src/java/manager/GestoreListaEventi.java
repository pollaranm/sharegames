/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.Evento;
import ejb.Listaeventiutente;
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
    public List<Listaeventiutente> getEventoByUtente(int idutente) {
       return listaeventiutenteFacade.getEventoByUtente(idutente);
    }
    
    @Override
    public List<Evento> getEventoByPagato(int idutente) {
        return listaeventiutenteFacade.getEventoByPagato(idutente);
    }

    @Override
    public List<Listaeventiutente> getListaEventiUtenti() {
        return listaeventiutenteFacade.getListaEventiUtenti();
    }
    
    @Override
    public void addEventoUtente(int idevento, int idutente){
        Listaeventiutente l=listaeventiutenteFacade.addEventoUtente(idevento, idutente);
        listaeventiutenteFacade.create(l);
    }

    @Override
    public Listaeventiutente getObjByIdUtenteIdEvento(int idutente, int idevento) {
        return listaeventiutenteFacade.getObjByIdUtenteIdEvento(idutente, idevento);
    }

    @Override
    public void removeEventoUtente(int idevento, int idutente) {
        listaeventiutenteFacade.removeEventoUtente(idevento, idutente);
    }
    
    
    
    

}
