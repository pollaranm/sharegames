/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.Evento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface GestoreEventoLocal {
      Evento getEvento(int idevento);

      
      /**
       * 
       * @param idimpianto
       * @param idcampo
       * @param data
       * @param ora
       * @param sport
       * @param pagato
       * @param completo
       * @param giocatoripagato
       * @param idutente 
       * 
       * Aggiunge un evento settando le specifiche chiavi e campi passati come parametro
       * EVENTO è composto da EVENTOPK, quindi prima creeremo un oggetto EVENTOPK che poi passeremo
       * come parametro ad un oggetto EVENTO
       */
    void addEvento(int idimpianto, int idcampo, String data, 
            String ora, String sport, String pagato, 
            String completo, int giocatoripagato,
            int idutente);

    void removeEvento(int idevento);

    
    /**
     * 
     * @param idimpianto
     * @param idcampo
     * @param idevento
     * @param data
     * @param ora
     * @param pagato
     * @param sport
     * @param giocatoripagato 
     * Aggiorna un evento modificando chiavi primarie e campi della tabella evento specificati come parametri
     */
    void updateEvento(int idimpianto, int idcampo, int idevento, 
            String data, String ora,String pagato,String sport,int giocatoripagato);

    /**
     * 
     * @param idevento
     * @return 
     * restituisce il numero di giocatori che hanno pagato per uno specifico evento 
     * che è identificato dall'ID passato come parametro
     */
    int getGiocatoriPagato(int idevento);

    
    /**
     * 
     * @param data
     * @return 
     * Restituisce una lista di Eventi che si tengono nella data indicata come parametro
     */
    List<Evento> getEventoByData(String data);

    
    /**
     * 
     * @param ora
     * @return 
     * Restituisce una lista di eventi che si tengono all'ora indicata come parametro
     */
    List<Evento> getEventoByOra(String ora);
    
    /**
     * 
     * @param citta
     * @return 
     * Restituisce una lista di eventi che si tengono nella città specificata come parametro
     */
    List<Evento> getEventoByCitta(String citta);
    
    
    /**
     * 
     * @param sport
     * @return 
     * Restituisce una lista di eventi relativi alla tipologia di sport passato come parametro 
     */
    List<Evento> getEventoBySport(String sport);

    
    /**
     * 
     * @param citta
     * @return 
     * Restituisce una lista di eventi relativi alla città indicata come parametro in cui il campo COMPLETO=SI
     */
    List<Evento> getEventoCompletoByCitta(String citta);

    
    /**
     * 
     * @param provincia
     * @return 
     * Restituisce una lista di eventi giocati nella provincia indicata come parametro in cui il campo COMPLETO=SI
     */
    List<Evento> getEventoCompletoByProvincia(String provincia);
    
    
}
