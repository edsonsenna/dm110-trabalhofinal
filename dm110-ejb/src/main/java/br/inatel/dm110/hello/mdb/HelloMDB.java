package br.inatel.dm110.hello.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(
				propertyName = "destinationType",
				propertyValue = "javax.jms.Queue"
		),
		@ActivationConfigProperty(
				propertyName = "destination",
				propertyValue = "java:/jms/queue/dm110queue"
		),
		@ActivationConfigProperty(
				propertyName = "maxSession",
				propertyValue = "5"
		)
		
	
		
})

public class HelloMDB implements MessageListener{

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		System.out.println("Acessa Classse!");
		try {
			 
			TextMessage textMsg = (TextMessage) message;
			
			String text = textMsg.getText();
			
			System.out.println("Iniciando processamento do MDB...");
			
			Thread.sleep(1500);
			
			System.out.println("Processando mensagem: "+ text);
			
			Thread.sleep(1500);
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		System.out.println("Finalizando processamento do MDB.");
		
		
		
		
	}

}
