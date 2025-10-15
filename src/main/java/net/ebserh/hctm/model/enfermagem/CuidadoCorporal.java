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
@Table(schema = "enfermagem", name = "cuidado_corporal")
public class CuidadoCorporal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@MapsId
	@OneToOne
	@JoinColumn(name = "id")
	private Evolucao evolucao;
	
	@Column(name = "autocuidado_preservado")
	@Size(max = 2)
	private String autocuidadoPreservado;
	
	private Boolean alimentacao;
	
	private Boolean banho;
	
	@Column(name = "higiene_intima")
	private Boolean higieneIntima;
	
	private Boolean vestir;
	
	@Column(name = "padrao_sono")
	@Size(max = 2)
	private String padraoSono;
	
	@Column(name = "padrao_sono_obs")
	private String padraoSonoObs;

	/*
	 * Auto-generated
	 */
	@Override
	public String toString() {
		return "CuidadoCorporal [id=" + id + "]";
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
		CuidadoCorporal other = (CuidadoCorporal) obj;
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

	public String getAutocuidadoPreservado() {
		return autocuidadoPreservado;
	}

	public void setAutocuidadoPreservado(String autocuidadoPreservado) {
		this.autocuidadoPreservado = autocuidadoPreservado;
	}

	public Boolean getAlimentacao() {
		return alimentacao;
	}

	public void setAlimentacao(Boolean alimentacao) {
		this.alimentacao = alimentacao;
	}

	public Boolean getBanho() {
		return banho;
	}

	public void setBanho(Boolean banho) {
		this.banho = banho;
	}

	public Boolean getHigieneIntima() {
		return higieneIntima;
	}

	public void setHigieneIntima(Boolean higieneIntima) {
		this.higieneIntima = higieneIntima;
	}

	public Boolean getVestir() {
		return vestir;
	}

	public void setVestir(Boolean vestir) {
		this.vestir = vestir;
	}

	public String getPadraoSono() {
		return padraoSono;
	}

	public void setPadraoSono(String padraoSono) {
		this.padraoSono = padraoSono;
	}

	public String getPadraoSonoObs() {
		return padraoSonoObs;
	}

	public void setPadraoSonoObs(String padraoSonoObs) {
		this.padraoSonoObs = padraoSonoObs;
	}

}
