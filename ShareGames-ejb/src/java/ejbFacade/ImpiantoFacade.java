/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

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
public class ImpiantoFacade extends AbstractFacade<Impianto> implements ImpiantoFacadeLocal {
    @PersistenceContext(unitName = "ShareGames-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImpiantoFacade() {
        super(Impianto.class);
    }


    @Override
    public Impianto getObjUtente(int id) {
        
        Query q  = em.createNamedQuery("Impianto.findByIdimpianto");
              q.setParameter("idimpianto", id);

        return (Impianto) q.getResultList().get(0);

    }
    @Override
    public List<Impianto> getImpiantoByProvincia(String provincia) {
        
        Query q  = em.createNamedQuery("Impianto.findByProvincia");
              q.setParameter("provincia", provincia);

        return (List<Impianto>) q.getResultList();
    }
    @Override
    public List<Impianto> getImpiantoByCitta(String citta){
        
        Query q  = em.createNamedQuery("Impianto.findByCitta");
              q.setParameter("citta", citta);

        return (List<Impianto>) q.getResultList();
    }
    
}
    