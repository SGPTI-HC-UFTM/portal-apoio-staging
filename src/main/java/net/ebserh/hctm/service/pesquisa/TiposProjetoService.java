package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.TipoProjeto;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


@Stateless
public class TiposProjetoService {

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

    public void excluiTipoProjeto(TipoProjeto tipoProjeto) {
        if (Objects.isNull(tipoProjeto))
            throw new CustomRuntimeException("É necessário selecionar um tipo de projeto.");

        try {
            entityManager.remove(entityManager.merge(tipoProjeto));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao excluir o tipo de projeto.");
        }
    }
}
