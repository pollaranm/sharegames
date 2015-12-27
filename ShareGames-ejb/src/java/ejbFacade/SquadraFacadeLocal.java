/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbFacade;

import ejb.Squadra;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface SquadraFacadeLocal {

    void create(Squadra squadra);

    void edit(Squadra squadra);

    void remove(Squadra squadra);

    Squadra find(Object id);

    List<Squadra> findAll();

    List<Squadra> findRange(int[] range);

    int count();

    /**
     * Restituisce l'oggetto Squadra associato all'ID passato come parametro.
     * 
     * @param idSquadra Id identificativo della squadra
     * @return L'oggetto Squadra associato a quell'ID
     */
    Squadra getObjSquadra(Integer idSquadra);

    /**
     * Controlla la disponibilità di un nome per una Squadra, valutando se è già presente.
     * @param name Nome squadra da controllare
     * @return <i>True</i> se il nome è disponibile, <i>false</i> se è già stato utilizzato
     */
    Boolean checkNomeSquadra(String name);

    /**
     * Restituisce l'elenco delle squadre che hanno sede nella città passata come parametro.
     * @param city Città di riferimento per la ricerca della squadre
     * @return L'elenco delle squadre
     */
    Collection<Squadra> getSquadraByCitta(String city);

    /**
     * Restituisce l'elenco delle squadre che hanno come tipologia di sport quella passata come parametro.
     * 
     * @param tipologia Tipologia di sport ricercata
     * @return L'elenco delle squadre
     */
    Collection<Squadra> getSquadraByTipologia(String tipologia);

    /**
     * Restituisce l'elenco delle squadre filtrate in base alla città ed alla tipologia di sport passate per parametro.
     * 
     * @param citta Città nella quale cercare
     * @param tipologia Sport di riferimento per la ricerca
     * @return L'elenco delle squadre che rispettano il filtro
     */
    Collection<Squadra> getSquadraByCittaTipologia(String citta, String tipologia);

    Squadra getObjSquadraByName(String nomeSquadra);
    
}
