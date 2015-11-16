/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Utente;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alex
 */
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
     * Controlla la presenza di un Utente registrato con Facebook.
     * 
     * @param id Id Facebook da cercare
     * @return <i>true</i> se l'utente è presente, <i>false</i> altrimenti
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
     * Controlla la presenza di un Utente registrato con Google.
     * 
     * @param id Id Google da cercare
     * @return <i>true</i> se l'utente è presente, <i>false</i> altrimenti
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
     * Recupera l'Utente associato all'Id e al social network passati come parametro.
     * 
     * @param idsocial Id identificativo dell'utente
     * @param tipo Social network utilizzato, 'facebook' o 'google'
     * @return L'utente cercato se presente, <i>false</i> altrimenti
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
