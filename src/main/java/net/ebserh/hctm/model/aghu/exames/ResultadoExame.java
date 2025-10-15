package net.ebserh.hctm.model.aghu.exames;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "agh",
		name = "ael_resultados_exames")
public class ResultadoExame implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ResultadoExamePK resultadoExamePK;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "ise_soe_seq", 
			referencedColumnName = "soe_seq", 
			insertable = false, 
			updatable = false),
		@JoinColumn(name = "ise_seqp",
			referencedColumnName = "seqp",
			insertable = false,
			updatable = false)
	})
	private ItemSolicitacaoExame itemSolicitacaoExame;
	
	@Column(name = "ind_anulacao_laudo")
	private String indAnulacaoLaudo;

	/*
	 * Auto-generated
	 */
	@Override
	public String toString() {
		return "ResultadoExame [resultadoExamePK=" + resultadoExamePK + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(resultadoExamePK);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultadoExame other = (ResultadoExame) obj;
		return Objects.equals(resultadoExamePK, other.resultadoExamePK);
	}

	public ResultadoExamePK getResultadoExamePK() {
		return resultadoExamePK;
	}

	public void setResultadoExamePK(ResultadoExamePK resultadoExamePK) {
		this.resultadoExamePK = resultadoExamePK;
	}

	public ItemSolicitacaoExame getItemSolicitacaoExame() {
		return itemSolicitacaoExame;
	}

	public void setItemSolicitacaoExame(ItemSolicitacaoExame itemSolicitacaoExame) {
		this.itemSolicitacaoExame = itemSolicitacaoExame;
	}

	public String getIndAnulacaoLaudo() {
		return indAnulacaoLaudo;
	}

	public void setIndAnulacaoLaudo(String indAnulacaoLaudo) {
		this.indAnulacaoLaudo = indAnulacaoLaudo;
	}

}
