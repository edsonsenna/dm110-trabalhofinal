package br.inatel.dm110.pedidovenda.mdb;

import java.time.LocalDate;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import br.inatel.dm110.log.dao.LogDAO;
import br.inatel.dm110.log.entities.Log;
import br.inatel.dm110.pedidovenda.dao.PedidoVendaDAO;
import br.inatel.dm110.pedidovenda.to.PedidoVendaTO;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(
				propertyName = "destinationType",
				propertyValue = "javax.jms.Queue"
		),
		@ActivationConfigProperty(
				propertyName = "destination",
				propertyValue = "java:/jms/queue/dm110pedidovenda"
		),
		@ActivationConfigProperty(
				propertyName = "maxSession",
				propertyValue = "5"
		)
		
	
		
})

public class PedidoVendaMDB implements MessageListener {
	
	@EJB
	private LogDAO dao;

	@Override
	public void onMessage(Message pedidovenda) {
		// TODO Auto-generated method stub
			
		if(pedidovenda instanceof ObjectMessage) {
			
			
			try {
				ObjectMessage objMsg = (ObjectMessage) pedidovenda;
				
				Object object = objMsg.getObject();
				
				if(object instanceof PedidoVendaTO) {
					
					PedidoVendaTO pedidoVendaTO = (PedidoVendaTO) object;
					
					System.out.println("Iniciando processamento do MDB...");
					
					Thread.sleep(1500);
					
					System.out.println("Recebendo objeto: "+ pedidoVendaTO.toString());
					
					
					
					Log log = new Log();
					
					log.setId(1);
					log.setCodigo(Integer.toString(pedidoVendaTO.getCodigo()));
					log.setOperacao("Operacao Default");
					log.setData(LocalDate.now());
					
					dao.insert(log);
					
					System.out.println(log.toString());
					
					Thread.sleep(1500);

					System.out.println("Objeto recebido!");
				
				}
				
				
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
	}

}
