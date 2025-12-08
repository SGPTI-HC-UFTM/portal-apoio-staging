package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.BolsaProdutividadeCnpq;
import net.ebserh.hctm.model.pesquisa.Cbo;
import net.ebserh.hctm.util.MockDb;

import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CbosService {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private MockDb mockDb;

    public List<Cbo> buscaCbos() {
        return mockDb.getCbosCadastrados();
    }

    public void salvaCbo(Cbo cbo) {
        throw new CustomRuntimeException("Em construção...");
    }
}
