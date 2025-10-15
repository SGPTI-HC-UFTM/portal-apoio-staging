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
@Table(schema = "enfermagem", name = "eliminacao")
public class Eliminacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@MapsId
	@OneToOne
	@JoinColumn(name = "id")
	private Evolucao evolucao;
	
	@Column(name = "balanco_hidrico_total")
	private Integer balancoHidricoTotal;
	
	@Column(name = "miccao_espontanea")
	private Boolean miccaoEspontanea;
	
	@Column(name = "miccao_espontanea_obs")
	@Size(max = 64)
	private String miccaoEspontaneaObs;
	
	private Boolean cateter;
	
	private Integer volume;
	
	@Column(name = "volume_horas")
	private Integer volumeHoras;

	@Column(name = "padrao_preservado")
	private Boolean padraoPreservado;
	
	@Column(name = "padrao_caracteristicas")
	@Size(max = 64)
	private String padraoCaracteristicas;
	
	@Column(name = "eliminacao_espontanea")
	private Boolean eliminacaoEspontanea;
	
	@Column(name = "eliminacao_espontanea_obs")
	@Size(max = 64)
	private String eliminacaoEspontaneaObs;
	
	private Boolean colostomia;
	
	private Boolean ileostomia;
	
	@Column(name = "padrao_eliminacao")
	@Size(max = 64)
	private String padraoEliminacao;
	
	private Boolean efluentes;
	
	@Column(name = "efluentes_obs")
	@Size(max = 64)
	private String efluentesObs;

	/*
	 * Auto-generated
	 */
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
		Eliminacao other = (Eliminacao) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Eliminacao [id=" + id + "]";
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

	public Integer getBalancoHidricoTotal() {
		return balancoHidricoTotal;
	}

	public void setBalancoHidricoTotal(Integer balancoHidricoTotal) {
		this.balancoHidricoTotal = balancoHidricoTotal;
	}

	public Boolean getMiccaoEspontanea() {
		return miccaoEspontanea;
	}

	public void setMiccaoEspontanea(Boolean miccaoEspontanea) {
		this.miccaoEspontanea = miccaoEspontanea;
	}

	public String getMiccaoEspontaneaObs() {
		return miccaoEspontaneaObs;
	}

	public void setMiccaoEspontaneaObs(String miccaoEspontaneaObs) {
		this.miccaoEspontaneaObs = miccaoEspontaneaObs;
	}

	public Boolean getCateter() {
		return cateter;
	}

	public void setCateter(Boolean cateter) {
		this.cateter = cateter;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public Integer getVolumeHoras() {
		return volumeHoras;
	}

	public void setVolumeHoras(Integer volumeHoras) {
		this.volumeHoras = volumeHoras;
	}

	public Boolean getPadraoPreservado() {
		return padraoPreservado;
	}

	public void setPadraoPreservado(Boolean padraoPreservado) {
		this.padraoPreservado = padraoPreservado;
	}

	public String getPadraoCaracteristicas() {
		return padraoCaracteristicas;
	}

	public void setPadraoCaracteristicas(String padraoCaracteristicas) {
		this.padraoCaracteristicas = padraoCaracteristicas;
	}

	public Boolean getEliminacaoEspontanea() {
		return eliminacaoEspontanea;
	}

	public void setEliminacaoEspontanea(Boolean eliminacaoEspontanea) {
		this.eliminacaoEspontanea = eliminacaoEspontanea;
	}

	public String getEliminacaoEspontaneaObs() {
		return eliminacaoEspontaneaObs;
	}

	public void setEliminacaoEspontaneaObs(String eliminacaoEspontaneaObs) {
		this.eliminacaoEspontaneaObs = eliminacaoEspontaneaObs;
	}

	public Boolean getColostomia() {
		return colostomia;
	}

	public void setColostomia(Boolean colostomia) {
		this.colostomia = colostomia;
	}

	public Boolean getIleostomia() {
		return ileostomia;
	}

	public void setIleostomia(Boolean ileostomia) {
		this.ileostomia = ileostomia;
	}

	public String getPadraoEliminacao() {
		return padraoEliminacao;
	}

	public void setPadraoEliminacao(String padraoEliminacao) {
		this.padraoEliminacao = padraoEliminacao;
	}

	public Boolean getEfluentes() {
		return efluentes;
	}

	public void setEfluentes(Boolean efluentes) {
		this.efluentes = efluentes;
	}

	public String getEfluentesObs() {
		return efluentesObs;
	}

	public void setEfluentesObs(String efluentesObs) {
		this.efluentesObs = efluentesObs;
	}

}
