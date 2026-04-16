package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.FonteFinanciadora;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class FontesFinanciadorasService {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

    public List<FonteFinanciadora> buscaFontes() {
        try {
            return entityManager
                    .createNamedQuery("FonteFinanciadora.findAll", FonteFinanciadora.class)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as fontes financiadoras.");
        }
    }

    public void salvaFonteFinanciadora(FonteFinanciadora fonteFinanciadora) {
        if (Objects.isNull(fonteFinanciadora))
            throw new CustomRuntimeException("É necessário informar os dados da fonte financiadora.");

        if (StringUtils.isBlank(fonteFinanciadora.getDescricao()))
            throw new CustomRuntimeException("É necessário informar a descrição da fonte financiadora.");

        try {
            // Impede entrada de string com espaco no inicio ou no fim
            fonteFinanciadora.setDescricao(StringUtils.trim(fonteFinanciadora.getDescricao()));
            // Verifica duplicidade de registros
            try {
                FonteFinanciadora fonteExistente = entityManager
                        .createNamedQuery("FonteFinanciadora.findByDescricao", FonteFinanciadora.class)
                        .setParameter("descricao", fonteFinanciadora.getDescricao().toUpperCase())
                        .getSingleResult();

                if (!fonteExistente.getId().equals(fonteFinanciadora.getId()))
                    throw new CustomRuntimeException("Já existe uma fonte financiadora cadastrada com esta descrição.");
            } catch (CustomRuntimeException e) {
                throw e;
            } catch (NoResultException e) {
                // Ok, não há duplicidade
            } catch (NonUniqueResultException e) {
                throw new CustomRuntimeException(
                        "Mais de uma fonte financiadora previamente cadastrada com a descrição informada.");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                throw new CustomRuntimeException("Ocorreu um erro ao verificar a duplicidade de registros.");
            }

            if (Objects.isNull(fonteFinanciadora.getId()))
                entityManager.persist(fonteFinanciadora);
            else
                entityManager.merge(fonteFinanciadora);
        } catch (CustomRuntimeException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os dados da fonte financiadora.");
        }
    }

}
