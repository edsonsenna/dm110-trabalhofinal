package br.inatel.dm110.pedidovenda.interfaces;

import java.util.List;

import br.inatel.dm110.pedidovenda.to.PedidoVendaTO;


public interface IPedidoVenda {

	public List<PedidoVendaTO> listAll();
	
	public void insert(PedidoVendaTO pedido);
	
	public void atualiza(PedidoVendaTO pedido);
	
	public PedidoVendaTO buscaUnico(int codigo);
	
	public void remove(int pedido);
	
}
