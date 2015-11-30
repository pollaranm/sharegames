/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Evento;
import ejb.EventoPK;
import ejb.Impianto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
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
    @EJB
    private ImpiantoFacadeLocal impiantoFacade;

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
    
    
    
    @Override
    public int getGiocatoriPagato(int idevento) {

        Query q;

        q = em.createNamedQuery("Evento.findByIdevento");
        q.setParameter("idevento", idevento);

        Evento e = (Evento) q.getResultList().get(0);
        return e.getGiocatoripagato();

    }

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

    @Override
    public List<Evento> getEventoByData(String data) {
         Query q;

        q = em.createNamedQuery("Evento.findByData");
        q.setParameter("data", data);
        return q.getResultList();
    }

    @Override
    public List<Evento> getEventoByOra(String ora) {
        Query q;

        q = em.createNamedQuery("Evento.findByOra");
        q.setParameter("ora", ora);
        return q.getResultList();
    }

    @Override
    public List<Evento> getEventoByCitta(String citta) {
        Query q;

        q = em.createNamedQuery("Impianto.findByCitta");
        q.setParameter("citta", citta);
        
        List<Impianto> l = q.getResultList(); //Lista impianti in quella citta
        List  idimpianto=new ArrayList();     //Lista idimpianti
        List <List<Evento>> eventi=new ArrayList();   //lista che contiene liste di eventi di uno specifico impianto
        List <Evento> finale=new ArrayList(); //lista finale di tutti gli eventi per i vari impianti di quella citta
        
        
        
        for(int i=0; i<l.size(); i++){
             idimpianto.add(i,(int)l.get(i).getIdimpianto());  //aggiungo i vari impianti
        }
        
        int k=0;
        
        for(int i=0; i<idimpianto.size(); i++){  //Per ogni impianto aggiungo in eventi la lista degli eventi di quell'impianto
            
            int index=(int)idimpianto.get(i);    //memorizzo idimpianto
            
            q = em.createNamedQuery("Evento.findByIdimpianto");
            q.setParameter("idimpianto", index);
            
            if(!(q.getResultList().size()==0)) //se non è vuoita la lista di eventi relativi ad un impianto con specifico id
            eventi.add(k++,q.getResultList()); //aggiungo in posizione k che incremento la lista di eventi (se metto in posizione i potrei avere buchi
        }
        
         k=0;
        
        for(int i=0; i<eventi.size(); i++){ //scorro la lista di liste eventi
            
            for(int j=0; j<eventi.get(i).size(); j++){ // se j<della lunghezza della lista in posizione i
                finale.add(k++, (Evento)eventi.get(i).get(j)); /*aggiungo sempre su indice k l'evento memorizzato nella lista Eventi in posizione j
                                                                 dentro la lista di Liste<Eventi> in posizione i*/
            }
        }
        
        return finale;
    }
    
    
    
    

    @Override
    public List<Evento> getEventoBySport(String sport) {
        
        Query q;

        q = em.createNamedQuery("Evento.findBySport");
        q.setParameter("sport", sport);
        
        return q.getResultList();
        
        
    }

    @Override
    public List<Evento> getEventoCompletoByCitta(String citta) { //VEDI COMMENTI getEventoByCitta
        
                Query q;

        q = em.createNamedQuery("Impianto.findByCitta");
        q.setParameter("citta", citta);
        
        List<Impianto> l = q.getResultList(); 
        List  idimpianto=new ArrayList();     
        List <List<Evento>> eventi=new ArrayList();  
        List <Evento> finale=new ArrayList(); 
        
        
        
        for(int i=0; i<l.size(); i++){
             idimpianto.add(i,(int)l.get(i).getIdimpianto());  //aggiungo i vari impianti
        }
        
        int k=0;
        
        for(int i=0; i<idimpianto.size(); i++){  //Per ogni impianto aggiungo in eventi la lista degli eventi di quell'impianto
            
            int index=(int)idimpianto.get(i);    
            
            q = em.createNamedQuery("Evento.findByIdimpianto");
            q.setParameter("idimpianto", index);
            
            if(!(q.getResultList().size()==0))
            eventi.add(k++,q.getResultList());
        }
        
         k=0;
        
        for(int i=0; i<eventi.size(); i++){
            
            for(int j=0; j<eventi.get(i).size(); j++){
                
                if(eventi.get(i).get(j).getCompleto().equals("si"))
                finale.add(k++, (Evento)eventi.get(i).get(j));
            }
        }
        
        return finale;
    }

    @Override
    public List<Evento> getEventoCompletoByProvincia(String provincia) { //VEDI COMMENTI getEventoByCitta
        Query q;

        q = em.createNamedQuery("Impianto.findByProvincia");  //CAMBIA SOLO LA QUERY PER CERCARE SU PROVINCIA
        q.setParameter("provincia", provincia);
        
        List<Impianto> l = q.getResultList(); //Lista impianti in quella citta
        List  idimpianto=new ArrayList();     //Lista idimpianti
        List <List<Evento>> eventi=new ArrayList();   //lista che contiene liste di eventi di uno specifico impianto
        List <Evento> finale=new ArrayList(); //lista finale di tutti gli eventi per i vari impianti di quella citta
        
        
        
        for(int i=0; i<l.size(); i++){
             idimpianto.add(i,(int)l.get(i).getIdimpianto());  //aggiungo i vari impianti
        }
        
        int k=0;
        
        for(int i=0; i<idimpianto.size(); i++){  //Per ogni impianto aggiungo in eventi la lista degli eventi di quell'impianto
            
            int index=(int)idimpianto.get(i);    
            
            q = em.createNamedQuery("Evento.findByIdimpianto");
            q.setParameter("idimpianto", index);
            
            if(!(q.getResultList().size()==0))
            eventi.add(k++,q.getResultList());
        }
        
         k=0;
        
        for(int i=0; i<eventi.size(); i++){
            
            for(int j=0; j<eventi.get(i).size(); j++){
                
                if(eventi.get(i).get(j).getCompleto().equals("si"))
                finale.add(k++, (Evento)eventi.get(i).get(j));
            }
        }
        
        return finale;
        
    }

    @Override
    public Evento getObjEventoByPK(int idimpianto, int idcampo, String ora, String data) {
        
      Query q = em.createNamedQuery("Evento.findByPK");
            q.setParameter("idimpianto", idimpianto);
            q.setParameter("idcampo", idcampo);
            q.setParameter("ora", ora);
            q.setParameter("data", data);
            
            return (Evento)q.getResultList().get(0);
        
    }
    
    
    
}

