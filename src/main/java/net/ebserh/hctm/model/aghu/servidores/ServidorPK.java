package net.ebserh.hctm.model.aghu.servidores;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ServidorPK implements Serializable {

	private Integer matricula;

	@Column(name = "vin_codigo")
	private Integer vinCodigo;
	
	public ServidorPK() {
		
	}
	
	public ServidorPK(Integer vinCodigo, Integer matricula) {
		this.vinCodigo = vinCodigo;
		this.matricula = matricula;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((vinCodigo == null) ? 0 : vinCodigo.hashCode());
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
		ServidorPK other = (ServidorPK) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (vinCodigo == null) {
			if (other.vinCodigo != null)
				return false;
		} else if (!vinCodigo.equals(other.vinCodigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServidorPK [matricula=" + matricula + ", vinCodigo=" + vinCodigo + "]";
	}


	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public Integer getVinCodigo() {
		return vinCodigo;
	}

	public void setVinCodigo(Integer vinCodigo) {
		this.vinCodigo = vinCodigo;
	}

}
