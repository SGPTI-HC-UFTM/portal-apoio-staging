package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.LinhaPesquisa;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class LinhasPesquisaService {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

    public List<LinhaPesquisa> buscaLinhasPesquisa() {
        try {
            return entityManager
                    .createNamedQuery("LinhaPesquisa.findAll", LinhaPesquisa.class)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as linhas de pesquisas.");
        }
    }

    public void salvaLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
        if (Objects.isNull(linhaPesquisa))
            throw new CustomRuntimeException("É necessário informar os dados da linha de pesquisas.");

        if (StringUtils.isBlank(linhaPesquisa.getDescricao()))
            throw new CustomRuntimeException("É necessário informar a descrição da linha de pesquisas.");

        try {
            // Impede a entrada de string com espaço no inicio ou no fim
            linhaPesquisa.setDescricao(StringUtils.trim(linhaPesquisa.getDescricao()));
            // Verifica duplicidade de registros
            try {
                LinhaPesquisa linhaPesquisaExistente = entityManager
                        .createNamedQuery("LinhaPesquisa.findByDescricao", LinhaPesquisa.class)
                        .setParameter("descricao", linhaPesquisa.getDescricao().toUpperCase())
                        .getSingleResult();

                if (!linhaPesquisaExistente.getId().equals(linhaPesquisa.getId()))
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

            if (Objects.isNull(linhaPesquisa.getId()))
                entityManager.persist(linhaPesquisa);
            else
                entityManager.merge(linhaPesquisa);
        } catch (CustomRuntimeException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os dados da linha de pesquisas.");
        }
    }

}