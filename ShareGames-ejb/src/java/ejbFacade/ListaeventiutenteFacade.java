/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Evento;
import ejb.Listaeventiutente;
import ejb.ListaeventiutentePK;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import manager.GestoreEventoLocal;

/**
 *
 * @author Alex
 */



@Stateless
public class ListaeventiutenteFacade extends AbstractFacade<Listaeventiutente> implements ListaeventiutenteFacadeLocal {
    @EJB
    private GestoreEventoLocal gestoreEvento;
    @PersistenceContext(unitName = "ShareGames-ejbPU")
    private EntityManager em;
    
    
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListaeventiutenteFacade() {
        super(Listaeventiutente.class);
    }
    
    @Override
    public List<Listaeventiutente> getEventoByUtente(int idutente) {
         Query q  = em.createNamedQuery("Listaeventiutente.findByIdutente");
        q.setParameter("idutente", idutente);
        return q.getResultList();
    }

    @Override
    public List<Evento> getEventoByPagato(int idutente) {
        
       Query q;

        q = em.createNamedQuery("Listaeventiutente.findByIdutente");
        q.setParameter("idutente", idutente);
        
        List<Listaeventiutente> l = q.getResultList(); 
        List  idevento=new ArrayList();    
        List <Evento> eventi=new ArrayList();  
        
        int k=0;
        

        for(int i=0; i<l.size(); i++){
            idevento.add(i,l.get(i).getListaeventiutentePK().getIdevento());
        }
        
        for(int i=0; i<idevento.size(); i++){
            q = em.createNamedQuery("Evento.findByIdevento");
            q.setParameter("idevento", idevento.get(i));
            Evento e=(Evento)q.getResultList().get(0);
            
            if(e.getPagato().equals("si"))
                eventi.add(k++,e);
        }
        
        return eventi;
   
    }

    @Override
    public List<Listaeventiutente> getListaEventiUtenti() {
        Query q  = em.createNamedQuery("Listaeventiutente.findAll");
        return q.getResultList();
    }
    
    
     @Override
     public Listaeventiutente addEventoUtente(int idevento, int idutente){
         ListaeventiutentePK lista_pk=new ListaeventiutentePK();
         
         lista_pk.setIdevento(idevento);
         lista_pk.setIdutente(idutente);
         
         Listaeventiutente lista=new Listaeventiutente();
         lista.setListaeventiutentePK(lista_pk);
         
         Evento e=gestoreEvento.getEvento(idevento);
         
         lista.setEvento(e);
         lista.setPostopagato("no");
         lista.setProprietario("no");
         
         return lista;

     }
    
    
}
