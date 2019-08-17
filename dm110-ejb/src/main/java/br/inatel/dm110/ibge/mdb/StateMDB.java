package br.inatel.dm110.ibge.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import br.inatel.dm110.ibge.dto.StateDto;
import br.inatel.dm110.ibge.to.StateTO;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(
				propertyName = "destinationType",
				propertyValue = "javax.jms.Queue"
		),
		@ActivationConfigProperty(
				propertyName = "destination",
				propertyValue = "java:/jms/queue/dm110state"
		),
		@ActivationConfigProperty(
				propertyName = "maxSession",
				propertyValue = "5"
		)
		
})

public class StateMDB implements MessageListener{

	@Override
	public void onMessage(Message state) {
		// TODO Auto-generated method stub
		if(state instanceof ObjectMessage) {
			
			
			try {
				ObjectMessage objMsg = (ObjectMessage) state;
				
				Object object = objMsg.getObject();
				
				if(object instanceof StateTO) {
					
					StateTO stateDto = (StateTO) object;
					
					System.out.println("Iniciando processamento do MDB...");
					
					Thread.sleep(1500);
					
					System.out.println("Recebendo objeto: "+ stateDto.toString());
					
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
