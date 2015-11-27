/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Evento;
import ejb.Listaeventiutente;
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
public class ListaeventiutenteFacade extends AbstractFacade<Listaeventiutente> implements ListaeventiutenteFacadeLocal {
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
    public List getEventoByUtente(int idutente) {
         Query q  = em.createNamedQuery("Listaeventiutente.findByIdutente");
        q.setParameter("idutente", idutente);
        return q.getResultList();
    }

    @Override
    public List getEventoByPagato(int idutente) {
        
        Query q  = em.createNamedQuery("Listaeventiutente.findByIdutente");
        q.setParameter("idutente", idutente);
                
        List<Evento> l ;
        List<Evento> res=null;
        
        l=(List<Evento>)q.getResultList();

        for(int i = 0;i<l.size();i++){
            if(l.get(i).getPagato()=="si")
                res.add(l.get(i));
        }
        
        return res;
       
    }
    
    
}
