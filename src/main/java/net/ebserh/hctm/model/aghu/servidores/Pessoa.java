package net.ebserh.hctm.model.aghu.servidores;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "agh.rap_pessoas_fisicas")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer codigo;

	private String nome;

	@Transient
	private Qualificacao qualificacao;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Qualificacao getQualificacao() {
		return this.qualificacao;
	}

	public void setQualificacao(Qualificacao qualificacao) {
		this.qualificacao = qualificacao;
	}
}
