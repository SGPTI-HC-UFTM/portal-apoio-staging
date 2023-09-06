package net.ebserh.hctm.service.aghu.internacoes;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.aghu.internacoes.Leito;

@Stateless
public class LeitosService {

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Leito> buscaLeitos() {
        try {
            return entityManager.createNamedQuery("Leito.findAll", Leito.class).getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os leitos.");
        }
        
    }
    
}
