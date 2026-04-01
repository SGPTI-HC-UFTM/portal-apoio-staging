package net.ebserh.hctm.service.pesquisa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.pesquisa.NivelFormacao;
import net.ebserh.hctm.model.pesquisa.OrgaoFinanciador;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class NiveisFormacaoService {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

    public List<NivelFormacao> buscaNiveis() {
        try {
            return entityManager
                    .createNamedQuery("NivelFormacao.findAll", NivelFormacao.class)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Erro na busca pelos níveis de formação!");
        }
    }

    public void salvaNivelFormacao (NivelFormacao nivelFormacao) {
        if (Objects.isNull(nivelFormacao))
            throw new CustomRuntimeException("É necessário informar os dados do nível de formação!");

        if (StringUtils.isBlank(nivelFormacao.getNivel()))
            throw new CustomRuntimeException("É necessário informar o nome do nível de formação!");

        try {
            if (Objects.isNull(nivelFormacao.getId()))
                entityManager.persist(nivelFormacao);
            else
                entityManager.merge(nivelFormacao);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Erro ao salvar nível de formação!");
        }
    }
}