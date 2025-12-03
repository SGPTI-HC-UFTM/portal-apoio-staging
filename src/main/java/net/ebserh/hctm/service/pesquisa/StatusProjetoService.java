package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.StatusProjeto;
import net.ebserh.hctm.util.MockDb;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class StatusProjetoService {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private MockDb mockDb;

    public List<StatusProjeto> buscaStatusProjetos(){
        return mockDb.getStatusProjetoCadastrados();
    }

    public void salvaStatus(StatusProjeto statusProjeto){
        throw new CustomRuntimeException("Em construção...");
    }

}
