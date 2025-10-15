package net.ebserh.hctm.model.aghu.cirurgias;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EspecialidadeProcedimentoCirurgiaId implements Serializable {

    @Column(name = "pci_seq", insertable = false, updatable = false)
    private Integer pciSeq;

    @Column(name = "esp_seq")
    private Integer espSeq;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pciSeq == null) ? 0 : pciSeq.hashCode());
        result = prime * result + ((espSeq == null) ? 0 : espSeq.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EspecialidadeProcedimentoCirurgiaId other = (EspecialidadeProcedimentoCirurgiaId) obj;
        if (pciSeq == null) {
            if (other.pciSeq != null)
                return false;
        } else if (!pciSeq.equals(other.pciSeq))
            return false;
        if (espSeq == null) {
            if (other.espSeq != null)
                return false;
        } else if (!espSeq.equals(other.espSeq))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "EspecialidadeProcedimentoCirurgiaId [pciSeq=" + pciSeq + ", espSeq=" + espSeq + "]";
    }

    public Integer getPciSeq() {
        return pciSeq;
    }

    public void setPciSeq(Integer pciSeq) {
        this.pciSeq = pciSeq;
    }

    public Integer getEspSeq() {
        return espSeq;
    }

    public void setEspSeq(Integer espSeq) {
        this.espSeq = espSeq;
    }
    
}
