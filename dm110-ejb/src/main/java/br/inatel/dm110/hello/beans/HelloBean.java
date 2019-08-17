package br.inatel.dm110.hello.beans;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.JMSException;

import br.inatel.dm110hello.interfaces.IHelloLocal;
import br.inatel.dm110hello.interfaces.IHelloRemote;

@Stateless
@Remote(IHelloRemote.class)
@Local(IHelloLocal.class)
public class HelloBean implements IHelloLocal, IHelloRemote {
	
	@EJB
	private HelloMessageSender msgSender;

	@Override
	public String sayHello(String name) {
		
		try {
			msgSender.sendHelloMessage(name);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Mensagem enviada para o Hello MDB.");
		
		return "HelloBean saying hello to " + name;
		
	}
}
