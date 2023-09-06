package net.ebserh.hctm.model.aghu.pacientes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "agh.aip_logradouros")
public class Logradouro {
	
	@Id
	@Column(name = "codigo", unique = true, nullable = false)
	private Integer codigo;
	
	@ManyToOne
	@JoinColumn(name = "cdd_codigo")
	private Cidade cidade;

	@ManyToOne
	@JoinColumn(name = "tlg_codigo")
	private TipoLogradouro tipoLogradouro;
	
	@Column(name = "nome")
	private String nome;
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}