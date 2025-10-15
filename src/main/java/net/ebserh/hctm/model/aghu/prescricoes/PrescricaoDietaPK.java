package net.ebserh.hctm.model.aghu.prescricoes;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PrescricaoDietaPK implements Serializable {

    @Column(name = "atd_seq", insertable = false, updatable = false)
    private Integer atdSeq;

    @Column(name = "seq")
    private Integer seq;

    @Override
    public String toString() {
        return "PrescricaoDietaPK [atdSeq=" + atdSeq + ", seq=" + seq + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((atdSeq == null) ? 0 : atdSeq.hashCode());
        result = prime * result + ((seq == null) ? 0 : seq.hashCode());
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
        PrescricaoDietaPK other = (PrescricaoDietaPK) obj;
        if (atdSeq == null) {
            if (other.atdSeq != null)
                return false;
        } else if (!atdSeq.equals(other.atdSeq))
            return false;
        if (seq == null) {
            if (other.seq != null)
                return false;
        } else if (!seq.equals(other.seq))
            return false;
        return true;
    }

    public Integer getAtdSeq() {
        return atdSeq;
    }

    public void setAtdSeq(Integer atdSeq) {
        this.atdSeq = atdSeq;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    
}
