package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.OrgaoFinanciador;
import net.ebserh.hctm.util.MockDb;
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
            if (Objects.isNull(orgaoFinanciador.getId()))
                entityManager.persist(orgaoFinanciador);
            else
                entityManager.merge(orgaoFinanciador);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Erro ao salvar os dados do órgão financiador!");
        }
    }
}