package net.ebserh.hctm.model.aghu.exames;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ResultadoExamePK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "ise_soe_seq")
	private Integer solicitacaoSeq;
	
	@Column(name = "ise_seqp")
	private Integer itemSolicitacaoSeq;
	
	@Column(name = "pcl_cel_ema_exa_sigla")
	private String exameSigla;
	
	@Column(name = "pcl_vel_ema_man_seq")
	private Integer materialAnaliseSeq;
	
	@Column(name = "pcl_vel_seqp")
	private Integer versaoLaudoSeq;
	
	@Column(name = "pcl_cal_seq")
	private Integer campoLaudoSeq;
	
	@Column(name = "pcl_seqp")
	private Integer parametroCampoLaudoSeq;
	
	private Integer seqp;

	@Override
	public int hashCode() {
		return Objects.hash(campoLaudoSeq, exameSigla, itemSolicitacaoSeq, materialAnaliseSeq, parametroCampoLaudoSeq,
				seqp, solicitacaoSeq, versaoLaudoSeq);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultadoExamePK other = (ResultadoExamePK) obj;
		return Objects.equals(campoLaudoSeq, other.campoLaudoSeq) && Objects.equals(exameSigla, other.exameSigla)
				&& Objects.equals(itemSolicitacaoSeq, other.itemSolicitacaoSeq)
				&& Objects.equals(materialAnaliseSeq, other.materialAnaliseSeq)
				&& Objects.equals(parametroCampoLaudoSeq, other.parametroCampoLaudoSeq)
				&& Objects.equals(seqp, other.seqp) && Objects.equals(solicitacaoSeq, other.solicitacaoSeq)
				&& Objects.equals(versaoLaudoSeq, other.versaoLaudoSeq);
	}

}
