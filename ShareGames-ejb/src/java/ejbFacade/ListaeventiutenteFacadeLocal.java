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

    List<Evento> getEventoByUtente(int idutente);

    List<Evento> getEventoByPagato(int idutente);

    List<Evento> getListaEventiUtenti();
}
