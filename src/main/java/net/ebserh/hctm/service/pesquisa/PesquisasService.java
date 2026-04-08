package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class PesquisasService {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

    public List<BolsaProdutividadeCnpq> buscaBolsas() {
        try {
            return entityManager
                    .createNamedQuery("BolsaProdutividadeCnpq.findAll", BolsaProdutividadeCnpq.class)
                    .getResultList();
        } catch (Exception  e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as bolsas.");
        }
    }

    public void salvaBolsaProdutividade(BolsaProdutividadeCnpq bolsaProdutividadeCnpq) {
        if (bolsaProdutividadeCnpq == null)
            throw new CustomRuntimeException("É necessário informar os dados da bolsa.");

        try {
            if (bolsaProdutividadeCnpq.getId() == null)
                entityManager.persist(bolsaProdutividadeCnpq);
            else
                entityManager.merge(bolsaProdutividadeCnpq);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os dados da bolsa.");
        }
    }

    public List<FormacaoAcademica> buscaFormacoesAcademicas() {
        try {
            return entityManager
                    .createNamedQuery("FormacaoAcademica.findAll", FormacaoAcademica.class)
                    .getResultList();
        } catch (Exception  e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as formações.");
        }
    }

    public void salvaFormacaoAcademica(FormacaoAcademica formacaoAcademica) {
        if (formacaoAcademica == null)
            throw new CustomRuntimeException("É necessário informar os dados da formação.");

        try {
            if (formacaoAcademica.getId() == null)
                entityManager.persist(formacaoAcademica);
            else
                entityManager.merge(formacaoAcademica);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os dados da formação.");
        }
    }

    public List<Instituicao> buscaInstituicoes() {
        try {
            return entityManager
                    .createNamedQuery("Instituicao.findAll", Instituicao.class)
                    .getResultList();
        } catch (Exception  e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as instituições.");
        }
    }

    public void salvaInstituicao(Instituicao instituicao) {
        if (instituicao == null)
            throw new CustomRuntimeException("É necessário informar os dados da instituição.");

        try {
            if (instituicao.getId() == null)
                entityManager.persist(instituicao);
            else
                entityManager.merge(instituicao);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os dados da instituição.");
        }
    }

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

    public void salvaNivelFormacao(NivelFormacao nivelFormacao) {
        if (nivelFormacao == null)
            throw new CustomRuntimeException("É necessário informar os dados do nível de formação.");

        try {
            if (nivelFormacao.getId() == null)
                entityManager.persist(nivelFormacao);
            else
                entityManager.merge(nivelFormacao);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os dados do nível de formação.");
        }
    }

    public List<ProgramaPosGraduacao> buscaProgramasPosGraduacao() {
        try {
            return entityManager
                    .createNamedQuery("ProgramaPosGraduacao.findAll", ProgramaPosGraduacao.class)
                    .getResultList();
        } catch (Exception  e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os programas de pós graduação.");
        }
    }

    public void salvaProgramaPosGraduacao(ProgramaPosGraduacao programaPosGraduacao) {
        if (programaPosGraduacao == null)
            throw new CustomRuntimeException("É necessário informar os dados do programa de pós graduação.");

        try {
            if (programaPosGraduacao.getId() == null)
                entityManager.persist(programaPosGraduacao);
            else
                entityManager.merge(programaPosGraduacao);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os dados do programa de pós graduação.");
        }
    }



    public void salvaPesquisador(Pesquisador pesquisador) {
        if (pesquisador == null)
            throw new CustomRuntimeException("É necessário informar os dados do pesquisador.");

        try {
            if (pesquisador.getId() == null)
                entityManager.persist(pesquisador);
            else
                entityManager.merge(pesquisador);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os dados do pesquisador.");
        }
    }

    public List<Pesquisador> buscaPesquisadoresPorNome(String nome) {
        if (StringUtils.isBlank(nome))
            throw new CustomRuntimeException("É necessário informar o nome para pesquisa.");

        try {
            return entityManager
                    .createNamedQuery("Pesquisador.findByNomeLike", Pesquisador.class)
                    .setParameter("nome", String.format("%%%s%%", nome.toLowerCase()))
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os pesquisadores.");
        }
    }

}