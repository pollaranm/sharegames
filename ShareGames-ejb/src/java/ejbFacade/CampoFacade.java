package ejbFacade;

import ejb.Campo;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class CampoFacade extends AbstractFacade<Campo> implements CampoFacadeLocal {
    @PersistenceContext(unitName = "ShareGames-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CampoFacade() {
        super(Campo.class);
    }
    
    /**
     * Restituisce un oggetto Campo, passando come parametri idcampo 
     * e idimpianto.
     * @param idcampo id del campo
     * @param idimpianto id dell'impianto
     * @return oggetto di tipo Campo
    */
    public Campo getObjectCampo(int idcampo,int idimpianto) {
        
        Query q  = em.createNamedQuery("Campo.findByIdcampo");
        q.setParameter("idcampo", idcampo);
                
        List<Campo> l ;
        l=(List<Campo>)q.getResultList();

        for(int i = 0;i<l.size();i++){

            if(l.get(i).getImpianto().getIdimpianto() == idimpianto){

                return (Campo)l.get(i);
            }

        }
                
        return null;
    }

    /**
     * Restituisce un oggetto List<Campo> usando il parametro tipologia
     * @param tipologia tipologia del campo
     * @return oggetto List<Campo>
    */
    @Override
    public List<Campo> getCampoByTipologia(String tipologia) {
        
        Query q  = em.createNamedQuery("Campo.findByTipologia");
        q.setParameter("tipologia", tipologia);
                
        return q.getResultList();
        
    }

    /**
     * Restituisce un oggetto List<Campo> usando il parametro idimpianto
     * @param idimpianto id dell'impianto
     * @return oggetto List<Campo>
    */
    @Override
    public List<Campo> getCampoByImpianto(int idimpianto) {
        
        Query q  = em.createNamedQuery("Campo.findByIdimpianto");
                q.setParameter("idimpianto", idimpianto);
                
        return q.getResultList();
    }
    
    
}
