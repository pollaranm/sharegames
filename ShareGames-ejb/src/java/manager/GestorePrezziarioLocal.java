/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.Prezziario;
import java.math.BigDecimal;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface GestorePrezziarioLocal {
    
    void addPrezziario(int idCampo, int idImpianto, BigDecimal prezzo, int sconto);
    
    Prezziario getObjPrezziario(int idPrezziario);

    boolean removePrezziario(int idPrezziario);

    void updatePrezziario(int idPrezziario, int idCampo, int idImpianto, BigDecimal prezzo, int sconto);
}
