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

    /**
     * Metodo costruttore di un oggetto persistente di tipo Utente.
     * 
     * @param name Nome dell'utente
     * @param email Email dell'utente
     * @param idgoogle Id Google associato all'utente
     * @param idfacebook Id Facebook associato all'utente
     * @param telefono Numero di telefono
     */
    void AddUser(String name, String email, String idgoogle, String idfacebook, String telefono);

    /**
     * Controlla la presenza di un Utente associato all'id Facebook passato come parametro.
     * 
     * @param idfacebook Id da ricercare tra gli utenti registrati
     * @return Restituisce <i>true</i> se l'utente è già presente, <i>false</i> altrimenti.
     */
    boolean findFacebook(String idfacebook);

    /**
     * Controlla la presenza di un Utente associato all'id Google passato come parametro.
     * 
     * @param idgoogle Id da ricercare tra gli utenti registrati
     * @return Restituisce <i>true</i> se l'utente è già presente, <i>false</i> altrimenti.
     */
    boolean findGoogle(String idgoogle);

    /**
     * Rimuove l'utente a cui è associato l'id passato del tipo passato.
     * 
     * @param idsocial Id da ricercare e rimuovere
     * @param tipo tipologia di social su cui cercare: 'facebook' o 'google'
     * @return <i>true</i> se l'eliminazione avviene con successo, <i>false</i> altrimenti
     */
    boolean removeUtente(String idsocial, String tipo);

    /**
     * Recupera l'Utente associato all'Id e al social network passati come parametro.
     * 
     * @param idsocial Id identificativo dell'utente
     * @param tipo Social network utilizzato, 'facebook' o 'google'
     * @return L'utente cercato se presente, <i>false</i> altrimenti
     */
    Utente getObjUtente(String idsocial, String tipo);

    /**
     * Inserisce l'Utente passato come parametro nella squadra associata all'ID nei parametri
     * @param utente Oggetto Utente a cui associare la Squadra  
     * @param idSquadra ID della Squadra da associare
     */
    void joinSquadra(Utente utente, Integer idSquadra);


}
