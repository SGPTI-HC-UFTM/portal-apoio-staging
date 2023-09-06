package net.ebserh.hctm.service.aghu.internacoes;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.ebserh.hctm.dto.aghu.indicadores.IndicadorPreceptoriaDto;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class EvolucoesEnfermagemService {
	
	@Inject
	private Logger logger;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final String QUERY_EVOLUCOES_ENFERMAGEM = 
			  "SELECT tmp1.qtd AS comPreceptoria, "
			  + "     tmp2.qtd AS total "
			  + "FROM ( "
			  + "    SELECT COUNT(DISTINCT me.seq) AS qtd "
			  + "    FROM agh.mpm_evolucoes me "
			  + "    	JOIN agh.mpm_anamneses ma ON me.ana_seq = ma.seq "
			  + "    WHERE DATE_PART('YEAR', me.dthr_criacao) = :ano "
			  + "		AND DATE_PART('MONTH', me.dthr_criacao) = :mes "
			  + "       AND ma.tql_codigo IN (3, 5) "
			  + "		AND me.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			  + ") AS tmp1, ( "
			  + "    SELECT COUNT(DISTINCT me.seq) AS qtd "
			  + "    FROM agh.mpm_evolucoes me "
			  + "		JOIN agh.mpm_anamneses ma ON me.ana_seq = ma.seq "
			  + "    WHERE DATE_PART('YEAR', me.dthr_criacao) = :ano "
			  + "    	AND DATE_PART('MONTH', me.dthr_criacao) = :mes "
			  + "		AND ma.tql_codigo IN (3, 5) "
			  + ") AS tmp2";
	public IndicadorPreceptoriaDto buscaPorMes(int mes, int ano) {
		try {
			Object[] rset = (Object[]) entityManager
					.createNativeQuery(QUERY_EVOLUCOES_ENFERMAGEM)
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
