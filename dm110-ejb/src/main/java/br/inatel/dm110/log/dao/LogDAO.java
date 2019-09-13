package br.inatel.dm110.log.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.inatel.dm110.log.entities.Log;


@Stateless
public class LogDAO {
	
	@PersistenceContext(unitName="trabalhoFinal_pu")
	private EntityManager em;
	
	public void insert(Log log) {
		em.persist(log);
	}
	

}
