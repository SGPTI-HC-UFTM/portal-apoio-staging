package net.ebserh.hctm.service.auth;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.auth.Grupo;
import net.ebserh.hctm.model.auth.Usuario;
import net.ebserh.hctm.model.auth.UsuarioGrupo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class UsuariosService {

	private static final Logger LOGGER = Logger.getAnonymousLogger();

	@Inject
	private GruposService gruposService;

	@PersistenceContext
	private EntityManager entityManager;

	public List<Usuario> buscaPorLogin(String login) {
		try {
			return entityManager
					.createNamedQuery("Usuario.findByLogin", Usuario.class)
					.setParameter("login", "%" + login.toUpperCase() + "%")
					.getResultList();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao pesquisar os usuários.");
		}
	}

	public Usuario buscaUsuarioPorLogin(String login) {
		try {
            return entityManager.createNamedQuery("Usuario.findByLogin", Usuario.class)
                .setParameter("login", login.toUpperCase())
                .getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao pesquisar os usuários.");
		}
	}

	public List<Grupo> buscaGruposPorUsuario(Usuario usuario) {
		if (usuario == null || usuario.getId() == null)
			throw new CustomRuntimeException("É necessário informar um usuário.");

		try {
			Usuario usuarioExistente = entityManager.find(Usuario.class, usuario.getId());

			if (usuarioExistente == null)
				throw new CustomRuntimeException("Usuário não encontrado.");

			return gruposService.buscaGruposPorUsuario(usuarioExistente);
		} catch (CustomRuntimeException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao pesquisar os grupos do usuário.");
		}
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario buscaUsuarioPorId(Integer id) {
		try {
			return entityManager.find(Usuario.class, id);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao pesquisar o usuário.");
		}
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Usuario> buscaUsuarioPorIdIn(List<Integer> ids) {
		if (ids == null || ids.isEmpty())
			throw new CustomRuntimeException("É necessário informar os usuários.");

		try {
			return entityManager
					.createNamedQuery("Usuario.findByIdIn", Usuario.class)
					.setParameter("ids", ids)
					.getResultList();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao pesquisar os usuários.");
		}
	}

	public Grupo buscaGrupoPorNome(String nomeGrupo) {
		try {
			return entityManager
					.createNamedQuery("Grupo.findByNome", Grupo.class)
					.setParameter("nome", nomeGrupo)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao pesquisar o grupo.");
		}
	}

	public void atualizaGrupos(Usuario usuario, List<String> gruposRemover, List<String> gruposAcrescentar) {
		if (usuario == null)
			throw new CustomRuntimeException("É necessário informar o usuário.");

		if (gruposRemover == null || gruposAcrescentar == null)
			throw new CustomRuntimeException("É necessário informar os perfis do usuário.");

		try {
			List<Grupo> gruposUsuario = gruposService.buscaGruposPorUsuario(usuario);
			for (String nomeGrupo : gruposRemover) {
				Grupo grupo = gruposService.buscaPorNome(nomeGrupo);
				if (grupo == null)
					throw new CustomRuntimeException("O grupo " + nomeGrupo + " não foi encontrado.");

                gruposUsuario.remove(grupo);
			}

			for (String nomeGrupo : gruposAcrescentar) {
				Grupo grupo = gruposService.buscaPorNome(nomeGrupo);
				if (!gruposUsuario.contains(grupo))
					gruposUsuario.add(grupo);
			}

			usuario.setGrupos(gruposUsuario);
			entityManager.merge(usuario);
		} catch (CustomRuntimeException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao atualizar os perfis.");
		}
	}

	public void removeGrupo(Integer usuarioId, Integer grupoId) {
		try {
			entityManager
					.createNativeQuery("delete from auth.users_groups where user_id = :userId and group_id = :groupId")
					.setParameter("userId", usuarioId)
					.setParameter("groupId", grupoId)
					.executeUpdate();

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao remover grupo");
		}
	}

	public void salva(Usuario usuario) {
		LOGGER.severe("DBG 1: salva");
		if (usuario.getLogin() == null || usuario.getLogin().isBlank())
			throw new CustomRuntimeException("É necessário informar o login do usuário.");

		Usuario usuarioExistente = null;
		try {
			usuarioExistente = entityManager
					.createNamedQuery("Usuario.findByLogin", Usuario.class)
					.setParameter("login", usuario.getLogin().toUpperCase())
					.getSingleResult();

			if (!usuarioExistente.equals(usuario))
				throw new CustomRuntimeException("Já existe um usuário cadastrado com este login.");
		} catch (CustomRuntimeException e) {
			throw e;
		} catch (NoResultException e) {
			// Ok, novo usuário
		} catch (NonUniqueResultException e) {
			throw new CustomRuntimeException("Mais de um usuário encontrado com o login informado.");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao pesquisar os usuários existentes.");
		}

		LOGGER.severe("DBG 2: sem conflito de usuário existente");
		try {
			usuario.setLogin(usuario.getLogin().toLowerCase());
			if (usuario.getId() != null) {
				LOGGER.severe("DBG 3: merge");
				entityManager.merge(usuario);
			} else {
				LOGGER.severe("DBG 4: persist");
				entityManager.persist(usuario);
			}

			LOGGER.severe("DBG 5: ok");

			/*
			for (Grupo g : usuario.getGrupos()) {
				LOGGER.severe("DBG 6: salvando grupo");
				UsuarioGrupo ug = new UsuarioGrupo();
				ug.setUsuario(usuario);
				ug.setGrupo(g);

				entityManager.persist(ug);
			}

			LOGGER.severe("DBG 7: grupos salvos");
			 */
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao salvar o usuário.");
		}
	}

	public List<Grupo> buscaGruposPorLogin(String login) {
		if (login == null || login.isBlank())
			throw new CustomRuntimeException("É necessário informar o login.");

		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Grupo> criteriaQuery = criteriaBuilder.createQuery(Grupo.class);
			Root<Usuario> usuario = criteriaQuery.from(Usuario.class);
			Join<Usuario, Grupo> grupos = usuario.join("grupos");

			criteriaQuery.select(grupos)
					.where(criteriaBuilder.equal(criteriaBuilder.upper(usuario.get("login")), login.toUpperCase()));
			return entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar as permissões do usuário.");
		}
	}

	public Boolean verificaRole(String login, String role) {
		if (login == null)
			return false;

		Usuario user = buscaUsuarioPorLogin(login);
		if (user == null)
			return false;

		for (Grupo groupAuth : user.getGrupos()) {
			if (groupAuth.getGrupo().equals(role))
				return true;
		}

		return false;
	}

}
