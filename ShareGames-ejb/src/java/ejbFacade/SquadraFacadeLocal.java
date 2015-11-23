/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Squadra;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface SquadraFacadeLocal {

    void create(Squadra squadra);

    void edit(Squadra squadra);

    void remove(Squadra squadra);

    Squadra find(Object id);

    List<Squadra> findAll();

    List<Squadra> findRange(int[] range);

    int count();

    /**
     * Restituisce l'oggetto Squadra associato all'ID passato come parametro.
     * 
     * @param idSquadra Id identificativo della squadra
     * @return L'oggetto Squadra associato a quell'ID
     */
    Squadra getObjSquadra(Integer idSquadra);
    
}
