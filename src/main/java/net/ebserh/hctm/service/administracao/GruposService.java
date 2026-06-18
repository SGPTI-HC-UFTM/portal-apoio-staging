package net.ebserh.hctm.service.administracao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.auth.Grupo;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class GruposService {

	private static final Logger logger = Logger.getAnonymousLogger();

    @PersistenceContext
	private EntityManager entityManager;

	public List<Grupo> buscaTodos() {
		try {
			return entityManager.createNamedQuery("Grupo.findAll", Grupo.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os grupos disponíveis.");
		}
	}

	public void salvaGrupo(Grupo grupo) {
		if (grupo == null)
			throw new CustomRuntimeException("É necessário informar um grupo.");

		if (StringUtils.isBlank(grupo.getGrupo()))
			throw new CustomRuntimeException("É necessário informar o nome do grupo.");

		try {
			Grupo grupoExistente;
			try {
				grupoExistente = entityManager
					.createNamedQuery("Grupo.findByGrupo", Grupo.class)
					.setParameter("grupo", grupo.getGrupo().toUpperCase())
					.getSingleResult();

				if (!grupoExistente.equals(grupo))
					throw new CustomRuntimeException("Já existe grupo cadastrado com este nome.");
			} catch (NoResultException e) {
				// Ok, sem duplicidade...
			}

			if (grupo.getId() == null)
				entityManager.persist(grupo);
			else
				entityManager.merge(grupo);
		} catch (CustomRuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao salvar o grupo.");
		}
	}

}
