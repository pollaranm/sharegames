/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Alex
 */
@Stateless
public class GestoreEvento implements GestoreEventoLocal {
    @EJB
    private ListaeventiutenteFacadeLocal listaeventiutenteFacade;
    @EJB
    private CampoFacadeLocal campoFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
     @EJB
    private EventoFacadeLocal eventoFacade;
     
     
    
    @Override
    public Evento getEvento(int idevento) {
        
        return eventoFacade.getObjEvento(idevento);
    }

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
                            //ADESSO AGGIUNGO UN OGGETTO IN LISTAEVENTIUTENTE
       
        eventoFacade.create(e);
        
        Evento tmp=eventoFacade.getObjEventoByPK(idimpianto, idcampo, ora, data); 
                
            
        Listaeventiutente l=new Listaeventiutente();
        ListaeventiutentePK l_pk=new ListaeventiutentePK();
        
        
        l_pk.setIdevento(tmp.getEventoPK().getIdevento());
        l_pk.setIdutente(idutente);
      
        
        l.setListaeventiutentePK(l_pk);
        l.setEvento(tmp);
        l.setUtente(u);
        l.setPostopagato("no");
        l.setProprietario("si");
        
        listaeventiutenteFacade.create(l);
     
    }

    @Override
    public void removeEvento(int idevento) {
        
         Evento e=eventoFacade.getObjEvento(idevento);
         eventoFacade.remove(e);
       
    }
    
    
    @Override
    public void updateEvento(int idimpianto, int idcampo, int idevento, 
            String data, String ora,String pagato,String sport,int giocatoripagato) {

        eventoFacade.update(idimpianto, idcampo, data, ora, idevento,pagato,giocatoripagato,sport );       
    }


    @Override
    public int getGiocatoriPagato(int idevento) {
        
       return eventoFacade.getGiocatoriPagato(idevento);
        
    }


    @Override
    public List<Evento> getEventoByData(String data) {
        return eventoFacade.getEventoByData(data);
        
    }

    @Override
    public List<Evento> getEventoByOra(String ora) {
        return eventoFacade.getEventoByOra(ora);
    }

    @Override
    public List<Evento> getEventoByCitta(String citta) {
        return eventoFacade.getEventoByCitta(citta);
    }

    @Override
    public List<Evento> getEventoBySport(String sport) {
        return eventoFacade.getEventoBySport(sport);
    }

    @Override
    public List<Evento> getEventoCompletoByCitta(String citta) {
        return eventoFacade.getEventoCompletoByCitta(citta);
    }

    @Override
    public List<Evento> getEventoCompletoByProvincia(String provincia) {
        return eventoFacade.getEventoCompletoByProvincia(provincia);
    }
}
