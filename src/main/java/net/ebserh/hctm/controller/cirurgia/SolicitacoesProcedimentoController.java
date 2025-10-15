package net.ebserh.hctm.controller.cirurgia;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.aghu.internacoes.Leito;
import net.ebserh.hctm.model.aghu.pacientes.Paciente;
import net.ebserh.hctm.model.cirurgias.EspecialidadeCirurgica;
import net.ebserh.hctm.model.cirurgias.LocalOrigem;
import net.ebserh.hctm.model.cirurgias.SolicitacaoProcedimentoCirurgico;
import net.ebserh.hctm.model.cirurgias.TipoPrecaucao;
import net.ebserh.hctm.service.aghu.pacientes.PacientesService;
import net.ebserh.hctm.service.cirurgias.SolicitacoesProcedimentoService;
import net.ebserh.hctm.util.FacesUtils;

@Named
@ViewScoped
public class SolicitacoesProcedimentoController implements Serializable {

	private static final Logger logger = Logger.getAnonymousLogger();

	@Inject
	private PacientesService pacientesService;

	@Inject
	private SolicitacoesProcedimentoService solicitacoesProcedimentoService;

	private Integer prontuario;

	private Paciente paciente;

	private LocalOrigem localOrigem;

	private List<LocalOrigem> locaisOrigem;

	private TipoPrecaucao tipoPrecaucao;

	private List<TipoPrecaucao> tiposPrecaucao;

	private Leito leito;

	private SolicitacaoProcedimentoCirurgico solicitacao;

	private List<EspecialidadeCirurgica> especialidadesCirurgicas;

	@PostConstruct
	public void init() {
		try {
			locaisOrigem = solicitacoesProcedimentoService.buscaLocaisOrigem();
			tiposPrecaucao = solicitacoesProcedimentoService.buscaTiposPrecaucao();
			especialidadesCirurgicas = solicitacoesProcedimentoService.buscaEspecialidadesCirurgicas();

			solicitacao = new SolicitacaoProcedimentoCirurgico();
			solicitacao.setDthrCirurgia(LocalDateTime.now().plusDays(1).withHour(7).withMinute(0));
			solicitacao.setTempoCirurgiaPrevisto(LocalTime.of(1, 0, 0));
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public void buscaPaciente() {
		try {
			paciente = pacientesService.buscaPorProntuario(prontuario);
			if (paciente == null) {
				FacesUtils.showError("Nenhum paciente encontrado com o prontuário informado.");
				return;
			}
		} catch (CustomRuntimeException e) {
			FacesUtils.showError(e.getMessage());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao pesquisar os pacientes.");
		}
	}

	public Integer getProntuario() {
		return prontuario;
	}

	public void setProntuario(Integer prontuario) {
		this.prontuario = prontuario;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LocalOrigem getLocalOrigem() {
		return localOrigem;
	}

	public void setLocalOrigem(LocalOrigem localOrigem) {
		this.localOrigem = localOrigem;
	}

	public List<LocalOrigem> getLocaisOrigem() {
		return locaisOrigem;
	}

	public void setLocaisOrigem(List<LocalOrigem> locaisOrigem) {
		this.locaisOrigem = locaisOrigem;
	}

	public TipoPrecaucao getTipoPrecaucao() {
		return tipoPrecaucao;
	}

	public void setTipoPrecaucao(TipoPrecaucao tipoPrecaucao) {
		this.tipoPrecaucao = tipoPrecaucao;
	}

	public List<TipoPrecaucao> getTiposPrecaucao() {
		return tiposPrecaucao;
	}

	public void setTiposPrecaucao(List<TipoPrecaucao> tiposPrecaucao) {
		this.tiposPrecaucao = tiposPrecaucao;
	}

	public Leito getLeito() {
		return leito;
	}

	public void setLeito(Leito leito) {
		this.leito = leito;
	}

	public SolicitacaoProcedimentoCirurgico getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(SolicitacaoProcedimentoCirurgico solicitacao) {
		this.solicitacao = solicitacao;
	}

	public List<EspecialidadeCirurgica> getEspecialidadesCirurgicas() {
		return especialidadesCirurgicas;
	}

	public void setEspecialidadesCirurgicas(List<EspecialidadeCirurgica> especialidadesCirurgicas) {
		this.especialidadesCirurgicas = especialidadesCirurgicas;
	}

}
