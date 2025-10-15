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
@Table(schema = "enfermagem", name = "oxigenacao")
public class Oxigenacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@MapsId
	@OneToOne
	@JoinColumn(name = "id")
	private Evolucao evolucao;
	
	@Column(name = "ventilacao_espontanea")
	private Boolean ventilacaoEspontanea;
	
	@Column(name = "ventilacao_espontanea_ar")
	private Boolean ventilacaoEspontaneaAr;
	
	@Column(name = "ventilacao_espontanea_o2")
	private Boolean ventilacaoEspontaneaO2;
	
	@Size(max = 64)
	@Column(name = "ventilacao_espontanea_fl")
	private String ventilacaoEspontaneaFl;
	
	@Column(name = "ventilacao_espontanea_l_min")
	private String ventilacaoEspontaneaLMin;
	
	@Column(name = "ventilacao_mecanica")
	private Boolean ventilacaoMecanica;
	
	@Column(name = "ventilacao_mecanica_invasiva")
	private Boolean ventilacaoMecanicaInvasiva;
	
	@Column(name = "ventilacao_mecanica_vni")
	private Boolean ventilacaoMecanicaVni;
	
	@Column(name = "ventilacao_mecanica_modo")
	private String ventilacaoMecanicaModo;

	@Column(name = "ventilacao_mecanica_peep")
	private String ventilacaoMecanicaPeep;

	@Column(name = "ventilacao_mecanica_fio2")
	private String ventilacaoMecanicaFio2;

	@Column(name = "ventilacao_mecanica_vt_p")
	private String ventilacaoMecanicaVtP;

	@Column(name = "padrao_respiratorio")
	private String padraoRespiratorio;
	
	@Column(name = "padrao_respiratorio_outro")
	private String padraoRespiratorioOutro;
	
	@Column(name = "delta_fr")
	private String deltaFr;
	
	private String tosse;
	
	@Column(name = "reflexo_tosse")
	private Boolean reflexoTosse;

	@Column(name = "reflexo_tosse_eficaz")
	private Boolean reflexoTosseEficaz;

	@Column(name = "reflexo_tosse_ineficaz")
	private Boolean reflexoTosseIneficaz;

	@Column(name = "reflexo_tosse_nao")
	private Boolean reflexoTosseNao;

	@Column(name = "secrecao_vias_aereas")
	private Boolean secrecaoViasAereas;
	
	@Column(name = "secrecao_vias_aereas_obs")
	private String secrecaoViasAereasObs;
	
	@Column(name = "delta_spo2")
	private String deltaSpo2;
	
	private String torax;
	
	@Column(name = "torax_inspecao")
	private String toraxInspecao;
	
	/*
	 * Auto-generated
	 */
	@Override
	public String toString() {
		return "Oxigenacao [id=" + id + "]";
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
		Oxigenacao other = (Oxigenacao) obj;
		return Objects.equals(id, other.id);
	}

	public Evolucao getEvolucao() {
		return evolucao;
	}
	
	public void setEvolucao(Evolucao evolucao) {
		this.evolucao = evolucao;
	}

	public Boolean getVentilacaoEspontanea() {
		return ventilacaoEspontanea;
	}

	public void setVentilacaoEspontanea(Boolean ventilacaoEspontanea) {
		this.ventilacaoEspontanea = ventilacaoEspontanea;
	}

	public Boolean getVentilacaoEspontaneaAr() {
		return ventilacaoEspontaneaAr;
	}

	public void setVentilacaoEspontaneaAr(Boolean ventilacaoEspontaneaAr) {
		this.ventilacaoEspontaneaAr = ventilacaoEspontaneaAr;
	}

	public Boolean getVentilacaoEspontaneaO2() {
		return ventilacaoEspontaneaO2;
	}

	public void setVentilacaoEspontaneaO2(Boolean ventilacaoEspontaneaO2) {
		this.ventilacaoEspontaneaO2 = ventilacaoEspontaneaO2;
	}

	public String getVentilacaoEspontaneaFl() {
		return ventilacaoEspontaneaFl;
	}

	public void setVentilacaoEspontaneaFl(String ventilacaoEspontaneaFl) {
		this.ventilacaoEspontaneaFl = ventilacaoEspontaneaFl;
	}

	public String getVentilacaoEspontaneaLMin() {
		return ventilacaoEspontaneaLMin;
	}

	public void setVentilacaoEspontaneaLMin(String ventilacaoEspontaneaLMin) {
		this.ventilacaoEspontaneaLMin = ventilacaoEspontaneaLMin;
	}

	public Boolean getVentilacaoMecanica() {
		return ventilacaoMecanica;
	}

	public void setVentilacaoMecanica(Boolean ventilacaoMecanica) {
		this.ventilacaoMecanica = ventilacaoMecanica;
	}

	public Boolean getVentilacaoMecanicaInvasiva() {
		return ventilacaoMecanicaInvasiva;
	}

	public void setVentilacaoMecanicaInvasiva(Boolean ventilacaoMecanicaInvasiva) {
		this.ventilacaoMecanicaInvasiva = ventilacaoMecanicaInvasiva;
	}

	public Boolean getVentilacaoMecanicaVni() {
		return ventilacaoMecanicaVni;
	}

	public void setVentilacaoMecanicaVni(Boolean ventilacaoMecanicaVni) {
		this.ventilacaoMecanicaVni = ventilacaoMecanicaVni;
	}

	public String getVentilacaoMecanicaModo() {
		return ventilacaoMecanicaModo;
	}

	public void setVentilacaoMecanicaModo(String ventilacaoMecanicaModo) {
		this.ventilacaoMecanicaModo = ventilacaoMecanicaModo;
	}

	public String getVentilacaoMecanicaPeep() {
		return ventilacaoMecanicaPeep;
	}

	public void setVentilacaoMecanicaPeep(String ventilacaoMecanicaPeep) {
		this.ventilacaoMecanicaPeep = ventilacaoMecanicaPeep;
	}

	public String getVentilacaoMecanicaFio2() {
		return ventilacaoMecanicaFio2;
	}

	public void setVentilacaoMecanicaFio2(String ventilacaoMecanicaFio2) {
		this.ventilacaoMecanicaFio2 = ventilacaoMecanicaFio2;
	}

	public String getVentilacaoMecanicaVtP() {
		return ventilacaoMecanicaVtP;
	}

	public void setVentilacaoMecanicaVtP(String ventilacaoMecanicaVtP) {
		this.ventilacaoMecanicaVtP = ventilacaoMecanicaVtP;
	}

	public String getPadraoRespiratorio() {
		return padraoRespiratorio;
	}

	public void setPadraoRespiratorio(String padraoRespiratorio) {
		this.padraoRespiratorio = padraoRespiratorio;
	}

	public String getDeltaFr() {
		return deltaFr;
	}

	public void setDeltaFr(String deltaFr) {
		this.deltaFr = deltaFr;
	}

	public String getTosse() {
		return tosse;
	}

	public void setTosse(String tosse) {
		this.tosse = tosse;
	}

	public Boolean getReflexoTosse() {
		return reflexoTosse;
	}

	public void setReflexoTosse(Boolean reflexoTosse) {
		this.reflexoTosse = reflexoTosse;
	}

	public Boolean getReflexoTosseEficaz() {
		return reflexoTosseEficaz;
	}

	public void setReflexoTosseEficaz(Boolean reflexoTosseEficaz) {
		this.reflexoTosseEficaz = reflexoTosseEficaz;
	}

	public Boolean getSecrecaoViasAereas() {
		return secrecaoViasAereas;
	}

	public void setSecrecaoViasAereas(Boolean secrecaoViasAereas) {
		this.secrecaoViasAereas = secrecaoViasAereas;
	}

	public String getSecrecaoViasAereasObs() {
		return secrecaoViasAereasObs;
	}

	public void setSecrecaoViasAereasObs(String secrecaoViasAereasObs) {
		this.secrecaoViasAereasObs = secrecaoViasAereasObs;
	}

	public String getDeltaSpo2() {
		return deltaSpo2;
	}

	public void setDeltaSpo2(String deltaSpo2) {
		this.deltaSpo2 = deltaSpo2;
	}

	public String getTorax() {
		return torax;
	}

	public void setTorax(String torax) {
		this.torax = torax;
	}

	public String getToraxInspecao() {
		return toraxInspecao;
	}

	public void setToraxInspecao(String toraxInspecao) {
		this.toraxInspecao = toraxInspecao;
	}

	public String getPadraoRespiratorioOutro() {
		return padraoRespiratorioOutro;
	}

	public void setPadraoRespiratorioOutro(String padraoRespiratorioOutro) {
		this.padraoRespiratorioOutro = padraoRespiratorioOutro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Boolean getReflexoTosseIneficaz() {
		return reflexoTosseIneficaz;
	}

	public void setReflexoTosseIneficaz(Boolean reflexoTosseIneficaz) {
		this.reflexoTosseIneficaz = reflexoTosseIneficaz;
	}

	public Boolean getReflexoTosseNao() {
		return reflexoTosseNao;
	}

	public void setReflexoTosseNao(Boolean reflexoTosseNao) {
		this.reflexoTosseNao = reflexoTosseNao;
	}
}
