/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "squadra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Squadra.findAll", query = "SELECT s FROM Squadra s"),
    @NamedQuery(name = "Squadra.findByIdsquadra", query = "SELECT s FROM Squadra s WHERE s.idsquadra = :idsquadra"),
    @NamedQuery(name = "Squadra.findByNomesquadra", query = "SELECT s FROM Squadra s WHERE s.nomesquadra = :nomesquadra"),
    @NamedQuery(name = "Squadra.findByNumerocomponenti", query = "SELECT s FROM Squadra s WHERE s.numerocomponenti = :numerocomponenti"),
    @NamedQuery(name = "Squadra.findByTipologia", query = "SELECT s FROM Squadra s WHERE s.tipologia = :tipologia")})
public class Squadra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsquadra")
    private Integer idsquadra;
    @Size(max = 100)
    @Column(name = "nomesquadra")
    private String nomesquadra;
    @Size(max = 45)
    @Column(name = "numerocomponenti")
    private String numerocomponenti;
    @Size(max = 9)
    @Column(name = "tipologia")
    private String tipologia;
    @OneToMany(mappedBy = "idsquadra")
    private Collection<Utente> utenteCollection;

    public Squadra() {
    }

    public Squadra(Integer idsquadra) {
        this.idsquadra = idsquadra;
    }

    public Integer getIdsquadra() {
        return idsquadra;
    }

    public void setIdsquadra(Integer idsquadra) {
        this.idsquadra = idsquadra;
    }

    public String getNomesquadra() {
        return nomesquadra;
    }

    public void setNomesquadra(String nomesquadra) {
        this.nomesquadra = nomesquadra;
    }

    public String getNumerocomponenti() {
        return numerocomponenti;
    }

    public void setNumerocomponenti(String numerocomponenti) {
        this.numerocomponenti = numerocomponenti;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    @XmlTransient
    public Collection<Utente> getUtenteCollection() {
        return utenteCollection;
    }

    public void setUtenteCollection(Collection<Utente> utenteCollection) {
        this.utenteCollection = utenteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsquadra != null ? idsquadra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Squadra)) {
            return false;
        }
        Squadra other = (Squadra) object;
        if ((this.idsquadra == null && other.idsquadra != null) || (this.idsquadra != null && !this.idsquadra.equals(other.idsquadra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Squadra[ idsquadra=" + idsquadra + " ]";
    }
    
}
