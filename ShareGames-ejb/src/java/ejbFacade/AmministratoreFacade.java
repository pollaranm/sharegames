package ejbFacade;

import ejb.Amministratore;
import ejb.Campo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


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
       
    /**
     * Restituisce un oggetto Amministratore passando come parametro idAmministratore
     * @param idAmministratore è l'id dell'amministratore
     * @return <i>Amministratore</i>
    */
    public Amministratore getObjAmministratore(int idAmministratore) {
        Query q;
        
        q=em.createNamedQuery("Amministratore.findByIdamministratore");
        q.setParameter("idamministratore", idAmministratore);
        
        Amministratore a = null;
        
        try {
           a = (Amministratore) q.getResultList().get(0);

        } catch (Exception e) {
            return a;
        }
        return a;
    }

    /**
     * Restituisce un nuovo oggetto di tipo Amministratore passando come 
     * parametro idimpianto
     * @param idimpianto è l'id dell'impianto
     * @return <i>Amministratore</i>
    */
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
