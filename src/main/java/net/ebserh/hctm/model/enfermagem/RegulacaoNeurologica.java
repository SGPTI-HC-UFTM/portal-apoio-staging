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

@Entity
@Table(schema = "enfermagem", name = "regulacao_neurologica")
public class RegulacaoNeurologica implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "id")
	private Evolucao evolucao;
	
	private Integer glasgow;
	
	private String rass;
		
	@Column(name = "nivel_consciencia")
	private String nivelConsciencia;
	
	private String orientacao;
	
	@Column(name = "orientacao_alo")
	private Boolean orientacaoAlo;
	
	@Column(name = "orientacao_auto")
	private Boolean orientacaoAuto;
	
	@Column(name = "orientacao_observacao")
	private String orientacaoObservacao;
	
	private String sedacao;
	
	@Column(name = "sedacao_leve")
	private Boolean sedacaoLeve;
	
	@Column(name = "sedacao_moderado")
	private Boolean sedacaoModerado;
	
	@Column(name = "sedacao_profundo")
	private Boolean sedacaoProfundo;
	
	private String pupilas;
	
	@Column(name = "pupilas_anisocoricas_1")
	private String pupilasAnisocoricas1;
	
	@Column(name = "pupilas_anisocoricas_2")
	private String pupilasAnisocoricas2;
	
	@Column(name = "pupilas_midriaticas")
	private Boolean pupilasMidriaticas;
	
	@Column(name = "pupilas_mioticas")
	private Boolean pupilasMioticas;
	
	@Column(name = "pupilas_nao_reagentes_luz")
	private Boolean pupilasNaoReagentesLuz;
	
	@Column(name = "abertura_olhos")
	private String aberturaOlhos;
	
	@Column(name = "resposta_motora")
	private String respostaMotora;
	
	/*
	 * Auto-generated
	 */
	@Override
	public String toString() {
		return "RegulacaoNeurologica [id=" + id + "]";
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
		RegulacaoNeurologica other = (RegulacaoNeurologica) obj;
		return Objects.equals(id, other.id);
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGlasgow() {
		return glasgow;
	}

	public void setGlasgow(Integer glasgow) {
		this.glasgow = glasgow;
	}

	public String getRass() {
		return rass;
	}

	public void setRass(String rass) {
		this.rass = rass;
	}

	public String getNivelConsciencia() {
		return nivelConsciencia;
	}

	public void setNivelConsciencia(String nivelConsciencia) {
		this.nivelConsciencia = nivelConsciencia;
	}

	public String getOrientacao() {
		return orientacao;
	}

	public void setOrientacao(String orientacao) {
		this.orientacao = orientacao;
	}

	public Boolean getOrientacaoAlo() {
		return orientacaoAlo;
	}

	public void setOrientacaoAlo(Boolean orientacaoAlo) {
		this.orientacaoAlo = orientacaoAlo;
	}

	public Boolean getOrientacaoAuto() {
		return orientacaoAuto;
	}

	public void setOrientacaoAuto(Boolean orientacaoAuto) {
		this.orientacaoAuto = orientacaoAuto;
	}

	public String getOrientacaoObservacao() {
		return orientacaoObservacao;
	}

	public void setOrientacaoObservacao(String orientacaoObservacao) {
		this.orientacaoObservacao = orientacaoObservacao;
	}

	public String getSedacao() {
		return sedacao;
	}

	public void setSedacao(String sedacao) {
		this.sedacao = sedacao;
	}

	public Boolean getSedacaoLeve() {
		return sedacaoLeve;
	}

	public void setSedacaoLeve(Boolean sedacaoLeve) {
		this.sedacaoLeve = sedacaoLeve;
	}

	public Boolean getSedacaoModerado() {
		return sedacaoModerado;
	}

	public void setSedacaoModerado(Boolean sedacaoModerado) {
		this.sedacaoModerado = sedacaoModerado;
	}

	public Boolean getSedacaoProfundo() {
		return sedacaoProfundo;
	}

	public void setSedacaoProfundo(Boolean sedacaoProfundo) {
		this.sedacaoProfundo = sedacaoProfundo;
	}

	public String getPupilas() {
		return pupilas;
	}

	public void setPupilas(String pupilas) {
		this.pupilas = pupilas;
	}

	public Boolean getPupilasMidriaticas() {
		return pupilasMidriaticas;
	}

	public void setPupilasMidriaticas(Boolean pupilasMidriaticas) {
		this.pupilasMidriaticas = pupilasMidriaticas;
	}

	public Boolean getPupilasNaoReagentesLuz() {
		return pupilasNaoReagentesLuz;
	}

	public void setPupilasNaoReagentesLuz(Boolean pupilasNaoReagentesLuz) {
		this.pupilasNaoReagentesLuz = pupilasNaoReagentesLuz;
	}

	public String getAberturaOlhos() {
		return aberturaOlhos;
	}

	public void setAberturaOlhos(String aberturaOlhos) {
		this.aberturaOlhos = aberturaOlhos;
	}

	public String getRespostaMotora() {
		return respostaMotora;
	}

	public void setRespostaMotora(String respostaMotora) {
		this.respostaMotora = respostaMotora;
	}

	public Evolucao getEvolucao() {
		return evolucao;
	}

	public void setEvolucao(Evolucao evolucao) {
		this.evolucao = evolucao;
	}

	public String getPupilasAnisocoricas1() {
		return pupilasAnisocoricas1;
	}

	public void setPupilasAnisocoricas1(String pupilasAnisocoricas1) {
		this.pupilasAnisocoricas1 = pupilasAnisocoricas1;
	}

	public String getPupilasAnisocoricas2() {
		return pupilasAnisocoricas2;
	}

	public void setPupilasAnisocoricas2(String pupilasAnisocoricas2) {
		this.pupilasAnisocoricas2 = pupilasAnisocoricas2;
	}

	public Boolean getPupilasMioticas() {
		return pupilasMioticas;
	}

	public void setPupilasMioticas(Boolean pupilasMioticas) {
		this.pupilasMioticas = pupilasMioticas;
	}
	
	
	
}
