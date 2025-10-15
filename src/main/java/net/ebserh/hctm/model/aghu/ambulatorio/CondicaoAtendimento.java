package net.ebserh.hctm.model.aghu.ambulatorio;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(schema = "agh", name = "aac_condicao_atendimentos")
@NamedQuery(name = "CondicaoAtendimento.findByDescricao",
    query = "SELECT c " +
        "FROM CondicaoAtendimento c " +
        "WHERE c.descricao = :descricao")
public class CondicaoAtendimento implements Serializable {

    public static final String RECONSULTA = "RECONSULTA";

    public static final String INTERCONSULTA = "INTERCONSULTA";

	@Id
	private Integer seq;
	
	private String descricao;

	/*
	 * Auto-generated
	 */
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
        CondicaoAtendimento other = (CondicaoAtendimento) obj;
        if (seq == null) {
            if (other.seq != null)
                return false;
        } else if (!seq.equals(other.seq))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CondicaoAtendimento [seq=" + seq + "]";
    }

    public Integer getSeq() {
		return seq;
	}

    public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
