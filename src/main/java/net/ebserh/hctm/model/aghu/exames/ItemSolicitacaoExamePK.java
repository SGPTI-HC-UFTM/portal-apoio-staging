package net.ebserh.hctm.model.aghu.exames;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ItemSolicitacaoExamePK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "soe_seq")
	private Integer soeSeq;
	
	private Integer seqp;

	@Override
	public int hashCode() {
		return Objects.hash(seqp, soeSeq);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemSolicitacaoExamePK other = (ItemSolicitacaoExamePK) obj;
		return Objects.equals(seqp, other.seqp) && Objects.equals(soeSeq, other.soeSeq);
	}

}
