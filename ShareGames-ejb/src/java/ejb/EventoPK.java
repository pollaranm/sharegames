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
import javax.validation.constraints.Size;

/**
 *
 * @author Alex
 */
@Embeddable
public class EventoPK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int idevento;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false,unique = true)
    
    private int idimpianto;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int idcampo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 10)
    private String data;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String ora;

    public EventoPK() {
    }

    public EventoPK(int idevento, int idimpianto, int idcampo, String data, String ora) {
        this.idevento = idevento;
        this.idimpianto = idimpianto;
        this.idcampo = idcampo;
        this.data = data;
        this.ora = ora;
    }

    public int getIdevento() {
        return idevento;
    }

    public void setIdevento(int idevento) {
        this.idevento = idevento;
    }

    public int getIdimpianto() {
        return idimpianto;
    }

    public void setIdimpianto(int idimpianto) {
        this.idimpianto = idimpianto;
    }

    public int getIdcampo() {
        return idcampo;
    }

    public void setIdcampo(int idcampo) {
        this.idcampo = idcampo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idevento;
        hash += (int) idimpianto;
        hash += (int) idcampo;
        hash += (data != null ? data.hashCode() : 0);
        hash += (ora != null ? ora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventoPK)) {
            return false;
        }
        EventoPK other = (EventoPK) object;
        if (this.idevento != other.idevento) {
            return false;
        }
        if (this.idimpianto != other.idimpianto) {
            return false;
        }
        if (this.idcampo != other.idcampo) {
            return false;
        }
        if ((this.data == null && other.data != null) || (this.data != null && !this.data.equals(other.data))) {
            return false;
        }
        if ((this.ora == null && other.ora != null) || (this.ora != null && !this.ora.equals(other.ora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.EventoPK[ idevento=" + idevento + ", idimpianto=" + idimpianto + ", idcampo=" + idcampo + ", data=" + data + ", ora=" + ora + " ]";
    }
    
}
