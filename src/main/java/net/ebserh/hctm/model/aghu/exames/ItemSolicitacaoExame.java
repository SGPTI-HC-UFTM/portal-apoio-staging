package net.ebserh.hctm.model.aghu.exames;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "agh",
	name = "ael_item_solicitacao_exames")
public class ItemSolicitacaoExame implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ItemSolicitacaoExamePK itemSolicitacaoExamesPK;
	
	@ManyToOne
	@JoinColumn(name = "soe_seq", insertable = false, updatable = false)
	private SolicitacaoExame solicitacaoExame;
	
	@Column(insertable = false, updatable = false)
	private Integer seqp;
	
	@Column(name = "sit_codigo")
	private String sitCodigo;

	/*
	 * Auto-generated
	 */
	@Override
	public int hashCode() {
		return Objects.hash(itemSolicitacaoExamesPK);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemSolicitacaoExame other = (ItemSolicitacaoExame) obj;
		return Objects.equals(itemSolicitacaoExamesPK, other.itemSolicitacaoExamesPK);
	}

	@Override
	public String toString() {
		return "ItemSolicitacaoExames [itemSolicitacaoExamesPK=" + itemSolicitacaoExamesPK + "]";
	}

	public ItemSolicitacaoExamePK getItemSolicitacaoExamesPK() {
		return itemSolicitacaoExamesPK;
	}

	public void setItemSolicitacaoExamesPK(ItemSolicitacaoExamePK itemSolicitacaoExamesPK) {
		this.itemSolicitacaoExamesPK = itemSolicitacaoExamesPK;
	}

	public SolicitacaoExame getSolicitacaoExame() {
		return solicitacaoExame;
	}

	public void setSolicitacaoExame(SolicitacaoExame solicitacaoExame) {
		this.solicitacaoExame = solicitacaoExame;
	}

	public Integer getSeqp() {
		return seqp;
	}

	public void setSeqp(Integer seqp) {
		this.seqp = seqp;
	}

	public String getSitCodigo() {
		return sitCodigo;
	}

	public void setSitCodigo(String sitCodigo) {
		this.sitCodigo = sitCodigo;
	}

}
