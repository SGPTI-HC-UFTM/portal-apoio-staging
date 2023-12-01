package net.ebserh.hctm.model.enfermagem;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.ebserh.hctm.model.aghu.pacientes.Paciente;

@Entity
@Table(schema = "enfermagem", name = "evolucoes")
@NamedQuery(name = "Evolucao.findAtivaByInternacaoDataReferencia", query = "SELECT e "
		+ "FROM Evolucao e "
		+ "WHERE e.numeroInternacao = :numeroInternacao "
		+ "AND e.dataReferencia = :dataReferencia "
		+ "AND e.ativa = TRUE")
public class Evolucao implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "internacao_seq")
	private Integer numeroInternacao;

	@Column(name = "data_referencia")
	private LocalDate dataReferencia;

	@Column(name = "dthr_evolucao")
	private LocalDateTime dthrEvolucao;

	@Column(name = "escala_fugulin")
	private String escalaFugulin;

	@Column(name = "ind_precaucao_padrao")
	private Boolean indPrecaucaoPadrao;

	@Column(name = "ind_precaucao_contato")
	private Boolean indPrecaucaoContato;

	@Column(name = "ind_precaucao_goticulas")
	private Boolean indPrecaucaoGoticulas;

	@Column(name = "ind_precaucao_aerossois")
	private Boolean indPrecaucaoAerossois;

	@Column(name = "ind_precaucao_reversa")
	private Boolean indPrecaucaoReversa;

	@Column(name = "observacao_precaucao")
	private String observacaoPrecaucao;

	private Boolean ativa;

	@Column(name = "informacoes_complementares")
	private String informacoesComplementares;

	@Column(name = "evolucao")
	private String evolucao;

	@OneToOne(mappedBy = "evolucao", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private DispositivoInvasivo dispositivoInvasivo;

	@OneToOne(mappedBy = "evolucao", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private RegulacaoNeurologica regulacaoNeurologica;

	@OneToOne(mappedBy = "evolucao", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Oxigenacao oxigenacao;

	@OneToOne(mappedBy = "evolucao", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private RegulacaoCardiovascular regulacaoCardiovascular;

	@OneToOne(mappedBy = "evolucao", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private NutricaoHidratacao nutricaoHidratacao;

	@OneToOne(mappedBy = "evolucao", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Eliminacao eliminacao;

	@OneToOne(mappedBy = "evolucao", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private RegulacaoTermica regulacaoTermica;

	@OneToOne(mappedBy = "evolucao", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private ComunicacaoPercepcao comunicacaoPercepcao;

	@OneToOne(mappedBy = "evolucao", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private CuidadoCorporal cuidadoCorporal;

	@OneToOne(mappedBy = "evolucao", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Mobilidade mobilidade;

	@OneToOne(mappedBy = "evolucao", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private NecessidadePsicossocial necessidadePsicossocial;

	@OneToOne(mappedBy = "evolucao", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private AlergiaMedicamento alergiaMedicamento;

	@Column(name = "procedimentos_realizados")
	private String procedimentosRealizados;

	@Column(name = "diagnosticos_enfermagem")
	private String diagnosticosEnfermagem;

	@Column(name = "condutas")
	private String condutas;

	@Transient
	private Paciente paciente;

	public Evolucao() {
		dispositivoInvasivo = new DispositivoInvasivo();
		dispositivoInvasivo.setEvolucao(this);

		regulacaoNeurologica = new RegulacaoNeurologica();
		regulacaoNeurologica.setEvolucao(this);

		oxigenacao = new Oxigenacao();
		oxigenacao.setEvolucao(this);

		regulacaoCardiovascular = new RegulacaoCardiovascular();
		regulacaoCardiovascular.setEvolucao(this);

		nutricaoHidratacao = new NutricaoHidratacao();
		nutricaoHidratacao.setEvolucao(this);

		eliminacao = new Eliminacao();
		eliminacao.setEvolucao(this);

		regulacaoTermica = new RegulacaoTermica();
		regulacaoTermica.setEvolucao(this);

		comunicacaoPercepcao = new ComunicacaoPercepcao();
		comunicacaoPercepcao.setEvolucao(this);

		cuidadoCorporal = new CuidadoCorporal();
		cuidadoCorporal.setEvolucao(this);

		mobilidade = new Mobilidade();
		mobilidade.setEvolucao(this);

		necessidadePsicossocial = new NecessidadePsicossocial();
		necessidadePsicossocial.setEvolucao(this);

		alergiaMedicamento = new AlergiaMedicamento();
		alergiaMedicamento.setEvolucao(this);
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
		Evolucao other = (Evolucao) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Evolucao [id=" + id + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroInternacao() {
		return numeroInternacao;
	}

	public void setNumeroInternacao(Integer numeroInternacao) {
		this.numeroInternacao = numeroInternacao;
	}

	public LocalDateTime getDthrEvolucao() {
		return dthrEvolucao;
	}

	public void setDthrEvolucao(LocalDateTime dthrEvolucao) {
		this.dthrEvolucao = dthrEvolucao;
	}

	public String getEscalaFugulin() {
		return escalaFugulin;
	}

	public void setEscalaFugulin(String escalaFugulin) {
		this.escalaFugulin = escalaFugulin;
	}

	public String getObservacaoPrecaucao() {
		return observacaoPrecaucao;
	}

	public void setObservacaoPrecaucao(String observacaoPrecaucao) {
		this.observacaoPrecaucao = observacaoPrecaucao;
	}

	public DispositivoInvasivo getDispositivoInvasivo() {
		return dispositivoInvasivo;
	}

	public void setDispositivoInvasivo(DispositivoInvasivo dispositivoInvasivo) {
		this.dispositivoInvasivo = dispositivoInvasivo;
	}

	public Boolean getIndPrecaucaoPadrao() {
		return indPrecaucaoPadrao;
	}

	public void setIndPrecaucaoPadrao(Boolean indPrecaucaoPadrao) {
		this.indPrecaucaoPadrao = indPrecaucaoPadrao;
	}

	public Boolean getIndPrecaucaoContato() {
		return indPrecaucaoContato;
	}

	public void setIndPrecaucaoContato(Boolean indPrecaucaoContato) {
		this.indPrecaucaoContato = indPrecaucaoContato;
	}

	public Boolean getIndPrecaucaoGoticulas() {
		return indPrecaucaoGoticulas;
	}

	public void setIndPrecaucaoGoticulas(Boolean indPrecaucaoGoticulas) {
		this.indPrecaucaoGoticulas = indPrecaucaoGoticulas;
	}

	public Boolean getIndPrecaucaoAerossois() {
		return indPrecaucaoAerossois;
	}

	public void setIndPrecaucaoAerossois(Boolean indPrecaucaoAerossois) {
		this.indPrecaucaoAerossois = indPrecaucaoAerossois;
	}

	public Boolean getIndPrecaucaoReversa() {
		return indPrecaucaoReversa;
	}

	public void setIndPrecaucaoReversa(Boolean indPrecaucaoReversa) {
		this.indPrecaucaoReversa = indPrecaucaoReversa;
	}

	public LocalDate getDataReferencia() {
		return dataReferencia;
	}

	public void setDataReferencia(LocalDate dataReferencia) {
		this.dataReferencia = dataReferencia;
	}

	public Boolean getAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}

	public RegulacaoNeurologica getRegulacaoNeurologica() {
		return regulacaoNeurologica;
	}

	public void setRegulacaoNeurologica(RegulacaoNeurologica regulacaoNeurologica) {
		this.regulacaoNeurologica = regulacaoNeurologica;
	}

	public Oxigenacao getOxigenacao() {
		return oxigenacao;
	}

	public void setOxigenacao(Oxigenacao oxigenacao) {
		this.oxigenacao = oxigenacao;
	}

	public RegulacaoCardiovascular getRegulacaoCardiovascular() {
		return regulacaoCardiovascular;
	}

	public void setRegulacaoCardiovascular(RegulacaoCardiovascular regulacaoCardiovascular) {
		this.regulacaoCardiovascular = regulacaoCardiovascular;
	}

	public NutricaoHidratacao getNutricaoHidratacao() {
		return nutricaoHidratacao;
	}

	public void setNutricaoHidratacao(NutricaoHidratacao nutricaoHidratacao) {
		this.nutricaoHidratacao = nutricaoHidratacao;
	}

	public Eliminacao getEliminacao() {
		return eliminacao;
	}

	public void setEliminacao(Eliminacao eliminacao) {
		this.eliminacao = eliminacao;
	}

	public RegulacaoTermica getRegulacaoTermica() {
		return regulacaoTermica;
	}

	public void setRegulacaoTermica(RegulacaoTermica regulacaoTermica) {
		this.regulacaoTermica = regulacaoTermica;
	}

	public ComunicacaoPercepcao getComunicacaoPercepcao() {
		return comunicacaoPercepcao;
	}

	public void setComunicacaoPercepcao(ComunicacaoPercepcao comunicacaoPercepcao) {
		this.comunicacaoPercepcao = comunicacaoPercepcao;
	}

	public CuidadoCorporal getCuidadoCorporal() {
		return cuidadoCorporal;
	}

	public void setCuidadoCorporal(CuidadoCorporal cuidadoCorporal) {
		this.cuidadoCorporal = cuidadoCorporal;
	}

	public Mobilidade getMobilidade() {
		return mobilidade;
	}

	public void setMobilidade(Mobilidade mobilidade) {
		this.mobilidade = mobilidade;
	}

	public NecessidadePsicossocial getNecessidadePsicossocial() {
		return necessidadePsicossocial;
	}

	public void setNecessidadePsicossocial(NecessidadePsicossocial necessidadePsicossocial) {
		this.necessidadePsicossocial = necessidadePsicossocial;
	}

	public AlergiaMedicamento getAlergiaMedicamento() {
		return alergiaMedicamento;
	}

	public void setAlergiaMedicamento(AlergiaMedicamento alergiaMedicamento) {
		this.alergiaMedicamento = alergiaMedicamento;
	}

	public String getInformacoesComplementares() {
		return informacoesComplementares;
	}

	public void setInformacoesComplementares(String informacoesComplementares) {
		this.informacoesComplementares = informacoesComplementares;
	}

	public String getEvolucao() {
		return evolucao;
	}

	public void setEvolucao(String evolucao) {
		this.evolucao = evolucao;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Boolean isIndPrecaucaoPadrao() {
		return this.indPrecaucaoPadrao;
	}

	public Boolean isIndPrecaucaoContato() {
		return this.indPrecaucaoContato;
	}

	public Boolean isIndPrecaucaoGoticulas() {
		return this.indPrecaucaoGoticulas;
	}

	public Boolean isIndPrecaucaoAerossois() {
		return this.indPrecaucaoAerossois;
	}

	public Boolean isIndPrecaucaoReversa() {
		return this.indPrecaucaoReversa;
	}

	public Boolean isAtiva() {
		return this.ativa;
	}

	public String getProcedimentosRealizados() {
		return this.procedimentosRealizados;
	}

	public void setProcedimentosRealizados(String procedimentosRealizados) {
		this.procedimentosRealizados = procedimentosRealizados;
	}

	public String getDiagnosticosEnfermagem() {
		return this.diagnosticosEnfermagem;
	}

	public void setDiagnosticosEnfermagem(String diagnosticosEnfermagem) {
		this.diagnosticosEnfermagem = diagnosticosEnfermagem;
	}

	public String getCondutas() {
		return this.condutas;
	}

	public void setCondutas(String condutas) {
		this.condutas = condutas;
	}

}
