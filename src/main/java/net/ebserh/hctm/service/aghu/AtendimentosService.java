package net.ebserh.hctm.service.aghu;

import java.util.List;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import net.ebserh.hctm.model.aghu.Atendimento;
import net.ebserh.hctm.model.aghu.internacoes.Internacao;

@Stateless
public class AtendimentosService {

	private static final Logger logger = Logger.getAnonymousLogger();
		
	@PersistenceContext
	private EntityManager entityManager;

    public Atendimento buscaPorProntuario(Integer prontuario) {
		List<Atendimento> atendimentos = entityManager.createQuery("select a "
				+ "from Atendimento a "
				+ "join a.paciente p "
				+ "where p.prontuario = :prontuario "
				+ "and a.origem = 'I' "
				+ "order by seq desc", Atendimento.class)
				.setParameter("prontuario", prontuario)
				.getResultList();
		
		if (!atendimentos.isEmpty())
			return atendimentos.get(0);
		
		return null;
	}
}
