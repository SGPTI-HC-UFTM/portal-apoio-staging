package net.ebserh.hctm.service.triagens;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import net.ebserh.hctm.model.triagens.TriagemNeonatal;

@Stateless
public class TriagemNeonatalService {
    
    @PersistenceContext
	private EntityManager entityManager;

    public void salvaTriagem(TriagemNeonatal triagemNeonatal){
        entityManager.persist(triagemNeonatal);
    }

}
