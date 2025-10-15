package net.ebserh.hctm.model.aghu;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(schema = "agh", name = "agh_unidades_funcionais")
@NamedQuery(name = "UnidadeFuncional.findAtivas",
	query = "SELECT u "
		+ "FROM UnidadeFuncional u "
		+ "WHERE u.situacao = 'A' "
		+ "ORDER BY u.descricao")
public class UnidadeFuncional implements Serializable {

	public static final String SIGLA_PS_ADULTO = "PSA";

	@Id
	private Integer seq;
	
	private String sigla;
	
	private String descricao;
	
	@Column(name="ind_sit_unid_func")
	private String situacao;

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
		UnidadeFuncional other = (UnidadeFuncional) obj;
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

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
}
