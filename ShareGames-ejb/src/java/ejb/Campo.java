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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Campo.findAll", query = "SELECT c FROM Campo c"),
    @NamedQuery(name = "Campo.findByIdcampo", query = "SELECT c FROM Campo c WHERE c.campoPK.idcampo = :idcampo"),
    @NamedQuery(name = "Campo.findByIdimpianto", query = "SELECT c FROM Campo c WHERE c.campoPK.idimpianto = :idimpianto"),
    @NamedQuery(name = "Campo.findByTipologia", query = "SELECT c FROM Campo c WHERE c.tipologia = :tipologia"),
    @NamedQuery(name = "Campo.findByNumerogiocatori", query = "SELECT c FROM Campo c WHERE c.numerogiocatori = :numerogiocatori")})
public class Campo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CampoPK campoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(nullable = false, length = 9)
    private String tipologia;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int numerogiocatori;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campo", fetch = FetchType.LAZY)
    private Collection<Prezziario> prezziarioCollection;
    @JoinColumn(name = "idimpianto", referencedColumnName = "idimpianto", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Impianto impianto;

    public Campo() {
    }

    public Campo(CampoPK campoPK) {
        this.campoPK = campoPK;
    }

    public Campo(CampoPK campoPK, String tipologia, int numerogiocatori) {
        this.campoPK = campoPK;
        this.tipologia = tipologia;
        this.numerogiocatori = numerogiocatori;
    }

    public Campo(int idcampo, int idimpianto) {
        this.campoPK = new CampoPK(idcampo, idimpianto);
    }

    public CampoPK getCampoPK() {
        return campoPK;
    }

    public void setCampoPK(CampoPK campoPK) {
        this.campoPK = campoPK;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public int getNumerogiocatori() {
        return numerogiocatori;
    }

    public void setNumerogiocatori(int numerogiocatori) {
        this.numerogiocatori = numerogiocatori;
    }

    @XmlTransient
    public Collection<Prezziario> getPrezziarioCollection() {
        return prezziarioCollection;
    }

    public void setPrezziarioCollection(Collection<Prezziario> prezziarioCollection) {
        this.prezziarioCollection = prezziarioCollection;
    }

    public Impianto getImpianto() {
        return impianto;
    }

    public void setImpianto(Impianto impianto) {
        this.impianto = impianto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (campoPK != null ? campoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campo)) {
            return false;
        }
        Campo other = (Campo) object;
        if ((this.campoPK == null && other.campoPK != null) || (this.campoPK != null && !this.campoPK.equals(other.campoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Campo[ campoPK=" + campoPK + " ]";
    }
    
}
