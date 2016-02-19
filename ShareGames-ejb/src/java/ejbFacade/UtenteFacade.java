package ejbFacade;

import ejb.Utente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class UtenteFacade extends AbstractFacade<Utente> implements UtenteFacadeLocal {

    @PersistenceContext(unitName = "ShareGames-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtenteFacade() {
        super(Utente.class);
    }


    /**
     * Indica se un utente è registrato mediante facebook al database
     * @param id è l'id dell'utente
     * @return true o false
    */
    @Override
    public boolean findbyface(String id) {

        Query q = em.createNamedQuery("Utente.findByIdfacebook");
        q.setParameter("idfacebook", id);
        if (q.getResultList().isEmpty()) {
            return false;
        }
        return true;

    }

    /**
     * Indica se un utente è registrato mediante google al database
     * @param id è l'id dell'utente
     * @return true o false
    */
    @Override
    public boolean findbygoogle(String id) {
        Query q = em.createNamedQuery("Utente.findByIdgoogle");
        q.setParameter("idgoogle", id);
        if (q.getResultList().isEmpty()) {
            return false;
        }
        return true;
        
    }

    /**
     * Restituisce un oggetto Utente cercando il social
     * @param idsocial è l'id del social
     * @param tipo è il tipo di social (Facebook, Google)
     * @return oggetto di tipo Utente
    */
    @Override
    public Utente getObjUtente(String idsocial, String tipo) {
        Query q;
        if (tipo.equals("facebook")) {
            q = em.createNamedQuery("Utente.findByIdfacebook");
            q.setParameter("idfacebook", idsocial);
        } else {
            q = em.createNamedQuery("Utente.findByIdgoogle");
            q.setParameter("idgoogle", idsocial);
        }
        return (Utente) q.getResultList().get(0);

    }

}
