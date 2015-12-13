/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Prezziario;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alex
 */
@Stateless
public class PrezziarioFacade extends AbstractFacade<Prezziario> implements PrezziarioFacadeLocal {
    @PersistenceContext(unitName = "ShareGames-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrezziarioFacade() {
        super(Prezziario.class);
    }
    
       public Prezziario getObjectPrezziario(int idCampo, int idImpianto) {
        Query q;
        
        q=em.createNamedQuery("Prezziario.findByIdcampoIdimpianto");
        q.setParameter("idcampo", idCampo);
        q.setParameter("idimpianto", idImpianto);
        
        if (q.getResultList().isEmpty()){
            return null;
        }
        
        else{
        
        return (Prezziario) q.getResultList().get(0);
        }
    }
       
    
      public void updateAll(int idCampo, int idImpianto, BigDecimal prezzo, int sconto) {
        Query q;
        
        q=em.createNamedQuery("Prezziario.updateAll");
        q.setParameter("idcampo", idCampo);
        q.setParameter("idimpianto", idImpianto);
        q.setParameter("prezzo", prezzo);
        q.setParameter("sconto", sconto);
        
        q.executeUpdate();
    }
       
    
    
}
