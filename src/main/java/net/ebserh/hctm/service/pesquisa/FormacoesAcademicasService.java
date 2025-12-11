package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.FormacaoAcademica;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class FormacoesAcademicasService {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

    public List<FormacaoAcademica> buscaFormacoes() {
        try {
            return entityManager
                    .createNamedQuery("FormacaoAcademica.findAll", FormacaoAcademica.class)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as formações acadêmicas.");
        }
    }

    public void salvaFormacaoAcademica(FormacaoAcademica formacaoAcademica) {
        if (Objects.isNull(formacaoAcademica))
            throw new CustomRuntimeException("É necessário informar os dados da formação.");

        if (StringUtils.isBlank(formacaoAcademica.getNome()))
            throw new CustomRuntimeException("É necessário informar a descrição da formação.");

        try {
            // Verifica duplicidade de registros
            try {
                FormacaoAcademica formacaoExistente = entityManager
                        .createNamedQuery("FormacaoAcademica.findByNome", FormacaoAcademica.class)
                        .setParameter("nome", formacaoAcademica.getNome())
                        .getSingleResult();

                if (!formacaoExistente.getId().equals(formacaoAcademica.getId()))
                    throw new CustomRuntimeException("Já existe uma formação cadastrada com este nome.");
            } catch (CustomRuntimeException e) {
                throw e;
            } catch (NoResultException e) {
                // Ok, não há duplicidade
            } catch (NonUniqueResultException e) {
                throw new CustomRuntimeException("Mais de uma formação previamente cadastrada com a descrição informada.");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                throw new CustomRuntimeException("Ocorreu um erro ao verificar a duplicidade de registros.");
            }

            if (Objects.isNull(formacaoAcademica.getId()))
                entityManager.persist(formacaoAcademica);
            else
                entityManager.merge(formacaoAcademica);
        } catch (CustomRuntimeException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os dados da bolsa.");
        }
    }

}
