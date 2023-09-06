package net.ebserh.hctm.service.aghu.faturamento;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import net.ebserh.hctm.model.aghu.faturamento.ItemProcedimentoHospitalar;
import net.ebserh.hctm.model.aghu.faturamento.ItemProcedimentoHospitalarPK;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class ProcedimentosService {

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    public List<ItemProcedimentoHospitalar> buscaItensProcedimentoHospitalar() {
        try {
            return entityManager
                    .createNamedQuery("ItemProcedimentoHospitalar.findAll", ItemProcedimentoHospitalar.class)
                    .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os procedimentos.");
        }
    }

    public List<ItemProcedimentoHospitalar> buscaItensProcedimentoHospitalarAtivos() {
        try {
            return entityManager
                .createNamedQuery("ItemProcedimentoHospitalar.findAtivos", ItemProcedimentoHospitalar.class)
                .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os procedimentos.");
        }
    }

    public ItemProcedimentoHospitalar buscaPorId(Integer phoSeq, Integer seq) {
        try {
            ItemProcedimentoHospitalarPK pk = new ItemProcedimentoHospitalarPK();
            pk.setPhoSeq(phoSeq);
            pk.setSeq(seq);

            return entityManager.find(ItemProcedimentoHospitalar.class, pk);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar o procedimento.");
        }
    }

    public ItemProcedimentoHospitalar buscaPorSigtap(Long sigtap) {
        try {
            // O AGHU permite o cadastro de mais de um procedimento com o mesmo SIGTAP
            List<ItemProcedimentoHospitalar> procedimentos = entityManager.createNamedQuery("ItemProcedimentoHospitalar.findByCodTabela", ItemProcedimentoHospitalar.class)
                .setParameter("codTabela", sigtap)
                .getResultList();
            
            if (procedimentos.isEmpty())
                return null;

            return procedimentos.get(0);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar o procedimento.");
        }
    }

    public ItemProcedimentoHospitalar buscaPorCodigo(Long codigo) {
        try {

            ItemProcedimentoHospitalar procedimento = entityManager
                    .createNamedQuery("ItemProcedimentoHospitalar.findByCodigo", ItemProcedimentoHospitalar.class)
                    .setParameter("codigo", codigo).getSingleResult();

            return procedimento;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar o procedimento.");
        }
    }

    public List<ItemProcedimentoHospitalar> buscaPorDescricao(String query) {
        try {

            List<ItemProcedimentoHospitalar> listaProcedimentos = entityManager
                    .createNamedQuery("ItemProcedimentoHospitalar.findByDescricao", ItemProcedimentoHospitalar.class)
                    .setParameter("descricao", query.toUpperCase())
                    .getResultList();

            return listaProcedimentos;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar o procedimento.");
        }
    }

    /*
	 * Busca procedimento por código
	 */
	public ItemProcedimentoHospitalar buscaProcedimentoPorCodigo(Integer phoSeq, Integer seq) {
		try {
			return entityManager
					.createNamedQuery("ItemProcedimentoHospitalar.findByPhoSeqAndSeq", ItemProcedimentoHospitalar.class)
					.setParameter("phoSeq", phoSeq)
					.setParameter("seq", seq)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw new CustomRuntimeException("Mais de um procedimento encontrado para este código.");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar o procedimento.");
		}
	}

}
