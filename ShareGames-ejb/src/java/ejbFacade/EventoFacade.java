/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Evento;
import ejb.EventoPK;
import ejb.Impianto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alex
 */
@Stateless
public class EventoFacade extends AbstractFacade<Evento> implements EventoFacadeLocal {

    @PersistenceContext(unitName = "ShareGames-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventoFacade() {
        super(Evento.class);
    }

    @Override
    public Evento getObjEvento(int idevento) {

        Query q;

        q = em.createNamedQuery("Evento.findByIdevento");
        q.setParameter("idevento", idevento);

        return (Evento) q.getResultList().get(0);

    }

    /*@Override
     public Evento getEventoBy(String data, String ora, String cittÃ , String sport) {
        
     Query q;
        
     q = em.createNamedQuery("Evento.findByData");
     q.setParameter("data", data);
       
     List<Evento> l=q.getResultList();
            
            
     for(int i=0; i<l.size(); i++){
                
     EventoPK e_pk=l.get(i).getEventoPK();
     Impianto imp=l.get(i).getImpianto();
                
     if(e_pk.getOra().equals(ora) &&
     imp.getCitta().equals(citta ) &&
     l.get(i).getSport().equals(sport)
                        
     )
     return l.get(i);

     }
            
            
     return null;
     }*/
    @Override
    public int getGiocatoriPagato(int idevento) {

        Query q;

        q = em.createNamedQuery("Evento.findByIdevento");
        q.setParameter("idevento", idevento);

        Evento e = (Evento) q.getResultList().get(0);
        return e.getGiocatoripagato();

    }

    /*@Override
     public Evento getEventoCompletoBy(String citta , String provincia) {
     Query q;
        
     q = em.createNamedQuery("Evento.findByCompleto");
     q.setParameter("completo", "si");
        
     List<Evento> l=q.getResultList();
        
     for(int i=0; i<l.size(); i++){
     if(l.get(i).getImpianto().getCitta().equals(citta ) &&
     l.get(i).getImpianto().getProvincia().equals(provincia))
     return l.get(i);
     }
        
     return null;
     }*/
    @Override
    public void update(int idimp, int idcam, String data, String ora, int idevento,String pagato,int giocatoripagato, String sport) {
        Query q;
        q = em.createNamedQuery("Evento.updateAll");
        q.setParameter("idimpianto", idimp);
        q.setParameter("idcampo", idcam);
        q.setParameter("data", data);
        q.setParameter("ora", ora);
        q.setParameter("idevento", idevento);
        q.setParameter("completo", data);
        q.setParameter("giocatoripagato",giocatoripagato);
        q.setParameter("sport", sport);
        q.setParameter("pagato", pagato);
        q.executeUpdate();

    }

}
