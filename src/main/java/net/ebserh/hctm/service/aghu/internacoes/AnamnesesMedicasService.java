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
public class AnamnesesMedicasService {
	
	private static final Logger logger = Logger.getLogger(AnamnesesMedicasService.class.getName());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final String QUERY_ANAMNESES = 
			  "SELECT "
			  + "	tmp1.qtd AS com_preceptoria, "
			  + "	tmp2.qtd AS total "
			  + "FROM ( "
			  + "	SELECT COUNT(*) AS qtd "
			  + "	FROM agh.mpm_anamneses AS ma "
			  + "	WHERE EXTRACT(YEAR FROM ma.dthr_criacao) = :ano "
			  + "		AND EXTRACT(MONTH FROM ma.dthr_criacao) = :mes "
			  + "		AND ma.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			  + "		AND ma.tql_codigo = 2 "
			  + ") AS tmp1, ( "
			  + "	SELECT COUNT(*) AS qtd "
			  + "	FROM agh.mpm_anamneses AS ma "
			  + "	WHERE EXTRACT(YEAR FROM ma.dthr_criacao) = :ano "
			  + "		AND EXTRACT(MONTH FROM ma.dthr_criacao) = :mes "
			  + "		AND ma.tql_codigo = 2 "
			  + ") AS tmp2";
	public IndicadorPreceptoriaDto buscaPorMes(int mes, int ano) {
		try {
			Object[] rset = (Object[]) entityManager
					.createNativeQuery(QUERY_ANAMNESES)
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
