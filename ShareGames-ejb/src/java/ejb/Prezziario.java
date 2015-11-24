/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "prezziario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prezziario.findAll", query = "SELECT p FROM Prezziario p"),
    @NamedQuery(name = "Prezziario.findByIdprezziario", query = "SELECT p FROM Prezziario p WHERE p.idprezziario = :idprezziario"),
    @NamedQuery(name = "Prezziario.findByPrezzo", query = "SELECT p FROM Prezziario p WHERE p.prezzo = :prezzo"),
    @NamedQuery(name = "Prezziario.findBySconto", query = "SELECT p FROM Prezziario p WHERE p.sconto = :sconto")})
public class Prezziario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprezziario")
    private Integer idprezziario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "prezzo")
    private BigDecimal prezzo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sconto")
    private int sconto;
    @PrimaryKeyJoinColumn(name = "idcampo", referencedColumnName = "idcampo")
    @ManyToOne(optional = false)
    private Campo idcampo;
    @JoinColumn(name = "idimpianto", referencedColumnName = "idimpianto")
    @ManyToOne(optional = false)
    private Impianto idimpianto;

    public Prezziario() {
    }

    public Prezziario(Integer idprezziario) {
        this.idprezziario = idprezziario;
    }

    public Prezziario(Integer idprezziario, BigDecimal prezzo, int sconto) {
        this.idprezziario = idprezziario;
        this.prezzo = prezzo;
        this.sconto = sconto;
    }

    public Integer getIdprezziario() {
        return idprezziario;
    }

    public void setIdprezziario(Integer idprezziario) {
        this.idprezziario = idprezziario;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public int getSconto() {
        return sconto;
    }

    public void setSconto(int sconto) {
        this.sconto = sconto;
    }

    public Campo getIdcampo() {
        return idcampo;
    }

    public void setIdcampo(Campo idcampo) {
        this.idcampo = idcampo;
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
        hash += (idprezziario != null ? idprezziario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prezziario)) {
            return false;
        }
        Prezziario other = (Prezziario) object;
        if ((this.idprezziario == null && other.idprezziario != null) || (this.idprezziario != null && !this.idprezziario.equals(other.idprezziario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Prezziario[ idprezziario=" + idprezziario + " ]";
    }
    
}
