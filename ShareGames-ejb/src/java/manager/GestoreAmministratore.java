package manager;

import javax.ejb.Stateless;

import ejb.Amministratore;
import ejb.Impianto;
import ejbFacade.AmministratoreFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *Beans dedicato all'interazione con gli oggetti persistenti di tipo Amministratore.
 * Contiene le info degli Amministratori: "idImpianto", "nome" e "cognome".
 * 
 * @author Ivan
 */


@Stateless
public class GestoreAmministratore implements GestoreAmministratoreLocal {

    @EJB
    private AmministratoreFacadeLocal amministratoreFacade;

    
    
    /**
     * Metodo costruttore di un oggetto persistente di tipo Amministratore.
     * 
     * @param idImpianto Nome dell'impianto a cui appartiene l'amministratore
     * @param nome Nome dell'amministratore     
     * @param cognome Cognome dell'amministratore
     */
    @Override
    public void addAmministratore(int idImpianto, String nome, String cognome) {
        
        Impianto i= new Impianto();
        i.setIdimpianto(idImpianto);
        
        Amministratore a = new Amministratore();
        a.setIdimpianto(i);
        a.setNome(nome);
        a.setCognome(cognome);
        
        amministratoreFacade.create(a);  
    }

    
    
    /**
     * Recupera l'oggetto persistente amministratore 
     * associato all'Id passato come parametro
     *
     * @param idAmministratore id identificativo dell'amministratore
     * @return L'amministratore cercato se presente, <i>false</i> altrimenti 
     */
    @Override
    public Amministratore getObjAmministratore(int idAmministratore) {
        return amministratoreFacade.getObjAmministratore(idAmministratore);
    }

    
    
    /**
     *  Rimuove l'amministratore a cui è associato l'id passato di tipo intero
     *
     * @param idAmministratore Id amministratore da ricercare e rimuovere
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
    
}
