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
public class InternacoesEnfermagemService {
	
	private static final Logger logger = Logger.getLogger(InternacoesEnfermagemService.class.getName());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final String QUERY_INTERNACOES_ENFERMAGEM = 
			"SELECT tmp1.qtd AS com_preceptoria, "
			+ "	tmp2.qtd AS total "
			+ "FROM ("
			+ "	SELECT COUNT(DISTINCT ai.seq) AS qtd "
			+ "	FROM agh.ain_internacoes ai "
			+ "		JOIN agh.agh_atendimentos aa ON aa.int_seq = ai.seq	"
			+ "		LEFT JOIN agh.mpm_anamneses ma ON aa.seq = ma.atd_seq "
			+ "		LEFT JOIN agh.mpm_evolucoes me ON me.ana_seq = ma.seq "
			+ "		LEFT JOIN agh.epe_prescricoes_enfermagem epe on epe.atd_seq = aa.seq "
			+ "	WHERE EXTRACT(YEAR FROM ai.dthr_internacao) = :ano  "
			+ " 	AND EXTRACT(MONTH FROM ai.dthr_internacao) = :mes "
			+ "     AND ( "
			+ "			ma.tql_codigo IN (3, 5) AND "
			+ "				(ma.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			+ "					OR me.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			+ "					OR epe.ser_vin_codigo IN (5, 6, 8, 10, 11, 12)) "
			+ "     )"
			+ ") AS tmp1, "
			+ "("
			+ "	SELECT COUNT(DISTINCT ai.seq) AS qtd "
			+ "	FROM agh.ain_internacoes ai "
			+ "		JOIN agh.agh_atendimentos aa ON aa.int_seq = ai.seq	"
			+ "		LEFT JOIN agh.mpm_anamneses ma ON aa.seq = ma.atd_seq "
			+ "		LEFT JOIN agh.mpm_evolucoes me ON me.ana_seq = ma.seq "
			+ "		LEFT JOIN agh.epe_prescricoes_enfermagem epe on epe.atd_seq = aa.seq "
			+ "	WHERE EXTRACT(YEAR FROM ai.dthr_internacao) = :ano "
			+ "		AND EXTRACT(MONTH FROM ai.dthr_internacao) = :mes "
			+ "     AND (ma.tql_codigo IN (3, 5) OR epe IS NOT NULL) "
			+ ") AS tmp2";
	
	public IndicadorPreceptoriaDto buscaPorMes(int mes, int ano) {
		try {
			Object[] rset = (Object[]) entityManager
					.createNativeQuery(QUERY_INTERNACOES_ENFERMAGEM)
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
			logger.log(Level.SEVERE, "Erro gerando indicador de internaçõem com preceptoria em enfermagem", e);
			throw new CustomRuntimeException("Ocorreu um erro ao gerar o indicador.");
		}
	}

}
