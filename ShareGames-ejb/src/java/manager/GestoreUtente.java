package manager;

import ejb.Squadra;
import ejb.Utente;
import ejbFacade.SquadraFacadeLocal;
import ejbFacade.UtenteFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Beans dedicato all'interazione con gli oggetti persistenti di tipo Utente.
 * Contiene le info degli utenti nome, email, idgoogle, idfacebook e telefono.
 */
@Stateless
public class GestoreUtente implements GestoreUtenteLocal {
    @EJB
    private SquadraFacadeLocal squadraFacade;

    @EJB
    private UtenteFacadeLocal utenteFacade;

    
     /**
     * Aggiunta di un nuovo oggetto di tipo Impianto nel database. 
     * 
     * @param name nome dell'utente
     * @param email email dell'utente
     * @param idgoogle id account google
     * @param idfacebook id account facebook
     * @param telefono telefono utente
     * @return <i>void</i>
    */
    @Override
    public void addUser(String name, String email, String idgoogle, String idfacebook, String telefono) {

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
     * @param idfacebook id account facebook
     * @return <i>true</i> se l'utente è già presente, <i>false</i> altrimenti.
     */
    @Override
    public boolean findFacebook(String idfacebook) {
        
        return utenteFacade.findbyface(idfacebook);

    }

    /**
     * Controlla la presenza di un Utente associato all'id Google passato come parametro.
     * 
     * @param idgoogle id account google
     * @return <i>true</i> se l'utente è già presente, <i>false</i> altrimenti.
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
     * @return L'utente cercato se presente, <i>null</i> altrimenti
     */
    @Override
    public Utente getObjUtente(String idsocial, String tipo) {
        return utenteFacade.getObjUtente(idsocial, tipo);
    }

    
    /**
     * Inserisce l'utente nella squadra con identificativo idSquadra,
     * aggiorna le informazioni dell'utente nel database
     * 
     * @param utente Oggetto utente
     * @param idSquadra id della squadra
     * @return <i>void</i>
     */
    @Override
    public void joinSquadra(Utente utente, Integer idSquadra) {
        Squadra temp = squadraFacade.getObjSquadra(idSquadra);
        temp.setNumerocomponenti(temp.getNumerocomponenti() + 1);
        utente.setIdsquadra(squadraFacade.getObjSquadra(idSquadra));
        utenteFacade.edit(utente);
        squadraFacade.edit(temp);
    }

    
    /**
     * Rimuove l'utente dalla squadra associata al suo profilo
     * 
     * @param utente Oggetto utente
     * @return <i>void</i>
     */
    @Override
    public void leaveSquadra(Utente utente) {
        Squadra temp = utente.getIdsquadra();
        temp.setNumerocomponenti(temp.getNumerocomponenti() - 1);
        utente.setIdsquadra(null);
        utenteFacade.edit(utente);
        squadraFacade.edit(temp);
    }

    
    /**
     * Aggiorna le informazioni dell'utente 
     * 
     * @param idsocial id del social di accesso 'facebook' o 'google'
     * @param tipo 'facebook' o 'google'
     * @param nome nome utente
     * @param email email utente
     * @param telefono telefono dell'utente
     * @return <i>void</i>
     */
    @Override
    public void updateUtente(String idsocial, String tipo, String nome, String email, String telefono) {
        Utente u = utenteFacade.getObjUtente(idsocial, tipo);
        u.setNome(nome);
        u.setEmail(email);
        u.setTelefono(telefono);
        utenteFacade.edit(u);
    }

    
    
    
    
    
    

}
