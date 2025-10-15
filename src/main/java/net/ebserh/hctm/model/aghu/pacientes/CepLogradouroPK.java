package net.ebserh.hctm.model.aghu.pacientes;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class CepLogradouroPK implements Serializable {

	private static final long serialVersionUID = 2476366134556488204L;
	
	@Column(name = "lgr_codigo", insertable = false, updatable = false)
	protected Integer codigoLogradouro;
	
	@Column(name = "cep", insertable = false, updatable = false)
	protected Integer cep;

	public CepLogradouroPK() {
		
	}
	
	public CepLogradouroPK(int codigoLogradouro, int cep) {
		super();
		this.codigoLogradouro = codigoLogradouro;
		this.cep = cep;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((codigoLogradouro == null) ? 0 : codigoLogradouro.hashCode());
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
		CepLogradouroPK other = (CepLogradouroPK) obj;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (codigoLogradouro == null) {
			if (other.codigoLogradouro != null)
				return false;
		} else if (!codigoLogradouro.equals(other.codigoLogradouro))
			return false;
		return true;
	}
	
	

}
