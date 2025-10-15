package net.ebserh.hctm.model.aghu.pacientes;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EnderecoPacientePK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="pac_codigo", insertable = false, updatable = false)
	protected Integer pacienteCodigo;
	
	@Column(name = "seqp")
	protected Integer seq;

	public EnderecoPacientePK() {
		
	}
	
	public EnderecoPacientePK(Integer pacienteCodigo, Integer seq) {
		super();
		this.pacienteCodigo = pacienteCodigo;
		this.seq = seq;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pacienteCodigo;
		result = prime * result + seq;
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
		EnderecoPacientePK other = (EnderecoPacientePK) obj;
		if (pacienteCodigo != other.pacienteCodigo)
			return false;
		if (seq != other.seq)
			return false;
		return true;
	}
	
}
