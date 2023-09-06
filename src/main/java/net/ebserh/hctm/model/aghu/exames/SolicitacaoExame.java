package net.ebserh.hctm.model.aghu.exames;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.ebserh.hctm.model.aghu.Atendimento;

@Entity
@Table(schema = "agh",
		name = "ael_solicitacao_exames")
public class SolicitacaoExame implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer seq;
	
	@ManyToOne
	@JoinColumn(name = "atd_seq")
	private Atendimento atendimento;

	/*
	 * Auto-generated
	 */
	@Override
	public int hashCode() {
		return Objects.hash(seq);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SolicitacaoExame other = (SolicitacaoExame) obj;
		return Objects.equals(seq, other.seq);
	}

	@Override
	public String toString() {
		return "SolicitacaoExame [seq=" + seq + "]";
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

}
