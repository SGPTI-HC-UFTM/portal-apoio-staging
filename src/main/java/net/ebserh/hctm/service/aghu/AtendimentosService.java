package net.ebserh.hctm.service.aghu;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.ebserh.hctm.model.aghu.Atendimento;
import net.ebserh.hctm.model.aghu.internacoes.Internacao;

@Stateless
public class AtendimentosService {

    @Inject
	private Logger logger;
		
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
