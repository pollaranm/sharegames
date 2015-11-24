/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.Campo;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface GestoreCampoLocal {

    void addCampo(int idcampo, int idimpianto, String tipologia, int numerogiocatori);

    Campo getObjectCampoById(int idcampo, int idimpianto);

    void removeCampo(int idcampo, int idimpianto);

    void updateCampo(int idcampo, int idimpianto, String tipologia, int numerogiocatori);

    Campo getCampoByTipologia(String tipologia);

    Campo getCampoByImpianto(int idimpianto);
    
}
