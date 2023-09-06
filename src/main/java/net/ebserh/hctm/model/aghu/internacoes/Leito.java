package net.ebserh.hctm.model.aghu.internacoes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import net.ebserh.hctm.model.aghu.UnidadeFuncional;

@Entity
@Table(name = "agh.ain_leitos")
@NamedQuery(name = "Leito.findAll",
	query = "SELECT l "
		+ "FROM Leito l "
		+ "ORDER BY l.id")
public class Leito implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "lto_id")
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "unf_seq")
	private UnidadeFuncional unidadeFuncional;

	
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
		Leito other = (Leito) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UnidadeFuncional getUnidadeFuncional() {
		return unidadeFuncional;
	}

	public void setUnidadeFuncional(UnidadeFuncional unidadeFuncional) {
		this.unidadeFuncional = unidadeFuncional;
	}
	
}
