package ejbFacade;

import ejb.Squadra;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SquadraFacade extends AbstractFacade<Squadra> implements SquadraFacadeLocal {
    @PersistenceContext(unitName = "ShareGames-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SquadraFacade() {
        super(Squadra.class);
    }

    
    /**
     * Restituisce un oggetto Squadra in base all'idsquadra
     * @param idSquadra è l'id della squadra
     * @return oggetto di tipo Squadra
    */
    @Override
    public Squadra getObjSquadra(Integer idSquadra) {
        Query q = em.createNamedQuery("Squadra.findByIdsquadra");
        q.setParameter("idsquadra", idSquadra);
        return (Squadra) q.getResultList().get(0);
    }
    
    /**
     * Restituisce un oggetto Squadra in base al nome 
     * @param nomeSquadra è il nome della squadra
     * @return un oggetto di tipo Squadra
    */
    @Override
    public Squadra getObjSquadraByName(String nomeSquadra) {
        Query q = em.createNamedQuery("Squadra.findByNomesquadra");
        q.setParameter("nomesquadra", nomeSquadra);
        return (Squadra) q.getResultList().get(0);
    }

    /**
     * Indica se una squadra esiste oppure no 
     * @param name
     * @return true o false
    */
    @Override
    public Boolean checkNomeSquadra(String name) {
        Query q = em.createNamedQuery("Squadra.findByNomesquadra");
        q.setParameter("nomesquadra", name);
        return q.getResultList().isEmpty();
    }

    /**
     * Restituisce una collezione di squadra in base alla città
     * @param city è la città 
     * @return Collection<Squadra>
    */
    @Override
    public Collection<Squadra> getSquadraByCitta(String city) {
        Query q = em.createNamedQuery("Squadra.findByCitta");
        q.setParameter("citta", city);
        return q.getResultList();
    }

    /**
     * Restituisce una collezione di squadra in base alla tipologia 
     * @param tipologia è la tipologia della squadra
     * @return oggetto Collection<Squadra>
    */
    @Override
    public Collection<Squadra> getSquadraByTipologia(String tipologia) {
        Query q = em.createNamedQuery("Squadra.findByTipologia");
        q.setParameter("tipologia", tipologia);
        return q.getResultList();
    }

    /**
     * Restituisce un oggetto collezione squadra in base all tipologia e città
     * @param citta è la città
     * @param tipologia è la tipologia di sport
     * @return oggetto Collection<Squadra>
    */
    @Override
    public Collection<Squadra> getSquadraByCittaTipologia(String citta, String tipologia) {
        Query q = em.createNamedQuery("Squadra.findByCittaTipologia");
        q.setParameter("citta", citta);
        q.setParameter("tipologia", tipologia);
        return q.getResultList();
    }

}
