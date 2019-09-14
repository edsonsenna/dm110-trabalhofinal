package br.inatel.dm110.pedidovenda.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import br.inatel.dm110.pedidovenda.api.IPedidoVendaService;
import br.inatel.dm110.pedidovenda.interfaces.IPedidoVendaRemote;
import br.inatel.dm110.pedidovenda.to.PedidoVendaTO;

@RequestScoped
public class PedidoVendaServiceImpl implements IPedidoVendaService {

	@EJB(lookup = "ejb:dm110-ear-1.0/dm110-ejb-1.0/PedidoVendaBean!br.inatel.dm110.pedidovenda.interfaces.IPedidoVendaRemote")
	private IPedidoVendaRemote pedidoBean;

	@Override
	public void criarPedidoVenda(PedidoVendaTO pedido) {
		pedidoBean.insert(pedido);
	}

	@Override
	public void atualizarPedidoVenda(PedidoVendaTO pedido) {
		pedidoBean.atualiza(pedido);
	}

	@Override
	public PedidoVendaTO buscaPedidoVenda(int codigo) {
		return pedidoBean.buscaUnico(codigo);
	}

	@Override
	public List<PedidoVendaTO> buscaPedidosVenda() {
		return pedidoBean.listAll();
	}

	@Override
	public void removePedidoVenda(int codigo) {
		pedidoBean.remove(codigo);
	}

}
