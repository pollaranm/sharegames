/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.Evento;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface GestoreEventoLocal {
      Evento getEvento(int idevento);

    void addEvento(int idimpianto, int idcampo, String data, 
            String ora, String sport, String pagato, 
            String completo, int giocatoripagato,
            int idutente);

    void removeEvento(int idevento);

    void updateEvento(int idimpianto, int idcampo, int idevento, 
            String data, String ora,String pagato,String sport,int giocatoripagato);

    //Evento getEventoBy(String data, String ora, String citta , String sport);

    int getGiocatoriPagato(int idevento);

    //void getEventoCompletoBy(String citta , String provincia);
    
}
