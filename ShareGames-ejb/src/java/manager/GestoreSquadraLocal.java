/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.Squadra;
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

    void updateSquadra();
    
}
