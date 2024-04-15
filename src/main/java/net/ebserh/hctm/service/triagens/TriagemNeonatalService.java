package net.ebserh.hctm.service.triagens;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.ebserh.hctm.model.triagens.TriagemNeonatal;

@Stateless
public class TriagemNeonatalService {
    
    @PersistenceContext
	private EntityManager entityManager;

    public void salvaTriagem(TriagemNeonatal triagemNeonatal){
        entityManager.persist(triagemNeonatal);
    }

}
