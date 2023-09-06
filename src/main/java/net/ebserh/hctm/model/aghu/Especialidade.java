package net.ebserh.hctm.model.aghu;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "agh", name = "agh_especialidades")
@NamedQuery(name = "Especialidade.findAtivas", 
	query = "SELECT e " 
		+ "FROM Especialidade e " 
		+ "WHERE e.situacao = 'A' " 
		+ "ORDER BY e.nome")
@NamedQuery(name = "Especialidade.findByNomeLike", 
	query = "SELECT e "
		+ "FROM Especialidade e "
		+ "WHERE UPPER(e.nome) = :nome "
		+ "ORDER BY e.nome")
@NamedQuery(name = "Especialidade.findBySituacao", 
	query = "SELECT e "
		+ "FROM Especialidade e "
		+ "WHERE e.situacao = :situacao "
		+ "ORDER BY e.nome")
@NamedQuery(name = "Especialidade.findBySigla", 
	query = "SELECT e "
		+ "FROM Especialidade e "
		+ "WHERE UPPER(e.sigla) = :sigla ")
@NamedQuery(name = "Especialidade.findBySeqIn",
	query = "SELECT e "
		+ "FROM Especialidade e "
		+ "WHERE e.seq IN :seqs "
		+ "ORDER BY e.nome")
public class Especialidade implements Serializable {

	@Id
	private Integer seq;

	private String sigla;

	@Column(name = "nome_especialidade")
	private String nome;

	@Column(name = "ind_situacao")
	private String situacao;

	/*
	 * Auto-generated
	 */
	@Override
	public int hashCode() {
		return Objects.hash(seq);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Especialidade other = (Especialidade) obj;
		return Objects.equals(seq, other.seq);
	}

	@Override
	public String toString() {
		return "Especialidade [seq=" + seq + "]";
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}
