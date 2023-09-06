package net.ebserh.hctm.model.aghu;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "agh", name = "agh_cids")
@NamedQuery(name = "Cid.findAll", 
	query = "SELECT c "
		+ "FROM Cid c "
		+ "ORDER BY c.descricao")
@NamedQuery(name = "Cid.findAllWhereIn", 
	query = "SELECT c "
		+ "FROM Cid c "
		+ "WHERE c.codigo IN (:cids)"
		+ "ORDER BY c.descricao")
@NamedQuery(name = "Cid.findByDescricao", 
	query = "SELECT c "
		+ "from Cid c "
		+ "WHERE descricao like concat(:descricao, '%') "
		+ "ORDER BY descricao")
@NamedQuery(name = "Cid.findByCodigo", 
	query = "SELECT c "
		+ "FROM Cid c "
		+ "WHERE c.codigo = :codigo")
@NamedQuery(name = "Cid.findById", 
	query = "SELECT c "
		+ "FROM Cid c "
		+ "WHERE c.id = :id")

public class Cid implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "seq")
	private Integer id;

	private String codigo;

	private String descricao;

	/*
	 * Auto-generated
	 */
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
		Cid other = (Cid) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
