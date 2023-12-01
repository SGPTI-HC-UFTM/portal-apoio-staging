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
@Table(schema = "enfermagem", name = "regulacao_termica")
public class RegulacaoTermica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@MapsId
	@OneToOne
	@JoinColumn(name = "id")
	private Evolucao evolucao;
	
	@Size(max = 2)
	private String braden;
	
	@Column(name = "temperatura_delta_1")
	private Double temperaturaDelta1;
	
	@Column(name = "temperatura_delta_2")
	private Double temperaturaDelta2;
	
	@Column(name = "temperatura_obs")
	@Size(max = 64)
	private String temperaturaObs;

	@Column(name = "pele_alterada")
	private Boolean peleAlterada;
	
	@Column(name = "pele_palidez")
	private Boolean pelePalidez;
	
	@Column(name = "pele_cianose")
	private Boolean peleCianose;
	
	@Column(name = "pele_rubor")
	private Boolean peleRubor;
	
	@Column(name = "pele_fria")
	private Boolean peleFria;
	
	@Column(name = "pele_ictericia")
	private Boolean peleIctericia;
	
	@Column(name = "pele_prurido")
	private Boolean pelePrurido;

	@Column(name = "pele_sudorese")
	private Boolean peleSudorese;
	
	@Column(name = "pele_ressecada")
	private Boolean peleRessecada;

	@Column(name = "pele_lesoes")
	private Boolean peleLesoes;

	@Column(name = "pele_obs")
	@Size(max = 64)
	private String peleObs;
	
	@Column(name = "conjuntiva_alterada")
	private Boolean conjuntivaAlterada;
	
	@Column(name = "conjuntiva_alterada_d")
	private Boolean conjuntivaAlteradaD;

	@Column(name = "conjuntiva_alterada_e")
	private Boolean conjuntivaAlteradaE;
	
	@Column(name = "conjuntiva_hipocorada")
	private Boolean conjuntivaHipocorada;
	
	@Column(name = "conjuntiva_hipocorada_valor")
	private Integer conjuntivaHipocoradaValor;
	
	@Column(name = "conjuntiva_prurido")
	private Boolean conjuntivaPrurido;

	@Column(name = "conjuntiva_hiperemia")
	private Boolean conjuntivaHiperemia;
	
	@Column(name = "conjuntiva_ictericia")
	private Boolean conjuntivaIctericia;
	
	@Column(name = "conjuntiva_ressecada")
	private Boolean conjuntivaRessecada;
	
	@Column(name = "conjuntiva_obs")
	@Size(max = 64)
	private String conjuntivaObs;

	@Column(name = "conjuntiva_outro")
	@Size(max = 64)
	private String conjuntivaOutro;
	
	@Column(name = "cavidade_bucal_alterada")
	private Boolean cavidadeBucalAlterada;
	
	@Column(name = "cavidade_bucal_obs")
	@Size(max = 64)
	private String cavidadeBucalObs;
	
	@Column(name = "genitalia_alterada")
	private Boolean genitaliaAlterada;
	
	@Column(name = "genitalia_obs")
	@Size(max = 64)
	private String genitaliaObs;
	
	private Boolean ferida;
	
	@Column(name = "ferida_obs")
	@Size(max = 64)
	private String feridaObs;

	/*
	 * Auto-generated
	 */
	@Override
	public String toString() {
		return "RegulacaoTermica [id=" + id + "]";
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
		RegulacaoTermica other = (RegulacaoTermica) obj;
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

	public String getBraden() {
		return braden;
	}

	public void setBraden(String braden) {
		this.braden = braden;
	}

	public Double getTemperaturaDelta1() {
		return temperaturaDelta1;
	}

	public void setTemperaturaDelta1(Double temperaturaDelta1) {
		this.temperaturaDelta1 = temperaturaDelta1;
	}

	public Double getTemperaturaDelta2() {
		return temperaturaDelta2;
	}

	public void setTemperaturaDelta2(Double temperaturaDelta2) {
		this.temperaturaDelta2 = temperaturaDelta2;
	}

	public String getTemperaturaObs() {
		return temperaturaObs;
	}

	public void setTemperaturaObs(String temperaturaObs) {
		this.temperaturaObs = temperaturaObs;
	}

	public Boolean getPeleAlterada() {
		return peleAlterada;
	}

	public void setPeleAlterada(Boolean peleAlterada) {
		this.peleAlterada = peleAlterada;
	}

	public Boolean getPelePalidez() {
		return pelePalidez;
	}

	public void setPelePalidez(Boolean pelePalidez) {
		this.pelePalidez = pelePalidez;
	}

	public Boolean getPeleCianose() {
		return peleCianose;
	}

	public void setPeleCianose(Boolean peleCianose) {
		this.peleCianose = peleCianose;
	}

	public Boolean getPeleRubor() {
		return peleRubor;
	}

	public void setPeleRubor(Boolean peleRubor) {
		this.peleRubor = peleRubor;
	}

	public Boolean getPeleFria() {
		return peleFria;
	}

	public void setPeleFria(Boolean peleFria) {
		this.peleFria = peleFria;
	}

	public Boolean getPeleIctericia() {
		return peleIctericia;
	}

	public void setPeleIctericia(Boolean peleIctericia) {
		this.peleIctericia = peleIctericia;
	}

	public Boolean getPelePrurido() {
		return pelePrurido;
	}

	public void setPelePrurido(Boolean pelePrurido) {
		this.pelePrurido = pelePrurido;
	}

	public Boolean getPeleSudorese() {
		return peleSudorese;
	}

	public void setPeleSudorese(Boolean peleSudorese) {
		this.peleSudorese = peleSudorese;
	}

	public Boolean getPeleRessecada() {
		return peleRessecada;
	}

	public void setPeleRessecada(Boolean peleRessecada) {
		this.peleRessecada = peleRessecada;
	}

	public Boolean getPeleLesoes() {
		return peleLesoes;
	}

	public void setPeleLesoes(Boolean peleLesoes) {
		this.peleLesoes = peleLesoes;
	}

	public String getPeleObs() {
		return peleObs;
	}

	public void setPeleObs(String peleObs) {
		this.peleObs = peleObs;
	}

	public Boolean getConjuntivaAlterada() {
		return conjuntivaAlterada;
	}

	public void setConjuntivaAlterada(Boolean conjuntivaAlterada) {
		this.conjuntivaAlterada = conjuntivaAlterada;
	}

	public Boolean getConjuntivaAlteradaD() {
		return conjuntivaAlteradaD;
	}

	public void setConjuntivaAlteradaD(Boolean conjuntivaAlteradaD) {
		this.conjuntivaAlteradaD = conjuntivaAlteradaD;
	}

	public Boolean getConjuntivaAlteradaE() {
		return conjuntivaAlteradaE;
	}

	public void setConjuntivaAlteradaE(Boolean conjuntivaAlteradaE) {
		this.conjuntivaAlteradaE = conjuntivaAlteradaE;
	}

	public Boolean getConjuntivaHipocorada() {
		return conjuntivaHipocorada;
	}

	public void setConjuntivaHipocorada(Boolean conjuntivaHipocorada) {
		this.conjuntivaHipocorada = conjuntivaHipocorada;
	}

	public Integer getConjuntivaHipocoradaValor() {
		return conjuntivaHipocoradaValor;
	}

	public void setConjuntivaHipocoradaValor(Integer conjuntivaHipocoradaValor) {
		this.conjuntivaHipocoradaValor = conjuntivaHipocoradaValor;
	}

	public Boolean getConjuntivaPrurido() {
		return conjuntivaPrurido;
	}

	public void setConjuntivaPrurido(Boolean conjuntivaPrurido) {
		this.conjuntivaPrurido = conjuntivaPrurido;
	}

	public Boolean getConjuntivaHiperemia() {
		return conjuntivaHiperemia;
	}

	public void setConjuntivaHiperemia(Boolean conjuntivaHiperemia) {
		this.conjuntivaHiperemia = conjuntivaHiperemia;
	}

	public Boolean getConjuntivaIctericia() {
		return conjuntivaIctericia;
	}

	public void setConjuntivaIctericia(Boolean conjuntivaIctericia) {
		this.conjuntivaIctericia = conjuntivaIctericia;
	}

	public Boolean getConjuntivaRessecada() {
		return conjuntivaRessecada;
	}

	public void setConjuntivaRessecada(Boolean conjuntivaRessecada) {
		this.conjuntivaRessecada = conjuntivaRessecada;
	}

	public Boolean getCavidadeBucalAlterada() {
		return cavidadeBucalAlterada;
	}

	public void setCavidadeBucalAlterada(Boolean cavidadeBucalAlterada) {
		this.cavidadeBucalAlterada = cavidadeBucalAlterada;
	}

	public String getCavidadeBucalObs() {
		return cavidadeBucalObs;
	}

	public void setCavidadeBucalObs(String cavidadeBucalObs) {
		this.cavidadeBucalObs = cavidadeBucalObs;
	}

	public Boolean getGenitaliaAlterada() {
		return genitaliaAlterada;
	}

	public void setGenitaliaAlterada(Boolean genitaliaAlterada) {
		this.genitaliaAlterada = genitaliaAlterada;
	}

	public String getGenitaliaObs() {
		return genitaliaObs;
	}

	public void setGenitaliaObs(String genitaliaObs) {
		this.genitaliaObs = genitaliaObs;
	}

	public Boolean getFerida() {
		return ferida;
	}

	public void setFerida(Boolean ferida) {
		this.ferida = ferida;
	}

	public String getFeridaObs() {
		return feridaObs;
	}

	public void setFeridaObs(String feridaObs) {
		this.feridaObs = feridaObs;
	}

	public String getConjuntivaObs() {
		return conjuntivaObs;
	}

	public void setConjuntivaObs(String conjuntivaObs) {
		this.conjuntivaObs = conjuntivaObs;
	}

	public String getConjuntivaOutro() {
		return conjuntivaOutro;
	}

	public void setConjuntivaOutro(String conjuntivaOutro) {
		this.conjuntivaOutro = conjuntivaOutro;
	}
}
