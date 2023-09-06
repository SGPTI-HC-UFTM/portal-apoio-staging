package net.ebserh.hctm.model.aghu.prescricoes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ItemPrescricaoDietaPK implements Serializable {

    @Column(name = "pdt_atd_seq", insertable = false, updatable = false)
    private Integer pdtAtdSeq;

    @Column(name = "pdt_seq", insertable = false, updatable = false)
    private Integer pdtSeq;

    @Column(name = "tid_seq", insertable = false, updatable = false)
    private Integer tidSeq;

    @Override
    public String toString() {
        return "ItemPrescricaoDietaPK [pdtAtdSeq=" + pdtAtdSeq + ", pdtSeq=" + pdtSeq + ", tidSeq=" + tidSeq + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pdtAtdSeq == null) ? 0 : pdtAtdSeq.hashCode());
        result = prime * result + ((pdtSeq == null) ? 0 : pdtSeq.hashCode());
        result = prime * result + ((tidSeq == null) ? 0 : tidSeq.hashCode());
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
        ItemPrescricaoDietaPK other = (ItemPrescricaoDietaPK) obj;
        if (pdtAtdSeq == null) {
            if (other.pdtAtdSeq != null)
                return false;
        } else if (!pdtAtdSeq.equals(other.pdtAtdSeq))
            return false;
        if (pdtSeq == null) {
            if (other.pdtSeq != null)
                return false;
        } else if (!pdtSeq.equals(other.pdtSeq))
            return false;
        if (tidSeq == null) {
            if (other.tidSeq != null)
                return false;
        } else if (!tidSeq.equals(other.tidSeq))
            return false;
        return true;
    }

    public Integer getPdtAtdSeq() {
        return pdtAtdSeq;
    }

    public void setPdtAtdSeq(Integer pdtAtdSeq) {
        this.pdtAtdSeq = pdtAtdSeq;
    }

    public Integer getPdtSeq() {
        return pdtSeq;
    }

    public void setPdtSeq(Integer pdtSeq) {
        this.pdtSeq = pdtSeq;
    }

    public Integer getTidSeq() {
        return tidSeq;
    }

    public void setTidSeq(Integer tidSeq) {
        this.tidSeq = tidSeq;
    }
    
}
