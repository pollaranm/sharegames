/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Utente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface UtenteFacadeLocal {

    void create(Utente utente);

    void edit(Utente utente);

    void remove(Utente utente);

    Utente find(Object id);

    List<Utente> findAll();

    List<Utente> findRange(int[] range);

    int count();

    /**
     * Controlla la presenza di un Utente registrato con Facebook.
     * 
     * @param id Id Facebook da cercare
     * @return <i>true</i> se l'utente è presente, <i>false</i> altrimenti
     */
    boolean findbyface(String idfacebook);

    /**
     * Controlla la presenza di un Utente registrato con Google.
     * 
     * @param id Id Google da cercare
     * @return <i>true</i> se l'utente è presente, <i>false</i> altrimenti
     */
    boolean findbygoogle(String idgoogle);

    /**
     * Recupera l'Utente associato all'Id e al social network passati come parametro.
     * 
     * @param idsocial Id identificativo dell'utente
     * @param tipo Social network utilizzato, 'facebook' o 'google'
     * @return L'utente cercato se presente, <i>false</i> altrimenti
     */
    Utente getObjUtente(String idsocial, String tipo);

}
