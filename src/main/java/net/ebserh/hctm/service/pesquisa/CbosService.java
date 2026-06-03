package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.Cbo;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class CbosService {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cbo> buscaCbos() {
        try {
            return entityManager
                    .createNamedQuery("Cbo.findAll", Cbo.class)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as CBOs");
        }
    }

    public void salvaCbo(Cbo cbo) {
        if (Objects.isNull(cbo))
            throw new CustomRuntimeException("É necessário informar os dados da CBO.");

        if (StringUtils.isBlank(cbo.getDescricao()))
            throw new CustomRuntimeException("É necessário informar a descrição da CBO.");

        try {
            // Impede a entrada de string com espaço no inicio ou no fim
            cbo.setDescricao(StringUtils.trim(cbo.getDescricao()));
            // Verifica duplicidade de registros
            try {
                Cbo cboExistente = entityManager
                        .createNamedQuery("Cbo.findByDescricao", Cbo.class)
                        .setParameter("descricao", cbo.getDescricao().toUpperCase())
                        .getSingleResult();

                if (!cboExistente.getId().equals(cbo.getId()))
                    throw new CustomRuntimeException("Já existe uma linha de pesquisas cadastrada com esta descrição.");
            } catch (CustomRuntimeException e) {
                throw e;
            } catch (NoResultException e) {
                // Ok, não há duplicidade
            } catch (NonUniqueResultException e) {
                throw new CustomRuntimeException(
                        "Mais de uma linha de pesquisas previamente cadastrada com a descrição informada.");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                throw new CustomRuntimeException("Ocorreu um erro ao verificar a duplicidade de registros.");
            }

            if (Objects.isNull(cbo.getId()))
                entityManager.persist(cbo);
            else
                entityManager.merge(cbo);
        } catch (CustomRuntimeException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os dados da CBO.");
        }
    }
}
