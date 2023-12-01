package net.ebserh.hctm.controller.enfermagem;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import net.ebserh.hctm.dto.enfermagem.EscalaBradenDto;
import net.ebserh.hctm.dto.enfermagem.EscalaFugulinDto;
import net.ebserh.hctm.dto.enfermagem.EscalaGlasgowDto;
import net.ebserh.hctm.dto.enfermagem.EscalaMorseDto;
import net.ebserh.hctm.dto.enfermagem.EscalaRassDto;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.aghu.internacoes.Internacao;
import net.ebserh.hctm.model.enfermagem.Evolucao;
import net.ebserh.hctm.service.aghu.internacoes.InternacoesService;
import net.ebserh.hctm.service.enfermagem.EvolucoesEnfermagemService;
import net.ebserh.hctm.util.FacesUtils;

@Named
@ViewScoped
public class EvolucoesEnfermagemController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	private InternacoesService internacoesService;

	@Inject
	private EvolucoesEnfermagemService evolucoesService;

	@Inject
	private DialogExportaEvolucaoController dialogExportaEvolucaoController;

	private Integer prontuarioPesquisa;

	private LocalDate dataReferenciaPesquisa = LocalDate.now();

	private Internacao internacao;

	private Evolucao evolucao;

	private Evolucao evolucaoExistente;

	private EscalaFugulinDto escalaFugulin = new EscalaFugulinDto();

	private EscalaGlasgowDto escalaGlasgow = new EscalaGlasgowDto();

	private EscalaBradenDto escalaBraden = new EscalaBradenDto();

	private EscalaMorseDto escalaMorse = new EscalaMorseDto();

	private EscalaRassDto escalaRass = new EscalaRassDto();

	public void pesquisaEvolucoes() {
		if (prontuarioPesquisa == null) {
			FacesUtils.showError("É necessário informar o prontuário para pesquisa.");
			return;
		}

		if (dataReferenciaPesquisa == null) {
			FacesUtils.showError("É necessário informar a data de referência para pesquisa.");
			return;
		}

		try {
			internacao = internacoesService.buscaAtivaPorProntuario(prontuarioPesquisa);
			if (internacao == null) {
				FacesUtils.showError("Nenhuma internação encontrada para o prontuário informado.");
				return;
			}

			evolucaoExistente = evolucoesService.pesquisaEvolucaoPorInternacaoData(internacao.getSeq(),
					dataReferenciaPesquisa);
			if (evolucaoExistente != null) {
				PrimeFaces.current().executeScript("PF('dlgEvolucaoExistente').show()");
				return;
			}

			evolucao = new Evolucao();
			evolucao.setDataReferencia(dataReferenciaPesquisa);
			evolucao.setNumeroInternacao(internacao.getSeq());
		} catch (CustomRuntimeException e) {
			FacesUtils.showError(e.getMessage());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao pesquisar as internações.");
		}
	}

	public void salva() {
		if (evolucao == null) {
			FacesUtils.showError("É necessário selecionar uma evolução.");
			return;
		}

		if (evolucao.getDataReferencia() == null) {
			FacesUtils.showError("É necessário informar a data de referência.");
			return;
		}

		try {
			evolucoesService.salvaEvolucao(evolucao);
			FacesUtils.showInfo("Evolução salva com sucesso!");
		} catch (CustomRuntimeException e) {
			FacesUtils.showError(e.getMessage());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao salvar a evolução.");
		}
	}

	public void editaEvolucaoExistente() {
		if (evolucaoExistente == null) {
			FacesUtils.showError("Nenhuma evolução existente encontrada.");
			return;
		}

		evolucao = evolucaoExistente;

		PrimeFaces.current().executeScript("PF('dlgEvolucaoExistente').hide()");
	}

	public void inativaEvolucaoExistente() {
		if (evolucaoExistente == null) {
			FacesUtils.showError("Nenhuma evolução existente encontrada.");
			return;
		}

		try {
			evolucoesService.inativaEvolucao(evolucaoExistente);
			evolucao = new Evolucao();
			evolucao.setDataReferencia(evolucaoExistente.getDataReferencia());
			evolucao.setNumeroInternacao(evolucaoExistente.getNumeroInternacao());
			FacesUtils.showInfo("Evolução existente inativada com sucesso!");
			PrimeFaces.current().executeScript("PF('dlgEvolucaoExistente').hide()");
		} catch (CustomRuntimeException e) {
			FacesUtils.showError(e.getMessage());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao inativar a evolução existente.");
		}
	}

	public void fecharDialogEvolucaoExistente() {
		PrimeFaces.current().executeScript("PF('dlgEvolucaoExistente').hide()");
	}

	public void calcularEscalaFugulin() {

		try {

			String erro = escalaFugulin.validar();
			if (!erro.isBlank()) {
				FacesUtils.showError(erro);
				return;
			}

			Integer total = escalaFugulin.calcScore();
			if (total > 0 && total < 17) {
				evolucao.setEscalaFugulin("MI");
			} else if (total >= 18 && total <= 22) {
				evolucao.setEscalaFugulin("IM");
			} else if (total >= 23 && total <= 28) {
				evolucao.setEscalaFugulin("AD");
			} else if (total >= 29 && total <= 34) {
				evolucao.setEscalaFugulin("SI");
			} else {
				evolucao.setEscalaFugulin("IS");
			}

			PrimeFaces.current().executeScript("PF('dlgEscalaFugulin').hide()");
			FacesUtils.showInfo("Escala selecionada! Escore: " + total.toString());

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao calcular escore.");
		}

	}

	public void calcularEscalaGlasgow() {

		try {

			String erro = escalaGlasgow.validar();
			if (!erro.isBlank()) {
				FacesUtils.showError(erro);
				return;
			}

			Integer total = escalaGlasgow.calcScore();

			evolucao.getRegulacaoNeurologica().setGlasgow(total);

			PrimeFaces.current().executeScript("PF('dlgEscalaGlasgow').hide()");
			FacesUtils.showInfo("Escore: " + total.toString());

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao calcular escore.");
		}

	}

	public void calcularEscalaBraden() {

		try {

			String erro = escalaBraden.validar();
			if (!erro.isBlank()) {
				FacesUtils.showError(erro);
				return;
			}

			Integer total = escalaBraden.calcScore();
			if (total >= 19) {
				evolucao.getRegulacaoTermica().setBraden("SR");
			}
			if (total >= 15 && total <= 18) {
				evolucao.getRegulacaoTermica().setBraden("LE");
			}
			if (total >= 13 && total <= 14) {
				evolucao.getRegulacaoTermica().setBraden("MO");
			}
			if (total <= 12) {
				evolucao.getRegulacaoTermica().setBraden("AL");
			}

			PrimeFaces.current().executeScript("PF('dlgEscalaBraden').hide()");
			FacesUtils.showInfo("Escore: " + total.toString());

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao calcular escore.");
		}
	}

	public void calcularEscalaMorse() {

		try {

			String erro = escalaMorse.validar();
			if (!erro.isBlank()) {
				FacesUtils.showError(erro);
				return;
			}

			Integer total = escalaMorse.calcScore();

			if (total <= 24) {
				evolucao.getMobilidade().setMorse("BR");
			}
			if (total >= 25 && total <= 44) {
				evolucao.getMobilidade().setMorse("MO");
			}
			if (total >= 45) {
				evolucao.getMobilidade().setMorse("EL");
			}

			PrimeFaces.current().executeScript("PF('dlgEscalaMorse').hide()");
			FacesUtils.showInfo("Escore: " + total.toString());

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao calcular escore.");
		}
	}

	public void calcularEscalaRass() {

		try {

			String erro = escalaRass.validar();
			if (!erro.isBlank()) {
				FacesUtils.showError(erro);
				return;
			}

			evolucao.getRegulacaoNeurologica().setRass(escalaRass.getClassificacao());

			PrimeFaces.current().executeScript("PF('dlgEscalaRass').hide()");
			FacesUtils.showInfo("Escore: " + escalaRass.getClassificacao());

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao calcular escore.");
		}
	}

	public void exporta() {
		if (evolucao == null) {
			FacesUtils.showError("É necessário selecionar uma evolução.");
			return;
		}

		try {		
			if (evolucao.getPaciente() == null) 
				evolucoesService.completaPaciente(evolucao);
			
			dialogExportaEvolucaoController.atualiza(evolucao);
			PrimeFaces.current().executeScript("PF('dlgExportaEvolucao').show()");
		} catch (CustomRuntimeException e) {
			FacesUtils.showError(e.getMessage());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao exportar a evolução.");
		}
	}

	public Integer getProntuarioPesquisa() {
		return prontuarioPesquisa;
	}

	public void setProntuarioPesquisa(Integer prontuarioPesquisa) {
		this.prontuarioPesquisa = prontuarioPesquisa;
	}

	public Internacao getInternacao() {
		return internacao;
	}

	public void setInternacao(Internacao internacao) {
		this.internacao = internacao;
	}

	public Evolucao getEvolucao() {
		return evolucao;
	}

	public void setEvolucao(Evolucao evolucao) {
		this.evolucao = evolucao;
	}

	public LocalDate getDataReferenciaPesquisa() {
		return dataReferenciaPesquisa;
	}

	public void setDataReferenciaPesquisa(LocalDate dataReferenciaPesquisa) {
		this.dataReferenciaPesquisa = dataReferenciaPesquisa;
	}

	public Evolucao getEvolucaoExistente() {
		return evolucaoExistente;
	}

	public void setEvolucaoExistente(Evolucao evolucaoExistente) {
		this.evolucaoExistente = evolucaoExistente;
	}

	public EscalaFugulinDto getEscalaFugulin() {
		return escalaFugulin;
	}

	public void setEscalaFugulin(EscalaFugulinDto escalaFugulin) {
		this.escalaFugulin = escalaFugulin;
	}

	public EscalaGlasgowDto getEscalaGlasgow() {
		return escalaGlasgow;
	}

	public void setEscalaGlasgow(EscalaGlasgowDto escalaGlasgow) {
		this.escalaGlasgow = escalaGlasgow;
	}

	public EscalaBradenDto getEscalaBraden() {
		return escalaBraden;
	}

	public void setEscalaBraden(EscalaBradenDto escalaBraden) {
		this.escalaBraden = escalaBraden;
	}

	public EscalaMorseDto getEscalaMorse() {
		return escalaMorse;
	}

	public void setEscalaMorse(EscalaMorseDto escalaMorse) {
		this.escalaMorse = escalaMorse;
	}

	public EscalaRassDto getEscalaRass() {
		return escalaRass;
	}

	public void setEscalaRass(EscalaRassDto escalaRass) {
		this.escalaRass = escalaRass;
	}
}
