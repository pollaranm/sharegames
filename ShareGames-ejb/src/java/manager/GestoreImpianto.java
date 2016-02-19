package manager;

import ejb.Impianto;
import ejbFacade.ImpiantoFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;


@Stateless
public class GestoreImpianto implements GestoreImpiantoLocal {
    @EJB
    private ImpiantoFacadeLocal impiantoFacade;
    
    /**
     * Aggiunta di un nuovo oggetto di tipo Impianto nel database. 
     * 
     * @param nomeimpianto nome dell'impianto
     * @param stato stato nel quale l'impianto è locato
     * @param regione regione nella quale l'impianto è locato
     * @param provincia provincia nella quale l'impianto è locato
     * @param citta città nel quale l'impianto è locato
     * @param indirizzo indirizzo nel quale l'impianto è locato
     * @param telefono telefono dell'impianto
     * @param partitaiva numero di partita iva
     * @param fasciaoraria ora apertura/chiusura
     * @param servizi servizi offerti dall'impianto
     * @return <i>void</i>
    */
@Override
    public void addImpianto(String nomeimpianto, String stato, String regione, String provincia, 
                            String citta, String indirizzo, String telefono, String partitaiva, 
                            String fasciaoraria, String servizi) 
    {
    
    Impianto i = new Impianto();
    
    i.setNome(nomeimpianto);
    i.setStato(stato);              i.setRegione(regione);              i.setProvincia(provincia);  
    i.setCitta(citta);              i.setIndirizzo(indirizzo);          i.setTelefono(telefono);
    i.setPartitaiva(partitaiva);    i.setFasciaoraria(fasciaoraria);    i.setServizi(servizi);
    
    impiantoFacade.create(i);
    
    }
    
    
    /**
     * Rimuove l'impianto passato da paramentro dal database.
     * 
     * @param id id dell'impianto
     * @return <i>void</i>
    */    
    @Override
    public void removeImpianto(int id) {
        
        impiantoFacade.remove(impiantoFacade.getObjImpianto(id));
    }

    
    /**
     * Recupera lista impianti nella città passata da parametro.
     * 
     * @param citta città dell'evento
     * @return <i>List Impianto</i>
    */
    @Override
    public List<Impianto> getImpiantoByCitta(String citta) {
        
        return (List<Impianto>)impiantoFacade.getImpiantoByCitta(citta);
        
    }

    
    /**
     * Recupera lista impianti nella provincia passata da parametro.
     * 
     * @param provincia provincia dell'evento
     * @return <i>List Impianto</i>
    */
    @Override
    public List<Impianto> getImpiantoByProvincia(String provincia) {
        
       return (List<Impianto>) impiantoFacade.getImpiantoByProvincia(provincia);

    }

    
    /**
     * Recupera l'oggetto Impianto tramite il suo id passato da parametro.
     * 
     * @param citta città dell'evento
     * @return <i>Impianto</i>
    */
    @Override
    public Impianto getObjectImpiantoById(int id) {
        
        return (Impianto)impiantoFacade.getObjImpianto(id);
        
    }
    
    
    /**
     * Recupera l'oggetto Impianto tramite nome,partitaiva e telefono passati da parametro.
     * 
     * @param nome nome dell'impianto
     * @param partitaiva partita iva dell'impianto
     * @param telefono numero di telefono dell'impianto
     * @return <i>Impianto</i>
    */
    @Override
    public Impianto getImpiantoByNomePartitaivaTelefono(String nome, String partitaiva, String telefono) {
        

        return (Impianto)impiantoFacade.getImpiantoByNomePartitaivaTelefono(nome, partitaiva, telefono);
    }

    
    /**
     * Controllo dell'esistenza di un'altro oggetto Impianto con gli stessi parametri passati.
     * 
     * @param nome nome dell'impianto
     * @param partitaiva partita iva dell'impianto
     * @param telefono numero di telefono dell'impianto
     * @return <i>true</i> se esiste, <i>false</i> altrimenti
    */
    @Override
    public Boolean checkImpianto(String nome, String partitaiva, String telefono) {
        
        
        Impianto i = impiantoFacade.getImpiantoByNomePartitaivaTelefono(nome, partitaiva, telefono);

        
        if ( i == null){
            
           return false;
           
        } 
           return true;
        
    }

    
}
