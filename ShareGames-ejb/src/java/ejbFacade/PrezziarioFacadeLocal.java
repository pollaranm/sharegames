/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Prezziario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
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
    
}
