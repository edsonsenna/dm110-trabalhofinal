package br.inatel.dm110.pedidovenda.dao;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.inatel.dm110.pedidovenda.entities.PedidoVenda;
import br.inatel.dm110.pedidovenda.to.PedidoVendaTO;

@Stateless
public class PedidoVendaDAO {

	@PersistenceContext(unitName="ibge_pu")
	private EntityManager em;
	
	public List<PedidoVenda> listAll() {
		TypedQuery<PedidoVenda> q = em.createQuery("select s from PedidoVenda s", PedidoVenda.class);
		return q.getResultList();
	}
	
	public void insert(PedidoVenda pedido) {
		em.persist(pedido);
	}
	
	public void atualiza(PedidoVendaTO pedido) {
		System.out.println("Falta implementar atualização!");
	}
	
	public PedidoVenda buscaUnico(int codigo) {
		
		PedidoVenda pedidovenda = em.find(PedidoVenda.class, codigo);
		
		System.out.println("Retorno DAO => " + pedidovenda);
		
		return pedidovenda;
	}
	
}
