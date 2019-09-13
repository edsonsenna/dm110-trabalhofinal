package br.inatel.dm110.pedidovenda.beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import br.inatel.dm110.log.entities.Log;
import br.inatel.dm110.pedidovenda.dao.PedidoVendaDAO;
import br.inatel.dm110.pedidovenda.entities.PedidoVenda;
import br.inatel.dm110.pedidovenda.interfaces.IPedidoVendaLocal;
import br.inatel.dm110.pedidovenda.interfaces.IPedidoVendaRemote;
import br.inatel.dm110.pedidovenda.to.PedidoVendaTO;

@Stateless
@Remote(IPedidoVendaRemote.class)
@Local(IPedidoVendaLocal.class)
public class PedidoVendaBean implements IPedidoVendaLocal, IPedidoVendaRemote {

	@EJB
	private PedidoVendaDAO dao;

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connFactory;

	@Resource(lookup = "java:/jms/queue/dm110pedidovenda")
	private Queue queue;

	@Override
	public List<PedidoVendaTO> listAll() {

		ArrayList<PedidoVendaTO> list = new ArrayList<>();
		for (PedidoVenda pedidovenda : dao.listAll()) {
			PedidoVendaTO pv = new PedidoVendaTO();

			pv.setCodigo(pedidovenda.getCodigo());
			pv.setCodigo_produto(pedidovenda.getCodigo_produto());
			pv.setCpf_cliente(pedidovenda.getCpf_cliente());
			pv.setData(pedidovenda.getData());
			pv.setQuantidade(pedidovenda.getQuantidade());
			pv.setValor(pedidovenda.getValor());

			list.add(pv);
		}

		return list;
	}

	@Override
	public void insert(PedidoVendaTO pedidoVenda) {
		PedidoVenda pv = new PedidoVenda();

		pv.setCodigo(pedidoVenda.getCodigo());
		pv.setCodigo_produto(pedidoVenda.getCodigo_produto());
		pv.setCpf_cliente(pedidoVenda.getCpf_cliente());
		pv.setData(LocalDate.now());
		pv.setQuantidade(pedidoVenda.getQuantidade());
		pv.setValor(pedidoVenda.getValor());

		dao.insert(pv);

		this.sendStateCreated(pedidoVenda);

	}

	@Override
	public void atualiza(PedidoVendaTO pedidoVenda) {
		PedidoVenda pv = new PedidoVenda();
		pv.setCodigo(pedidoVenda.getCodigo());
		pv.setCodigo_produto(pedidoVenda.getCodigo_produto());
		pv.setCpf_cliente(pedidoVenda.getCpf_cliente());
		pv.setData(LocalDate.now());
		pv.setQuantidade(pedidoVenda.getQuantidade());
		pv.setValor(pedidoVenda.getValor());

		dao.atualiza(pv);
		this.sendStateUpdated(pedidoVenda);
	}

	@Override
	public PedidoVendaTO buscaUnico(int codigo) {
		PedidoVenda pedidoVenda = dao.buscaUnico(codigo);

		PedidoVendaTO pv = new PedidoVendaTO();

		pv.setCodigo(pedidoVenda.getCodigo());
		pv.setCodigo_produto(pedidoVenda.getCodigo_produto());
		pv.setCpf_cliente(pedidoVenda.getCpf_cliente());
		pv.setData(pedidoVenda.getData());
		pv.setQuantidade(pedidoVenda.getQuantidade());
		pv.setValor(pedidoVenda.getValor());

		return pv;
	}

	public void sendStateCreated(PedidoVendaTO pedido) {

		System.out.println("pedido created!");

		try {
			Connection conn = connFactory.createConnection();

			Session session = conn.createSession();

			MessageProducer producer = session.createProducer(queue);
			
			Log log = new Log();
			
			log.setCodigo(Integer.toString(pedido.getCodigo()));
			log.setOperacao("Insercao");
			log.setData(LocalDate.now());

			ObjectMessage msg = session.createObjectMessage(log);

			producer.send(msg);

		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

	public void sendStateUpdated(PedidoVendaTO pedido) {

		System.out.println("pedido updated!");

		try {
			Connection conn = connFactory.createConnection();

			Session session = conn.createSession();

			MessageProducer producer = session.createProducer(queue);
			
			Log log = new Log();
			
			log.setCodigo(Integer.toString(pedido.getCodigo()));
			log.setOperacao("Atualizacao");
			log.setData(LocalDate.now());

			ObjectMessage msg = session.createObjectMessage(log);

			producer.send(msg);

		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}
