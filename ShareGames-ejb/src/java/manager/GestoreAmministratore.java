package manager;

import javax.ejb.Stateless;

import ejb.Amministratore;
import ejb.AmministratorePK;
import ejb.Impianto;
import ejbFacade.AmministratoreFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *Beans dedicato all'interazione con gli oggetti persistenti di tipo Amministratore.
 * Contiene le info degli Amministratori: "idImpianto", "nome" e "cognome".
 * 
 */


@Stateless
public class GestoreAmministratore implements GestoreAmministratoreLocal {

    @EJB
    private AmministratoreFacadeLocal amministratoreFacade;

    
    
    /**
     * Aggiunta di un nuovo oggetto di tipo Amministratore nel database. 
     * 
     * @param idImpianto Nome dell'impianto a cui appartiene l'amministratore
     * @param nome Nome dell'amministratore     
     * @param cognome Cognome dell'amministratore
     * @param psw Password amministratore
     * @return void
     */
    @Override
    public void addAmministratore(int idImpianto, String nome, String cognome, String psw) {
        
        Impianto i= new Impianto();
        i.setIdimpianto(idImpianto);
        
        Amministratore a = new Amministratore();
        AmministratorePK a_pk = new AmministratorePK();
        a.setAmministratorePK(a_pk);
        a_pk.setPassword(psw);
        a.setIdimpianto(i);
        a.setNome(nome);
        a.setCognome(cognome);
        amministratoreFacade.create(a);  
    }

    
    
    /**
     * Recupera l'oggetto persistente amministratore attraverso IdAmministratore passato come parametro
     *
     * @param idAmministratore idImpianto identificativo dell'amministratore
     * @return L'amministratore cercato se presente, <i>false</i> altrimenti 
     */
    @Override
    public Amministratore getObjAmministratore(int idAmministratore) {
        return amministratoreFacade.getObjAmministratore(idAmministratore);
    }

    
    
    /**
     * Rimuove l'amministratore a cui è associato l'id passato da parametro
     *
     * @param idAmministratore IdAmministratore
     * @return <i>true</i> se l'eliminazione avviene con successo, <i>false</i> altrimenti
     */
    @Override
    public boolean removeAmministratore(int idAmministratore) {
        
        Amministratore a= amministratoreFacade.getObjAmministratore(idAmministratore);
        
        if( a == null ) {
            return false;
        } else {
            amministratoreFacade.remove(a);
            return true;
        }
        
    }
    
    
    /**
     * Controllo corrispondenza password e idAmministratore passati da parametro
     *
     * @param id Id amministratore
     * @param psw Password amministratore
     * @return <i>true</i> se la corrispondenza id/psw è corretta, <i>false</i> altrimenti.
     */
    @Override
    public Boolean checkAuthAmm(int id, String psw) {
        
        Amministratore a = amministratoreFacade.getObjAmministratore(id);
        
        if ( a == null){
            return false;
        } else {
            
           if (a.getAmministratorePK().getPassword().equals(psw)){
               return true;
           }
        }
        return false;
    }
    
    
    /**
     * Recupera l'oggetto Amministratore associato all'Idimpianto passato come parametro
     *
     * @param idImpianto idImpianto identificativo dell'amministratore
     * @return L'amministratore cercato se presente, <i>false</i> altrimenti 
     */
    @Override
    public Amministratore getObjAmministratoreByIdimpianto(int idimpianto) {
        
        return amministratoreFacade.getObjAmministratoreByImpianto(idimpianto);
    }
    
}
