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
@Table(schema = "enfermagem", name = "comunicacao_percepcao")
public class ComunicacaoPercepcao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@MapsId
	@OneToOne
	@JoinColumn(name = "id")
	private Evolucao evolucao;
	
	@Column(name = "fala_preservada")
	@Size(max = 2)
	private String falaPreservada;
	
	@Column(name = "fala_obs")
	@Size(max = 64)
	private String falaObs;
	
	@Column(name = "acuidade_visual_preservada")
	@Size(max = 2)
	private String acuidadeVisualPreservada;
	
	@Column(name = "acuidade_visual_prejudicada_d")
	private Boolean acuidadeVisualPrejudicadaD;
	
	@Column(name = "acuidade_visual_prejudicada_e")
	private Boolean acuidadeVisualPrejudicadaE;
	
	@Column(name = "acuidade_visual_obs")
	private String acuidadeVisualObs;

	@Column(name = "acuidade_auditiva_preservada")
	@Size(max = 2)
	private String acuidadeAuditivaPreservada;
	
	@Column(name = "acuidade_auditiva_prejudicada_d")
	private Boolean acuidadeAuditivaPrejudicadaD;

	@Column(name = "acuidade_auditiva_prejudicada_e")
	private Boolean acuidadeAuditivaPrejudicadaE;
	
	@Column(name = "acuidade_auditiva_obs")
	@Size(max = 64)
	private String acuidadeAuditivaObs;
	
	@Size(max = 2)
	private String dor;
	
	@Column(name = "dor_obs")
	@Size(max = 64)
	private String dorObs;
	
	/*
	 * Auto-generated
	 */
	@Override
	public String toString() {
		return "ComunicacaoPercepcao [id=" + id + "]";
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
		ComunicacaoPercepcao other = (ComunicacaoPercepcao) obj;
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

	public String getFalaPreservada() {
		return falaPreservada;
	}

	public void setFalaPreservada(String falaPreservada) {
		this.falaPreservada = falaPreservada;
	}

	public String getFalaObs() {
		return falaObs;
	}

	public void setFalaObs(String falaObs) {
		this.falaObs = falaObs;
	}

	public String getAcuidadeVisualPreservada() {
		return acuidadeVisualPreservada;
	}

	public void setAcuidadeVisualPreservada(String acuidadeVisualPreservada) {
		this.acuidadeVisualPreservada = acuidadeVisualPreservada;
	}

	public Boolean getAcuidadeVisualPrejudicadaD() {
		return acuidadeVisualPrejudicadaD;
	}

	public void setAcuidadeVisualPrejudicadaD(Boolean acuidadeVisualPrejudicadaD) {
		this.acuidadeVisualPrejudicadaD = acuidadeVisualPrejudicadaD;
	}

	public Boolean getAcuidadeVisualPrejudicadaE() {
		return acuidadeVisualPrejudicadaE;
	}

	public void setAcuidadeVisualPrejudicadaE(Boolean acuidadeVisualPrejudicadaE) {
		this.acuidadeVisualPrejudicadaE = acuidadeVisualPrejudicadaE;
	}

	public String getAcuidadeVisualObs() {
		return acuidadeVisualObs;
	}

	public void setAcuidadeVisualObs(String acuidadeVisualObs) {
		this.acuidadeVisualObs = acuidadeVisualObs;
	}

	public String getAcuidadeAuditivaPreservada() {
		return acuidadeAuditivaPreservada;
	}

	public void setAcuidadeAuditivaPreservada(String acuidadeAuditivaPreservada) {
		this.acuidadeAuditivaPreservada = acuidadeAuditivaPreservada;
	}

	public Boolean getAcuidadeAuditivaPrejudicadaD() {
		return acuidadeAuditivaPrejudicadaD;
	}

	public void setAcuidadeAuditivaPrejudicadaD(Boolean acuidadeAuditivaPrejudicadaD) {
		this.acuidadeAuditivaPrejudicadaD = acuidadeAuditivaPrejudicadaD;
	}

	public Boolean getAcuidadeAuditivaPrejudicadaE() {
		return acuidadeAuditivaPrejudicadaE;
	}

	public void setAcuidadeAuditivaPrejudicadaE(Boolean acuidadeAuditivaPrejudicadaE) {
		this.acuidadeAuditivaPrejudicadaE = acuidadeAuditivaPrejudicadaE;
	}

	public String getAcuidadeAuditivaObs() {
		return acuidadeAuditivaObs;
	}

	public void setAcuidadeAuditivaObs(String acuidadeAuditivaObs) {
		this.acuidadeAuditivaObs = acuidadeAuditivaObs;
	}

	public String getDor() {
		return dor;
	}

	public void setDor(String dor) {
		this.dor = dor;
	}

	public String getDorObs() {
		return dorObs;
	}

	public void setDorObs(String dorObs) {
		this.dorObs = dorObs;
	}

}
