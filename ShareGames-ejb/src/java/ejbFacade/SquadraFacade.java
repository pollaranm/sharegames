/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Squadra;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alex
 */
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

    @Override
    public Squadra getObjSquadra(Integer idSquadra) {
        Query q = em.createNamedQuery("Squadra.findByIdsquadra");
        q.setParameter("idsquadra", idSquadra);
        return (Squadra) q.getResultList().get(0);
    }

    @Override
    public Boolean checkNomeSquadra(String name) {
        Query q = em.createNamedQuery("Squadra.findByNomesquadra");
        q.setParameter("nomesquadra", name);
        return q.getResultList().isEmpty();
    }

    @Override
    public Collection<Squadra> getSquadraByCitta(String city) {
        Query q = em.createNamedQuery("Squadra.findByCitta");
        q.setParameter("citta", city);
        return q.getResultList();
    }

    @Override
    public Collection<Squadra> getSquadraByTipologia(String tipologia) {
        Query q = em.createNamedQuery("Squadra.findByTipologia");
        q.setParameter("tipologia", tipologia);
        return q.getResultList();
    }

    @Override
    public Collection<Squadra> getSquadraByCittaTipologia(String citta, String tipologia) {
        Query q = em.createNamedQuery("Squadra.findByCittaTipologia");
        q.setParameter("citta", citta);
        q.setParameter("tipologia", tipologia);
        return q.getResultList();
    }
    
    
    
    
    
    
    
    
}
