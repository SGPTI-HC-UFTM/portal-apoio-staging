package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
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
        } catch (Exception  e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as linhas de pesquisa.");
        }
    }

    public void salvaLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
        if (linhaPesquisa == null)
            throw new CustomRuntimeException("É necessário informar os dados da linha de pesquisa.");

        try {
            if (linhaPesquisa.getId() == null)
                entityManager.persist(linhaPesquisa);
            else
                entityManager.merge(linhaPesquisa);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os dados da linha de pesquisa.");
        }
    }

    public List<NivelFormacao> buscaNiveisFormacao() {
        try {
            return entityManager
                    .createNamedQuery("NivelFormacao.findAll", NivelFormacao.class)
                    .getResultList();
        } catch (Exception  e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os níveis de formação.");
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