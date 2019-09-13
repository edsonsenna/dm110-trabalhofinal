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
	public void onMessage(Message log) {
		// TODO Auto-generated method stub
			
		if(log instanceof ObjectMessage) {
			
			
			try {
				ObjectMessage objMsg = (ObjectMessage) log;
				
				Object object = objMsg.getObject();
				
				if(object instanceof Log) {
					
					Log logd = (Log) object;
					
					
					dao.insert(logd);
					
					System.out.println(log.toString());
				
				}
				
				
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
	}

}
