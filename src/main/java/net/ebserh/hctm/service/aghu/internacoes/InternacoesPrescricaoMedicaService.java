package net.ebserh.hctm.service.aghu.internacoes;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import net.ebserh.hctm.dto.aghu.indicadores.IndicadorPreceptoriaDto;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class InternacoesPrescricaoMedicaService {
	
	private static final Logger logger = Logger.getLogger(InternacoesPrescricaoMedicaService.class.getName());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	// Será considerada internação com preceptoria se mais de 30% das prescrições
	// naquele mês registradas por alunos
	private static final double LIMIAR_PRECEPTORIA = 0.3;

	private final String QUERY_INTERNACOES_PRESCRICAO = 
			  "SELECT "
			  + "	COUNT(tmp.int_seq) FILTER(WHERE (CAST(tmp.com_preceptoria AS REAL) / CAST(tmp.total AS REAL)) > :limiar) AS com_preceptoria, "
			  + "	COUNT (tmp.int_seq) AS total "
			  + "FROM ( "
			  + "	SELECT "
			  + "		aa.int_seq, "
			  + "		COUNT(mpm.seq) FILTER(WHERE mpm.ser_vin_codigo IN (5, 6, 8, 10, 11, 12)) AS com_preceptoria, "
			  + "		COUNT(mpm.seq) AS total "
			  + "	FROM "
			  + "		agh.agh_atendimentos AS aa "
			  + "		JOIN agh.mpm_prescricao_medicas AS mpm ON mpm.atd_seq = aa.seq "
			  + "	WHERE "
			  + "		aa.int_seq IS NOT NULL "
			  + "		AND DATE_PART('YEAR', mpm.criado_em) = :ano "
			  + "		AND DATE_PART('MONTH', mpm.criado_em) = :mes "
			  + "	GROUP BY aa.int_seq "
			  + ") AS tmp";
	public IndicadorPreceptoriaDto buscaPorMes(int mes, int ano) {
		try {
			Object[] rset = (Object[]) entityManager
					.createNativeQuery(QUERY_INTERNACOES_PRESCRICAO)
					.setParameter("limiar", LIMIAR_PRECEPTORIA)
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
			logger.log(Level.SEVERE, "Erro gerando indicador de internacoes com preceptoria em prescricao médica", e);
			throw new CustomRuntimeException("Ocorreu um erro ao gerar o indicador.");
		}
	}

}
