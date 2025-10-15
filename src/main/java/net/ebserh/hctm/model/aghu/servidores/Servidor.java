package net.ebserh.hctm.model.aghu.servidores;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(schema = "agh", name = "rap_servidores")
@NamedQuery(name = "Servidor.findAtivosByMatriculaContains", query = "SELECT s "
		+ "FROM Servidor s "
		+ "JOIN s.pessoa p "
		+ "WHERE FUNCTION('STR', s.id.matricula) LIKE FUNCTION('CONCAT', '%', :matricula, '%') "
		+ "AND s.situacao <> 'I' "
		+ "ORDER BY p.nome")
@NamedQuery(name = "Servidor.findAtivosByNomeContains", query = "SELECT s "
		+ "FROM Servidor s "
		+ "JOIN s.pessoa p "
		+ "WHERE FUNCTION('UPPER', p.nome) LIKE FUNCTION('CONCAT', '%', FUNCTION('UPPER', :nome), '%') "
		+ "AND s.situacao <> 'I' "
		+ "ORDER BY p.nome")
@NamedQuery(name = "Servidor.findAtivosByMatriculaContainsNomeContains", query = "SELECT s "
		+ "FROM Servidor s "
		+ "JOIN s.pessoa p "
		+ "WHERE FUNCTION('STR', s.id.matricula) LIKE FUNCTION('CONCAT', '%', :matricula, '%') "
		+ "AND FUNCTION('UPPER', p.nome) LIKE FUNCTION('CONCAT', '%', FUNCTION('UPPER', :nome), '%') "
		+ "AND s.situacao <> 'I' "
		+ "ORDER BY p.nome")
@NamedQuery(name = "Servidor.findByCodigoPessoa", query = "SELECT s FROM Servidor s "
		+ "JOIN s.pessoa p "
		+ "WHERE p.codigo = :codigo")
public class Servidor implements Serializable {

	@EmbeddedId
	private ServidorPK id;

	private String usuario;

	@Column(name = "oca_car_codigo")
	private String codigoCargo;

	@Column(name = "ind_situacao")
	private String situacao;

	@ManyToOne
	@JoinColumn(name = "pes_codigo")
	private Pessoa pessoa;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Servidor other = (Servidor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Servidor [id=" + id + "]";
	}

	public ServidorPK getId() {
		return id;
	}

	public void setId(ServidorPK id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCodigoCargo() {
		return codigoCargo;
	}

	public void setCodigoCargo(String codigoCargo) {
		this.codigoCargo = codigoCargo;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}
