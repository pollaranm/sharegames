/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alex
 */
@Entity
@Table(catalog = "newsharegames", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Impianto.findAll", query = "SELECT i FROM Impianto i"),
    @NamedQuery(name = "Impianto.findByIdimpianto", query = "SELECT i FROM Impianto i WHERE i.idimpianto = :idimpianto"),
    @NamedQuery(name = "Impianto.findByNome", query = "SELECT i FROM Impianto i WHERE i.nome = :nome"),
    @NamedQuery(name = "Impianto.findByStato", query = "SELECT i FROM Impianto i WHERE i.stato = :stato"),
    @NamedQuery(name = "Impianto.findByRegione", query = "SELECT i FROM Impianto i WHERE i.regione = :regione"),
    @NamedQuery(name = "Impianto.findByProvincia", query = "SELECT i FROM Impianto i WHERE i.provincia = :provincia"),
    @NamedQuery(name = "Impianto.findByCitta", query = "SELECT i FROM Impianto i WHERE i.citta = :citta"),
    @NamedQuery(name = "Impianto.findByIndirizzo", query = "SELECT i FROM Impianto i WHERE i.indirizzo = :indirizzo"),
    @NamedQuery(name = "Impianto.findByTelefono", query = "SELECT i FROM Impianto i WHERE i.telefono = :telefono"),
    @NamedQuery(name = "Impianto.findByPartitaiva", query = "SELECT i FROM Impianto i WHERE i.partitaiva = :partitaiva"),
    @NamedQuery(name = "Impianto.findByFasciaoraria", query = "SELECT i FROM Impianto i WHERE i.fasciaoraria = :fasciaoraria"),
    @NamedQuery(name = "Impianto.findByServizi", query = "SELECT i FROM Impianto i WHERE i.servizi = :servizi")})
public class Impianto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idimpianto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String stato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String regione;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String provincia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String citta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String indirizzo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String partitaiva;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String fasciaoraria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String servizi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "impianto", fetch = FetchType.LAZY)
    private Collection<Prezziario> prezziarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "impianto", fetch = FetchType.LAZY)
    private Collection<Campo> campoCollection;

    public Impianto() {
    }

    public Impianto(Integer idimpianto) {
        this.idimpianto = idimpianto;
    }

    public Impianto(Integer idimpianto, String nome, String stato, String regione, String provincia, String citta, String indirizzo, String telefono, String partitaiva, String fasciaoraria, String servizi) {
        this.idimpianto = idimpianto;
        this.nome = nome;
        this.stato = stato;
        this.regione = regione;
        this.provincia = provincia;
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.partitaiva = partitaiva;
        this.fasciaoraria = fasciaoraria;
        this.servizi = servizi;
    }

    public Integer getIdimpianto() {
        return idimpianto;
    }

    public void setIdimpianto(Integer idimpianto) {
        this.idimpianto = idimpianto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPartitaiva() {
        return partitaiva;
    }

    public void setPartitaiva(String partitaiva) {
        this.partitaiva = partitaiva;
    }

    public String getFasciaoraria() {
        return fasciaoraria;
    }

    public void setFasciaoraria(String fasciaoraria) {
        this.fasciaoraria = fasciaoraria;
    }

    public String getServizi() {
        return servizi;
    }

    public void setServizi(String servizi) {
        this.servizi = servizi;
    }

    @XmlTransient
    public Collection<Prezziario> getPrezziarioCollection() {
        return prezziarioCollection;
    }

    public void setPrezziarioCollection(Collection<Prezziario> prezziarioCollection) {
        this.prezziarioCollection = prezziarioCollection;
    }

    @XmlTransient
    public Collection<Campo> getCampoCollection() {
        return campoCollection;
    }

    public void setCampoCollection(Collection<Campo> campoCollection) {
        this.campoCollection = campoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idimpianto != null ? idimpianto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Impianto)) {
            return false;
        }
        Impianto other = (Impianto) object;
        if ((this.idimpianto == null && other.idimpianto != null) || (this.idimpianto != null && !this.idimpianto.equals(other.idimpianto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Impianto[ idimpianto=" + idimpianto + " ]";
    }
    
}
