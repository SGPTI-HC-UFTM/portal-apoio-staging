package net.ebserh.hctm.model.aghu.pacientes;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "agh.aip_cep_logradouros")
public class CepLogradouro {
	
	@EmbeddedId
	private CepLogradouroPK cepLogradouroPk;
	
	@ManyToOne
	@JoinColumn(name = "lgr_codigo")
	private Logradouro logradouro;
	
	@Column(name = "cep")
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
