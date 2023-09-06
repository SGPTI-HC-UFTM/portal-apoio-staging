package net.ebserh.hctm.service.aghu;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import net.ebserh.hctm.model.aghu.Especialidade;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class EspecialidadesService {

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Especialidade> buscaEspecialidadesAtivas() {
        try {
            return entityManager
                    .createNamedQuery("Especialidade.findAtivas", Especialidade.class)
                    .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as especialidades.");
        }
    }

    public List<Especialidade> buscaEspecialidade(String descricao) {
		
		try {
			return entityManager.createNamedQuery("Especialidade.findByNomeLike", Especialidade.class)
					.setParameter("nome", String.format("%%%s%%", descricao.toUpperCase()))
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao pesquisar as especialidades.");
		}

	}

    public Especialidade buscaPorCodigo(Integer seq) {
        return entityManager.find(Especialidade.class, seq);
    }

    public List<Especialidade> buscaPorCodigoIn(List<Integer> seqs) {
        try {
            return entityManager.createNamedQuery("Especialidade.findBySeqIn", Especialidade.class)
                .setParameter("seqs", seqs)
                .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao buscar as especialidades.");
        }
    }

    public Especialidade buscaPorSigla(String sigla) {

        try {
            return entityManager.createNamedQuery("Especialidade.findBySigla", Especialidade.class)
                    .setParameter("sigla", sigla.toUpperCase())
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new CustomRuntimeException("Nenhuma especialidade não encontrada.");
        } catch (NonUniqueResultException e) {
            throw new CustomRuntimeException("Mais de uma especialidade encontrada para a sigla informada.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new CustomRuntimeException("Ocorreu um erro ao pesquisar as especialidades.");
        }

    }

}
