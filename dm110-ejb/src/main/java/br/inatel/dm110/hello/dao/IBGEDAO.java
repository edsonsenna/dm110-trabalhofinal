package br.inatel.dm110.hello.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.inatel.dm110.hello.entities.State;

@Stateless
public class IBGEDAO {

	@PersistenceContext(unitName="ibge_pu")
	private EntityManager em;
	
	public List<State> listAll() {
		TypedQuery<State> q = em.createQuery("select s from State s", State.class);
		return q.getResultList();
	}
	
	public void insert(State state) {
		em.persist(state);
	}
}
