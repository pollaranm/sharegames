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
public interface GestoreListaEventiLocal {
    
        List<Evento> getEventoByUtente(int idutente);

    List<Evento> getEventoByPagato(int idutente);

    List<Evento> getListaEventiUtenti();
    
}
