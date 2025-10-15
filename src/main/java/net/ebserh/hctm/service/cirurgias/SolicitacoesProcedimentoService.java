package net.ebserh.hctm.service.cirurgias;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.aghu.Cid;
import net.ebserh.hctm.model.aghu.Especialidade;
import net.ebserh.hctm.model.aghu.faturamento.ItemProcedimentoHospitalar;
import net.ebserh.hctm.model.aghu.pacientes.Paciente;
import net.ebserh.hctm.model.cirurgias.EspecialidadeCirurgica;
import net.ebserh.hctm.model.cirurgias.LocalOrigem;
import net.ebserh.hctm.model.cirurgias.LogAlteracao;
import net.ebserh.hctm.model.cirurgias.Prioridade;
import net.ebserh.hctm.model.cirurgias.SolicitacaoProcedimento;
import net.ebserh.hctm.model.cirurgias.StatusSolicitacaoProcedimento;
import net.ebserh.hctm.model.cirurgias.TipoPrecaucao;
import net.ebserh.hctm.service.aghu.EspecialidadesService;
import net.ebserh.hctm.service.aghu.faturamento.CidsService;
import net.ebserh.hctm.service.aghu.faturamento.ProcedimentosService;
import net.ebserh.hctm.service.aghu.pacientes.PacientesService;

@Stateless
public class SolicitacoesProcedimentoService {

	private static final Logger logger = Logger.getAnonymousLogger();

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private PacientesService pacienteService;

	@Inject
	private EspecialidadesService especialidadeService;

	@Inject
	private ProcedimentosService procedimentoService;

	@Inject
	private CidsService cidsService;

	/*
	 * Insere uma solicitação caso não exista solicitação em aberto para o
	 * paciente/especialidade/procedimento
	 */
	public void insert(SolicitacaoProcedimento sp) {

		StatusSolicitacaoProcedimento ssp = entityManager.find(StatusSolicitacaoProcedimento.class,
				StatusSolicitacaoProcedimento.REGISTRADO);

		Prioridade prioridade = entityManager.createNamedQuery("Prioridade.findByDescricao", Prioridade.class)
				.setParameter("descricao", "NORMAL").getSingleResult();

		try {

			SolicitacaoProcedimento spTmp = entityManager
					.createNamedQuery("SolicitacaoProcedimento.findSolicitacaoExistente", SolicitacaoProcedimento.class)
					.setParameter("prontuario", sp.getProntuario())
					.setParameter("especialidade", sp.getEspecialidade())
					.setParameter("pseq", sp.getProcedimentoSeq())
					.setParameter("phoseq", sp.getProcedimentoPhoSeq())
					.setParameter("status", ssp).getSingleResult();

			if (spTmp != null) {
				throw new CustomRuntimeException("Já existe uma solicitação registrada.");
			}

		}
		// Sem nenhum resultado, registrar procedimento
		catch (NoResultException e) {

			sp.setStatus(ssp);
			sp.setPrioridade(prioridade);
			entityManager.persist(sp);

		} 
		catch(CustomRuntimeException e) {
			throw e;
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao inserir solicitação.");
		}

	}

	/*
	 * Solicitações de procedimento por procedimento/especialidade
	 */
	public List<SolicitacaoProcedimento> listaPorEspecialidadeProcedimento(
			Especialidade especialidade, ItemProcedimentoHospitalar procedimento) {

		List<SolicitacaoProcedimento> lista = entityManager
				.createNamedQuery("SolicitacaoProcedimento.findSolicitacoes", SolicitacaoProcedimento.class)
				.setParameter("especialidade", especialidade.getSeq())
				.setParameter("pseq", procedimento.getItemProcedimentoHospitalarPK().getSeq())
				.setParameter("phoseq", procedimento.getItemProcedimentoHospitalarPK().getPhoSeq())
				.getResultList();

		// Carrega os nomes dos pacientes
		for (SolicitacaoProcedimento sp : lista) {
			Paciente p = pacienteService.buscaPorProntuario(sp.getProntuario());
			sp.setPaciente(p);
			sp.setNomeEspecialidade(especialidade.getNome());
			sp.setNomeProcedimento(procedimento.getDescricao());
		}

		return lista;
	}

	public List<SolicitacaoProcedimento> listaPorEspecialidade(Especialidade especialidade) {
		List<SolicitacaoProcedimento> lista = entityManager
				.createNamedQuery("SolicitacaoProcedimento.listaPorEspecialidade", SolicitacaoProcedimento.class)
				.setParameter("especialidade", especialidade.getSeq())
				.getResultList();

		// Carrega os nomes dos pacientes
		for (SolicitacaoProcedimento sp : lista) {
			Paciente p = pacienteService.buscaPorProntuario(sp.getProntuario());
			sp.setPaciente(p);
			sp.setNomeEspecialidade(especialidade.getNome());

			if (sp.getAghuCidSeq() != null) {
				Cid cid = cidsService.buscaCidPorId(sp.getAghuCidSeq());
				sp.setCid(cid);
			}

			if (sp.getProcedimentoSeq() != null && sp.getProcedimentoPhoSeq() != null) {
				ItemProcedimentoHospitalar itemProcedimentoHospitalar = 
					procedimentoService.buscaPorId(sp.getProcedimentoPhoSeq(), sp.getProcedimentoSeq());
				sp.setItemProcedimentoHospitalar(itemProcedimentoHospitalar);
			}
		}

		return lista;
	}

	public List<SolicitacaoProcedimento> listaAguardandoRealizacao() {
		List<SolicitacaoProcedimento> lista = entityManager
			.createNamedQuery("SolicitacaoProcedimento.listaAguardandoRealizacao", SolicitacaoProcedimento.class)
			.getResultList();

		// Carrega os nomes dos pacientes
		for (SolicitacaoProcedimento sp : lista) {
			Paciente p = pacienteService.buscaPorProntuario(sp.getProntuario());
			sp.setPaciente(p);

			Especialidade e = especialidadeService.buscaPorCodigo(sp.getEspecialidade());
			sp.setNomeEspecialidade(e.getNome());
			
			if (sp.getAghuCidSeq() != null) {
				Cid cid = cidsService.buscaCidPorId(sp.getAghuCidSeq());
				sp.setCid(cid);
			}

			if (sp.getProcedimentoSeq() != null && sp.getProcedimentoPhoSeq() != null) {
				ItemProcedimentoHospitalar itemProcedimentoHospitalar = 
					procedimentoService.buscaPorId(sp.getProcedimentoPhoSeq(), sp.getProcedimentoSeq());
				sp.setItemProcedimentoHospitalar(itemProcedimentoHospitalar);
			}
		}

		Collections.sort(lista, new Comparator<SolicitacaoProcedimento>() {
			@Override
			public int compare(SolicitacaoProcedimento sp1, SolicitacaoProcedimento sp2) {
				if (!sp1.getNomeEspecialidade().equals(sp2.getNomeEspecialidade()))
					return sp1.getNomeEspecialidade().compareTo(sp2.getNomeEspecialidade());

				// Prioridade por ordem decrescente
				if (sp1.getPrioridade() != null && sp2.getPrioridade() != null)
					return sp2.getPrioridade().getId().compareTo(sp1.getPrioridade().getId());

				if (sp1.getPrioridade() != null && sp2.getPrioridade() == null)
					return -1;

				if (sp1.getPrioridade() == null && sp2.getPrioridade() != null)
					return 1;

				return 0;
			}
		});

		return lista;
	}

	/*
	 * Solicitações de procedimento por prontuário
	 */
	public List<SolicitacaoProcedimento> listaPorProntuario(Integer prontuario) {
		try {
			List<SolicitacaoProcedimento> lista = entityManager
					.createNamedQuery("SolicitacaoProcedimento.findByProntuario", SolicitacaoProcedimento.class)
					.setParameter("prontuario", prontuario).getResultList();

			// Carrega as especialidades e procedimentos
			for (SolicitacaoProcedimento sp : lista) {
				Especialidade esp = especialidadeService.buscaPorCodigo(sp.getEspecialidade());
				if (esp == null)
					throw new CustomRuntimeException("Especialidade não encontrada.");
				sp.setNomeEspecialidade(esp.getNome());

				Paciente pac = pacienteService.buscaPorProntuario(sp.getProntuario());
				if (pac == null)
					throw new CustomRuntimeException("Paciente não encontrado.");
				sp.setPaciente(pac);
		
				if (sp.getProcedimentoPhoSeq() != null && sp.getProcedimentoSeq() != null) {
					ItemProcedimentoHospitalar proc = procedimentoService.buscaProcedimentoPorCodigo(sp.getProcedimentoPhoSeq(),
						sp.getProcedimentoSeq());
					if (proc == null)
						throw new CustomRuntimeException("Procedimento não encontrado.");
					sp.setNomeProcedimento(proc.getDescricao());
				}
			}

			return lista;
		} catch (CustomRuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar as solicitações.");
		}
	}

	/*
	 * Lista de prioridades
	 */
	public List<Prioridade> listaPrioridades() {
		return entityManager.createNamedQuery("Prioridade.findAll", Prioridade.class)
				.getResultList();
	}

	public List<StatusSolicitacaoProcedimento> listaStatus() {
		return entityManager
				.createNamedQuery("StatusSolicitacaoProcedimento.findAll", StatusSolicitacaoProcedimento.class)
				.getResultList();
	}

	public SolicitacaoProcedimento find(Integer id) {
		return entityManager.find(SolicitacaoProcedimento.class, id);
	}

	/*
	 * Registra uma alteração de solicitação
	 */
	public void registraAlteracao(SolicitacaoProcedimento sp, String justificativa, String usuario) {

		SolicitacaoProcedimento spOld = find(sp.getId());

		if (spOld.getStatus().equals(sp.getStatus()) &&
				spOld.getPrioridade().equals(sp.getPrioridade()))

			throw new CustomRuntimeException("Nenhum alteração realizada");
		else {
			LogAlteracao log = new LogAlteracao();
			log.setSolicitacaoProcedimento(sp);
			log.setPrioridadeAnterior(spOld.getPrioridade());
			log.setPrioridadeNova(sp.getPrioridade());
			log.setStatusAnterior(spOld.getStatus());
			log.setStatusNovo(sp.getStatus());
			log.setDtAlteracao(new Date());
			log.setJustificativa(justificativa);
			log.setUsuario(usuario);

			entityManager.merge(log);

			entityManager.merge(sp);

		}
	}

	/*
	 * Busca as alterações de determinado procedimento
	 */
	public List<LogAlteracao> buscaAlteracoes(SolicitacaoProcedimento sp) {

		return entityManager
				.createNamedQuery("LogAlteracao.findBySolicitacao", LogAlteracao.class)
				.setParameter("solicitacao", sp)
				.getResultList();
	}

	/*
	 * Solicitações de procedimento por procedimento ou especialidade
	 */
	public List<SolicitacaoProcedimento> listaPorEspecialidadeOuProcedimento(String siglaEspecialidade, Long sigtap, Boolean apenasSemOnus) {
		try {
			List<SolicitacaoProcedimento> filaProcedimento = new ArrayList<>();

			if (!siglaEspecialidade.isBlank() && sigtap != null) {
				try {
					String sigla = siglaEspecialidade.split(" ")[0].trim();
					Especialidade especialidade = especialidadeService.buscaPorSigla(sigla);
					ItemProcedimentoHospitalar procedimento = procedimentoService.buscaPorSigtap(sigtap);

					filaProcedimento = entityManager
							.createNamedQuery("SolicitacaoProcedimento.findSolicitacoes", SolicitacaoProcedimento.class)
							.setParameter("especialidade", especialidade.getSeq())
							.setParameter("pseq", procedimento.getItemProcedimentoHospitalarPK().getSeq())
							.setParameter("phoseq", procedimento.getItemProcedimentoHospitalarPK().getPhoSeq())
							.getResultList();
				} catch (CustomRuntimeException e) {
					throw e;
				} catch (Exception e) {
					throw new CustomRuntimeException("Ocorreu um erro ao buscar a lista de procedimentos");
				}
			} else if (!siglaEspecialidade.isBlank()) {
				try {
					String sigla = siglaEspecialidade.split(" ")[0].trim();
					Especialidade especialidade = especialidadeService.buscaPorSigla(sigla);

					if (apenasSemOnus) {
						filaProcedimento = entityManager
							.createNamedQuery("SolicitacaoProcedimento.listaSemSigtapPorEspecialidade", SolicitacaoProcedimento.class)
							.setParameter("especialidade", especialidade.getSeq())
							.getResultList();
					} else {
						filaProcedimento = entityManager.createNamedQuery("SolicitacaoProcedimento.listaPorEspecialidade",
									SolicitacaoProcedimento.class)
							.setParameter("especialidade", especialidade.getSeq())
							.getResultList();
					}
				} catch (CustomRuntimeException e) {
					throw e;
				} catch (Exception e) {
					throw new CustomRuntimeException("Ocorreu um erro ao buscar a lista de procedimentos");
				}
			} else if (sigtap != null) {
				try {
					ItemProcedimentoHospitalar procedimento = procedimentoService.buscaPorSigtap(sigtap);
					filaProcedimento = entityManager
						.createNamedQuery("SolicitacaoProcedimento.listaPorProcedimento", SolicitacaoProcedimento.class)
						.setParameter("pseq", procedimento.getItemProcedimentoHospitalarPK().getSeq())
						.setParameter("phoseq", procedimento.getItemProcedimentoHospitalarPK().getPhoSeq())
						.getResultList();
				} catch (CustomRuntimeException e) {
					throw e;
				} catch (Exception e) {
					throw new CustomRuntimeException("Ocorreu um erro ao buscar a lista de procedimentos");
				}
			}

			// Carrega os nomes dos pacientes
			for (SolicitacaoProcedimento sp : filaProcedimento) {
				Paciente p = pacienteService.buscaPorProntuario(sp.getProntuario());
				sp.setPaciente(p);

				Especialidade especialidade = especialidadeService.buscaPorCodigo(sp.getEspecialidade());
				sp.setNomeEspecialidade(especialidade.getNome());

				if (sp.getProcedimentoSeq() != null && sp.getProcedimentoPhoSeq() != null) {
					ItemProcedimentoHospitalar procedimento = procedimentoService.buscaProcedimentoPorCodigo(sp.getProcedimentoPhoSeq(),
						sp.getProcedimentoSeq());
					sp.setNomeProcedimento(procedimento.getDescricao());
				}
			}

			return filaProcedimento;
		} catch (CustomRuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao listar as solicitações.");
		}
	}

	public List<LocalOrigem> buscaLocaisOrigem() {
		try {
			return entityManager.createNamedQuery("LocalOrigem.findAll", LocalOrigem.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os locais de origem.");
		}
	}

	public List<TipoPrecaucao> buscaTiposPrecaucao() {
		try {
			return entityManager.createNamedQuery("TipoPrecaucao.findAll", TipoPrecaucao.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os tipos de precaução.");
		}
	}

	public List<EspecialidadeCirurgica> buscaEspecialidadesCirurgicas() {
		try {
			return entityManager.createNamedQuery("EspecialidadeCirurgica.findAll", EspecialidadeCirurgica.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar as especialidades cirúrgicas.");
		}
	}

}
