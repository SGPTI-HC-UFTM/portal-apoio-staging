package net.ebserh.hctm.model.aghu.faturamento;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ItemProcedimentoHospitalarPK implements Serializable {

    @Column(name = "pho_seq")
    private Integer phoSeq;

    private Integer seq;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((phoSeq == null) ? 0 : phoSeq.hashCode());
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
        ItemProcedimentoHospitalarPK other = (ItemProcedimentoHospitalarPK) obj;
        if (phoSeq == null) {
            if (other.phoSeq != null)
                return false;
        } else if (!phoSeq.equals(other.phoSeq))
            return false;
        if (seq == null) {
            if (other.seq != null)
                return false;
        } else if (!seq.equals(other.seq))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ItemProcedimentoHospitalarPK [phoSeq=" + phoSeq + ", seq=" + seq + "]";
    }

    public Integer getPhoSeq() {
        return phoSeq;
    }

    public void setPhoSeq(Integer phoSeq) {
        this.phoSeq = phoSeq;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    } 

}
