package br.inatel.dm110.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import br.inatel.dm110.api.HelloService;
import br.inatel.dm110.api.MessageTO;
import br.inatel.dm110hello.interfaces.IHelloRemote;

@RequestScoped
public class HelloServiceImpl implements HelloService {

	static private int count = 0;
	
	
	@EJB(lookup = "ejb:dm110-ear-1.0/dm110-ejb-1.0/HelloBean!br.inatel.dm110hello.interfaces.IHelloRemote")
	private IHelloRemote helloBean;
	
	//test: in memory storage
	static private Map<Integer, MessageTO> cache = new HashMap<>();


	@Override
	public String sayHello(String name) {
		String message = helloBean.sayHello(name);
		return "<h1>" + message + "</h1>";
	}

	@Override
	public MessageTO message(String first, String last) {
		MessageTO result = new MessageTO();
		result.setFirstName(first);
		result.setLastName(last);
		String message = String.format("Hello %s %s!!!", first, last);
		result.setMessage(message);
		return result;
	}

	@Override
	public int storeNewMessage(MessageTO message) {
		message.setMessage("Hello " + message.getFirstName() + " " + message.getLastName());
		System.out.println("Message created: " + message.getMessage());
		count++;
		cache.put(count, message);
		return count;
	}
	
	@Override
	public Collection<MessageTO> getMessages() {
		return cache.values();
	}
}
