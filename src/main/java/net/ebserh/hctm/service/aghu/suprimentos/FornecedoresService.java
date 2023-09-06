package net.ebserh.hctm.service.aghu.suprimentos;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.ebserh.hctm.model.aghu.suprimentos.Fornecedor;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class FornecedoresService {

    @Inject
    private Logger logger;

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