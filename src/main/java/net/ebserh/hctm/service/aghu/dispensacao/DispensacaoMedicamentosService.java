package net.ebserh.hctm.service.aghu.dispensacao;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class DispensacaoMedicamentosService {
	
	@Inject
	private Logger logger;
	
	@PersistenceContext
    private EntityManager entityManager;

    private static final String QUERY_DISPENSADOS = 
          "SELECT "
        + "    SUM(adm.qtde_dispensada) "
        + "FROM agh.afa_dispensacao_mdtos adm "
        + "    JOIN agh.mpm_prescricao_medicas mpm ON adm.pme_atd_seq = mpm.atd_seq AND adm.pme_seq = mpm.seq "
        + "WHERE ((adm.tod_seq IS NULL) OR (adm.tod_seq != 2)) "
        + "    AND adm.qtde_dispensada > 0 "
		+ "    AND adm.ind_situacao = 'D' "
        + "    AND DATE_PART('MONTH', mpm.dt_referencia) = :mes "
        + "    AND DATE_PART('YEAR', mpm.dt_referencia) = :ano";

    private static final String QUERY_NAO_DISPENSADOS = 
          "SELECT "
        + "    SUM(adm.qtde_dispensada) "
        + "FROM agh.afa_dispensacao_mdtos adm "
        + "    JOIN agh.mpm_prescricao_medicas mpm ON adm.pme_atd_seq = mpm.atd_seq AND adm.pme_seq = mpm.seq "
        + "WHERE adm.tod_seq = 2 "
        + "    AND adm.qtde_dispensada > 0 "
        + "    AND adm.ind_sit_item_prescrito != 'EG' "
        + "    AND DATE_PART('MONTH', mpm.dt_referencia) = :mes "
        + "    AND DATE_PART('YEAR', mpm.dt_referencia) = :ano";
	
	public Double buscaDispensadosPorAnoMes(int ano, int mes) {
        try {
			Double sum = (Double) entityManager
					.createNativeQuery(QUERY_DISPENSADOS)
					.setParameter("ano", ano)
					.setParameter("mes", mes)
					.getSingleResult();
			
			if (sum != null)
                return sum;
			
			return 0.0;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar o indicador.");
		}
	}

	public Double buscaNaoDispensadosPorAnoMes(int ano, int mes) {
        try {
			Double sum = (Double) entityManager
					.createNativeQuery(QUERY_NAO_DISPENSADOS)
					.setParameter("ano", ano)
					.setParameter("mes", mes)
					.getSingleResult();
			
			if (sum != null)
                return sum; 
			
			return 0.0;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar o indicador.");
		}
	}

}

