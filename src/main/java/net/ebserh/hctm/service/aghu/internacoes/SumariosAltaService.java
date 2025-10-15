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
public class SumariosAltaService {
	
	private static final Logger logger = Logger.getLogger(SumariosAltaService.class.getName());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final String QUERY_SUMARIOS_ALTA = 
			  "SELECT tmp1.qtd AS comPreceptoria, "
			  + "     tmp2.qtd AS total "
			  + "FROM ( "
			  + "    SELECT COUNT(DISTINCT mas) AS qtd "
			  + "    FROM agh.mpm_alta_sumarios mas "
			  + "    WHERE DATE_PART('YEAR', mas.criado_em) = :ano "
			  + "		AND DATE_PART('MONTH', mas.criado_em) = :mes "
			  + "		AND mas.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			  + ") AS tmp1, ( "
			  + "    SELECT COUNT(DISTINCT mas) AS qtd "
			  + "    FROM agh.mpm_alta_sumarios mas "
			  + "    WHERE DATE_PART('YEAR', mas.criado_em) = :ano "
			  + "    	AND DATE_PART('MONTH', mas.criado_em) = :mes "
			  + ") AS tmp2";
	public IndicadorPreceptoriaDto buscaPorMes(int mes, int ano) {
		try {
			Object[] rset = (Object[]) entityManager
					.createNativeQuery(QUERY_SUMARIOS_ALTA)
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
