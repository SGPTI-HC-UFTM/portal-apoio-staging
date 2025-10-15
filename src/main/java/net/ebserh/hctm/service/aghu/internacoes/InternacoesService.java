package net.ebserh.hctm.service.aghu.internacoes;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import net.ebserh.hctm.dto.aghu.internacoes.InternacoesCidDto;
import net.ebserh.hctm.model.aghu.UnidadeFuncional;
import net.ebserh.hctm.model.aghu.internacoes.Internacao;
import net.ebserh.hctm.model.aghu.internacoes.TipoAltaMedica;
import net.ebserh.hctm.exception.CustomRuntimeException;


@Stateless
public class InternacoesService {
	
	private static final Logger logger = Logger.getAnonymousLogger();
		
	@PersistenceContext
	private EntityManager entityManager;
		
	public Internacao buscaPorSeq(Integer seq) {
		try {		
			return entityManager.find(Internacao.class, seq);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar a internação.");
		}
	}
	
	
	public Internacao buscaAtivaPorProntuario(Integer prontuario) {
		List<Internacao> internacoes = entityManager.createQuery("select i "
				+ "from Internacao i "
				+ "join i.paciente p "
				+ "where i.indPacienteInternado = 'S' "
				+ "and p.prontuario = :prontuario", Internacao.class)
				.setParameter("prontuario", prontuario)
				.getResultList();
		
		if (!internacoes.isEmpty())
			return internacoes.get(0);
		
		return null;
	}

	public List<Internacao> buscaAtivasPorProntuarioIn(List<Integer> prontuarios) {
		try {
			if (prontuarios == null || prontuarios.isEmpty())
				throw new CustomRuntimeException("É necessário informar ao menos um prontuáiro.");

			return entityManager
				.createNamedQuery("Internacao.findAtivasByProntuarioIn", Internacao.class)
				.setParameter("prontuarios", prontuarios)
				.getResultList();
		} catch (CustomRuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar as internações.");
		}
	}

	public List<Internacao> buscaSeqIn(List<Integer> seqs) {
		try {
			return entityManager.createQuery(
					"from Internacao where seq in (:seqs)", Internacao.class)
					.setParameter("seqs", seqs)
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao pesquisar as internações.");
		}
	}

	public List<InternacoesCidDto> buscaInternacoesPorCid(LocalDate dataReferencia) {
		if (dataReferencia == null)
			throw new CustomRuntimeException("É necessário informar a data de referência.");

		try {
			@SuppressWarnings("unchecked")
			List<Object[]> rset = (List<Object[]>) entityManager
				.createNamedQuery("Internacao.topInternacoesPorCid")
				.setParameter("ano", dataReferencia.getYear())
				.setParameter("mes", dataReferencia.getMonthValue())
				.getResultList();

			List<InternacoesCidDto> lista = new ArrayList<>();
			for (Object[] r : rset) {
				InternacoesCidDto dto = new InternacoesCidDto();
				dto.setCid((String) r[0]);
				dto.setInternacoes(((BigInteger) r[1]).intValue());
				dto.setPercentual((Double) r[2]);

				lista.add(dto);
			}

			return lista;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os dados de internação.");
		}
	}

	public List<InternacoesCidDto> buscaObitosPorCid(LocalDate dataReferencia) {
		if (dataReferencia == null)
			throw new CustomRuntimeException("É necessário informar a data de referência.");

		try {
			@SuppressWarnings("unchecked")
			List<Object[]> rset = (List<Object[]>) entityManager
				.createNamedQuery("Internacao.topObitosPorCid")
				.setParameter("ano", dataReferencia.getYear())
				.setParameter("mes", dataReferencia.getMonthValue())
				.getResultList();

			List<InternacoesCidDto> lista = new ArrayList<>();
			for (Object[] r : rset) {
				InternacoesCidDto dto = new InternacoesCidDto();
				dto.setCid((String) r[0]);
				dto.setInternacoes(((BigInteger) r[1]).intValue());
				dto.setPercentual((Double) r[2]);

				lista.add(dto);
			}

			return lista;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar o número de óbitos.");
		}
	}

	public Integer buscaPacientesInternadosPorData(LocalDate dataReferencia) {
		if (dataReferencia == null)
			throw new CustomRuntimeException("É necessário informar a data de referência.");

		try {
			Long count = entityManager
				.createNamedQuery("Internacao.countPacientesByAnoMes", Long.class)
				.setParameter("ano", dataReferencia.getYear())
				.setParameter("mes", dataReferencia.getMonthValue())
				.getSingleResult();

			return count.intValue();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os pacientes internados.");
		}
	}

	public Integer buscaPacientesInternadosPorSexoData(String sexo, LocalDate dataReferencia) {
		if (dataReferencia == null)
			throw new CustomRuntimeException("É necessário informar a data de referência.");

		try {
			Long count = entityManager
				.createNamedQuery("Internacao.countInternacoesBySexoAnoMes", Long.class)
				.setParameter("sexo", sexo)
				.setParameter("ano", dataReferencia.getYear())
				.setParameter("mes", dataReferencia.getMonthValue())
				.getSingleResult();

			return count.intValue();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os pacientes internados.");
		}
	}

	public Integer buscaObitosPorSexoData(String sexo, LocalDate dataReferencia) {
		if (dataReferencia == null)
			throw new CustomRuntimeException("É necessário informar a data de referência.");

		try {
			Long count = entityManager
				.createNamedQuery("Internacao.countObitosBySexoAnoMes", Long.class)
				.setParameter("sexo", sexo)
				.setParameter("ano", dataReferencia.getYear())
				.setParameter("mes", dataReferencia.getMonthValue())
				.getSingleResult();

			return count.intValue();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar o número de óbitos.");
		}
	}

	public Integer buscaAltasPorData(LocalDate dataReferencia) {
		if (dataReferencia == null)
			throw new CustomRuntimeException("É necessário informar a data de referência.");

		try {
			Long count = entityManager
				.createNamedQuery("Internacao.countAltasByAnoMes", Long.class)
				.setParameter("ano", dataReferencia.getYear())
				.setParameter("mes", dataReferencia.getMonthValue())
				.getSingleResult();

			return count.intValue();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os pacientes internados.");
		}
	}

	public Integer buscaDesistenciasPorData(LocalDate dataReferencia) {
		if (dataReferencia == null)
			throw new CustomRuntimeException("É necessário informar a data de referência.");

		try {
			Long count = entityManager
				.createNamedQuery("Internacao.countAltasByDescricaoAnoMes", Long.class)
				.setParameter("ano", dataReferencia.getYear())
				.setParameter("mes", dataReferencia.getMonthValue())
				.setParameter("descricao", TipoAltaMedica.DESISTENCIA)
				.getSingleResult();

			return count.intValue();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os pacientes internados.");
		}
	}

	public Integer buscaEvasoesPorData(LocalDate dataReferencia) {
		if (dataReferencia == null)
			throw new CustomRuntimeException("É necessário informar a data de referência.");

		try {
			Long count = entityManager
				.createNamedQuery("Internacao.countAltasByDescricaoAnoMes", Long.class)
				.setParameter("ano", dataReferencia.getYear())
				.setParameter("mes", dataReferencia.getMonthValue())
				.setParameter("descricao", TipoAltaMedica.EVASAO)
				.getSingleResult();

			return count.intValue();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os pacientes internados.");
		}
	}

	public Integer buscaTransferenciasPorData(LocalDate dataReferencia) {
		if (dataReferencia == null)
			throw new CustomRuntimeException("É necessário informar a data de referência.");

		try {
			Long count = entityManager
				.createNamedQuery("Internacao.countAltasByDescricaoAnoMes", Long.class)
				.setParameter("ano", dataReferencia.getYear())
				.setParameter("mes", dataReferencia.getMonthValue())
				.setParameter("descricao", TipoAltaMedica.TRANSFERENCIA)
				.getSingleResult();

			return count.intValue();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os pacientes internados.");
		}
	}

	public Integer buscaObitosPorData(LocalDate dataReferencia) {
		if (dataReferencia == null)
			throw new CustomRuntimeException("É necessário informar a data de referência.");

		try {
			Long total = 0L;
			total += entityManager
				.createNamedQuery("Internacao.countAltasByDescricaoAnoMes", Long.class)
				.setParameter("ano", dataReferencia.getYear())
				.setParameter("mes", dataReferencia.getMonthValue())
				.setParameter("descricao", TipoAltaMedica.OBITO)
				.getSingleResult();

			total += entityManager
				.createNamedQuery("Internacao.countAltasByDescricaoAnoMes", Long.class)
				.setParameter("ano", dataReferencia.getYear())
				.setParameter("mes", dataReferencia.getMonthValue())
				.setParameter("descricao", TipoAltaMedica.OBITO_MAIS_24H)
				.getSingleResult();

			total += entityManager
				.createNamedQuery("Internacao.countAltasByDescricaoAnoMes", Long.class)
				.setParameter("ano", dataReferencia.getYear())
				.setParameter("mes", dataReferencia.getMonthValue())
				.setParameter("descricao", TipoAltaMedica.OBITO_MENOS_24H)
				.getSingleResult();

			return total.intValue();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os pacientes com óbito.");
		}
	}

	public Map<String, Integer> buscaInternacoesPorFaixaEtaria(String sexo, LocalDate dataReferencia) {
		if (dataReferencia == null)
			throw new CustomRuntimeException("É necessário informar a data de referência.");

		try {
			@SuppressWarnings("unchecked")
			List<Object[]> rset = (List<Object[]>) entityManager
				.createNamedQuery("Internacao.internacoesPorSexoEIdade")
				.setParameter("ano", dataReferencia.getYear())
				.setParameter("mes", dataReferencia.getMonthValue())
				.setParameter("sexo", sexo)
				.getResultList();

			Map<String, Integer> mapa = new LinkedHashMap<>();
			Integer idadeReferencia = 4;
			Integer quantidade = 0;
			for (Object[] r : rset) {
				Integer idade = ((Double) r[0]).intValue();
				if (idadeReferencia == null || idade <= idadeReferencia) {
					quantidade += ((BigInteger) r[1]).intValue();
					continue;
				}

				mapa.put(String.format("%d - %d", idadeReferencia - 4, idadeReferencia),
					quantidade);
				quantidade = ((BigInteger) r[1]).intValue();
				if (idadeReferencia < 79) {
					idadeReferencia += 5;
				} else { 
					// Último intervalo (80+)
					idadeReferencia = null;
				}
			}

			mapa.put("80+", quantidade);

			return mapa;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar as internações.");
		}
	}

	public Map<String, Integer> buscaObitosPorFaixaEtaria(String sexo, LocalDate dataReferencia) {
		if (dataReferencia == null)
			throw new CustomRuntimeException("É necessário informar a data de referência.");

		try {
			@SuppressWarnings("unchecked")
			List<Object[]> rset = (List<Object[]>) entityManager
				.createNamedQuery("Internacao.obitosPorSexoEIdade")
				.setParameter("ano", dataReferencia.getYear())
				.setParameter("mes", dataReferencia.getMonthValue())
				.setParameter("sexo", sexo)
				.getResultList();

			Map<String, Integer> mapa = new LinkedHashMap<>();
			Integer idadeReferencia = 4;
			Integer quantidade = 0;
			for (Object[] r : rset) {
				Integer idade = ((Double) r[0]).intValue();
				if (idadeReferencia == null || idade <= idadeReferencia) {
					quantidade += ((BigInteger) r[1]).intValue();
					continue;
				}

				mapa.put(String.format("%d - %d", idadeReferencia - 4, idadeReferencia),
					quantidade);
				quantidade = ((BigInteger) r[1]).intValue();
				if (idadeReferencia < 79) {
					idadeReferencia += 5;
				} else { 
					// Último intervalo (80+)
					idadeReferencia = null;
				}
			}

			mapa.put("80+", quantidade);

			return mapa;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar o número de óbitos.");
		}
	}

	public List<Internacao> buscaInternacoesKanban() {
		try {
			List<Internacao> internacoes = entityManager.createNamedQuery("Internacao.findByLeitoUnidadeFuncionalSigla", Internacao.class)
				.setParameter("sigla", UnidadeFuncional.SIGLA_PS_ADULTO)
				.getResultList();

			return internacoes;
		} catch (CustomRuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar as internações.");
		}
	}

	public List<Internacao> buscaInternacoesKanbanPagina(Integer pagina) {
		try {
			List<Internacao> internacoes = entityManager.createNamedQuery("Internacao.findByLeitoUnidadeFuncionalSigla", Internacao.class)
				.setParameter("sigla", UnidadeFuncional.SIGLA_PS_ADULTO)
				.setMaxResults(10)
				.setFirstResult(10 * pagina)
				.getResultList();

			return internacoes;
		} catch (CustomRuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar as internações.");
		}
	}

}
