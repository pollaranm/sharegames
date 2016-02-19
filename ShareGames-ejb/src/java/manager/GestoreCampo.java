package manager;

import ejb.Campo;
import ejb.CampoPK;
import ejb.Impianto;
import ejbFacade.CampoFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class GestoreCampo implements GestoreCampoLocal {
    @EJB
    private CampoFacadeLocal campoFacade;
    
    
    /**
     * Aggiunta di un nuovo oggetto di tipo Amministratore nel database. 
     * 
     * @param idcampo id del campo interessato
     * @param idimpianto id dell' impianto
     * @param tipologia tipologia del campo (calcio5,calcio7,calcio11,pallavolo,basket, tennis)   
     * @param numerogiocatori numero dei giocatori 
     * @return void
    */
    
    @Override
    public void addCampo(int idcampo, int idimpianto, String tipologia, int numerogiocatori) {
        
        Impianto i = new Impianto();
        i.setIdimpianto(idimpianto);
        Campo c = new Campo();
        CampoPK c_pk = new CampoPK();
        c_pk.setIdcampo(idcampo);
        c_pk.setIdimpianto(idimpianto);
        c.setCampoPK(c_pk);
        c.setImpianto(i);
        c.setNumerogiocatori(numerogiocatori);
        c.setTipologia(tipologia);
        
        campoFacade.create(c);
        
    }
    
    
    /**
     * Recupera l'oggetto persistente Campo 
     * associato all'Idimpianto e all'IdCampo passato come parametro
     *
     * @param idImpianto idImpianto identificativo
     * @param idCampo idCampo identificativo all'interno di impianto
     * @return Campo cercato se presente, <i>null</i> altrimenti 
    */
    @Override
    public Campo getObjectCampoById(int idcampo, int idimpianto) {
        
        return campoFacade.getObjectCampo(idcampo, idimpianto);

    }
    
    
    /**
     * Elimina l'oggetto persistente Campo 
     * associato all'Idimpianto e all'IdCampo passato come parametro
     *
     * @param idImpianto idImpianto identificativo
     * @param idCampo idCampo identificativo all'interno di impianto
     * @return void
    */
    @Override
    public void removeCampo(int idcampo, int idimpianto) {
        
        campoFacade.remove(getObjectCampoById(idcampo, idimpianto));
        
        
    }
    /**
     * Aggiorna i parametri l'oggetto persistente campo
     * associato all'Idimpianto, all'idCampo, alla tipologia, al numero dei giocatori passati come parametro
     *
     * @param idImpianto idImpianto identificativo dell'amministratore
     * @return void
     */
    @Override
    public void updateCampo(int idcampo, int idimpianto, String tipologia, int numerogiocatori) {}
    
    
    /**
     * Recupera l'oggetto persistente Campo 
     * associato alla campo tipologia passato come parametro
     *
     * @param tipologia tipologia del campo (calcio5,calcio7,calcio11,pallavolo,basket, tennis)   
     * @return L'amministratore cercato se presente, <i>false</i> altrimenti 
     */
    @Override
    public List<Campo> getCampoByTipologia(String tipologia) {
        
        return campoFacade.getCampoByTipologia(tipologia);
    }
    
    
    
    /**
     * Recupera l'oggetto persistente Campo 
     * associato all'Idimpianto passato come parametro
     *
     * @param idImpianto idImpianto identificativo
     * @return Lista dei campi cercati se presente, <i>null</i> altrimenti 
    */
    @Override
    public List<Campo> getCampoByImpianto(int idimpianto) {
        
        return (List<Campo>)campoFacade.getCampoByImpianto(idimpianto);
        
    }

    
    
}
