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
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as bolsas cadastradas.");
        }
    }

    public void salvaBolsaProdutividade(BolsaProdutividadeCnpq bolsaProdutividadeCnpq) {
        if (Objects.isNull(bolsaProdutividadeCnpq))
            throw new CustomRuntimeException("É necessário informar os dados da bolsa.");

        if (StringUtils.isBlank(bolsaProdutividadeCnpq.getDescricao()))
            throw new CustomRuntimeException("É necessário informar a descrição da bolsa.");

        try {
            // Impede a entrada de string com espaço no inicio ou no fim
            bolsaProdutividadeCnpq.setDescricao(StringUtils.trim(bolsaProdutividadeCnpq.getDescricao()));
            // Verifica duplicidade de registros
            try {
                BolsaProdutividadeCnpq bolsaExistente = entityManager
                        .createNamedQuery("BolsaProdutividadeCnpq.findByDescricao", BolsaProdutividadeCnpq.class)
                        .setParameter("descricao", bolsaProdutividadeCnpq.getDescricao())
                        .getSingleResult();

                if (!bolsaExistente.getId().equals(bolsaProdutividadeCnpq.getId()))
                    throw new CustomRuntimeException("Já existe uma bolsa cadastrada com esta descrição.");
            } catch (CustomRuntimeException e) {
                throw e;
            } catch (NoResultException e) {
                // Ok, não há duplicidade
            } catch (NonUniqueResultException e) {
                throw new CustomRuntimeException("Mais de uma bolsa previamente cadastrada com a descrição informada.");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                throw new CustomRuntimeException("Ocorreu um erro ao verificar a duplicidade de registros.");
            }

            if (Objects.isNull(bolsaProdutividadeCnpq.getId()))
                entityManager.persist(bolsaProdutividadeCnpq);
            else
                entityManager.merge(bolsaProdutividadeCnpq);
        } catch (CustomRuntimeException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os dados da bolsa.");
        }
    }

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
            // Impede a entrada de string com espaço no inicio ou no fim
            formacaoAcademica.setNome(StringUtils.trim(formacaoAcademica.getNome()));
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
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os dados da formação.");
        }
    }

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
            // Impede entrada de string com espaco no inicio ou no fim
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