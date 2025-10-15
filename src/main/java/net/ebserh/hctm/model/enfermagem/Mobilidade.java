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
@Table(schema = "enfermagem", name = "mobilidade")
public class Mobilidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@MapsId
	@OneToOne
	@JoinColumn(name = "id")
	private Evolucao evolucao;
	
	@Size(max = 2)
	private String morse;
	
	@Size(max = 2)
	private String locomocao;
	
	@Column(name = "locomocao_obs")
	@Size(max = 64)
	private String locomocaoObs;
	
	@Column(name = "dependencia_parcial")
	private Boolean dependenciaParcial;
	
	@Column(name = "dependencia_total")
	private Boolean dependenciaTotal;
	
	@Size(max = 2)
	private String marcha;
	
	@Size(max = 2)
	private String equilibrio;
	
	@Column(name = "forca_muscular")
	@Size(max = 2)
	private String forcaMuscular;
	
	private Boolean fraqueza;
	
	@Column(name = "fraqueza_obs")
	@Size(max = 64)
	private String fraquezaObs;
	
	private Boolean plegia;
	
	@Column(name = "plegia_obs")
	@Size(max = 64)
	private String plegiaObs;

	/*
	 * Auto-generated
	 */
	@Override
	public String toString() {
		return "Mobilidade [id=" + id + "]";
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
		Mobilidade other = (Mobilidade) obj;
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

	public String getMorse() {
		return morse;
	}

	public void setMorse(String morse) {
		this.morse = morse;
	}

	public String getLocomocao() {
		return locomocao;
	}

	public void setLocomocao(String locomocao) {
		this.locomocao = locomocao;
	}

	public String getLocomocaoObs() {
		return locomocaoObs;
	}

	public void setLocomocaoObs(String locomocaoObs) {
		this.locomocaoObs = locomocaoObs;
	}

	public Boolean getDependenciaParcial() {
		return dependenciaParcial;
	}

	public void setDependenciaParcial(Boolean dependenciaParcial) {
		this.dependenciaParcial = dependenciaParcial;
	}

	public Boolean getDependenciaTotal() {
		return dependenciaTotal;
	}

	public void setDependenciaTotal(Boolean dependenciaTotal) {
		this.dependenciaTotal = dependenciaTotal;
	}

	public String getMarcha() {
		return marcha;
	}

	public void setMarcha(String marcha) {
		this.marcha = marcha;
	}

	public String getEquilibrio() {
		return equilibrio;
	}

	public void setEquilibrio(String equilibrio) {
		this.equilibrio = equilibrio;
	}

	public String getForcaMuscular() {
		return forcaMuscular;
	}

	public void setForcaMuscular(String forcaMuscular) {
		this.forcaMuscular = forcaMuscular;
	}

	public Boolean getFraqueza() {
		return fraqueza;
	}

	public void setFraqueza(Boolean fraqueza) {
		this.fraqueza = fraqueza;
	}

	public String getFraquezaObs() {
		return fraquezaObs;
	}

	public void setFraquezaObs(String fraquezaObs) {
		this.fraquezaObs = fraquezaObs;
	}

	public Boolean getPlegia() {
		return plegia;
	}

	public void setPlegia(Boolean plegia) {
		this.plegia = plegia;
	}

	public String getPlegiaObs() {
		return plegiaObs;
	}

	public void setPlegiaObs(String plegiaObs) {
		this.plegiaObs = plegiaObs;
	}

}
