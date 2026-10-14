package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.EstudoClinico;
import net.ebserh.hctm.model.pesquisa.StatusEstudoClinico;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class EstudosClinicosService {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

    public List<StatusEstudoClinico> buscaStatusEstudoClinico() {
        try {
            return entityManager
                    .createNamedQuery("StatusEstudoClinico.findAll", StatusEstudoClinico.class)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os status de estudos clínicos.");
        }
    }

    public void salvaEstudoClinico(EstudoClinico estudo) {
        if (Objects.isNull(estudo))
            throw new CustomRuntimeException("É necessário selecionar um estudo clínico.");

        try {
            if (Objects.isNull(estudo.getId()))
                entityManager.persist(estudo);
            else
                entityManager.merge(estudo);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar o estudo clínico.");
        }
    }

    public List<EstudoClinico> buscaEstudosPorTitulo(String titulo) {
        if (StringUtils.isBlank(titulo))
            throw new CustomRuntimeException("É necessário informar o título para pesquisa.");

        try {
            return entityManager
                    .createNamedQuery("EstudoClinico.findByTitulo", EstudoClinico.class)
                    .setParameter("titulo", String.format("%%%s%%", titulo.toUpperCase()))
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao pesquisar os estudos clínicos.");
        }
    }

}
