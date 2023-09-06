package net.ebserh.hctm.model.aghu;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.ebserh.hctm.model.aghu.pacientes.Paciente;

@Entity	
@Table(schema = "agh", name = "agh_atendimentos")
public class Atendimento implements Serializable {

	@Id
	private Integer seq;
	
	private String origem;

	@ManyToOne
	@JoinColumn(name = "pac_codigo")
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "unf_seq")
	private UnidadeFuncional unidadeFuncional;

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
		Atendimento other = (Atendimento) obj;
		return Objects.equals(seq, other.seq);
	}

	@Override
	public String toString() {
		return "Atendimento [seq=" + seq + "]";
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public UnidadeFuncional getUnidadeFuncional() {
		return unidadeFuncional;
	}

	public void setUnidadeFuncional(UnidadeFuncional unidadeFuncional) {
		this.unidadeFuncional = unidadeFuncional;
	}

}
