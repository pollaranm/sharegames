/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Amministratore;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alex
 */
@Stateless
public class AmministratoreFacade extends AbstractFacade<Amministratore> implements AmministratoreFacadeLocal {
    @PersistenceContext(unitName = "ShareGames-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AmministratoreFacade() {
        super(Amministratore.class);
    }
    
    public Amministratore getObjAmministratore(int idAmministratore) {
        Query q;
        
        q=em.createNamedQuery("Amministratore.findByIdamministratore");
        q.setParameter("idamministratore", idAmministratore);
        
        return (Amministratore) q.getResultList().get(0);
    }
    
}
