/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Prezziario;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ivan
 */
@Local
public interface PrezziarioFacadeLocal {

    void create(Prezziario prezziario);

    void edit(Prezziario prezziario);

    void remove(Prezziario prezziario);

    Prezziario find(Object id);

    List<Prezziario> findAll();

    List<Prezziario> findRange(int[] range);

    int count();
    
    
    
    /**
     * Restituisce l'oggetto Prezziario associato all'ID passato come parametro.
     * @param idCampo
     * @param idImpianto
     * @return L'oggetto Prezziario associato all'idCampo e all'idImpianto
     */
    List<Prezziario> getObjectPrezziario(int idImpianto);
    
    
    
    /**
     * Metodo che invoca la query personalizzata per l'aggiornamento dei campi
     * "prezzo" e "sconto"
     * 
     * @param idCampo
     * @param idImpianto
     * @param prezzo
     * @param sconto
     */
    void updateAll(int idCampo, int idImpianto, BigDecimal prezzo, int sconto);
    
}
