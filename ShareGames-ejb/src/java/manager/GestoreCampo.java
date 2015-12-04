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
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

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
        
        return campoFacade.getObjectCampo(idcampo, idimpianto);

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
    public List<Campo> getCampoByTipologia(String tipologia) {
        
        return campoFacade.getCampoByTipologia(tipologia);
    }

    @Override
    public List<Campo> getCampoByImpianto(int idimpianto) {
        
        return (List<Campo>)campoFacade.getCampoByImpianto(idimpianto);
        
    }

    
    
}
