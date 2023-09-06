package net.ebserh.hctm.service.aghu.internacoes;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.ebserh.hctm.dto.aghu.indicadores.IndicadorPreceptoriaDto;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class PrescricoesMedicasService {
	
	private static final Logger logger = Logger.getLogger(PrescricoesMedicasService.class.getName());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final String QUERY_PRESCRICOES_MEDICAS = 
			  "SELECT "
			  + "	tmp1.qtd AS com_preceptoria, "
			  + "	tmp2.qtd AS total "
			  + "FROM ( "
			  + "	SELECT COUNT(*) AS qtd "
			  + "	FROM agh.mpm_prescricao_medicas mpm "
			  + "	WHERE EXTRACT(YEAR FROM mpm.criado_em) = :ano "
			  + "		AND EXTRACT(MONTH FROM mpm.criado_em) = :mes "
			  + "		AND mpm.ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
			  + ") AS tmp1, ( "
			  + "	SELECT COUNT(*) AS qtd "
			  + "	FROM agh.mpm_prescricao_medicas mpm "
			  + "	WHERE EXTRACT(YEAR FROM mpm.criado_em) = :ano "
			  + "		AND EXTRACT(MONTH FROM mpm.criado_em) = :mes "
			  + ") AS tmp2";
	
	public IndicadorPreceptoriaDto buscaPorMes(int mes, int ano) {
		try {
			Object[] rset = (Object[]) entityManager
					.createNativeQuery(QUERY_PRESCRICOES_MEDICAS)
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
			logger.log(Level.SEVERE, "Erro gerando indicador de prescrições médicas com preceptoria", e);
			throw new CustomRuntimeException("Ocorreu um erro ao gerar o indicador.");
		}
	}

}
