package net.ebserh.hctm.service.aghu.servidores;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import net.ebserh.hctm.model.aghu.servidores.Qualificacao;
import net.ebserh.hctm.model.aghu.servidores.Servidor;
import net.ebserh.hctm.model.aghu.servidores.ServidorPK;
import net.ebserh.hctm.exception.CustomRuntimeException;

@Stateless
public class ServidoresService {

	private static final Logger logger = Logger.getAnonymousLogger();

	@PersistenceContext
	private EntityManager entityManager;

	private final String QUERY_ACADEMICOS_CPF = "SELECT "
			+ "	pf.cpf AS cpf, "
			+ "	ser.vin_codigo IN (5, 6, 8, 10, 11, 12) AS academico "
			+ "FROM "
			+ "	agh.rap_servidores ser "
			+ "	JOIN agh.rap_pessoas_fisicas pf ON ser.pes_codigo = pf.codigo "
			+ "WHERE "
			+ "	ser.ind_situacao <> 'I' "
			+ "	AND pf.cpf = :cpf";

	private final String QUERY_ACADEMICOS_LOGIN = "SELECT "
			+ "	ser.usuario AS login, "
			+ "	ser.vin_codigo IN (5, 6, 8, 10, 11, 12) AS academico "
			+ "FROM "
			+ "	agh.rap_servidores ser "
			+ "WHERE "
			+ "	ser.ind_situacao <> 'I' "
			+ "	AND ser.usuario = :login";

	public List<Servidor> buscaMedicos() {
		try {
			return entityManager.createQuery("select s "
					+ "from Servidor s "
					+ "join s.pessoa p "
					+ "where s.codigoCargo = '1'"
					+ "and s.situacao = 'A' "
					+ "order by p.nome", Servidor.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os médicos.");
		}
	}

	public List<Servidor> buscaMedicosPorMatricula(Integer matricula) {
		try {
			return entityManager.createQuery("select s "
					+ "from Servidor s "
					+ "join s.pessoa p "
					+ "where str(s.id.matricula) like concat('%', :matricula, '%') "
					+ "and s.codigoCargo = '1' "
					+ "and s.situacao <> 'I' "
					+ "order by p.nome", Servidor.class)
					.setParameter("matricula", matricula)
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os médicos.");
		}
	}

	public List<Servidor> buscaAtivosPorMatricula(Integer matricula) {
		try {
			return entityManager
					.createNamedQuery("Servidor.findAtivosByMatriculaContains", Servidor.class)
					.setParameter("matricula", matricula)
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os colaboradores.");
		}
	}

	public List<Servidor> buscaMedicosPorNome(String nome) {
		try {
			return entityManager.createQuery("select s "
					+ "from Servidor s "
					+ "join s.pessoa p "
					+ "where upper(p.nome) like concat('%', upper(:nome), '%') "
					+ "and s.codigoCargo = '1' "
					+ "and s.situacao <> 'I' "
					+ "order by p.nome", Servidor.class)
					.setParameter("nome", nome)
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os médicos.");
		}
	}

	public List<Servidor> buscaAtivosPorNome(String nome) {
		try {
			return entityManager
					.createNamedQuery("Servidor.findAtivosByNomeContains", Servidor.class)
					.setParameter("nome", nome)
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os colaboradores.");
		}
	}

	public List<Servidor> buscaMedicosPorMatriculaNome(Integer matricula, String nome) {
		try {
			return entityManager.createQuery("select s "
					+ "from Servidor s "
					+ "join s.pessoa p "
					+ "where str(s.id.matricula) like concat('%', :matricula, '%') "
					+ "and upper(p.nome) like concat('%', upper(:nome), '%') "
					+ "and s.codigoCargo = '1' "
					+ "and s.situacao <> 'I' "
					+ "order by p.nome", Servidor.class)
					.setParameter("matricula", matricula)
					.setParameter("nome", nome)
					.getResultList();
		} catch (CustomRuntimeException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os médicos.");
		}
	}

	public Servidor buscaMedidoPorCodigoPessoa(Integer codigoPessoa) {
		try {
			return entityManager.createNamedQuery("Servidor.findByCodigoPessoa", Servidor.class)
					.setParameter("codigo", codigoPessoa)
					.getSingleResult();

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar a médico.");
		}
	}

	public Qualificacao buscaQualificacao(Integer pessoaCodigo) {
		try {
			return entityManager.find(Qualificacao.class, pessoaCodigo);

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar a qualificação.");
		}
	}

	public List<Servidor> buscaAtivosPorMatriculaNome(Integer matricula, String nome) {
		try {
			return entityManager
					.createNamedQuery("Servidor.findAtivosByMatriculaContainsNomeContains", Servidor.class)
					.setParameter("matricula", matricula)
					.setParameter("nome", nome)
					.getResultList();
		} catch (CustomRuntimeException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os colaboradores.");
		}
	}

	public List<Servidor> buscaVinculoMatriculaIn(List<ServidorPK> listaChaves) {
		try {
			if (!listaChaves.isEmpty()) {
				return entityManager.createQuery("from Servidor "
						+ "where id in :listaChaves",
						Servidor.class)
						.setParameter("listaChaves", listaChaves)
						.getResultList();
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao buscar os médicos.");
		}

		return new ArrayList<Servidor>();
	}

	public boolean verificaAcademicoPorCpf(Long cpf) {
		if (cpf == null)
			return false;

		try {
			@SuppressWarnings("unchecked")
			List<Object[]> rset = (List<Object[]>) entityManager
					.createNativeQuery(QUERY_ACADEMICOS_CPF)
					.setParameter("cpf", cpf)
					.getResultList();

			if (!rset.isEmpty())
				return (Boolean) rset.get(0)[1];
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao pesquisar os servidores.");
		}

		return false;
	}

	public boolean verificaAcademicoPorLogin(String login) {
		if (login == null || login.isBlank())
			return false;

		try {
			@SuppressWarnings("unchecked")
			List<Object[]> rset = (List<Object[]>) entityManager
					.createNativeQuery(QUERY_ACADEMICOS_LOGIN)
					.setParameter("login", login)
					.getResultList();

			if (!rset.isEmpty())
				return (Boolean) rset.get(0)[1];
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Ocorreu um erro ao pesquisar os servidores.");
		}

		return false;
	}

	public Servidor buscaPorVinculoMatricula(Integer vinculo, Integer matricula) {
		ServidorPK id = new ServidorPK(vinculo, matricula);

		return entityManager.find(Servidor.class, id);
	}

}
