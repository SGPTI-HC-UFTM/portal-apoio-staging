package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.BolsaProdutividadeCnpq;
import net.ebserh.hctm.util.MockDb;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class BolsasProdutividadeService {

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
            if (Objects.isNull(bolsaProdutividadeCnpq.getId()))
                entityManager.persist(bolsaProdutividadeCnpq);
            else
                entityManager.merge(bolsaProdutividadeCnpq);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar os dados da bolsa.");
        }
    }

}
