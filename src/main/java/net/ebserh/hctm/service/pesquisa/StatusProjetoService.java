package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.StatusProjeto;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


@Stateless
public class StatusProjetoService {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

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
}
