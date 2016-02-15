/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.Evento;
import ejb.Listaeventiutente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface GestoreListaEventiLocal {
    
    
    /**
     * 
     * @param idutente
     * @return 
     * Restituisce una lista di eventi relativi all'iutente indicato come parametro
     */
    List<Listaeventiutente> getEventoByUtente(int idutente);
    
    
    /**
     * 
     * @param idutente
     * @return 
     * Restituisce una lista di eventi pagati relativi all'utente con ID passato come parametro
     */
    List<Evento> getEventoByPagato(int idutente);

    
    /**
     * 
     * @return 
     * Restituisce l'intera tabella del database
     */
    List<Listaeventiutente> getListaEventiUtenti();
    
    void addEventoUtente(int idevento, int idutente);

    Listaeventiutente getObjByIdUtenteIdEvento(int idutente, int idevento);

    void removeEventoUtente(int idevento, int idutente);
    
}
