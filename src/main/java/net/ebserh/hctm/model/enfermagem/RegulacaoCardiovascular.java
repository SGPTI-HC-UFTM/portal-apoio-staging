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
@Table(schema = "enfermagem", name = "regulacao_cardiovascular")
public class RegulacaoCardiovascular implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@MapsId
	@OneToOne
	@JoinColumn(name = "id")
	private Evolucao evolucao;
	
	@Column(name = "frequencia_cardiaca_1")
	private Integer frequenciaCardiaca1;
	
	@Column(name = "frequencia_cardiaca_2")
	private Integer frequenciaCardiaca2;
	
	@Column(name = "frequencia_cardiaca_obs")
	@Size(max = 64)
	private String frequenciaCardiacaObs;
	
	@Column(name = "pas_1")
	private Integer pas1;
	
	@Column(name = "pas_2")
	private Integer pas2;
	
	@Column(name = "pad_1")
	private Integer pad1;
	
	@Column(name = "pad_2")
	private Integer pad2;
	
	@Column(name = "pa_obs")
	@Size(max = 64)
	private String paObs;
	
	@Column(name = "pulsos_perifericos")
	@Size(max = 64)
	private String pulsosPerifericos;
	
	@Column(name = "perfusao_periferica")
	@Size(max = 2)
	private String perfusaoPeriferica;
	
	@Column(name = "rede_vascular")
	@Size(max = 2)
	private String redeVascular;
	
	@Column(name = "rede_vascular_obs")
	@Size(max = 64)
	private String redeVascularObs;
	
	private Boolean edema;
	
	@Column(name= "edema_local_cacifo")
	@Size(max = 64)
	private String edemaLocalCacifo;

	/*
	 * Auto-generated
	 */
	@Override
	public String toString() {
		return "RegulacaoCardiovascular [id=" + id + "]";
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
		RegulacaoCardiovascular other = (RegulacaoCardiovascular) obj;
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

	public Integer getFrequenciaCardiaca1() {
		return frequenciaCardiaca1;
	}

	public void setFrequenciaCardiaca1(Integer frequenciaCardiaca1) {
		this.frequenciaCardiaca1 = frequenciaCardiaca1;
	}

	public Integer getFrequenciaCardiaca2() {
		return frequenciaCardiaca2;
	}

	public void setFrequenciaCardiaca2(Integer frequenciaCardiaca2) {
		this.frequenciaCardiaca2 = frequenciaCardiaca2;
	}

	public String getFrequenciaCardiacaObs() {
		return frequenciaCardiacaObs;
	}

	public void setFrequenciaCardiacaObs(String frequenciaCardiacaObs) {
		this.frequenciaCardiacaObs = frequenciaCardiacaObs;
	}

	public Integer getPas1() {
		return pas1;
	}

	public void setPas1(Integer pas1) {
		this.pas1 = pas1;
	}

	public Integer getPas2() {
		return pas2;
	}

	public void setPas2(Integer pas2) {
		this.pas2 = pas2;
	}

	public Integer getPad1() {
		return pad1;
	}

	public void setPad1(Integer pad1) {
		this.pad1 = pad1;
	}

	public Integer getPad2() {
		return pad2;
	}

	public void setPad2(Integer pad2) {
		this.pad2 = pad2;
	}

	public String getPaObs() {
		return paObs;
	}

	public void setPaObs(String paObs) {
		this.paObs = paObs;
	}

	public String getPulsosPerifericos() {
		return pulsosPerifericos;
	}

	public void setPulsosPerifericos(String pulsosPerifericos) {
		this.pulsosPerifericos = pulsosPerifericos;
	}

	public String getPerfusaoPeriferica() {
		return perfusaoPeriferica;
	}

	public void setPerfusaoPeriferica(String perfusaoPeriferica) {
		this.perfusaoPeriferica = perfusaoPeriferica;
	}

	public String getRedeVascular() {
		return redeVascular;
	}

	public void setRedeVascular(String redeVascular) {
		this.redeVascular = redeVascular;
	}

	public String getRedeVascularObs() {
		return redeVascularObs;
	}

	public void setRedeVascularObs(String redeVascularObs) {
		this.redeVascularObs = redeVascularObs;
	}

	public Boolean getEdema() {
		return edema;
	}

	public void setEdema(Boolean edema) {
		this.edema = edema;
	}

	public String getEdemaLocalCacifo() {
		return edemaLocalCacifo;
	}

	public void setEdemaLocalCacifo(String edemaLocalCacifo) {
		this.edemaLocalCacifo = edemaLocalCacifo;
	}

}
