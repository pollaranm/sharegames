/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.Impianto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface GestoreImpiantoLocal {

    void addImpianto(String nomeimpianto, String stato, String regione, String provincia, String citta, String indirizzo, String telefono, String partitaiva, String fasciaoraria, String servizi);

    void removeImpianto(int id);

    List<Impianto> getImpiantoByCitta(String citta);

    List<Impianto> getImpiantoByProvincia(String provincia);

    Impianto getObjectImpiantoById(int id);

    void updateImpianto(String nomeimpianto, String stato, String regione, String provincia, 
                            String citta, String indirizzo, String telefono, String partitaiva, 
                            String fasciaoraria, String servizi);
    
}
