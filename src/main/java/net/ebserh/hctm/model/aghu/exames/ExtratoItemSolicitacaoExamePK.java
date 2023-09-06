package net.ebserh.hctm.model.aghu.exames;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExtratoItemSolicitacaoExamePK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "ise_soe_seq")
	private Integer iseSoeSeq;
	
	@Column(name = "ise_seqp")
	private Integer iseSeqp;
	
	private Integer seqp;

	@Override
	public int hashCode() {
		return Objects.hash(iseSeqp, iseSoeSeq, seqp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExtratoItemSolicitacaoExamePK other = (ExtratoItemSolicitacaoExamePK) obj;
		return Objects.equals(iseSeqp, other.iseSeqp) && Objects.equals(iseSoeSeq, other.iseSoeSeq)
				&& Objects.equals(seqp, other.seqp);
	}

}
