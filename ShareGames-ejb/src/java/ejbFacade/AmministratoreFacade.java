/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Amministratore;
import ejb.Campo;
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

    @Override
    public Amministratore getObjAmministratoreByImpianto(int idimpianto) {
        
        Query q=em.createNamedQuery("Amministratore.findAll");

                 
        List<Amministratore> a ;
        a=(List<Amministratore>)q.getResultList();

        for(int i = 0;i<a.size();i++){

            if(a.get(i).getIdimpianto().getIdimpianto() == idimpianto){

                return (Amministratore)a.get(i);
            }

        }
                
        return null;
        
        
    }
    
    
    
}
