package net.ebserh.hctm.service.aghu.cirurgias;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.ebserh.hctm.dto.aghu.indicadores.IndicadorPreceptoriaDto;
import net.ebserh.hctm.model.aghu.cirurgias.ProcedimentoCirurgia;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class CirurgiasService {

	@Inject
	private Logger logger;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final String QUERY_CIRURGIAS = "SELECT "
		+ "	tmp1.qtd AS com_preceptoria, "
		+ "   tmp2.qtd AS total "
		+ "FROM ("
		+ "	SELECT COUNT(DISTINCT mc.seq) AS qtd "
		+ "   FROM agh.mbc_cirurgias mc "
		+ "   	JOIN agh.mbc_prof_cirurgias mpc ON mpc.crg_seq = mc.seq "
		+ "   WHERE "
		+ "   	mc.situacao = 'RZDA' "
		+ "       AND EXTRACT(YEAR FROM mc.data) = :ano "
		+ "       AND EXTRACT(MONTH FROM mc.data) = :mes "
		+ "       AND mpc.puc_ser_vin_codigo IN (5, 6, 8, 10, 11, 12) "
		+ ") AS tmp1, ("
		+ "	SELECT COUNT(DISTINCT mc.seq) AS qtd "
		+ "   FROM agh.mbc_cirurgias mc "
		+ "   WHERE "
		+ "   	EXTRACT(YEAR FROM mc.data) = :ano " 
		+ "       AND EXTRACT(MONTH FROM mc.data) = :mes "
		+ ") AS tmp2";
	
	public IndicadorPreceptoriaDto buscaPorMes(int mes, int ano) {
		try {
			Object[] rset = (Object[]) entityManager
					.createNativeQuery(QUERY_CIRURGIAS)
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
			logger.log(Level.SEVERE, "Erro gerando indicador de cirurgias com preceptoria", e);
			throw new CustomRuntimeException("Ocorreu um erro ao gerar o indicador.");
		}
	}

	public List<ProcedimentoCirurgia> buscaProcedimentosCirurgiasDataReferencia(LocalDate dataReferencia) {
		if (dataReferencia == null)
			throw new CustomRuntimeException("É necessário informar a data de referência.");

		try {
			return entityManager.createNamedQuery("ProcedimentoCirurgia.findByCirurgiaData", ProcedimentoCirurgia.class)
				.setParameter("dataReferencia", dataReferencia)
				.getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar as cirurgias.");
		}
	}

}
