package net.ebserh.hctm.model.enfermagem;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(schema = "enfermagem", name = "alergias_medicamentos")
public class AlergiaMedicamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@MapsId
	@OneToOne
	@JoinColumn(name = "id")
	private Evolucao evolucao;
	
	@Size(max = 2)
	private String alergias;
	
	@Column(name = "alergias_obs")
	@Size(max = 64)
	private String alergiasObs;
	
	@Column(name = "medicamentos_vigilancia")
	@Size(max = 250)
	private String medicamentosVigilancia;

	/*
	 * Auto-generated
	 */
	@Override
	public String toString() {
		return "AlergiaMedicamento [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlergiaMedicamento other = (AlergiaMedicamento) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Evolucao getEvolucao() {
		return evolucao;
	}

	public void setEvolucao(Evolucao evolucao) {
		this.evolucao = evolucao;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public String getAlergiasObs() {
		return alergiasObs;
	}

	public void setAlergiasObs(String alergiasObs) {
		this.alergiasObs = alergiasObs;
	}

	public String getMedicamentosVigilancia() {
		return medicamentosVigilancia;
	}

	public void setMedicamentosVigilancia(String medicamentosVigilancia) {
		this.medicamentosVigilancia = medicamentosVigilancia;
	}

}
