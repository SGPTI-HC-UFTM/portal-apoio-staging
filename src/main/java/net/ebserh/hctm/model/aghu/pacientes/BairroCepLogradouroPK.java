package net.ebserh.hctm.model.aghu.pacientes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BairroCepLogradouroPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "clo_lgr_codigo", insertable = false, updatable = false)
	private Integer cloLgrCodigo;
	
	@Column(name = "clo_cep", insertable = false, updatable = false)
	private Integer cloCep;
	
	@Column(name = "bai_codigo", insertable = false, updatable = false)
	private Integer baiCodigo;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baiCodigo == null) ? 0 : baiCodigo.hashCode());
		result = prime * result + ((cloCep == null) ? 0 : cloCep.hashCode());
		result = prime * result + ((cloLgrCodigo == null) ? 0 : cloLgrCodigo.hashCode());
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
		BairroCepLogradouroPK other = (BairroCepLogradouroPK) obj;
		if (baiCodigo == null) {
			if (other.baiCodigo != null)
				return false;
		} else if (!baiCodigo.equals(other.baiCodigo))
			return false;
		if (cloCep == null) {
			if (other.cloCep != null)
				return false;
		} else if (!cloCep.equals(other.cloCep))
			return false;
		if (cloLgrCodigo == null) {
			if (other.cloLgrCodigo != null)
				return false;
		} else if (!cloLgrCodigo.equals(other.cloLgrCodigo))
			return false;
		return true;
	}

	public Integer getCloLgrCodigo() {
		return cloLgrCodigo;
	}

	public void setCloLgrCodigo(Integer cloLgrCodigo) {
		this.cloLgrCodigo = cloLgrCodigo;
	}

	public Integer getCloCep() {
		return cloCep;
	}

	public void setCloCep(Integer cloCep) {
		this.cloCep = cloCep;
	}

	public Integer getBaiCodigo() {
		return baiCodigo;
	}

	public void setBaiCodigo(Integer baiCodigo) {
		this.baiCodigo = baiCodigo;
	}

}
