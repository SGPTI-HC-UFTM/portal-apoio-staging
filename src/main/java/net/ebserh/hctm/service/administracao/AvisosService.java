package net.ebserh.hctm.service.administracao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.util.Aviso;

@Stateless
public class AvisosService {

    private static final Logger logger = Logger.getAnonymousLogger();

    @PersistenceContext
    private EntityManager entityManager;

    public List<Aviso> buscaTodos() {

        try {
            return entityManager
                    .createNamedQuery("Aviso.findAll", Aviso.class)
                    .getResultList();

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os avisos");
        }
    }

    public List<Aviso> buscaAvisosDia() {
        try {
            return entityManager.createNamedQuery("Aviso.findAvisosDia", Aviso.class)
                    .setParameter("horaAtual", LocalDateTime.now())
                    .getResultList();

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar os avisos");
        }
    }

    public void salva(Aviso aviso) {

        if (aviso == null)
            throw new CustomRuntimeException("É necessário preencher os dados do aviso.");

        if (aviso.getTitulo() == null || aviso.getTitulo().isBlank())
            throw new CustomRuntimeException("É necessário informar o título.");

        if (aviso.getInformacao() == null || aviso.getInformacao().isBlank())
            throw new CustomRuntimeException("É necessário informar o aviso.");

        if (aviso.getDthrInicial() == null)
            throw new CustomRuntimeException("É necessário informar a data inicial de exibição.");

        if (aviso.getDthrFinal() == null)
            throw new CustomRuntimeException("É necessário informar a data final de exibição.");

        try {

            if (aviso.getId() == null) {
                entityManager.persist(aviso);
            } else
                entityManager.merge(aviso);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao salvar o aviso.");
        }

    }

    public void excluir(Aviso aviso) {

        if (aviso == null)
            throw new CustomRuntimeException("Aviso não encontrado.");

        try {
            entityManager.remove(entityManager.merge(aviso));
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao excluir o aviso.");
        }

    }
}
