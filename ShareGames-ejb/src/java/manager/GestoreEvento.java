package manager;

import ejb.Campo;
import ejb.CampoPK;
import ejb.Evento;
import ejb.EventoPK;
import ejb.Impianto;
import ejb.Listaeventiutente;
import ejb.ListaeventiutentePK;
import ejb.Utente;
import ejbFacade.CampoFacadeLocal;
import ejbFacade.EventoFacadeLocal;
import ejbFacade.ListaeventiutenteFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;


@Stateless
public class GestoreEvento implements GestoreEventoLocal {
    @EJB
    private ListaeventiutenteFacadeLocal listaeventiutenteFacade;
    @EJB
    private CampoFacadeLocal campoFacade;

     @EJB
    private EventoFacadeLocal eventoFacade;
     
     
    /**
     * Recupera l'oggetto Evento associato all'idEvento passato come parametro.
     *
     * @param idEvento idEvento identificativo
     * @return <i>Evento</i>,<i>null</i> altrimenti 
    */
    @Override
    public Evento getEvento(int idevento) {
        
        return eventoFacade.getObjEvento(idevento);
    }
    
    
    /**
     * Aggiunta di un nuovo oggetto di tipo Evento nel database. 
     * Aggiunta di un nuovo oggetto di tipo ListaEventiUtente associato all'evento e all'idutente nel database.
     * 
     * @param idcampo id del campo interessato
     * @param idimpianto id dell' impianto
     * @param tipologia tipologia del campo (calcio5,calcio7,calcio11,pallavolo,basket, tennis)   
     * @param numerogiocatori numero dei giocatori
     * @param data data dell'evento
     * @param ora ora dell'evento
     * @param sport (calcio5,calcio7,calcio11,pallavolo,basket, tennis)
     * @param pagato pagato(si/no)
     * @param completo completo(si/no)
     * @param giocatoripagato numero giocatori che hanno pagato
     * @param idutente id dell'utente creatore dell'evento
     * @return <i>void</i>
    */
    
    @Override
    public void addEvento(int idimpianto,
            int idcampo, 
            String data, String ora, String sport, String pagato,
            String completo, int giocatoripagato, int idutente) {
        
        
        Impianto i=new Impianto();
        i.setIdimpianto(idimpianto);
        
        CampoPK c_pk=new CampoPK();
        c_pk.setIdcampo(idcampo);
        c_pk.setIdimpianto(idimpianto);
        
        Campo c=new Campo();
        c.setCampoPK(c_pk);
        c.setImpianto(i);
        
        Utente u=new Utente();
        u.setIdutente(idutente);
        
        EventoPK e_pk=new EventoPK();
        
        e_pk.setData(data);
        e_pk.setIdcampo(idcampo);
        e_pk.setIdimpianto(idimpianto);
        e_pk.setOra(ora);
        
        Evento e=new Evento();
        
        e.setCampo(c);
        e.setCompleto(completo);
        e.setEventoPK(e_pk);
        e.setGiocatoripagato(giocatoripagato);
        e.setIdutente(u);
        e.setImpianto(i);
        e.setPagato(pagato);
        e.setSport(sport);
       
        eventoFacade.create(e);
        
        Evento tmp=eventoFacade.getObjEventoByPK(idimpianto, idcampo, ora, data); 
                
        Listaeventiutente l=new Listaeventiutente();
        ListaeventiutentePK l_pk=new ListaeventiutentePK();
        
        l_pk.setIdevento(tmp.getEventoPK().getIdevento());
        l_pk.setIdutente(idutente);
      
        l.setListaeventiutentePK(l_pk);
        l.setEvento(tmp);
        l.setUtente(u);
        l.setPostopagato("si");
        l.setProprietario("si");
        
        listaeventiutenteFacade.create(l);
     
    }

    
    /**
     * Rimuove l'evento tramite idevento da paramentro.
     * 
     * @param idevento id dell'evento
     * @return <i>void</i>
    */
    @Override
    public void removeEvento(int idevento) {
        
         Evento e=eventoFacade.getObjEvento(idevento);
         eventoFacade.remove(e);
         
    }
    
    
    /**
     * Aggiornamento dei parametri del oggetto di tipo Evento.
     * 
     * @param idcampo id del campo interessato
     * @param idimpianto id dell' impianto
     * @param data data dell'evento
     * @param ora ora dell'evento
     * @param sport (calcio a 5,calcio a 7,calcio a 11,pallavolo ,basket , tennis)
     * @param pagato pagato(si/no)
     * @param giocatoripagato numero giocatori che hanno pagato
     * @return <i>void</i>
    */
    @Override
    public void updateEvento(int idimpianto, int idcampo, int idevento, 
            String data, String ora,String pagato,String sport,int giocatoripagato) {

        eventoFacade.update(idimpianto, idcampo, data, ora, idevento,pagato,giocatoripagato,sport );       
    }

    
    /**
     * Recupera numero giocatori che hanno pagato all'evento passato da parametro.
     * 
     * @param idevento id dell'evento
     * @return <i>int giocatori</i>
    */
    @Override
    public int getGiocatoriPagato(int idevento) {
        
       return eventoFacade.getGiocatoriPagato(idevento);
        
    }

    
    /**
     * Recupera la lista degli eventi nella data passata da parametro.
     * 
     * @param data data dell'evento
     * @return <i>List Evento</i>
    */
    @Override
    public List<Evento> getEventoByData(String data) {
        return eventoFacade.getEventoByData(data);
        
    }
    
    
    /**
     * Recupera la lista degli eventi organizzati per l'ora passata da parametro.
     * 
     * @param ora ora dell'evento
     * @return <i>List Evento</i>
    */
    @Override
    public List<Evento> getEventoByOra(String ora) {
        return eventoFacade.getEventoByOra(ora);
    }
    
    
    /**
     * Recupera la lista di tutti gli eventi nella citta passata da parametro.
     * 
     * @param citta città dell'evento
     * @return <i>List Evento</i>
    */
    @Override
    public List<Evento> getEventoByCitta(String citta) {
        return eventoFacade.getEventoByCitta(citta);
    }
    
    
    /**
     * Recupera la lista degli eventi riferiti allo sport passato come parametro.
     * 
     * @param sport sport dell'evento
     * @return <i>List Evento</i>
    */
    @Override
    public List<Evento> getEventoBySport(String sport) {
        return eventoFacade.getEventoBySport(sport);
    }
    
    
    /**
     * Recupera la lista degli eventi "completi" nella città passata da parametro.
     * 
     * @param citta città dell'evento
     * @return <i>List Evento</i>
    */
    @Override
    public List<Evento> getEventoCompletoByCitta(String citta) {
        return eventoFacade.getEventoCompletoByCitta(citta);
    }
    
    
    /**
     * Recupera la lista degli eventi "completi" nella provincia passata da parametro.
     * 
     * @param provincia provincia dell'evento
     * @return <i>List Evento</i>
    */
    @Override
    public List<Evento> getEventoCompletoByProvincia(String provincia) {
        return eventoFacade.getEventoCompletoByProvincia(provincia);
    }
    
    
   /**
     * Aggiorna l'evento, modificandolo attraverso i parametri dell'oggetto Evento event.
     *
     * @param event oggetto Evento dal quale prendiamo i valori per la modifica
     * @return <i>void</i>
     */
    @Override
    public void updateEvento(Evento event) {
        eventoFacade.edit(event);
    }
    

    
}
