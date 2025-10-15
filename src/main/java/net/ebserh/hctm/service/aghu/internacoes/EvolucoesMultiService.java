package net.ebserh.hctm.service.aghu.internacoes;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import net.ebserh.hctm.dto.aghu.indicadores.IndicadorPreceptoriaDto;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class EvolucoesMultiService {
	
	private static final Logger logger = Logger.getAnonymousLogger();
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final String QUERY_EVOLUCOES_MULTI = 
			  "SELECT tmp1.qtd AS comPreceptoria, "
			  + "     tmp2.qtd AS total "
			  + "FROM ( "
			  + "    SELECT COUNT(DISTINCT me.seq) AS qtd "
			  + "    FROM agh.mpm_evolucoes me "
			  + "    	JOIN agh.mpm_anamneses ma ON me.ana_seq = ma.seq "
			  + "       JOIN agh.rap_tipos_qualificacao rtq ON ma.tql_codigo = rtq.codigo "
			  + "    WHERE DATE_PART('YEAR', me.dthr_criacao) = :ano "
			  + "		AND DATE_PART('MONTH', me.dthr_criacao) = :mes "
			  + " 		AND rtq.ind_acessa_multiprofissional = 'S' "
			  + "		AND me.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			  + ") AS tmp1, ( "
			  + "    SELECT COUNT(DISTINCT me.seq) AS qtd "
			  + "    FROM agh.mpm_evolucoes me "
			  + "		JOIN agh.mpm_anamneses ma ON me.ana_seq = ma.seq "
			  + "       JOIN agh.rap_tipos_qualificacao rtq ON ma.tql_codigo = rtq.codigo "
			  + "    WHERE DATE_PART('YEAR', me.dthr_criacao) = :ano "
			  + "    	AND DATE_PART('MONTH', me.dthr_criacao) = :mes "
			  + " 		AND rtq.ind_acessa_multiprofissional = 'S' "
			  + ") AS tmp2";
	public IndicadorPreceptoriaDto buscaPorMes(int mes, int ano) {
		try {
			Object[] rset = (Object[]) entityManager
					.createNativeQuery(QUERY_EVOLUCOES_MULTI)
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
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao gerar o indicador.");
		}
	}

}
