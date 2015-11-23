/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.Squadra;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface GestoreSquadraLocal {

    Squadra getObjSquadra(Integer idSquadra);
    
}
