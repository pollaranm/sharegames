/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Impianto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface ImpiantoFacadeLocal {

    void create(Impianto impianto);

    void edit(Impianto impianto);

    void remove(Impianto impianto);

    Impianto find(Object id);

    List<Impianto> findAll();

    List<Impianto> findRange(int[] range);

    int count();

    Impianto getObjUtente(int id);

    List<Impianto> getImpiantoByProvincia(String provincia);

    List<Impianto> getImpiantoByCitta(String citta);
    
}
