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
public interface GestoreUtenteLocal {

    void AddUser(String name, String email, String idgoogle, String idfacebook, String telefono);

    boolean findFacebook(String idfacebook);

    boolean findGoogle(String idgoogle);

    boolean removeUtente(String idsocial, String tipo);

    Utente getObjUtente(String idsocial, String tipo);

}
