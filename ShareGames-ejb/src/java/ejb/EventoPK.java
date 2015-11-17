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
    @Column(name = "idevento")
    private int idevento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "data")
    private String data;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ora")
    private String ora;

    public EventoPK() {
    }

    public EventoPK(int idevento, String data, String ora) {
        this.idevento = idevento;
        this.data = data;
        this.ora = ora;
    }

    public int getIdevento() {
        return idevento;
    }

    public void setIdevento(int idevento) {
        this.idevento = idevento;
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
        return "ejb.EventoPK[ idevento=" + idevento + ", data=" + data + ", ora=" + ora + " ]";
    }
    
}
