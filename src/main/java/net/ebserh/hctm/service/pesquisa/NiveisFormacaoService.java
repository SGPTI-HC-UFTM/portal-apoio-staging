package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.NivelFormacao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class NiveisFormacaoService {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

    public List<NivelFormacao> buscaNiveis() {
        try {
            return entityManager
                    .createNamedQuery("NivelFormacao.findAll", NivelFormacao.class)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Erro na busca pelos níveis de formação!");
        }
    }

    public void salvaNivelFormacao (NivelFormacao nivelFormacao) {
        if (Objects.isNull(nivelFormacao))
            throw new CustomRuntimeException("É necessário informar os dados do nível de formação!");

        if (StringUtils.isBlank(nivelFormacao.getNivel()))
            throw new CustomRuntimeException("É necessário informar o nome do nível de formação!");

        try {

            // Impede a entrada de string com espaço no inicio ou no fim
            nivelFormacao.setNivel(StringUtils.trim(nivelFormacao.getNivel()));
            // Verifica duplicidade de registros
            try {
                NivelFormacao nivelFormacaoExistente = entityManager
                        .createNamedQuery("NivelFormacao.findByDescricao", NivelFormacao.class)
                        .setParameter("descricao", nivelFormacao.getNivel().toUpperCase())
                        .getSingleResult();

                if (!nivelFormacaoExistente.getId().equals(nivelFormacao.getId()))
                    throw new CustomRuntimeException("Já existe um nível de formação cadastrado com esta descrição.");
            } catch (CustomRuntimeException e) {
                throw e;
            } catch (NoResultException e) {
                // Ok, não há duplicidade
            } catch (NonUniqueResultException e) {
                throw new CustomRuntimeException(
                        "Mais de um nível de formação previamente cadastrada com a descrição informada.");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                throw new CustomRuntimeException("Ocorreu um erro ao verificar a duplicidade de registros.");
            }
            if (Objects.isNull(nivelFormacao.getId()))
                entityManager.persist(nivelFormacao);
            else
                entityManager.merge(nivelFormacao);

        } catch (CustomRuntimeException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Erro ao salvar nível de formação!");
        }
    }

    public void excluiNivelFormacao(NivelFormacao nivelFormacao){
        if (Objects.isNull(nivelFormacao))
            throw new CustomRuntimeException("É necessário selecionar um nível de formação.");

        try {
            entityManager.remove(entityManager.merge(nivelFormacao));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao excluir o nível de formação.");
        }
    }
}