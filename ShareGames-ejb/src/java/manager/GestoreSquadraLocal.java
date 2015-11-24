/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.Squadra;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface GestoreSquadraLocal {

    /**
     * Restituisce l'oggetto Squadra associato all'ID passato come parametro.
     * 
     * @param idSquadra ID identificativo dellasquadrada cercare
     * @return L'oggetto Squadra associato all'ID
     */
    Squadra getObjSquadra(Integer idSquadra);

    /**
     * Crea una nuova Squadra caratterizzata dai parametri passati.
     * Il numero dei componenti del team è impostato di default a 0.
     * 
     * @param nomeSquadra Nome della Squadra
     * @param tipologia Sport praticato dal team, scelto da un elenco prefissato
     * @param citta Città di riferimento per il team
     */
    void addSquadra(String nomeSquadra, String tipologia, String citta);

    /**
     * Elimina la Squadra avente l'Id passato come parametro.
     * Gli Utenti che vi appartenevano tornano ad avere come Squadra NULL.
     * @param idSquadra Id della Squadra da eliminare
     */
    void removeSquadra(Integer idSquadra);

    /**
     * Restituisce l'elenco di tutte le squadre presenti nel database.
     * 
     * @return Una collezione di tutte le squadre presenti in database
     * 
     */
    Collection<Squadra> getAllSquadra();

    
}
