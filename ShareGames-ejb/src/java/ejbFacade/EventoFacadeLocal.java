/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Evento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface EventoFacadeLocal {

   
    void create(Evento evento);

    void edit(Evento evento);

    void remove(Evento evento);

    Evento find(Object id);

    List<Evento> findAll();

    List<Evento> findRange(int[] range);

    int count();
    
    
    /**
     * 
     * @param idevento
     * @return 
     * restituisce un oggetto Evento specificando il relativo ID
     */
    Evento getObjEvento(int idevento);
    
    /**
     * 
     * @param idevento
     * @return 
     * restituisce il numero di giocatori che hanno pagato per l'evento specificato
     * dall'id passato come parametro
     */

    int getGiocatoriPagato(int idevento);

    /**
     * 
     * @param idimp
     * @param idcam
     * @param data
     * @param ora
     * @param idevento
     * @param pagato
     * @param giocatoripagato
     * @param sport 
     * 
     * Aggiorna un evento settando tutti i campi comprese chiavi primarie
     */
    void update(int idimp, int idcam, String data, String ora, int idevento,String pagato,int giocatoripagato, String sport);
    
    
    /**
     * 
     * @param data
     * @return 
     * Restituisce una lista di eventi che si tengono nella data specificata come parametro
     */
    List <Evento> getEventoByData(String data);
    
    
    
    /**
     * 
     * @param ora
     * @return 
     * Restituisce una lista di eventi che si tengono alla medesima ora indicata come parametro
     */
    List<Evento> getEventoByOra(String ora);
    
    
    /**
     * 
     * @param citta
     * @return 
     * Restituisce la lista di eventi che si tengono nella città specificata come parametro
     */
    List<Evento> getEventoByCitta(String citta);
    
    
    /**
     * 
     * @param sport
     * @return 
     * Restituisce una lista con tutti gli eventi sportivi che appartengono allo sport indicato come parametro
     */
    List<Evento> getEventoBySport(String sport);
    
    
    /**
     * 
     * @param citta
     * @return 
     * Restituisce una lista di tutti gli eventi che hanno il campo COMPLETO=SI nella città specificata come parametro
     */
    List<Evento> getEventoCompletoByCitta(String citta);

    
    /**
     * 
     * @param provincia
     * @return 
     * Restituisce una lista con tutti gli eventi che hanno il campo COMPLETO=SI giocati nella provincia indicata come parametro
     */
    List<Evento> getEventoCompletoByProvincia(String provincia);
    
    
    /**
     * 
     * @param idimpianto
     * @param idcampo
     * @param ora
     * @param data
     * @return 
     * Restituisce un oggetto EVENTOPK con le chiavi indicate come parametro, utile per aggiungere un evento poichè
     * EVENTO è composto da EVENTOPK
     */
    Evento getObjEventoByPK(int idimpianto, int idcampo, String ora, String data);
    
}
