package ejbFacade;

import ejb.Prezziario;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    
       /**
        * Restituisce una lista prezziario di un impianto
        * @param idImpianto è l'id dell'impianto
        * @return oggetto List<Prezziario>
       */
       public List<Prezziario> getObjectPrezziario(int idImpianto) {
        Query q;
        
        q=em.createNamedQuery("Prezziario.findByIdimpianto");
        q.setParameter("idimpianto", idImpianto);
        
        return q.getResultList();
    }

      /**
       * Aggiorna i campoi del prezziario
       * @param idCampo è l'id del campo
       * @param idImpianto è l'id dell'impianto
       * @param prezzo è il prezzo
       * @param sconto è lo sconto da applicare
       * @return void
      */
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