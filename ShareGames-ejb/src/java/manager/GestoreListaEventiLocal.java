/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface GestoreListaEventiLocal {
    
        List getEventoByUtente(int idutente);

    List getEventoByPagato(int idutente);
    
}
