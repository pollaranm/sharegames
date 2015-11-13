/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.Utente;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface gestoreUtenteLocal {

    void AddUser(String name, String email, String idgoogle, String idfacebook, String telefono);

    boolean find(String idfacebook);

    boolean findgoogle(String idgoogle);

    boolean removeUtente(String idsocial, String tipo);
    
}
