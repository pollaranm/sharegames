package manager;

import ejb.Squadra;
import ejb.Utente;
import ejbFacade.SquadraFacadeLocal;
import ejbFacade.UtenteFacadeLocal;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class GestoreSquadra implements GestoreSquadraLocal {

    @EJB
    private UtenteFacadeLocal utenteFacade;

    @EJB
    private SquadraFacadeLocal squadraFacade;

    
    /**
     * Restituisce l'oggetto Squadra tramite l'id passato da parametro
     * 
     * @param idSquadra id della squadra
     * @return <i>Squadra</i>
    */
    @Override
    public Squadra getObjSquadra(Integer idSquadra) {
        return squadraFacade.getObjSquadra(idSquadra);
    }

    
    /**
     * Restituisce l'oggetto Squadra con nome passato da parametro
     * 
     * @param nomeSquadra nome della squadra
     * @return <i>Squadra</i>
    */
    @Override
    public Squadra getObjSquadraByName(String nomeSquadra) {
        return squadraFacade.getObjSquadraByName(nomeSquadra);
    }

    
    /**
     * Aggiunta di un nuovo oggetto di tipo Squadra nel database. 
     * 
     * @param nomeSquadra nome della squadra
     * @param tipologia sport giocato
     * @param citta citta nella quale la squadra si trova
     * @return <i>void</i>
    */
    @Override
    public void addSquadra(String nomeSquadra, String tipologia, String citta) {
        if (!checkNomeSquadra(nomeSquadra)) {
            return;
        }
        Squadra s = new Squadra();
        s.setNomesquadra(nomeSquadra);
        s.setTipologia(tipologia);
        s.setCitta(citta);
        s.setNumerocomponenti(0);
        squadraFacade.create(s);
    }

    
    /**
     * Rimuove un oggetto Squadra dal database attraverso il suo id passato da parametro
     * 
     * @param idSquadra id della squadra
     * @return <i>void</i>
    */
    @Override
    public void removeSquadra(Integer idSquadra) {
        Squadra temp = squadraFacade.getObjSquadra(idSquadra);
        for (Utente player : temp.getUtenteCollection()) {
            player.setIdsquadra(null);
            utenteFacade.edit(player);
        }
        squadraFacade.remove(temp);
    }

    
    /**
     * Restituisce tutte le squadre
     * 
     * @return <i>Collection Squadra</i>
    */
    @Override
    public Collection<Squadra> getAllSquadra() {
        return squadraFacade.findAll();
    }

    
    /**
     * Controlla se esiste una squadra con il nome passato da parametro
     * 
     * @param name nome della squadra
     * @return <i>true</i> se esiste,<i>false</i> altrimenti
    */
    @Override
    public Boolean checkNomeSquadra(String name) {
        return squadraFacade.checkNomeSquadra(name);
    }

    
    /**
     * Restituisce una collezione di oggetti Squadra attraverso la citta passata da parametro
     * 
     * @param city citta della squadra
     * @return <i>Collection Squadra</i>
    */
    @Override
    public Collection<Squadra> getSquadraByCitta(String city) {
        return squadraFacade.getSquadraByCitta(city);
    }

    
    /**
     * Restituisce una collezione di oggetti Squadra attraverso la tipologia passata da parametro
     * 
     * @param city citta della squadra
     * @return <i>Collection Squadra</i>
    */
    @Override
    public Collection<Squadra> getSquadraByTipologia(String tipologia) {
        return squadraFacade.getSquadraByTipologia(tipologia);
    }

    
    /**
     * Aggiorna l'oggetto Squadra attraverso tutti i suoi parametri
     * 
     * @param idSquadra id della squadra
     * @param nomeSquadra nome della squadra
     * @param numeroComponenti numero dei componenti della squadra
     * @param tipologia sport praticato
     * @return <i>void</i>
    */
    @Override
    public void updateSquadra(int idSquadra, String nomeSquadra, int numeroComponenti, String tipologia, String citta) {
        if (!checkNomeSquadra(nomeSquadra)) {
            return;
        }
        Squadra s = squadraFacade.getObjSquadra(idSquadra);
        s.setNomesquadra(nomeSquadra);
        s.setTipologia(tipologia);
        s.setCitta(citta);
        s.setNumerocomponenti(numeroComponenti);
        squadraFacade.edit(s);
    }

    
    /**
     * Restituisce una collezione di oggetti Squadra attraverso la tipologia e la citta passati da parametro
     * 
     * @param city citta della squadra
     * @param tipologia sport praticato
     * @return <i>Collection Squadra</i>
    */
    @Override
    public Collection<Squadra> getSquadraByCittaTipologia(String citta, String tipologia) {
        return squadraFacade.getSquadraByCittaTipologia(citta, tipologia);
    }

}
