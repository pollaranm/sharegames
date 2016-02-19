package manager;

import ejb.Campo;
import ejb.CampoPK;
import ejb.Impianto;
import ejb.Prezziario;
import ejb.PrezziarioPK;
import ejbFacade.PrezziarioFacadeLocal;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Beans dedicato all'interazione con gli oggetti persistenti di tipo Prezziario.
 * Contiene le info prezzo e sconto, relativi ad un impianto (idImpianto) ed un
 * campo (idCampo)
 */

@Stateless
public class GestorePrezziario implements GestorePrezziarioLocal {

    @EJB
    private PrezziarioFacadeLocal prezziarioFacade;

    
    
    /**
     * Metodo costruttore di un oggetto persistente di tipo Prezziario
     *
     * @param idCampo identificativo del campo già esistente
     * @param idImpianto identificativo dell'impianto già esistente
     * @param prezzo prezzo totale della partita 
     * @param sconto sconto applicato (opzionale)
     */
    @Override
    public void addPrezziario(int idCampo, int idImpianto, BigDecimal prezzo, int sconto) {
        
        Impianto i= new Impianto();
        i.setIdimpianto(idImpianto);
        
        CampoPK c_pk= new CampoPK();
        c_pk.setIdcampo(idCampo);
        c_pk.setIdimpianto(idImpianto);
        Campo c = new Campo(c_pk);
        
        PrezziarioPK p_pk = new PrezziarioPK();
        
        p_pk.setIdcampo(idCampo);
        p_pk.setIdimpianto(idImpianto);
        
        Prezziario p = new Prezziario();
        
        p.setPrezziarioPK(p_pk);
        p.setCampo(c);
        p.setImpianto(i);
        p.setPrezzo(prezzo);
        p.setSconto(sconto);
             
        prezziarioFacade.create(p);
        
    }

    
    
    /**
     * Recupera il Prezziario a cui sono associati
     * l'IdCampo e l'idImpianto passati come parametri
     *
     * @param idCampo identificativo del campo 
     * @param idImpianto identificativo dell'impianto
     * @return Il prezziario cercato se presente, <i>false</i> altrimenti 
     */
    @Override
    public List<Prezziario> getObjPrezziario(int idImpianto) {
        return prezziarioFacade.getObjectPrezziario(idImpianto);
    }

 
    /**
     * Aggiorna il Prezziario a cui sono associati i parametri idCampo, idImpianto,
     * prezzo e sconto.
     * L'aggiornamento interessa ESCLUSIVAMENTE i parametri "prezzo" e "sconto", 
     * in quanto ritengo che non avrebbe senso aggiornare anche idCampo e IdPrezziario
     *
     * @param idCampo identificativo del campo 
     * @param idImpianto identificativo dell'impianto 
     * @param prezzo prezzo totale della partita 
     * @param sconto sconto applicato (opzionale)
     */
    @Override
    public void updatePrezziario(int idCampo, int idImpianto, BigDecimal prezzo, int sconto) {
    
       prezziarioFacade.updateAll(idCampo, idImpianto, prezzo, sconto);
    
    }
    
    
    
    
    
}
