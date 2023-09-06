package net.ebserh.hctm.model.aghu.pacientes;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "agh.aip_cep_logradouros")
public class CepLogradouro {
	
	@EmbeddedId
	private CepLogradouroPK cepLogradouroPk;
	
	@ManyToOne
	@JoinColumn(name = "lgr_codigo", insertable = false, updatable = false)
	private Logradouro logradouro;
	
	@Column(name = "cep", insertable = false, updatable = false)
	private Integer cep;

	public CepLogradouroPK getCepLogradouroPk() {
		return cepLogradouroPk;
	}

	public void setCepLogradouroPk(CepLogradouroPK cepLogradouroPk) {
		this.cepLogradouroPk = cepLogradouroPk;
	}

	public Integer getCep() {
	
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}
	
}
