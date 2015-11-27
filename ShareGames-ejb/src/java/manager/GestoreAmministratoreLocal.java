/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.Amministratore;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface GestoreAmministratoreLocal {
    
    void addAmministratore(int idImpianto, String nome, String cognome);

    Amministratore getObjAmministratore(int idAmministratore);

    boolean removeAmministratore(int idAmministratore);
    
}
