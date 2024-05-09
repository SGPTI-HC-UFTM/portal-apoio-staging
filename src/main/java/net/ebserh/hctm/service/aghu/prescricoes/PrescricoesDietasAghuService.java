package net.ebserh.hctm.service.aghu.prescricoes;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.ebserh.hctm.model.aghu.Atendimento;
import net.ebserh.hctm.model.aghu.UnidadeFuncional;
import net.ebserh.hctm.model.aghu.nutricao.TipoItemDieta;
import net.ebserh.hctm.model.aghu.prescricoes.PrescricaoDietaAghu;
import net.ebserh.hctm.model.aghu.prescricoes.PrescricaoDietaPK;
import net.ebserh.hctm.model.aghu.prescricoes.TipoFrequenciaAprazamento;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class PrescricoesDietasAghuService {

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    public List<PrescricaoDietaAghu> buscaPorDataInicio(LocalDate dataInicio) {
        try {
            return entityManager
                .createNamedQuery("PrescricaoDietaAghu.findByDataInicio", PrescricaoDietaAghu.class)
                .setParameter("dataInicio", dataInicio)
                .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as prescrições.");
        }
    }

    public List<PrescricaoDietaAghu> buscaPorDataInicioUnidadeFuncional(LocalDate dataInicio, UnidadeFuncional unidadeFuncional) {
        try {
            return entityManager.createNamedQuery("PrescricaoDietaAghu.findByDataInicioUnidadeFuncional", PrescricaoDietaAghu.class)
                .setParameter("dataInicio", dataInicio)
                .setParameter("unidadeFuncional", unidadeFuncional)
                .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as prescrições.");
        }
    }

    public List<TipoItemDieta> buscaTiposItemDieta() {
        try {
            return entityManager
                .createNamedQuery("TipoItemDieta.findAtivos", TipoItemDieta.class)
                .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os tipos de dieta.");
        }
    }

    public List<TipoFrequenciaAprazamento> buscaTiposFrequenciaAprazamento() {
        try {
            return entityManager
                .createNamedQuery("TipoFrequenciaAprazamento.findAll", TipoFrequenciaAprazamento.class)
                .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as frequências de aprazamento.");
        }
    }

    public TipoFrequenciaAprazamento buscaTipoFrequenciaAprazamento(Integer seq) {
        return entityManager.find(TipoFrequenciaAprazamento.class, seq);
    }

    public TipoItemDieta buscaTipoItemDieta(Integer seq) {
        return entityManager.find(TipoItemDieta.class, seq);
    }

    public PrescricaoDietaAghu buscaPrescricao(Integer atdSeq, Integer seq) {
        PrescricaoDietaPK id = new PrescricaoDietaPK();
        id.setAtdSeq(atdSeq);
        id.setSeq(seq);

        return entityManager.find(PrescricaoDietaAghu.class, id);
    }

    public PrescricaoDietaAghu buscaPrescricaoPorAtendimento(Integer atdSeq) {

        List<PrescricaoDietaAghu> prescricoes = entityManager.createQuery("select p "
				+ "from PrescricaoDietaAghu p "
                + "join p.atendimento a "
                + "where a.seq = :atendimento " , PrescricaoDietaAghu.class).setParameter("atendimento", atdSeq).getResultList();

            if (!prescricoes.isEmpty())
                return prescricoes.get(0);

        return null;
    }

    
}
