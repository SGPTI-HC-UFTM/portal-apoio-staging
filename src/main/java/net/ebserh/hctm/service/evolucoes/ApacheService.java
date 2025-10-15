package net.ebserh.hctm.service.evolucoes;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.evolucoes.Apache2Opcao;

@Stateless
public class ApacheService {

    private static final Logger logger = Logger.getAnonymousLogger();

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
