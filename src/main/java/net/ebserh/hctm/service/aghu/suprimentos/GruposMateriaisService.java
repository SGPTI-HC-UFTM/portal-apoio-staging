package net.ebserh.hctm.service.aghu.suprimentos;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.ebserh.hctm.model.aghu.suprimentos.GrupoMaterial;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class GruposMateriaisService {

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    public List<GrupoMaterial> buscaTodos() {
        try {
            return entityManager.createNamedQuery("GrupoMaterial.findAll", GrupoMaterial.class)
                .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os grupos de materiais.");
        }

    }
    
}
