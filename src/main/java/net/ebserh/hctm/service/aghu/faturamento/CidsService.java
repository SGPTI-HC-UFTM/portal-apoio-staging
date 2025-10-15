package net.ebserh.hctm.service.aghu.faturamento;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import net.ebserh.hctm.model.aghu.Cid;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class CidsService {

    private static final Logger logger = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cid> buscaCids() {

        try {
            return entityManager
                    .createNamedQuery("Cid.findAll", Cid.class)
                    .getResultList();
        } catch (final Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os cids");
        }

    }

    public List<Cid> buscaCidsPermitidos(List<String> cidsPermitidos) {

        try {
            return entityManager
                    .createNamedQuery("Cid.findAllWhereIn", Cid.class)
                    .setParameter("cids", cidsPermitidos)
                    .getResultList();
        } catch (final Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os cids");
        }
    }

    public List<Cid> buscaCidsPorDescricao(String descricao) {

        try {
            return entityManager
                    .createNamedQuery("Cid.findByDescricao", Cid.class)
                    .setParameter("descricao", descricao.toUpperCase())
                    .getResultList();
        } catch (final Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os cids");
        }

    }

    public Cid buscaCidPorCodigo(String codigo) {
        try {
            Cid cid = entityManager
                    .createNamedQuery("Cid.findByCodigo", Cid.class)
                    .setParameter("codigo", codigo).getSingleResult();

            return cid;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar o cid.");
        }
    }
    
    public Cid buscaCidPorId(Integer id) {
        if (id == null)
            throw new CustomRuntimeException("É necessário informar o código do CID.");

        try {
            return entityManager
                    .createNamedQuery("Cid.findById", Cid.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar o cid.");
        }
    }

}
