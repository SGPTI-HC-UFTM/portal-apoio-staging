package net.ebserh.hctm.service.evolucoes;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.evolucoes.Apache2Opcao;

@Stateless
public class ApacheService {

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Apache2Opcao> buscaPorQuestao(Integer questao) {
        try {
            return entityManager.createNamedQuery("Apache2Opcao.findByQuestao", Apache2Opcao.class)
                .setParameter("questao", questao)
                .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as opções de resposta.");
        }
    }
    
}
