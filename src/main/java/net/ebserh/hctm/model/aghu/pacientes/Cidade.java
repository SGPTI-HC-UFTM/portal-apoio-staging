package net.ebserh.hctm.model.aghu.pacientes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "agh",
	name = "aip_cidades")
public class Cidade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Integer codigo;
	
	@Column(name = "cod_ibge")
	private Integer codigoIbge;

	private String nome;
	
	@Column(name = "uf_sigla")
	private String uf;
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(Integer codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
}
