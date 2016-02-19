package ejbFacade;

import ejb.Impianto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


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
    
    
    /**
     * Restituisce un oggetto Impianto passando id impianto
     * @param id è l'id dell'impianto
     * @return <i>Impianto</i>
    */
    @Override
    public Impianto getObjImpianto(int id) {
        
        Query q  = em.createNamedQuery("Impianto.findByIdimpianto");
              q.setParameter("idimpianto", id);

        return (Impianto) q.getResultList().get(0);

    }
    
    /**
     * Restituisce una lista List<Impianto> degli impianti di una provincia
     * @param provincia è il nome della provincia
     * @return <i>List</i>
    */
    @Override
    public List<Impianto> getImpiantoByProvincia(String provincia) {
        
        Query q  = em.createNamedQuery("Impianto.findByProvincia");
              q.setParameter("provincia", provincia);

        return (List<Impianto>) q.getResultList();
    }
    
    
    /**
     * Restituisce una lista List<Impianto> degli impianti di una città 
     * @param citta è la città
     * @return <i>List</i>
    */
    @Override
    public List<Impianto> getImpiantoByCitta(String citta){
        
        Query q  = em.createNamedQuery("Impianto.findByCitta");
              q.setParameter("citta", citta);

        return (List<Impianto>) q.getResultList();
    }

    
    
    /**
     * Restituisce un oggetto Impianto
     * @param nome nome dell'impianto
     * @param partitaiva è il numero P.IVA
     * @param telefono è il numero telefonico dell'impianto
     * @return <i>Impianto</i>
    */
    @Override
    public Impianto getImpiantoByNomePartitaivaTelefono(String nome, String partitaiva, String telefono) {
    
        Query q = em.createNamedQuery("Impianto.getImpiantoByNomePartitaivaTelefono");
        q.setParameter("nome", nome);
        q.setParameter("telefono", telefono);
        q.setParameter("partitaiva", partitaiva);
        
        Impianto i = null;
        
        try {
           i = (Impianto) q.getResultList().get(0);

        } catch (Exception e) {
            return i;
        }
        return i;
    }


}
