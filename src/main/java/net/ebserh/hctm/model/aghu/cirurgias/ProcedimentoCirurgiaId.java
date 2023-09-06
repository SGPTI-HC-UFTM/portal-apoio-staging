package net.ebserh.hctm.model.aghu.cirurgias;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProcedimentoCirurgiaId implements Serializable {

    @Column(name = "crg_seq", insertable = false, updatable = false)
    private Integer cirurgiaSeq;

    @Column(name = "epr_pci_seq", insertable = false, updatable = false)
    private Integer especialidadeProcedimentoSeq;

    @Column(name = "epr_esp_seq", insertable = false, updatable = false)
    private Integer especialidadeProcedimentoEspSeq;

    @Column(name = "ind_resp_proc", insertable = false, updatable = false)
    private String respProc;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cirurgiaSeq == null) ? 0 : cirurgiaSeq.hashCode());
        result = prime * result
                + ((especialidadeProcedimentoSeq == null) ? 0 : especialidadeProcedimentoSeq.hashCode());
        result = prime * result
                + ((especialidadeProcedimentoEspSeq == null) ? 0 : especialidadeProcedimentoEspSeq.hashCode());
        result = prime * result + ((respProc == null) ? 0 : respProc.hashCode());
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
        ProcedimentoCirurgiaId other = (ProcedimentoCirurgiaId) obj;
        if (cirurgiaSeq == null) {
            if (other.cirurgiaSeq != null)
                return false;
        } else if (!cirurgiaSeq.equals(other.cirurgiaSeq))
            return false;
        if (especialidadeProcedimentoSeq == null) {
            if (other.especialidadeProcedimentoSeq != null)
                return false;
        } else if (!especialidadeProcedimentoSeq.equals(other.especialidadeProcedimentoSeq))
            return false;
        if (especialidadeProcedimentoEspSeq == null) {
            if (other.especialidadeProcedimentoEspSeq != null)
                return false;
        } else if (!especialidadeProcedimentoEspSeq.equals(other.especialidadeProcedimentoEspSeq))
            return false;
        if (respProc == null) {
            if (other.respProc != null)
                return false;
        } else if (!respProc.equals(other.respProc))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ProcedimentoCirurgiaId [cirurgiaSeq=" + cirurgiaSeq + ", especialidadeProcedimentoSeq="
                + especialidadeProcedimentoSeq + ", especialidadeProcedimentoEspSeq=" + especialidadeProcedimentoEspSeq
                + ", respProc=" + respProc + "]";
    }

    public Integer getCirurgiaSeq() {
        return cirurgiaSeq;
    }

    public void setCirurgiaSeq(Integer cirurgiaSeq) {
        this.cirurgiaSeq = cirurgiaSeq;
    }

    public Integer getEspecialidadeProcedimentoSeq() {
        return especialidadeProcedimentoSeq;
    }

    public void setEspecialidadeProcedimentoSeq(Integer especialidadeProcedimentoSeq) {
        this.especialidadeProcedimentoSeq = especialidadeProcedimentoSeq;
    }

    public Integer getEspecialidadeProcedimentoEspSeq() {
        return especialidadeProcedimentoEspSeq;
    }

    public void setEspecialidadeProcedimentoEspSeq(Integer especialidadeProcedimentoEspSeq) {
        this.especialidadeProcedimentoEspSeq = especialidadeProcedimentoEspSeq;
    }

    public String getRespProc() {
        return respProc;
    }

    public void setRespProc(String respProc) {
        this.respProc = respProc;
    }
   
}
