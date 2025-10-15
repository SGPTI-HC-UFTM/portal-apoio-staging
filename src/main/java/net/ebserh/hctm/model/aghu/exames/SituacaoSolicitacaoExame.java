package net.ebserh.hctm.model.aghu.exames;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "agh",
		name = "ael_sit_item_solicitacoes")
public class SituacaoSolicitacaoExame implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer codigo;
	
	private String descricao;

	/*
	 * Auto-generated
	 */
	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SituacaoSolicitacaoExame other = (SituacaoSolicitacaoExame) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	@Override
	public String toString() {
		return "SituacaoSolicitacaoExame [codigo=" + codigo + "]";
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
