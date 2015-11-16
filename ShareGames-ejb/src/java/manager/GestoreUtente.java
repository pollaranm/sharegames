/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.Utente;
import ejbFacade.UtenteFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Beans dedicato all'interazione con gli oggetti persistenti di tipo Utente.
 * Contiene le info degli utenti nome, email, idgoogle, idfacebook e telefono.
 * 
 * @author Alex
 */
@Stateless
public class GestoreUtente implements GestoreUtenteLocal {

    @EJB
    private UtenteFacadeLocal utenteFacade;

    /**
     * Metodo costruttore di un oggetto persistente di tipo Utente.
     * 
     * @param name Nome dell'utente
     * @param email Email dell'utente
     * @param idgoogle Id Google associato all'utente
     * @param idfacebook Id Facebook associato all'utente
     * @param telefono Numero di telefono
     */
    @Override
    public void AddUser(String name, String email, String idgoogle, String idfacebook, String telefono) {

        Utente u = new Utente();
        u.setEmail(email);
        u.setIdfacebook(idfacebook);
        u.setIdgoogle(idgoogle);
        u.setNome(name);
        u.setTelefono(telefono);
        utenteFacade.create(u);

    }

    /**
     * Controlla la presenza di un Utente associato all'id Facebook passato come parametro.
     * 
     * @param idfacebook Id da ricercare tra gli utenti registrati
     * @return Restituisce <i>true</i> se l'utente è già presente, <i>false</i> altrimenti.
     */
    @Override
    public boolean findFacebook(String idfacebook) {
        
        return utenteFacade.findbyface(idfacebook);

    }

    /**
     * Controlla la presenza di un Utente associato all'id Google passato come parametro.
     * 
     * @param idgoogle Id da ricercare tra gli utenti registrati
     * @return Restituisce <i>true</i> se l'utente è già presente, <i>false</i> altrimenti.
     */
    @Override
    public boolean findGoogle(String idgoogle) {
        
        return utenteFacade.findbygoogle(idgoogle);
        
    }

    /**
     * Rimuove l'utente a cui è associato l'id passato del tipo passato.
     * 
     * @param idsocial Id da ricercare e rimuovere
     * @param tipo tipologia di social su cui cercare: 'facebook' o 'google'
     * @return <i>true</i> se l'eliminazione avviene con successo, <i>false</i> altrimenti
     */
    @Override
    public boolean removeUtente(String idsocial, String tipo) {

        Utente t = utenteFacade.getObjUtente(idsocial, tipo);
        if( t == null ) {
            return false;
        } else {
            utenteFacade.remove(t);
            return true;
        }
        
    }

    /**
     * Recupera l'Utente associato all'Id e al social network passati come parametro.
     * 
     * @param idsocial Id identificativo dell'utente
     * @param tipo Social network utilizzato, 'facebook' o 'google'
     * @return L'utente cercato se presente, <i>false</i> altrimenti
     */
    @Override
    public Utente getObjUtente(String idsocial, String tipo) {
        return utenteFacade.getObjUtente(idsocial, tipo);
    }
    
    
    

}
