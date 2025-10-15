package net.ebserh.hctm.model.aghu.ambulatorio;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "agh", name = "agh_equipes")
public class Equipe implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Integer seq;
	
	private String nome;

	/*
	 * Auto-generated
	 */
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
