package net.ebserh.hctm.service.aghu;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.ebserh.hctm.model.aghu.UnidadeFuncional;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class UnidadesFuncionaisService {

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    public List<UnidadeFuncional> buscaAtivas() {
        try {
            return entityManager.createNamedQuery("UnidadeFuncional.findAtivas", UnidadeFuncional.class)
                    .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as unidades funcionais.");
        }
    }

    public UnidadeFuncional buscaUnidade(Integer codigoUnidade) {
        try {
            return entityManager.find(UnidadeFuncional.class, codigoUnidade);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar a unidade funcional.");
        }
    }

}
