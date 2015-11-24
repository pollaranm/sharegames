/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Campo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface CampoFacadeLocal {

    void create(Campo campo);

    void edit(Campo campo);

    void remove(Campo campo);

    Campo find(Object id);

    List<Campo> findAll();

    List<Campo> findRange(int[] range);

    int count();

    Campo getObjectCampo(int idcampo,int idimpianto);

    List<Campo> getCampoByTipologia(String tipologia);

    List<Campo> getCampoByImpianto(int idimpianto);
    
}
