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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(catalog = "newsharegames", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prezziario.findAll", query = "SELECT p FROM Prezziario p"),
    @NamedQuery(name = "Prezziario.updateAll", query = "UPDATE Prezziario p SET p.prezzo = :prezzo, p.sconto = :sconto WHERE p.prezziarioPK.idcampo = :idcampo AND p.prezziarioPK.idimpianto = :idimpianto"),
    @NamedQuery(name = "Prezziario.findByIdcampoIdimpianto", query = "SELECT p FROM Prezziario p WHERE p.prezziarioPK.idcampo = :idcampo AND p.prezziarioPK.idimpianto = :idimpianto"),
    @NamedQuery(name = "Prezziario.findByIdcampo", query = "SELECT p FROM Prezziario p WHERE p.prezziarioPK.idcampo = :idcampo"),
    @NamedQuery(name = "Prezziario.findByIdimpianto", query = "SELECT p FROM Prezziario p WHERE p.prezziarioPK.idimpianto = :idimpianto"),
    @NamedQuery(name = "Prezziario.findByPrezzo", query = "SELECT p FROM Prezziario p WHERE p.prezzo = :prezzo"),
    @NamedQuery(name = "Prezziario.findBySconto", query = "SELECT p FROM Prezziario p WHERE p.sconto = :sconto")})
public class Prezziario implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrezziarioPK prezziarioPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal prezzo;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int sconto;
    @PrimaryKeyJoinColumn(name = "idcampo", referencedColumnName = "idcampo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Campo campo;
    @PrimaryKeyJoinColumn(name = "idimpianto", referencedColumnName = "idimpianto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Impianto impianto;

    public Prezziario() {
    }

    public Prezziario(PrezziarioPK prezziarioPK) {
        this.prezziarioPK = prezziarioPK;
    }

    public Prezziario(PrezziarioPK prezziarioPK, BigDecimal prezzo, int sconto) {
        this.prezziarioPK = prezziarioPK;
        this.prezzo = prezzo;
        this.sconto = sconto;
    }

    public Prezziario(int idcampo, int idimpianto) {
        this.prezziarioPK = new PrezziarioPK(idcampo, idimpianto);
    }

    public PrezziarioPK getPrezziarioPK() {
        return prezziarioPK;
    }

    public void setPrezziarioPK(PrezziarioPK prezziarioPK) {
        this.prezziarioPK = prezziarioPK;
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

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
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
        hash += (prezziarioPK != null ? prezziarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prezziario)) {
            return false;
        }
        Prezziario other = (Prezziario) object;
        if ((this.prezziarioPK == null && other.prezziarioPK != null) || (this.prezziarioPK != null && !this.prezziarioPK.equals(other.prezziarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Prezziario[ prezziarioPK=" + prezziarioPK + " ]";
    }
    
}
