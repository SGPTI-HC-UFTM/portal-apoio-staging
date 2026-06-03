package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.Instituicao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class InstituicoesService {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

    public List<Instituicao> buscaInstituicoes() {
        try {
            return entityManager
                    .createNamedQuery("Instituicao.findAll", Instituicao.class)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as instituições.");
        }
    }

    public void salvaInstituicao(Instituicao instituicao) {
        if (Objects.isNull(instituicao))
            throw new CustomRuntimeException("É necessário informar os dados da instituição.");

        if (StringUtils.isBlank(instituicao.getNome()))
            throw new CustomRuntimeException("É necessário informar a descrição da instituição.");

        try {
            // Impede a entrada de string com espaço no inicio ou no fim
            instituicao.setNome(StringUtils.trim(instituicao.getNome()));
            // Verifica duplicidade de registros
            try {
                Instituicao instituicaoExistente = entityManager
                        .createNamedQuery("Instituicao.findByNome", Instituicao.class)
                        .setParameter("nome", instituicao.getNome())
                        .getSingleResult();

                if (!instituicaoExistente.getId().equals(instituicao.getId()))
                    throw new CustomRuntimeException("Já existe uma instituição cadastrada com este nome.");
            } catch (CustomRuntimeException e) {
                throw e;
            } catch (NoResultException e) {
                // Ok, não há duplicidade
            } catch (NonUniqueResultException e) {
                throw new CustomRuntimeException("Mais de uma instituição previamente cadastrada com o nome informado.");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                throw new CustomRuntimeException("Ocorreu um erro ao verificar a duplicidade de registros.");
            }

            if (Objects.isNull(instituicao.getId()))
                entityManager.persist(instituicao);
            else
                entityManager.merge(instituicao);
        } catch (CustomRuntimeException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os dados da instituição.");
        }
    }
}
