package net.ebserh.hctm.service.aghu.suprimentos;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import net.ebserh.hctm.model.aghu.suprimentos.Fornecedor;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class FornecedoresService {

    private static final Logger logger = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

    public List<Fornecedor> buscaFornecedores() {
        try {
            return entityManager.createNamedQuery("Fornecedor.findAll", Fornecedor.class).getResultList();
        } catch (Exception e) {
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os fornecedores");
        }
    }

    public Fornecedor buscaPorNumero(Integer numero) {
        try {
            return entityManager.find(Fornecedor.class, numero);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar fornecedor");
        }
    }

}