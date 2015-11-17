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
import javax.persistence.Id;
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
@Table(name = "campo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Campo.findAll", query = "SELECT c FROM Campo c"),
    @NamedQuery(name = "Campo.findByIdcampo", query = "SELECT c FROM Campo c WHERE c.idcampo = :idcampo"),
    @NamedQuery(name = "Campo.findByTipologia", query = "SELECT c FROM Campo c WHERE c.tipologia = :tipologia"),
    @NamedQuery(name = "Campo.findByNumerogiocatori", query = "SELECT c FROM Campo c WHERE c.numerogiocatori = :numerogiocatori")})
public class Campo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcampo")
    private Integer idcampo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "tipologia")
    private String tipologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numerogiocatori")
    private int numerogiocatori;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcampo")
    private Collection<Evento> eventoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcampo")
    private Collection<Prezziario> prezziarioCollection;
    @JoinColumn(name = "idimpianto", referencedColumnName = "idimpianto")
    @ManyToOne(optional = false)
    private Impianto idimpianto;

    public Campo() {
    }

    public Campo(Integer idcampo) {
        this.idcampo = idcampo;
    }

    public Campo(Integer idcampo, String tipologia, int numerogiocatori) {
        this.idcampo = idcampo;
        this.tipologia = tipologia;
        this.numerogiocatori = numerogiocatori;
    }

    public Integer getIdcampo() {
        return idcampo;
    }

    public void setIdcampo(Integer idcampo) {
        this.idcampo = idcampo;
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
    public Collection<Evento> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<Evento> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

    @XmlTransient
    public Collection<Prezziario> getPrezziarioCollection() {
        return prezziarioCollection;
    }

    public void setPrezziarioCollection(Collection<Prezziario> prezziarioCollection) {
        this.prezziarioCollection = prezziarioCollection;
    }

    public Impianto getIdimpianto() {
        return idimpianto;
    }

    public void setIdimpianto(Impianto idimpianto) {
        this.idimpianto = idimpianto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcampo != null ? idcampo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campo)) {
            return false;
        }
        Campo other = (Campo) object;
        if ((this.idcampo == null && other.idcampo != null) || (this.idcampo != null && !this.idcampo.equals(other.idcampo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Campo[ idcampo=" + idcampo + " ]";
    }
    
}
