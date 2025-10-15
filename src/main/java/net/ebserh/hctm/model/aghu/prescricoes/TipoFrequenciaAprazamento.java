package net.ebserh.hctm.model.aghu.prescricoes;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(schema = "agh", name = "mpm_tipo_freq_aprazamentos")
@NamedQuery(name = "TipoFrequenciaAprazamento.findAll",
    query = "SELECT t " +
        "FROM TipoFrequenciaAprazamento t " +
        "ORDER BY t.descricao")
public class TipoFrequenciaAprazamento implements Serializable {

    @Id
    private Integer seq;
    
    private String sigla;

    private String descricao;

    @Override
    public String toString() {
        return "TipoFrequenciaAprazamento [seq=" + seq + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        TipoFrequenciaAprazamento other = (TipoFrequenciaAprazamento) obj;
        if (seq == null) {
            if (other.seq != null)
                return false;
        } else if (!seq.equals(other.seq))
            return false;
        return true;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
