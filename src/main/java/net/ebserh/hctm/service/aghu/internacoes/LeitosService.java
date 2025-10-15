package net.ebserh.hctm.service.aghu.internacoes;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.aghu.internacoes.Leito;

@Stateless
public class LeitosService {

    private static final Logger logger = Logger.getAnonymousLogger();

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
