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
public class InternacoesMultiService {
	
	private static final Logger logger = Logger.getLogger(InternacoesMultiService.class.getName());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final String QUERY_INTERNACOES = "SELECT tmp1.qtd AS com_preceptoria, "
			+ "	tmp2.qtd AS total "
			+ "FROM ( "
			+ "	SELECT COUNT(DISTINCT ai.seq) AS qtd "
			+ "    FROM agh.ain_internacoes ai "
			+ "    	JOIN agh.agh_atendimentos aa ON aa.int_seq = ai.seq "
			+ "        JOIN agh.mpm_anamneses ma ON aa.seq = ma.atd_seq "
			+ "        JOIN agh.mpm_evolucoes me ON me.ana_seq = ma.seq "
			+ "        JOIN agh.rap_tipos_qualificacao rtq ON ma.tql_codigo = rtq.codigo "
			+ "    WHERE EXTRACT(YEAR FROM ai.dthr_internacao) = :ano "
			+ "    	AND EXTRACT(MONTH FROM ai.dthr_internacao) = :mes "
			+ "    	AND rtq.ind_acessa_multiprofissional = 'S' "
			+ "        AND ( "
			+ "        	ma.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			+ "        	OR me.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			+ "        ) "
			+ ") AS tmp1, "
			+ "( "
			+ "	SELECT COUNT(DISTINCT ai.seq) AS qtd "
			+ "    FROM agh.ain_internacoes ai "
			+ "		JOIN agh.agh_atendimentos aa ON aa.int_seq = ai.seq "
			+ "        JOIN agh.mpm_anamneses ma ON aa.seq = ma.atd_seq "
			+ "        JOIN agh.mpm_evolucoes me ON me.ana_seq = ma.seq "
			+ "        JOIN agh.rap_tipos_qualificacao rtq ON ma.tql_codigo = rtq.codigo "
			+ "    WHERE EXTRACT(YEAR FROM ai.dthr_internacao) = :ano "
			+ "    	AND EXTRACT(MONTH FROM ai.dthr_internacao) = :mes "
			+ "    	AND rtq.ind_acessa_multiprofissional = 'S' "
			+ ") AS tmp2";
	public IndicadorPreceptoriaDto buscaPorMes(int mes, int ano) {
		
		try {
			Object[] rset = (Object[]) entityManager
					.createNativeQuery(QUERY_INTERNACOES)
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
			logger.log(Level.SEVERE, "Erro gerando indicador de internações multiprofissionais com preceptoria", e);
			throw new CustomRuntimeException("Ocorreu um erro ao gerar o indicador.");
		}
		
	}

}
