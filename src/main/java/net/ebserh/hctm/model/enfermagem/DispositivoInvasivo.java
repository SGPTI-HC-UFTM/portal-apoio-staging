package net.ebserh.hctm.model.enfermagem;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "enfermagem", name = "dispositivos_invasivos")
public class DispositivoInvasivo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;

	@Column(name = "ind_sem_dispositivos")
	private Boolean indSemDipositivo = true;
	
	@Column(name = "ind_cateter_venoso_periferico")
	private Boolean indCateterVenosoPeriferico;
	
	@Column(name = "data_cateter_venoso_periferico")
	private LocalDate dataCateterVenosoPeriferico;
	
	@Column(name = "local_cateter_venoso_periferico")
	private String localCateterVenosoPeriferico;
	
	@Column(name = "obs_cateter_venoso_periferico")
	private String obsCateterVenosoPeriferico;
	
	@Column(name = "ind_cateter_venoso_central")
	private Boolean indCateterVenosoCentral;

	@Column(name = "data_cateter_venoso_central")
	private LocalDate dataCateterVenosoCentral;
	
	@Column(name = "tipo_cateter_venoso_central")
	private String tipoCateterVenosoCentral;
	
	@Column(name = "local_cateter_venoso_central")
	private String localCateterVenosoCentral;
	
	@Column(name = "obs_cateter_venoso_central")
	private String observacaoCateterVenosoCentral;
	
	@Column(name = "ind_outro_cateter")
	private Boolean indOutroCateter;
	
	@Column(name = "ind_outro_cateter_gastrico")
	private Boolean indOutroCateterGastrico;
	
	@Column(name = "ind_outro_cateter_enteral")
	private Boolean indOutroCateterEnteral;
	
	@Column(name = "obs_outro_cateter")
	private String observacaoOutroCateter;
	
	@Column(name = "ind_cateter_vesical")
	private Boolean indCateterVesical;
	
	@Column(name = "ind_cateter_vesical_demora")
	private Boolean indCateterVesicalDemora;
	
	@Column(name = "ind_cateter_vesical_intermitente")
	private Boolean indCateterVesicalIntermitente;
	
	@Column(name = "ind_cateter_vesical_cistostomia")
	private Boolean indCateterVesicalCistostomia;
		
	@Column(name = "obs_cateter_vesical")
	private String observacaoCateterVesical;
	
	@Column(name = "ind_canula_traqueal")
	private Boolean indCanulaTraqueal;
	
	@Column(name = "obs_canula_traqueal")
	private String observacaoCanulaTraqueal;
	
	@Column(name = "ind_dispositivos_ortopedicos")
	private Boolean indDispositivosOrtopedicos;
	
	@Column(name = "dispositivos_ortopedicos")
	private String dispositivosOrtopedicos;
	
	@Column(name = "ind_drenos")
	private Boolean indDrenos;
	
	private String drenos;
	
	private String outros;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "id")
	private Evolucao evolucao;

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
		DispositivoInvasivo other = (DispositivoInvasivo) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "DispositivoInvasivo [id=" + id + "]";
	}

	/*
	 * Auto-generated
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIndSemDipositivo() {
		return indSemDipositivo;
	}

	public void setIndSemDipositivo(Boolean indSemDipositivo) {
		this.indSemDipositivo = indSemDipositivo;
	}

	public Boolean getIndCateterVenosoPeriferico() {
		return indCateterVenosoPeriferico;
	}

	public void setIndCateterVenosoPeriferico(Boolean indCateterVenosoPeriferico) {
		this.indCateterVenosoPeriferico = indCateterVenosoPeriferico;
	}

	public LocalDate getDataCateterVenosoPeriferico() {
		return dataCateterVenosoPeriferico;
	}

	public void setDataCateterVenosoPeriferico(LocalDate dataCateterVenosoPeriferico) {
		this.dataCateterVenosoPeriferico = dataCateterVenosoPeriferico;
	}

	public String getLocalCateterVenosoPeriferico() {
		return localCateterVenosoPeriferico;
	}

	public void setLocalCateterVenosoPeriferico(String localCateterVenosoPeriferico) {
		this.localCateterVenosoPeriferico = localCateterVenosoPeriferico;
	}

	public String getObsCateterVenosoPeriferico() {
		return obsCateterVenosoPeriferico;
	}

	public void setObsCateterVenosoPeriferico(String obsCateterVenosoPeriferico) {
		this.obsCateterVenosoPeriferico = obsCateterVenosoPeriferico;
	}

	public Boolean getIndCateterVenosoCentral() {
		return indCateterVenosoCentral;
	}

	public void setIndCateterVenosoCentral(Boolean indCateterVenosoCentral) {
		this.indCateterVenosoCentral = indCateterVenosoCentral;
	}

	public String getTipoCateterVenosoCentral() {
		return tipoCateterVenosoCentral;
	}

	public void setTipoCateterVenosoCentral(String tipoCateterVenosoCentral) {
		this.tipoCateterVenosoCentral = tipoCateterVenosoCentral;
	}

	public String getLocalCateterVenosoCentral() {
		return localCateterVenosoCentral;
	}

	public void setLocalCateterVenosoCentral(String localCateterVenosoCentral) {
		this.localCateterVenosoCentral = localCateterVenosoCentral;
	}

	public String getObservacaoCateterVenosoCentral() {
		return observacaoCateterVenosoCentral;
	}

	public void setObservacaoCateterVenosoCentral(String observacaoCateterVenosoCentral) {
		this.observacaoCateterVenosoCentral = observacaoCateterVenosoCentral;
	}

	public Boolean getIndOutroCateter() {
		return indOutroCateter;
	}

	public void setIndOutroCateter(Boolean indOutroCateter) {
		this.indOutroCateter = indOutroCateter;
	}

	public Boolean getIndOutroCateterGastrico() {
		return indOutroCateterGastrico;
	}

	public void setIndOutroCateterGastrico(Boolean indOutroCateterGastrico) {
		this.indOutroCateterGastrico = indOutroCateterGastrico;
	}

	public Boolean getIndOutroCateterEnteral() {
		return indOutroCateterEnteral;
	}

	public void setIndOutroCateterEnteral(Boolean indOutroCateterEnteral) {
		this.indOutroCateterEnteral = indOutroCateterEnteral;
	}

	public String getObservacaoOutroCateter() {
		return observacaoOutroCateter;
	}

	public void setObservacaoOutroCateter(String observacaoOutroCateter) {
		this.observacaoOutroCateter = observacaoOutroCateter;
	}

	public Boolean getIndCateterVesical() {
		return indCateterVesical;
	}

	public void setIndCateterVesical(Boolean indCateterVesical) {
		this.indCateterVesical = indCateterVesical;
	}

	public Boolean getIndCateterVesicalDemora() {
		return indCateterVesicalDemora;
	}

	public void setIndCateterVesicalDemora(Boolean indCateterVesicalDemora) {
		this.indCateterVesicalDemora = indCateterVesicalDemora;
	}

	public Boolean getIndCateterVesicalIntermitente() {
		return indCateterVesicalIntermitente;
	}

	public void setIndCateterVesicalIntermitente(Boolean indCateterVesicalIntermitente) {
		this.indCateterVesicalIntermitente = indCateterVesicalIntermitente;
	}

	public Boolean getIndCateterVesicalCistostomia() {
		return indCateterVesicalCistostomia;
	}

	public void setIndCateterVesicalCistostomia(Boolean indCateterVesicalCistostomia) {
		this.indCateterVesicalCistostomia = indCateterVesicalCistostomia;
	}

	public String getObservacaoCateterVesical() {
		return observacaoCateterVesical;
	}

	public void setObservacaoCateterVesical(String observacaoCateterVesical) {
		this.observacaoCateterVesical = observacaoCateterVesical;
	}

	public Boolean getIndCanulaTraqueal() {
		return indCanulaTraqueal;
	}

	public void setIndCanulaTraqueal(Boolean indCanulaTraqueal) {
		this.indCanulaTraqueal = indCanulaTraqueal;
	}

	public String getObservacaoCanulaTraqueal() {
		return observacaoCanulaTraqueal;
	}

	public void setObservacaoCanulaTraqueal(String observacaoCanulaTraqueal) {
		this.observacaoCanulaTraqueal = observacaoCanulaTraqueal;
	}

	public Boolean getIndDispositivosOrtopedicos() {
		return indDispositivosOrtopedicos;
	}

	public void setIndDispositivosOrtopedicos(Boolean indDispositivosOrtopedicos) {
		this.indDispositivosOrtopedicos = indDispositivosOrtopedicos;
	}

	public String getDispositivosOrtopedicos() {
		return dispositivosOrtopedicos;
	}

	public void setDispositivosOrtopedicos(String dispositivosOrtopedicos) {
		this.dispositivosOrtopedicos = dispositivosOrtopedicos;
	}

	public Boolean getIndDrenos() {
		return indDrenos;
	}

	public void setIndDrenos(Boolean indDrenos) {
		this.indDrenos = indDrenos;
	}

	public String getDrenos() {
		return drenos;
	}

	public void setDrenos(String drenos) {
		this.drenos = drenos;
	}

	public String getOutros() {
		return outros;
	}

	public void setOutros(String outros) {
		this.outros = outros;
	}

	public Evolucao getEvolucao() {
		return evolucao;
	}

	public void setEvolucao(Evolucao evolucao) {
		this.evolucao = evolucao;
	}

	public LocalDate getDataCateterVenosoCentral() {
		return dataCateterVenosoCentral;
	}

	public void setDataCateterVenosoCentral(LocalDate dataCateterVenosoCentral) {
		this.dataCateterVenosoCentral = dataCateterVenosoCentral;
	}
}
