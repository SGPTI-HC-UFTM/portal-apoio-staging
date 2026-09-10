package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.FonteFinanciadora;
import net.ebserh.hctm.model.pesquisa.Projeto;
import net.ebserh.hctm.model.pesquisa.StatusProjeto;
import net.ebserh.hctm.model.pesquisa.TipoProjeto;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ProjetosService {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

    public void salvaProjeto(Projeto projeto) {
        throw new CustomRuntimeException("Em construção...");
    }

    public List<Projeto> buscaProjetosPorTitulo(String titulo) {
        throw new CustomRuntimeException("Em construção...");
    }

    public List<TipoProjeto> buscaTiposProjeto() {
        try {
            return entityManager.createNamedQuery("TipoProjeto.findAll", TipoProjeto.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os tipos de projeto.");
        }
    }

    public List<StatusProjeto> buscaStatusProjeto() {
        throw new CustomRuntimeException("Em construção...");
    }

    public List<FonteFinanciadora> buscaFontesFinanciadoras() {
        throw new CustomRuntimeException("Em construção...");
    }

}
