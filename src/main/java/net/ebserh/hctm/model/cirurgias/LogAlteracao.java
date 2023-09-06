
package net.ebserh.hctm.model.cirurgias;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "cirurgia", name="log_alteracoes")
@NamedQuery(name = "LogAlteracao.findBySolicitacao", query = "select l from LogAlteracao l where solicitacaoProcedimento = :solicitacao order by dtAlteracao desc")
public class LogAlteracao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="solicitacao_procedimento_id")
	private SolicitacaoProcedimento solicitacaoProcedimento;
	
	@ManyToOne
	@JoinColumn(name="status_anterior")
	private StatusSolicitacaoProcedimento statusAnterior;
	
	@ManyToOne
	@JoinColumn(name="status_novo")
	private StatusSolicitacaoProcedimento statusNovo;
	
	@ManyToOne
	@JoinColumn(name="prioridade_anterior")
	private Prioridade prioridadeAnterior;
	
	@ManyToOne
	@JoinColumn(name="prioridade_nova")
	private Prioridade prioridadeNova;
	
	@Column(name="dt_alteracao")
	private Date dtAlteracao;
	
	@Column(name="justificativa")
	private String justificativa;
	
	@Column(name="usuario")
	private String usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SolicitacaoProcedimento getSolicitacaoProcedimento() {
		return solicitacaoProcedimento;
	}

	public void setSolicitacaoProcedimento(SolicitacaoProcedimento solicitacaoProcedimento) {
		this.solicitacaoProcedimento = solicitacaoProcedimento;
	}

	public StatusSolicitacaoProcedimento getStatusAnterior() {
		return statusAnterior;
	}

	public void setStatusAnterior(StatusSolicitacaoProcedimento statusAnterior) {
		this.statusAnterior = statusAnterior;
	}

	public StatusSolicitacaoProcedimento getStatusNovo() {
		return statusNovo;
	}

	public void setStatusNovo(StatusSolicitacaoProcedimento statusNovo) {
		this.statusNovo = statusNovo;
	}

	public Prioridade getPrioridadeAnterior() {
		return prioridadeAnterior;
	}

	public void setPrioridadeAnterior(Prioridade prioridadeAnterior) {
		this.prioridadeAnterior = prioridadeAnterior;
	}

	public Prioridade getPrioridadeNova() {
		return prioridadeNova;
	}

	public void setPrioridadeNova(Prioridade prioridadeNova) {
		this.prioridadeNova = prioridadeNova;
	}

	public Date getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
