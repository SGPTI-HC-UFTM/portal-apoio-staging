package net.ebserh.hctm.service.aghu.ambulatorio;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import net.ebserh.hctm.dto.aghu.indicadores.IndicadorPreceptoriaDto;
import net.ebserh.hctm.model.aghu.Especialidade;
import net.ebserh.hctm.model.aghu.ambulatorio.CondicaoAtendimento;
import net.ebserh.hctm.model.aghu.ambulatorio.Consulta;
import net.ebserh.hctm.model.aghu.ambulatorio.Grade;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class AmbulatorioService {
	
    @Inject
    private Logger logger;

	@PersistenceContext
	private EntityManager entityManager;
	
	private final String QUERY_AMBULATORIO_ELETIVOS = "SELECT "
			+ "	tmp1.qtd AS comPreceptoria, "
			+ "	tmp2.qtd AS total "
			+ "FROM ("
			+ "	SELECT "
			+ "		COUNT(DISTINCT ac.numero) AS qtd "
			+ "	FROM "
			+ "		agh.aac_consultas ac "
			+ "		JOIN agh.aac_grade_agendamen_consultas agac ON ac.grd_seq = agac.seq "
			+ "		JOIN agh.agh_especialidades ae ON agac.esp_seq = ae.seq "
			+ "		LEFT JOIN agh.mam_anamneses ma ON ac.numero = ma.con_numero "
			+ "		LEFT JOIN agh.mam_evolucoes me ON ac.numero = me.con_numero "
			+ "		LEFT JOIN agh.mam_receituarios mr ON ac.numero = mr.con_numero "
			+ "		LEFT JOIN agh.mam_atestados mat ON ac.numero = mat.con_numero "
			+ "		LEFT JOIN agh.mam_relatorio_atendimento mra ON ac.numero = mra.con_numero "
			+ "	WHERE "
			+ "		ae.ind_pronto_atendimento = 'N' "
			+ "		AND ac.ret_seq = 10 "
			+ "		AND DATE_PART('YEAR', ac.dt_consulta) = :ano "
			+ "		AND DATE_PART('MONTH', ac.dt_consulta) = :mes "
			+ "		AND ( "
			+ "			ma.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			+ "			OR me.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			+ "			OR mr.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			+ "			OR mat.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			+ "			OR mra.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			+ "		) "
			+ ") AS tmp1, ( "
			+ "	SELECT "
			+ "		COUNT(DISTINCT ac.numero) AS qtd "
			+ "	FROM "
			+ "		agh.aac_consultas ac "
			+ "		JOIN agh.aac_grade_agendamen_consultas agac ON ac.grd_seq = agac.seq "
			+ "		JOIN agh.agh_especialidades ae ON agac.esp_seq = ae.seq "
			+ "	WHERE "
			+ "		ae.ind_pronto_atendimento = 'N' "
			+ "		AND ac.ret_seq = 10 "
			+ "		AND DATE_PART('YEAR', ac.dt_consulta) = :ano "
			+ "		AND DATE_PART('MONTH', ac.dt_consulta) = :mes "
			+ ") AS tmp2";
	
	private final String QUERY_AMBULATORIO_URGENCIA = "SELECT "
			+ "	tmp1.qtd AS comPreceptoria, "
			+ "	tmp2.qtd AS total "
			+ "FROM ("
			+ "	SELECT "
			+ "		COUNT(DISTINCT ac.numero) AS qtd "
			+ "	FROM "
			+ "		agh.aac_consultas ac "
			+ "		JOIN agh.aac_grade_agendamen_consultas agac ON ac.grd_seq = agac.seq "
			+ "		JOIN agh.agh_especialidades ae ON agac.esp_seq = ae.seq "
			+ "		LEFT JOIN agh.mam_anamneses ma ON ac.numero = ma.con_numero "
			+ "		LEFT JOIN agh.mam_evolucoes me ON ac.numero = me.con_numero "
			+ "		LEFT JOIN agh.mam_receituarios mr ON ac.numero = mr.con_numero "
			+ "		LEFT JOIN agh.mam_atestados mat ON ac.numero = mat.con_numero "
			+ "		LEFT JOIN agh.mam_relatorio_atendimento mra ON ac.numero = mra.con_numero "
			+ "	WHERE "
			+ "		ae.ind_pronto_atendimento = 'S' "
			+ "		AND ac.ret_seq = 10 "
			+ "		AND DATE_PART('YEAR', ac.dt_consulta) = :ano "
			+ "		AND DATE_PART('MONTH', ac.dt_consulta) = :mes "
			+ "		AND ( "
			+ "			ma.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			+ "			OR me.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			+ "			OR mr.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			+ "			OR mat.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			+ "			OR mra.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			+ "		) "
			+ ") AS tmp1, ( "
			+ "	SELECT "
			+ "		COUNT(DISTINCT ac.numero) AS qtd "
			+ "	FROM "
			+ "		agh.aac_consultas ac "
			+ "		JOIN agh.aac_grade_agendamen_consultas agac ON ac.grd_seq = agac.seq "
			+ "		JOIN agh.agh_especialidades ae ON agac.esp_seq = ae.seq "
			+ "	WHERE "
			+ "		ae.ind_pronto_atendimento = 'S' "
			+ "		AND ac.ret_seq = 10 "
			+ "		AND DATE_PART('YEAR', ac.dt_consulta) = :ano "
			+ "		AND DATE_PART('MONTH', ac.dt_consulta) = :mes "
			+ ") AS tmp2";
	
	public IndicadorPreceptoriaDto buscaEletivosPorMes(int mes, int ano) {
		try {
			Object[] rset = (Object[]) entityManager
					.createNativeQuery(QUERY_AMBULATORIO_ELETIVOS)
					.setParameter("ano", ano)
					.setParameter("mes", mes)
					.getSingleResult();
			
			IndicadorPreceptoriaDto dto = new IndicadorPreceptoriaDto();
			
			if (rset != null && rset.length == 2) {
				dto.setComPreceptoria((BigInteger) rset[0]);
				dto.setSemPreceptoria(((BigInteger) rset[1]).subtract((BigInteger) rset[0]));
			}
			
			return dto;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erro gerando indicador de consultas com preceptoria", e);
			throw new CustomRuntimeException("Ocorreu um erro ao gerar o indicador.");
		}
	}
	
	public IndicadorPreceptoriaDto buscaUrgenciaPorMes(int mes, int ano) {
		try {
			Object[] rset = (Object[]) entityManager
					.createNativeQuery(QUERY_AMBULATORIO_URGENCIA)
					.setParameter("ano", ano)
					.setParameter("mes", mes)
					.getSingleResult();
			
			IndicadorPreceptoriaDto dto = new IndicadorPreceptoriaDto();
			
			if (rset != null && rset.length == 2) {
				dto.setComPreceptoria((BigInteger) rset[0]);
				dto.setSemPreceptoria(((BigInteger) rset[1]).subtract((BigInteger) rset[0]));
			}
			
			return dto;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erro gerando indicador de consultas com preceptoria", e);
			throw new CustomRuntimeException("Ocorreu um erro ao gerar o indicador.");
		}
	}

    public List<Consulta> buscaConsultasPorProntuario(Integer prontuario) {
        try {
            return entityManager
                .createNamedQuery("Consulta.findByProntuario", Consulta.class)
                .setParameter("prontuario", prontuario)
                .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as consultas.");
        }
    }

    public List<Consulta> buscaConsultasPorProntuarioLoginProfissional(Integer prontuario, String login) {
        if (prontuario == null)
            throw new CustomRuntimeException("É necessário informar o prontuário do paciente.");

        if (login == null || login.isBlank())
            throw new CustomRuntimeException("É necessário informar o login do profissional.");

        try {
            return entityManager
                .createNamedQuery("Consulta.findByProntuarioAndLoginResponsavelAtendimento", Consulta.class)
                .setParameter("login", login.toUpperCase())
                .setParameter("prontuario", prontuario)
                .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as consultas.");
        }
    }

    public List<Consulta> buscaGeradasPorGradePeriodo(Grade grade, LocalDate dataInicial, LocalDate dataFinal) {
        if (grade == null)
            throw new CustomRuntimeException("É necessário informar a grade.");

        if (dataInicial == null)
            throw new CustomRuntimeException("É necessário informar a data inicial.");

        if (dataFinal == null)
            throw new CustomRuntimeException("É necessário informar a data final.");

        try {
            return entityManager
                .createNamedQuery("Consulta.findGeradasByGradeAndDthrConsultaBetween", Consulta.class)
                .setParameter("grade", grade)
                .setParameter("dataInicial", dataInicial)
                .setParameter("dataFinal", dataFinal)
                .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as consultas.");
        }
    }

    public List<Consulta> buscaReconsultasGeradasPorEspecialidadePeriodo(Especialidade especialidade, LocalDate dataInicial, LocalDate dataFinal) {
        if (especialidade == null)
            throw new CustomRuntimeException("É necessário informar a especialidade.");

        if (dataInicial == null)
            throw new CustomRuntimeException("É necessário informar a data inicial.");

        if (dataFinal == null)
            throw new CustomRuntimeException("É necessário informar a data final.");

        CondicaoAtendimento condicaoReconsulta = null;
        try {
            condicaoReconsulta = entityManager
                .createNamedQuery("CondicaoAtendimento.findByDescricao", CondicaoAtendimento.class)
                .setParameter("descricao", CondicaoAtendimento.RECONSULTA)
                .getSingleResult();
        } catch (NoResultException e) {
            throw new CustomRuntimeException("Não foi possível obter a condição de atendimento 'Reconsulta'.");
        } catch (Exception e) {
            throw new CustomRuntimeException("Ocorreu um erro ao pesquisar a condição de atendimento reconsulta.");
        }

        try {
            return entityManager
                .createNamedQuery("Consulta.findGeradasByCondicaoAtendimentoAndEspecialidadeAndDthrConsultaBetween", Consulta.class)
                .setParameter("condicaoAtendimento", condicaoReconsulta)
                .setParameter("especialidade", especialidade)
                .setParameter("dataInicial", dataInicial)
                .setParameter("dataFinal", dataFinal)
                .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as consultas.");
        }
    }

    public List<Consulta> buscaInterconsultasGeradasPorEspecialidadePeriodo(Especialidade especialidade, LocalDate dataInicial, LocalDate dataFinal) {
        if (especialidade == null)
            throw new CustomRuntimeException("É necessário informar a especialidade.");

        if (dataInicial == null)
            throw new CustomRuntimeException("É necessário informar a data inicial.");

        if (dataFinal == null)
            throw new CustomRuntimeException("É necessário informar a data final.");

        CondicaoAtendimento condicaoInterconsulta = null;
        try {
            condicaoInterconsulta = entityManager
                .createNamedQuery("CondicaoAtendimento.findByDescricao", CondicaoAtendimento.class)
                .setParameter("descricao", CondicaoAtendimento.INTERCONSULTA)
                .getSingleResult();
        } catch (NoResultException e) {
            throw new CustomRuntimeException("Não foi possível obter a condição de atendimento 'Interconsulta'.");
        } catch (Exception e) {
            throw new CustomRuntimeException("Ocorreu um erro ao pesquisar a condição de atendimento interconsulta.");
        }

        try {
            return entityManager
                .createNamedQuery("Consulta.findGeradasByCondicaoAtendimentoAndEspecialidadeAndDthrConsultaBetween", Consulta.class)
                .setParameter("condicaoAtendimento", condicaoInterconsulta)
                .setParameter("especialidade", especialidade)
                .setParameter("dataInicial", dataInicial)
                .setParameter("dataFinal", dataFinal)
                .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as consultas.");
        }
    }

    public Consulta buscaConsultaPorNumero(Integer numero) {
        try {
            if (numero == null)
                throw new CustomRuntimeException("É necessário informar o número da consulta.");

            return entityManager.find(Consulta.class, numero);
        } catch (CustomRuntimeException e) {
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar a consulta.");
        }
    }

    public List<Consulta> buscaConsultasPorData(LocalDate dataReferencia) {
        if (dataReferencia == null)
            throw new CustomRuntimeException("É necessário informar a data de referência.");

        try {
            return entityManager
                .createNamedQuery("Consulta.findByData", Consulta.class)
                .setParameter("dataReferencia", dataReferencia)
                .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as consultas.");
        }
    }
 
}
