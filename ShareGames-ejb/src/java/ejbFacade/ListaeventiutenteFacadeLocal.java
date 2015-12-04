/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Evento;
import ejb.Listaeventiutente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface ListaeventiutenteFacadeLocal {

   void create(Listaeventiutente listaeventiutente);

    void edit(Listaeventiutente listaeventiutente);

    void remove(Listaeventiutente listaeventiutente);

    Listaeventiutente find(Object id);

    List<Listaeventiutente> findAll();

    List<Listaeventiutente> findRange(int[] range);

    int count();

    /**
     * 
     * @param idutente
     * @return 
     * Restituisce una lista con tutti gli eventi relativi all'utente 
     * idetificato dall'ID passato come parametro
     */
    List<Evento> getEventoByUtente(int idutente);
    
    /**
     * 
     * @param idutente
     * @return 
     * Restituisce la lista di tutti gli eventi in cui il campo "pagato"
     * ha come valore "si" relativo all'utente identificato sempre da ID 
     * passato come parametro
     */

    List<Evento> getEventoByPagato(int idutente);
    
    /**
     * 
     * @return 
     * Restituisce l'intera tabella di tutti gli eventi
     */
    List<Evento> getListaEventiUtenti();
}
