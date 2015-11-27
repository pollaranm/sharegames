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
import ejb.Utente;
import ejbFacade.CampoFacadeLocal;
import ejbFacade.EventoFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Alex
 */
@Stateless
public class GestoreEvento implements GestoreEventoLocal {
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
        
           
        eventoFacade.create(e);
        
    }

    @Override
    public void removeEvento(int idevento) {
        
         Evento e=eventoFacade.getObjEvento(idevento);
         eventoFacade.remove(e);
       
    }

    
    /*ATTENZIONE!!! IN QUESTO METODO ORA E DATA SONO CHIAVI PRIMARIE 
    PERTANTO NON SI PUO' FARE UN UPDATE!
    */
    
    
    @Override
    public void updateEvento(int idimpianto, int idcampo, int idevento, 
            String data, String ora,String pagato,String sport,int giocatoripagato) {
                
        eventoFacade.update(idimpianto, idcampo, data, ora, idevento,pagato,giocatoripagato,sport );       
    }

    /*@Override
    public Evento getEventoBy(String data, String ora, String citta , String sport) {
        return eventoFacade.getEventoBy(data, ora, citta , sport);
    }*/

    @Override
    public int getGiocatoriPagato(int idevento) {
        
       return eventoFacade.getGiocatoriPagato(idevento);
        
    }

    /*@Override
    public void getEventoCompletoBy(String citta , String provincia) {
    }*/
    
    
    
    
}
