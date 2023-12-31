package net.ebserh.hctm.model.aghu.pacientes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "agh.aip_tipo_logradouros")
public class TipoLogradouro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "codigo")
	private Integer codigo;
	
	@Column(name = "descricao")
	private String descricao;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
