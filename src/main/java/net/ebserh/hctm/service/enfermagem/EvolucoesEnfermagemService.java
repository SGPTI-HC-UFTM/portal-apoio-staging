package net.ebserh.hctm.service.enfermagem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;

import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.aghu.internacoes.Internacao;
import net.ebserh.hctm.model.enfermagem.Evolucao;
import net.ebserh.hctm.service.aghu.internacoes.InternacoesService;

@Stateless
public class EvolucoesEnfermagemService {
	
	private static final Logger logger = Logger.getAnonymousLogger();

	@Inject
	private InternacoesService internacoesService;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void salvaEvolucao(Evolucao evolucao) {
		if (evolucao == null)
			throw new CustomRuntimeException("É necessário selecionar uma evolução.");
		
		if (evolucao.getDataReferencia() == null)
			throw new CustomRuntimeException("É necessário informar a data de referência.");
		
		evolucao.setDthrEvolucao(LocalDateTime.now());
		if (evolucao.getId() == null) {
			evolucao.setAtiva(true);
			entityManager.persist(evolucao);
		} else { 
			entityManager.merge(evolucao);
		}
	}
	
	public Evolucao pesquisaEvolucaoPorInternacaoData(Integer internacao, LocalDate dataReferencia) {
		try {
			return entityManager
					.createNamedQuery("Evolucao.findAtivaByInternacaoDataReferencia", Evolucao.class)
					.setParameter("numeroInternacao", internacao)
					.setParameter("dataReferencia", dataReferencia)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw new CustomRuntimeException(
					"Mais de uma evolução encontrada para esta internação e data de referência");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao pesquisar as evoluções.");
		}
	}
	
	public void inativaEvolucao(Evolucao evolucao) {
		if (evolucao == null)
			throw new CustomRuntimeException("É necessário selecionar uma evolução.");
			
		if (evolucao.getId() == null)
			throw new CustomRuntimeException("A evolução selecionada não está ativa.");
		
		try {
			evolucao.setAtiva(false);
			entityManager.merge(evolucao);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao inativar a evolução.");
		}
	}

	public void completaPaciente(Evolucao evolucao) {
		if (evolucao.getPaciente() != null)
			return;

		if (evolucao.getNumeroInternacao() == null)
			throw new CustomRuntimeException("Nenhuma internação vinculada à evolução selecionada.");

		try {
			Internacao internacao = internacoesService.buscaPorSeq(evolucao.getNumeroInternacao());
			if (internacao == null)
				throw new CustomRuntimeException("Nenhuma internação encontrada com o código informado.");

			if (internacao.getPaciente() == null)
				throw new CustomRuntimeException("Nenhum paciente vinculado à internação selecionada.");

			evolucao.setPaciente(internacao.getPaciente());
		} catch (CustomRuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os dados do paciente.");
		}
	}

}
