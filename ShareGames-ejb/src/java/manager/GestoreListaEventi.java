package manager;

import ejb.Evento;
import ejb.Listaeventiutente;
import ejbFacade.ListaeventiutenteFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;


@Stateless
public class GestoreListaEventi implements GestoreListaEventiLocal {

    @EJB
    private ListaeventiutenteFacadeLocal listaeventiutenteFacade;
    
    
/**
     * Recupera la lista degli eventi ai quali l'utente partecipa
     * 
     * @param idutente idutente dell'evento
     * @return List Evento
    */
    @Override
    public List<Listaeventiutente> getEventoByUtente(int idutente) {
       return listaeventiutenteFacade.getEventoByUtente(idutente);
    }
    
    
    /**
     * Recupera la lista degli eventi pagati ai quali l'utente partecipa
     * 
     * @param idutente idutente dell'evento
     * @return List Evento
    */
    @Override
    public List<Evento> getEventoByPagato(int idutente) {
        return listaeventiutenteFacade.getEventoByPagato(idutente);
    }
    
    
    /**
     * Recupera tutti gli eventi
     * 
     * @return List Evento
    */
    @Override
    public List<Listaeventiutente> getListaEventiUtenti() {
        return listaeventiutenteFacade.getListaEventiUtenti();
    }
    
    
    /**
     * Aggiunta di un nuovo oggetto di tipo EventoUtente nella listaeventiutente nel database. 
     * 
     * @param idutente idutente creatore
     * @param idevento idevento partecipante o creato
     * @return void
    */
    @Override
    public void addEventoUtente(int idevento, int idutente){
        Listaeventiutente l=listaeventiutenteFacade.addEventoUtente(idevento, idutente);
        listaeventiutenteFacade.create(l);
    }

    
    /**
     * Recupera evento al quale al quale l'utente partecipa
     * 
     * @param idutente idutente dell'evento
     * @param idevento id evento dell'evento
     * @return Evento
    */
    @Override
    public Listaeventiutente getObjByIdUtenteIdEvento(int idutente, int idevento) {
        return listaeventiutenteFacade.getObjByIdUtenteIdEvento(idutente, idevento);
    }

    
    /**
     * Rimuove l'evento dell'utente passato da paramentro dal database
     * 
     * @param idevento id dell'evento
     * @param idutente id dell'utente
     * @return void
    */  
    @Override
    public void removeEventoUtente(int idevento, int idutente) {
        listaeventiutenteFacade.removeEventoUtente(idevento, idutente);
    }
    
    
    
    

}
