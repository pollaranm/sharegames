/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import javax.ejb.Stateless;
import ejb.Campo;
import ejb.CampoPK;
import ejb.Impianto;
import ejb.Prezziario;
import ejb.PrezziarioPK;
import ejbFacade.PrezziarioFacadeLocal;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Alex
 */
@Stateless
public class GestorePrezziario implements GestorePrezziarioLocal {

    @EJB
    private PrezziarioFacadeLocal prezziarioFacade;

    @Override
    public void addPrezziario(int idCampo, int idImpianto, BigDecimal prezzo, int sconto) {
        
        Impianto i= new Impianto();
        i.setIdimpianto(idImpianto);
        
        CampoPK c_pk= new CampoPK();
        c_pk.setIdcampo(idCampo);
        c_pk.setIdimpianto(idImpianto);
        Campo c = new Campo(c_pk);
        
        PrezziarioPK p_pk = new PrezziarioPK();
        
        p_pk.setIdcampo(idCampo);
        p_pk.setIdimpianto(idImpianto);
        
        Prezziario p = new Prezziario();
        
        p.setPrezziarioPK(p_pk);
        p.setCampo(c);
        p.setImpianto(i);
        p.setPrezzo(prezzo);
        p.setSconto(sconto);
             
        prezziarioFacade.create(p);
        
    }

    @Override
    public Prezziario getObjPrezziario(int idPrezziario) {
        return prezziarioFacade.getObjectPrezziario(idPrezziario);
    }

    @Override
    public boolean removePrezziario(int idPrezziario) {
        
        Prezziario p= prezziarioFacade.getObjectPrezziario(idPrezziario);
        
        if( p == null ) {
            return false;
        } else {
            prezziarioFacade.remove(p);
            return true;
        }  
    }

    public void updatePrezziario(int idPrezziario, int idCampo, int idImpianto, BigDecimal prezzo, int sconto) {
    
       //creazione query personalizzata
    
    }
}
