package net.ebserh.hctm.model.cirurgias;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import net.ebserh.hctm.model.aghu.Cid;
import net.ebserh.hctm.model.aghu.faturamento.ItemProcedimentoHospitalar;
import net.ebserh.hctm.model.aghu.pacientes.Paciente;

@Entity
@Table(schema = "cirurgia", name = "solicitacoes_procedimento")
@NamedQuery(name = "SolicitacaoProcedimento.findSolicitacaoExistente", 
	query = "SELECT sp "
		+ "FROM SolicitacaoProcedimento sp "
		+ "WHERE sp.prontuario = :prontuario "
		+ "AND sp.especialidade = :especialidade "
		+ "AND sp.procedimentoSeq = :pseq "
		+ "AND sp.procedimentoPhoSeq = :phoseq "
		+ "AND sp.status = :status")
@NamedQuery(name = "SolicitacaoProcedimento.findByProntuario", 
	query = "SELECT sp FROM SolicitacaoProcedimento sp "
		+ "WHERE sp.prontuario = :prontuario "
		+ "ORDER BY sp.dtCadastroSolicitacao")
@NamedQuery(name = "SolicitacaoProcedimento.findSolicitacoes", 
	query = "SELECT sp "
		+ "FROM SolicitacaoProcedimento sp "
		+ "WHERE sp.especialidade = :especialidade "
		+ "AND sp.procedimentoSeq = :pseq "
		+ "AND sp.procedimentoPhoSeq = :phoseq "
		+ "AND sp.status.descricao = 'AGUARDANDO REALIZAÇÃO' "
		+ "ORDER BY sp.prioridade.id DESC, sp.dtCadastroSolicitacao")
@NamedQuery(name = "SolicitacaoProcedimento.listaPorEspecialidade", 
	query = "SELECT sp "
		+ "FROM SolicitacaoProcedimento sp "
		+ "WHERE sp.especialidade = :especialidade "
		+ "AND sp.status.descricao = 'AGUARDANDO REALIZAÇÃO' "
		+ "ORDER BY sp.prioridade.id DESC, sp.dtCadastroSolicitacao")
@NamedQuery(name = "SolicitacaoProcedimento.listaAguardandoRealizacao", 
	query = "SELECT sp "
		+ "FROM SolicitacaoProcedimento sp "
		+ "WHERE sp.status.descricao = 'AGUARDANDO REALIZAÇÃO'")
@NamedQuery(name = "SolicitacaoProcedimento.listaSemSigtapPorEspecialidade", 
	query = "SELECT sp "
		+ "FROM SolicitacaoProcedimento sp "
		+ "WHERE sp.especialidade = :especialidade "
		+ "AND sp.status.descricao = 'AGUARDANDO REALIZAÇÃO' "
		+ "AND sp.procedimentoSeq = null "
		+ "AND sp.procedimentoPhoSeq = null "
		+ "ORDER BY sp.prioridade.id DESC, sp.dtCadastroSolicitacao")
@NamedQuery(name = "SolicitacaoProcedimento.listaPorProcedimento", 
	query = "SELECT sp "
		+ "FROM SolicitacaoProcedimento sp "
		+ "WHERE sp.procedimentoSeq = :pseq "
		+ "AND sp.procedimentoPhoSeq = :phoseq "
		+ "AND sp.status.descricao = 'AGUARDANDO REALIZAÇÃO' "
		+ "ORDER BY sp.prioridade.id DESC, sp.dtCadastroSolicitacao")
public class SolicitacaoProcedimento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer prontuario;

	private Integer especialidade;

	@Column(name = "procedimento_seq")
	private Integer procedimentoSeq;

	@Column(name = "procedimento_pho_seq")
	private Integer procedimentoPhoSeq;

	@Column(name = "dt_cadastro_solicitacao")
	private LocalDate dtCadastroSolicitacao = LocalDate.now();

	@Column(name = "dt_consulta_anestesista")
	private LocalDate dtConsultaAnestesista;

	@Column(name = "aghu_cid_seq")
	private Integer aghuCidSeq;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private StatusSolicitacaoProcedimento status;

	@ManyToOne
	@JoinColumn(name = "prioridade_id")
	private Prioridade prioridade;

	@Transient
	private Paciente paciente;

	@Transient
	private String nomeEspecialidade;

	@Transient
	private String nomeProcedimento;

	@Transient
	private Cid cid;

	@Transient
	private ItemProcedimentoHospitalar itemProcedimentoHospitalar;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProntuario() {
		return prontuario;
	}

	public void setProntuario(Integer prontuario) {
		this.prontuario = prontuario;
	}

	public Integer getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Integer especialidade) {
		this.especialidade = especialidade;
	}

	public StatusSolicitacaoProcedimento getStatus() {
		return status;
	}

	public void setStatus(StatusSolicitacaoProcedimento status) {
		this.status = status;
	}

	public Integer getProcedimentoSeq() {
		return procedimentoSeq;
	}

	public void setProcedimentoSeq(Integer procedimentoSeq) {
		this.procedimentoSeq = procedimentoSeq;
	}

	public Integer getProcedimentoPhoSeq() {
		return procedimentoPhoSeq;
	}

	public void setProcedimentoPhoSeq(Integer procedimentoPhoSeq) {
		this.procedimentoPhoSeq = procedimentoPhoSeq;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getNomeEspecialidade() {
		return nomeEspecialidade;
	}

	public void setNomeEspecialidade(String nomeEspecialidade) {
		this.nomeEspecialidade = nomeEspecialidade;
	}

	public String getNomeProcedimento() {
		return nomeProcedimento;
	}

	public void setNomeProcedimento(String nomeProcedimento) {
		this.nomeProcedimento = nomeProcedimento;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public LocalDate getDtCadastroSolicitacao() {
		return dtCadastroSolicitacao;
	}

	public void setDtCadastroSolicitacao(LocalDate dtCadastroSolicitacao) {
		this.dtCadastroSolicitacao = dtCadastroSolicitacao;
	}

	public LocalDate getDtConsultaAnestesista() {
		return dtConsultaAnestesista;
	}

	public void setDtConsultaAnestesista(LocalDate dtConsultaAnestesista) {
		this.dtConsultaAnestesista = dtConsultaAnestesista;
	}

	public Integer getAghuCidSeq() {
		return aghuCidSeq;
	}

	public void setAghuCidSeq(Integer aghuCidSeq) {
		this.aghuCidSeq = aghuCidSeq;
	}

	public Cid getCid() {
		return cid;
	}

	public void setCid(Cid cid) {
		this.cid = cid;
	}

	public ItemProcedimentoHospitalar getItemProcedimentoHospitalar() {
		return itemProcedimentoHospitalar;
	}

	public void setItemProcedimentoHospitalar(ItemProcedimentoHospitalar itemProcedimentoHospitalar) {
		this.itemProcedimentoHospitalar = itemProcedimentoHospitalar;
	}

}
