package net.ebserh.hctm.model.enfermagem;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(schema = "enfermagem", name = "necessidades_psicossociais")
public class NecessidadePsicossocial implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@MapsId
	@OneToOne
	@JoinColumn(name = "id")
	private Evolucao evolucao;
	
	@Column(name = "estado_emocional")
	@Size(max = 2)
	private String estadoEmocional;
	
	@Column(name = "estado_emocional_obs")
	@Size(max = 64)
	private String estadoEmocionalObs;
	
	@Column(name = "estado_emocional_outro")
	@Size(max = 64)
	private String estadoEmocionalOutro;
	
	@Column(name = "nivel_colaboracao")
	@Size(max = 2)
	private String nivelColaboracao;
	
	@Column(name = "aprendizagem")
	@Size(max = 2)
	private String aprendizagem;
	
	private Boolean acompanhante;
	
	@Column(name = "acompanhante_pernoite")
	private Boolean acompanhantePernoite;
	
	@Column(name = "acompanhante_obs")
	@Size(max = 64)
	private String acompanhanteObs;
	
	private Boolean visita;
	
	@Column(name = "necessidade_psicoespiritual")
	private Boolean necessidadePsicoespiritual;

	@Column(name = "necessidade_psicoespiritual_obs")
	private String necessidadePsicoespiritualObs;
	
	/*
	 * Auto-generated
	 */
	@Override
	public String toString() {
		return "NecessidadePsicossocial [id=" + id + "]";
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
		NecessidadePsicossocial other = (NecessidadePsicossocial) obj;
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

	public String getEstadoEmocional() {
		return estadoEmocional;
	}

	public void setEstadoEmocional(String estadoEmocional) {
		this.estadoEmocional = estadoEmocional;
	}

	public String getEstadoEmocionalObs() {
		return estadoEmocionalObs;
	}

	public void setEstadoEmocionalObs(String estadoEmocionalObs) {
		this.estadoEmocionalObs = estadoEmocionalObs;
	}

	public String getNivelColaboracao() {
		return nivelColaboracao;
	}

	public void setNivelColaboracao(String nivelColaboracao) {
		this.nivelColaboracao = nivelColaboracao;
	}

	public String getAprendizagem() {
		return aprendizagem;
	}

	public void setAprendizagem(String aprendizagem) {
		this.aprendizagem = aprendizagem;
	}

	public Boolean getAcompanhante() {
		return acompanhante;
	}

	public void setAcompanhante(Boolean acompanhante) {
		this.acompanhante = acompanhante;
	}

	public Boolean getAcompanhantePernoite() {
		return acompanhantePernoite;
	}

	public void setAcompanhantePernoite(Boolean acompanhantePernoite) {
		this.acompanhantePernoite = acompanhantePernoite;
	}

	public String getAcompanhanteObs() {
		return acompanhanteObs;
	}

	public void setAcompanhanteObs(String acompanhanteObs) {
		this.acompanhanteObs = acompanhanteObs;
	}

	public Boolean getVisita() {
		return visita;
	}

	public void setVisita(Boolean visita) {
		this.visita = visita;
	}

	public Boolean getNecessidadePsicoespiritual() {
		return necessidadePsicoespiritual;
	}

	public void setNecessidadePsicoespiritual(Boolean necessidadePsicoespiritual) {
		this.necessidadePsicoespiritual = necessidadePsicoespiritual;
	}

	public String getEstadoEmocionalOutro() {
		return estadoEmocionalOutro;
	}

	public void setEstadoEmocionalOutro(String estadoEmocionalOutro) {
		this.estadoEmocionalOutro = estadoEmocionalOutro;
	}

	public String getNecessidadePsicoespiritualObs() {
		return necessidadePsicoespiritualObs;
	}

	public void setNecessidadePsicoespiritualObs(String necessidadePsicoespiritualObs) {
		this.necessidadePsicoespiritualObs = necessidadePsicoespiritualObs;
	}
}
