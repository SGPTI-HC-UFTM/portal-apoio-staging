package net.ebserh.hctm.model.cirurgias;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "cirurgia", name = "informacoes_complementares_procedimentos_cirurgicos")
@NamedQuery(name = "InformacoesComplementaresProcedimentoCirurgico.findByDadosProcedimento",
    query = "SELECT i "
        + "FROM InformacoesComplementaresProcedimentoCirurgico i "
        + "WHERE i.cirurgiaSeqAghu = :cirurgiaSeqAghu "
        + "AND i.especialidadeProcedimentoSeqAghu = :especialidadeProcedimentoSeqAghu "
        + "AND i.especialidadeProcedimentoEspSeqAghu = :especialidadeProcedimentoEspSeqAghu "
        + "AND i.respProcAghu = :respProcAghu")
public class InformacoesComplementaresProcedimentoCirurgico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "crg_seq_aghu")
    private Integer cirurgiaSeqAghu;

    @Column(name = "epr_pci_seq_aghu")
    private Integer especialidadeProcedimentoSeqAghu;

    @Column(name = "epr_esp_seq_aghu")
    private Integer especialidadeProcedimentoEspSeqAghu;

    @Column(name = "ind_resp_proc_aghu")
    private String respProcAghu;

    private String observacao;

    private Boolean realizada;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        InformacoesComplementaresProcedimentoCirurgico other = (InformacoesComplementaresProcedimentoCirurgico) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "InformacoesComplementaresProcedimentoCirurgico [id=" + id + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCirurgiaSeqAghu() {
        return cirurgiaSeqAghu;
    }

    public void setCirurgiaSeqAghu(Integer cirurgiaSeqAghu) {
        this.cirurgiaSeqAghu = cirurgiaSeqAghu;
    }

    public Integer getEspecialidadeProcedimentoSeqAghu() {
        return especialidadeProcedimentoSeqAghu;
    }

    public void setEspecialidadeProcedimentoSeqAghu(Integer especialidadeProcedimentoSeqAghu) {
        this.especialidadeProcedimentoSeqAghu = especialidadeProcedimentoSeqAghu;
    }

    public Integer getEspecialidadeProcedimentoEspSeqAghu() {
        return especialidadeProcedimentoEspSeqAghu;
    }

    public void setEspecialidadeProcedimentoEspSeqAghu(Integer especialidadeProcedimentoEspSeqAghu) {
        this.especialidadeProcedimentoEspSeqAghu = especialidadeProcedimentoEspSeqAghu;
    }

    public String getRespProcAghu() {
        return respProcAghu;
    }

    public void setRespProcAghu(String respProcAghu) {
        this.respProcAghu = respProcAghu;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Boolean getRealizada() {
        return realizada;
    }

    public void setRealizada(Boolean realizada) {
        this.realizada = realizada;
    }
    
}
