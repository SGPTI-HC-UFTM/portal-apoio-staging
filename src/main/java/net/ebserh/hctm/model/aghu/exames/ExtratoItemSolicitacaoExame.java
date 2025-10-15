package net.ebserh.hctm.model.aghu.exames;

import java.io.Serializable;
import java.time.LocalDateTime;
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
		name = "ael_extrato_item_solics")
public class ExtratoItemSolicitacaoExame implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ExtratoItemSolicitacaoExamePK extratoItemSolicitacaoExamePK;
	
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
	
	@Column(name = "sit_codigo")
	private String sitCodigo;
	
	@Column(name = "dthr_evento")
	private LocalDateTime dthrEvento;

	/*
	 * Auto-generated
	 */
	@Override
	public int hashCode() {
		return Objects.hash(extratoItemSolicitacaoExamePK);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExtratoItemSolicitacaoExame other = (ExtratoItemSolicitacaoExame) obj;
		return Objects.equals(extratoItemSolicitacaoExamePK, other.extratoItemSolicitacaoExamePK);
	}

	@Override
	public String toString() {
		return "ExtratoItemSolicitacaoExame [extratoItemSolicitacaoExamePK=" + extratoItemSolicitacaoExamePK + "]";
	}

	public ExtratoItemSolicitacaoExamePK getExtratoItemSolicitacaoExamePK() {
		return extratoItemSolicitacaoExamePK;
	}

	public void setExtratoItemSolicitacaoExamePK(ExtratoItemSolicitacaoExamePK extratoItemSolicitacaoExamePK) {
		this.extratoItemSolicitacaoExamePK = extratoItemSolicitacaoExamePK;
	}

	public ItemSolicitacaoExame getItemSolicitacaoExame() {
		return itemSolicitacaoExame;
	}

	public void setItemSolicitacaoExame(ItemSolicitacaoExame itemSolicitacaoExame) {
		this.itemSolicitacaoExame = itemSolicitacaoExame;
	}

	public String getSitCodigo() {
		return sitCodigo;
	}

	public void setSitCodigo(String sitCodigo) {
		this.sitCodigo = sitCodigo;
	}

	public LocalDateTime getDthrEvento() {
		return dthrEvento;
	}

	public void setDthrEvento(LocalDateTime dthrEvento) {
		this.dthrEvento = dthrEvento;
	}

}
