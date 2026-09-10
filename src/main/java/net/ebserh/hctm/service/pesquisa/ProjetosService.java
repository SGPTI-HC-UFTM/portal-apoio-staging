package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.FonteFinanciadora;
import net.ebserh.hctm.model.pesquisa.Projeto;
import net.ebserh.hctm.model.pesquisa.StatusProjeto;
import net.ebserh.hctm.model.pesquisa.TipoProjeto;
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

    public List<TipoProjeto> buscaTiposProjeto() {
        throw new CustomRuntimeException("Em construção... TIPOS");
    }

    public List<StatusProjeto> buscaStatusProjeto(){
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
                    .setParameter("titulo", String.format("%%%s%%", titulo.toLowerCase()))
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os projetos.");
        }
    }

    public void salvaProjeto(Projeto projeto) {
        if (Objects.isNull(projeto))
            throw new CustomRuntimeException("É necessário informar os dados do projeto.");
        //Impede titulo que comece com espaco ou termine com espaco
        projeto.setTitulo(StringUtils.trim(projeto.getTitulo()));
        
        try {
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
        } catch (CustomRuntimeException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os dados do projeto.");
        }
    }

}
