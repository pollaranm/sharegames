/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Alex
 */
@Embeddable
public class PrezziarioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int idcampo;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int idimpianto;

    public PrezziarioPK() {
    }

    public PrezziarioPK(int idcampo, int idimpianto) {
        this.idcampo = idcampo;
        this.idimpianto = idimpianto;
    }

    public int getIdcampo() {
        return idcampo;
    }

    public void setIdcampo(int idcampo) {
        this.idcampo = idcampo;
    }

    public int getIdimpianto() {
        return idimpianto;
    }

    public void setIdimpianto(int idimpianto) {
        this.idimpianto = idimpianto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcampo;
        hash += (int) idimpianto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrezziarioPK)) {
            return false;
        }
        PrezziarioPK other = (PrezziarioPK) object;
        if (this.idcampo != other.idcampo) {
            return false;
        }
        if (this.idimpianto != other.idimpianto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.PrezziarioPK[ idcampo=" + idcampo + ", idimpianto=" + idimpianto + " ]";
    }
    
}
