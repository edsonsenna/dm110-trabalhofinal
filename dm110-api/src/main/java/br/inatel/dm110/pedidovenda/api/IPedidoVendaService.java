package br.inatel.dm110.pedidovenda.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.inatel.dm110.pedidovenda.to.PedidoVendaTO;

@Path("/pedidovenda")
public interface IPedidoVendaService {

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public void criarPedidoVenda(PedidoVendaTO pedido);

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizarPedidoVenda(PedidoVendaTO pedido);

	@GET
	@Path("/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoVendaTO buscaPedidoVenda(@PathParam("codigo") int codigo);

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PedidoVendaTO> buscaPedidosVenda();
	
	@DELETE
	@Path("/{codigo}")
	public void removePedidoVenda(@PathParam("codigo") int codigo);

}
