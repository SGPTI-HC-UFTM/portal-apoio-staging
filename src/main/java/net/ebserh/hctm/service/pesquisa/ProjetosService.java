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
public class ProjetosService {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

    public List<TipoProjeto> buscaTiposProjeto(){
        try{
            return entityManager
                   .createNamedQuery("TipoProjeto.findAll", TipoProjeto.class)
                    .getResultList();
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os tipos de projeto cadastrados.");
        }
    }

    public void salvaTipoProjeto(TipoProjeto tipoProjeto){
        if(Objects.isNull(tipoProjeto)){
            throw new CustomRuntimeException("É necessário informar os dados do tipo de projeto.");
        }
        if(StringUtils.isBlank(tipoProjeto.getDescricao())){
            throw new CustomRuntimeException("É necessário informar a descrição do tipo de projeto.");
        }
        try{
            // Impede a entrada de string com espaço no inicio ou no fim
            tipoProjeto.setDescricao(StringUtils.trim(tipoProjeto.getDescricao()));
            // Verifica duplicidade de registros
            try {
                TipoProjeto tipoProjetoExistente = entityManager
                        .createNamedQuery("TipoProjeto.findByDescricao", TipoProjeto.class)
                        .setParameter("descricao", tipoProjeto.getDescricao().toUpperCase())
                        .getSingleResult();

                if (!tipoProjetoExistente.getId().equals(tipoProjeto.getId()))
                    throw new CustomRuntimeException("Já existe um tipo de projeto cadastrado com esta descrição.");
            } catch (CustomRuntimeException e) {
                throw e;
            } catch (NoResultException e) {
                // Ok, não há duplicidade
            } catch (NonUniqueResultException e) {
                throw new CustomRuntimeException(
                        "Mais de um tipo de projeto previamente cadastrado com a descrição informada.");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                throw new CustomRuntimeException("Ocorreu um erro ao verificar a duplicidade de registros.");
            }

            if (Objects.isNull(tipoProjeto.getId()))
                entityManager.persist(tipoProjeto);
            else
                entityManager.merge(tipoProjeto);
        } catch (CustomRuntimeException e) {
            throw e;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar o tipo de projeto cadastrado.");
        }
    }

    public List<StatusProjeto> buscaStatusProjetos(){
        try{
            return entityManager
                   .createNamedQuery("StatusProjeto.findAll", StatusProjeto.class)
                    .getResultList();
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os status de projeto cadastrados.");
        }
    }

    public void salvaStatus(StatusProjeto statusProjeto){
        if(Objects.isNull(statusProjeto)){
            throw new CustomRuntimeException("É necessário informar os dados do status de projeto.");
        }
        if(StringUtils.isBlank(statusProjeto.getDescricao())){
            throw new CustomRuntimeException("É necessário informar a descrição do status de projeto.");
        }
        try{
            // Impede a entrada de string com espaço no inicio ou no fim
            statusProjeto.setDescricao(StringUtils.trim(statusProjeto.getDescricao()));
            // Verifica duplicidade de registros
            try {
                StatusProjeto statusProjetoExistente = entityManager
                        .createNamedQuery("StatusProjeto.findByDescricao", StatusProjeto.class)
                        .setParameter("descricao", statusProjeto.getDescricao().toUpperCase())
                        .getSingleResult();

                if (!statusProjetoExistente.getId().equals(statusProjeto.getId()))
                    throw new CustomRuntimeException("Já existe um status cadastrado com esta descrição.");
            } catch (CustomRuntimeException e) {
                throw e;
            } catch (NoResultException e) {
                // Ok, não há duplicidade
            } catch (NonUniqueResultException e) {
                throw new CustomRuntimeException(
                        "Mais de um status previamente cadastrada com a descrição informada.");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                throw new CustomRuntimeException("Ocorreu um erro ao verificar a duplicidade de registros.");
            }

            if (Objects.isNull(statusProjeto.getId()))
                entityManager.persist(statusProjeto);
            else
                entityManager.merge(statusProjeto);
        } catch (CustomRuntimeException e) {
            throw e;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os status de projeto cadastrados.");
        }
    }

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


    public List<Projeto> buscaProjetosPorTitulo(String titulo) {
        if (StringUtils.isBlank(titulo))
            throw new CustomRuntimeException("É necessário informar o titulo para pesquisa.");

        try {
            return entityManager
                    .createNamedQuery("Projeto.findByTituloLike", Projeto.class)
                    .setParameter("titulo", String.format("%%%s%%", titulo.toLowerCase().trim()))
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os projetos.");
        }
    }

    public void salvaProjeto(Projeto projeto, List<ProjetoPesquisador> equipe) {
        if (Objects.isNull(projeto))
            throw new CustomRuntimeException("É necessário informar os dados do projeto.");

        if (Objects.isNull(equipe) || equipe.isEmpty())
            throw new CustomRuntimeException("É necessário informar a equipe do projeto.");

        try {
            //Impede titulo que comece com espaco ou termine com espaco
            projeto.setTitulo(StringUtils.trim(projeto.getTitulo()));

            //Verificar duplicidade de titulo
            try {
                Integer projetoTituloExistente_id = entityManager
                    .createNamedQuery("Projeto.findByTitulo", Integer.class)
                    .setParameter("titulo", projeto.getTitulo().toLowerCase())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

                if(!Objects.isNull(projetoTituloExistente_id) && !projetoTituloExistente_id.equals(projeto.getId()))
                    throw new CustomRuntimeException("Já existe um projeto cadastrado com este titulo.");

            } catch (CustomRuntimeException e){
                throw e;
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                throw new CustomRuntimeException("Ocorreu um erro ao verificar a duplicidade de registros.");
            }

            if (projeto.getId() == null)
                entityManager.persist(projeto);
            else
                entityManager.merge(projeto);
            
            //Esvaziar dados anteriores presentes no banco antes de salvar os novos dados presentes na memoria
            entityManager.createNamedQuery("ProjetoPesquisador.deleteByProjeto")
                        .setParameter("projeto", projeto)
                        .executeUpdate();
            entityManager.flush();

            for (ProjetoPesquisador pp : equipe) {
                pp.setProjeto(projeto);
                //Merge estava causando erro, ja que nao ha id no banco, ele nao encontra o id presente em pp, solucao: apenas criando novamente no banco
                pp.setId(null);
                entityManager.persist(pp);
            }

        } catch (CustomRuntimeException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os dados do projeto.");
        }
    }

    public List<ProjetoPesquisador> buscaEquipe(Projeto projeto) {
        if (Objects.isNull(projeto))
            throw new CustomRuntimeException("É necessário selecionar um projeto.");

        try {
            return entityManager
                    .createNamedQuery("ProjetoPesquisador.findByProjeto", ProjetoPesquisador.class)
                    .setParameter("projeto", projeto)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar a equipe do projeto.");
        }
    }

}
