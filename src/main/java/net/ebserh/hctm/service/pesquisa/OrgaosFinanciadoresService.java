package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.logging.Logger;

@Stateless
public class OrgaosFinanciadoresService {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

}
