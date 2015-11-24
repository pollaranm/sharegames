/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Campo;
import java.util.ArrayList;
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
    
    

    @Override
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

    @Override
    public List<Campo> getCampoByTipologia(String tipologia) {
        
        Query q  = em.createNamedQuery("Campo.findByTipologia");
        q.setParameter("tipologia", tipologia);
                
        return q.getResultList();
        
    }

    @Override
    public List<Campo> getCampoByImpianto(int idimpianto) {
        
        Query q  = em.createNamedQuery("Campo.findAll");
                
            List<Campo> l = new ArrayList();
            List<Campo> temp = new ArrayList();
            l=(List<Campo>)q.getResultList();

            for(int i = 0;i<l.size();i++){
                if(l.get(i).getImpianto().getIdimpianto() == idimpianto){
                    temp.add(l.get(i));
                }
            }
                
        return temp;
    }
    
    
}
