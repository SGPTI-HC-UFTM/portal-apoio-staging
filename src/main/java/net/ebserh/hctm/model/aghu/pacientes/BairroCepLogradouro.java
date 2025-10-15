package net.ebserh.hctm.model.aghu.pacientes;

import jakarta.persistence.Entity;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "agh",
		name = "aip_bairros_cep_logradouro")
public class BairroCepLogradouro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BairroCepLogradouroPK id;
	
	@ManyToOne
	@JoinColumn(name = "bai_codigo")
	private Bairro bairro;
	
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "clo_cep", referencedColumnName="cep"),
			@JoinColumn(name = "clo_lgr_codigo", referencedColumnName="lgr_codigo"),
	})
	private CepLogradouro cepLogradouro;

	public BairroCepLogradouroPK getId() {
		return id;
	}

	public void setId(BairroCepLogradouroPK id) {
		this.id = id;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public CepLogradouro getCepLogradouro() {
		return cepLogradouro;
	}

	public void setCepLogradouro(CepLogradouro cepLogradouro) {
		this.cepLogradouro = cepLogradouro;
	}

}
