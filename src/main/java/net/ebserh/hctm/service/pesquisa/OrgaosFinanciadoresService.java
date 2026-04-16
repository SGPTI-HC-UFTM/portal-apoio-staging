package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.OrgaoFinanciador;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class OrgaosFinanciadoresService {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

    public List<OrgaoFinanciador> buscaOrgaos() {
        try {
            return entityManager
                    .createNamedQuery("OrgaoFinanciador.findAll", OrgaoFinanciador.class)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Erro na busca pelos órgãos financiadores cadastrados!");
        }
    }

    public void salvaOrgaoFinanciador (OrgaoFinanciador orgaoFinanciador) {
        if (Objects.isNull(orgaoFinanciador))
            throw new CustomRuntimeException("É necessário informar os dados do órgão financiador!");

        if (StringUtils.isBlank(orgaoFinanciador.getDescricao()))
            throw new CustomRuntimeException("É necessário informar o nome do órgão financiador!");

        try {
            // Impede a entrada de string com espaço no inicio ou no fim
            orgaoFinanciador.setDescricao(StringUtils.trim(orgaoFinanciador.getDescricao()));
            // Verifica duplicidade de registros
            try {
                OrgaoFinanciador orgaoFinanciadorExistente = entityManager
                        .createNamedQuery("OrgaoFinanciador.findByDescricao", OrgaoFinanciador.class)
                        .setParameter("descricao", orgaoFinanciador.getDescricao().toUpperCase())
                        .getSingleResult();

                if (!orgaoFinanciadorExistente.getId().equals(orgaoFinanciador.getId()))
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

            if (Objects.isNull(orgaoFinanciador.getId()))
                entityManager.persist(orgaoFinanciador);
            else
                entityManager.merge(orgaoFinanciador);
        } catch (CustomRuntimeException e) {
            throw e;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Erro ao salvar os dados do órgão financiador!");
        }
    }
}