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
@Table(schema = "enfermagem", name = "nutricao_hidratacao")
public class NutricaoHidratacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@MapsId
	@OneToOne
	@JoinColumn(name = "id")
	private Evolucao evolucao;
	
	@Column(name = "peso")
	private Integer peso;
	
	@Column(name= "estatura")
	private Integer estatura;
	
	@Column(name = "dieta_oral")
	private Boolean dietaOral;
	
	@Column(name = "dieta_aceitacao")
	private String dietaAceitacao;
	
	@Column(name = "dieta_obs")
	@Size(max = 64)
	private String dietaObs;
	
	@Column(name = "dieta_enteral")
	private Boolean dietaEnteral;
	
	@Column(name = "dieta_enteral_volume")
	private Integer dietaEnteralVolume;
	
	@Column(name = "dieta_parenteral")
	private Boolean dietaParenteral;
	
	@Column(name = "dieta_parenteral_volume")
	private Integer dietaParenteralVolume;
	
	@Column(name = "dieta_zero")
	private Boolean dietaZero;

	@Column(name = "dieta_zero_obs")
	private String dietaZeroObs;
	
	@Column(name = "degluticao_preservada")
	private Boolean degluticaoPreservada;

	@Column(name = "degluticao_disfagia")
	private Boolean degluticaoDisfagia;
	
	@Column(name = "degluticao_engasgos")
	private Boolean degluticaoEngasgos;
	
	@Column(name = "degluticao_estase")
	private Boolean degluticaoEstase;
	
	@Column(name = "degluticao_obs")
	@Size(max = 64)
	private String degluticaoObs;
	
	@Column(name = "degluticao_na")
	private Boolean degluticaoNa;
	
	@Column(name = "abdome_preservado")
	private Boolean abdomePreservado;
	
	@Column(name = "abdome_distendido")
	private Boolean abdomeDistendido;
	
	@Column(name = "abdome_tenso")
	private Boolean abdomeTenso;
	
	@Column(name = "abdome_massa_palpavel")
	private Boolean abdomeMassaPalpavel;
	
	@Column(name = "abdome_ruido")
	private Boolean abdomeRuido;
	
	@Column(name = "abdome_obs")
	@Size(max = 64)
	private String abdomeObs;
	
	private Boolean vomito;
	
	@Column(name = "vomito_obs")
	@Size(max = 64)
	private String vomitoObs;
	
	private Boolean nausea;
	
	@Column(name = "restricao_hidrica")
	private Boolean restricaoHidrica;
	
	@Column(name = "restricao_hidrica_volume")
	private Integer restricaoHidricaVolume;

	/*
	 * Auto-generated
	 */
	@Override
	public String toString() {
		return "NutricaoHidratacao [id=" + id + "]";
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
		NutricaoHidratacao other = (NutricaoHidratacao) obj;
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

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Integer getEstatura() {
		return estatura;
	}

	public void setEstatura(Integer estatura) {
		this.estatura = estatura;
	}

	public Boolean getDietaOral() {
		return dietaOral;
	}

	public void setDietaOral(Boolean dietaOral) {
		this.dietaOral = dietaOral;
	}

	public String getDietaAceitacao() {
		return dietaAceitacao;
	}

	public void setDietaAceitacao(String dietaAceitacao) {
		this.dietaAceitacao = dietaAceitacao;
	}

	public String getDietaObs() {
		return dietaObs;
	}

	public void setDietaObs(String dietaObs) {
		this.dietaObs = dietaObs;
	}

	public Boolean getDietaEnteral() {
		return dietaEnteral;
	}

	public void setDietaEnteral(Boolean dietaEnteral) {
		this.dietaEnteral = dietaEnteral;
	}

	public Integer getDietaEnteralVolume() {
		return dietaEnteralVolume;
	}

	public void setDietaEnteralVolume(Integer dietaEnteralVolume) {
		this.dietaEnteralVolume = dietaEnteralVolume;
	}

	public Boolean getDietaParenteral() {
		return dietaParenteral;
	}

	public void setDietaParenteral(Boolean dietaParenteral) {
		this.dietaParenteral = dietaParenteral;
	}

	public Integer getDietaParenteralVolume() {
		return dietaParenteralVolume;
	}

	public void setDietaParenteralVolume(Integer dietaParenteralVolume) {
		this.dietaParenteralVolume = dietaParenteralVolume;
	}

	public Boolean getDietaZero() {
		return dietaZero;
	}

	public void setDietaZero(Boolean dietaZero) {
		this.dietaZero = dietaZero;
	}

	public String getDietaZeroObs() {
		return dietaZeroObs;
	}

	public void setDietaZeroObs(String dietaZeroObs) {
		this.dietaZeroObs = dietaZeroObs;
	}

	public Boolean getDegluticaoPreservada() {
		return degluticaoPreservada;
	}

	public void setDegluticaoPreservada(Boolean degluticaoPreservada) {
		this.degluticaoPreservada = degluticaoPreservada;
	}

	public Boolean getDegluticaoDisfagia() {
		return degluticaoDisfagia;
	}

	public void setDegluticaoDisfagia(Boolean degluticaoDisfagia) {
		this.degluticaoDisfagia = degluticaoDisfagia;
	}

	public Boolean getDegluticaoEngasgos() {
		return degluticaoEngasgos;
	}

	public void setDegluticaoEngasgos(Boolean degluticaoEngasgos) {
		this.degluticaoEngasgos = degluticaoEngasgos;
	}

	public Boolean getDegluticaoEstase() {
		return degluticaoEstase;
	}

	public void setDegluticaoEstase(Boolean degluticaoEstase) {
		this.degluticaoEstase = degluticaoEstase;
	}

	public String getDegluticaoObs() {
		return degluticaoObs;
	}

	public void setDegluticaoObs(String degluticaoObs) {
		this.degluticaoObs = degluticaoObs;
	}

	public Boolean getDegluticaoNa() {
		return degluticaoNa;
	}

	public void setDegluticaoNa(Boolean degluticaoNa) {
		this.degluticaoNa = degluticaoNa;
	}

	public Boolean getAbdomePreservado() {
		return abdomePreservado;
	}

	public void setAbdomePreservado(Boolean abdomePreservado) {
		this.abdomePreservado = abdomePreservado;
	}

	public Boolean getAbdomeDistendido() {
		return abdomeDistendido;
	}

	public void setAbdomeDistendido(Boolean abdomeDistendido) {
		this.abdomeDistendido = abdomeDistendido;
	}

	public Boolean getAbdomeTenso() {
		return abdomeTenso;
	}

	public void setAbdomeTenso(Boolean abdomeTenso) {
		this.abdomeTenso = abdomeTenso;
	}

	public Boolean getAbdomeMassaPalpavel() {
		return abdomeMassaPalpavel;
	}

	public void setAbdomeMassaPalpavel(Boolean abdomeMassaPalpavel) {
		this.abdomeMassaPalpavel = abdomeMassaPalpavel;
	}

	public Boolean getAbdomeRuido() {
		return abdomeRuido;
	}

	public void setAbdomeRuido(Boolean abdomeRuido) {
		this.abdomeRuido = abdomeRuido;
	}

	public String getAbdomeObs() {
		return abdomeObs;
	}

	public void setAbdomeObs(String abdomeObs) {
		this.abdomeObs = abdomeObs;
	}

	public Boolean getVomito() {
		return vomito;
	}

	public void setVomito(Boolean vomito) {
		this.vomito = vomito;
	}

	public String getVomitoObs() {
		return vomitoObs;
	}

	public void setVomitoObs(String vomitoObs) {
		this.vomitoObs = vomitoObs;
	}

	public Boolean getNausea() {
		return nausea;
	}

	public void setNausea(Boolean nausea) {
		this.nausea = nausea;
	}

	public Boolean getRestricaoHidrica() {
		return restricaoHidrica;
	}

	public void setRestricaoHidrica(Boolean restricaoHidrica) {
		this.restricaoHidrica = restricaoHidrica;
	}

	public Integer getRestricaoHidricaVolume() {
		return restricaoHidricaVolume;
	}

	public void setRestricaoHidricaVolume(Integer restricaoHidricaVolume) {
		this.restricaoHidricaVolume = restricaoHidricaVolume;
	}

}
