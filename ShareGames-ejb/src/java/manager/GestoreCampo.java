/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.Campo;
import ejb.CampoPK;
import ejb.Impianto;
import ejbFacade.CampoFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Alex
 */
@Stateless
public class GestoreCampo implements GestoreCampoLocal {
    @EJB
    private CampoFacadeLocal campoFacade;

    @Override
    public void addCampo(int idcampo, int idimpianto, String tipologia, int numerogiocatori) {
        
        Impianto i = new Impianto();
        i.setIdimpianto(idimpianto);
        Campo c = new Campo();
        CampoPK c_pk = new CampoPK();
        c_pk.setIdcampo(idcampo);
        c_pk.setIdimpianto(idimpianto);
        c.setCampoPK(c_pk);
        c.setImpianto(i);
        c.setNumerogiocatori(numerogiocatori);
        c.setTipologia(tipologia);
        
        campoFacade.create(c);
        
    }
    

    @Override
    public Campo getObjectCampoById(int idcampo, int idimpianto) {
        
        return null;

    }

    @Override
    public void removeCampo(int idcampo, int idimpianto) {
        
        campoFacade.remove(getObjectCampoById(idcampo, idimpianto));
        
        
    }

    @Override
    public void updateCampo(int idcampo, int idimpianto, String tipologia, int numerogiocatori) {
        
        //creare query
        
    }

    @Override
    public Campo getCampoByTipologia(String tipologia) {
        
        return null;
    }

    @Override
    public Campo getCampoByImpianto(int idimpianto) {
        
        return null;
        
    }

    
    
}
