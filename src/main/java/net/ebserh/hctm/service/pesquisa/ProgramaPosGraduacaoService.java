package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.ProgramaPosGraduacao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ProgramaPosGraduacaoService{

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

    public List<ProgramaPosGraduacao> buscaProgramasPosGraduacao() {
        try {
            return entityManager
                    .createNamedQuery("ProgramaPosGraduacao.findAll", ProgramaPosGraduacao.class)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar o programa de pós graduação.");
        }
    }

    public void salvaProgramaPosGraduacao(ProgramaPosGraduacao programaPosGraduacao) {
        if (Objects.isNull(programaPosGraduacao))
            throw new CustomRuntimeException("É necessário informar os dados do programa de pós graduação.");

        if (StringUtils.isBlank(programaPosGraduacao.getDescricao()))
            throw new CustomRuntimeException("É necessário informar a descrição do programa de pós graduação.");

        try {
            // Impede entrada de string com espaco no inicio ou no fim
            programaPosGraduacao.setDescricao(StringUtils.trim(programaPosGraduacao.getDescricao()));
            // Verifica duplicidade de registros
            try {
                ProgramaPosGraduacao programaPosGraduacaoExistente = entityManager
                        .createNamedQuery("ProgramaPosGraduacao.findByDescricao", ProgramaPosGraduacao.class)
                        .setParameter("descricao", programaPosGraduacao.getDescricao().toUpperCase())
                        .getSingleResult();

                if (!programaPosGraduacaoExistente.getId().equals(programaPosGraduacao.getId()))
                    throw new CustomRuntimeException("Já existe um programa de pós graduação cadastrado com esta descrição.");
            } catch (CustomRuntimeException e) {
                throw e;
            } catch (NoResultException e) {
                // Ok, não há duplicidade
            } catch (NonUniqueResultException e) {
                throw new CustomRuntimeException(
                        "Mais de um programa de pós graduação previamente cadastrada com a descrição informada.");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                throw new CustomRuntimeException("Ocorreu um erro ao verificar a duplicidade de registros.");
            }

            if (Objects.isNull(programaPosGraduacao.getId()))
                entityManager.persist(programaPosGraduacao);
            else
                entityManager.merge(programaPosGraduacao);
        } catch (CustomRuntimeException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os dados do programa de pós graduação.");
        }
    }

}
