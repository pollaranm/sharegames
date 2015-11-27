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
    
    Evento getObjEvento(int idevento);

    //Evento getEventoBy(String data, String ora, String citta , String sport);

    int getGiocatoriPagato(int idevento);

    //Evento getEventoCompletoBy(String citta , String provincia);

    void update(int idimp, int idcam, String data, String ora, int idevento,String pagato,int giocatoripagato, String sport);
    
}
