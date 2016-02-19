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
     * Aggiunta di un nuovo prezziario nel database. 
     * 
     * @param idCampo nome dell'impianto
     * @param idImpianto stato nel quale l'impianto è locato
     * @param prezzo regione nella quale l'impianto è locato
     * @param sconto provincia nella quale l'impianto è locato
     * @return <i>void</i>
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
     * Recupera il Prezziario del campo attraverso l'idimpianto passato da parametro.
     *
     * @param idImpianto identificativo dell'impianto
     * @return <i>List Impianto</i>
     */
    @Override
    public List<Prezziario> getObjPrezziario(int idImpianto) {
        return prezziarioFacade.getObjectPrezziario(idImpianto);
    }

 
    /**
     * Aggiorna il Prezziario attraverso idcampo, idimpianto, prezzo, sconto passati tramite parametro.
     *
     * @param idCampo id del campo 
     * @param idImpianto id dell'impianto 
     * @param prezzo prezzo cad
     * @param sconto sconto applicato (opzionale)
     * @return <i>void</i>
     */
    @Override
    public void updatePrezziario(int idCampo, int idImpianto, BigDecimal prezzo, int sconto) {
    
       prezziarioFacade.updateAll(idCampo, idImpianto, prezzo, sconto);
    
    }
    
    
    
    
    
}
