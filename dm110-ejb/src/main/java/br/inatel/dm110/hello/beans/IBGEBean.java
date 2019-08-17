package br.inatel.dm110.hello.beans;

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

import br.inatel.dm110.hello.dao.IBGEDAO;
import br.inatel.dm110.hello.entities.State;
import br.inatel.dm110.ibge.dto.StateDto;
import br.inatel.dm110.ibge.interfaces.IBGELocal;
import br.inatel.dm110.ibge.interfaces.IBGERemote;
import br.inatel.dm110.ibge.to.StateTO;

@Stateless
@Remote(IBGERemote.class)
@Local(IBGELocal.class)
public class IBGEBean implements IBGERemote, IBGELocal {

	@EJB
	private IBGEDAO dao;
	
	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connFactory;
	
	@Resource(lookup = "java:/jms/queue/dm110state")
	private Queue queue;
	
	
	
	@Override
	public void createState(StateTO state) {
		State st = new State();
		st.setArea(state.getArea());
		st.setIbge(state.getIbge());
		st.setNome(state.getNome());
		st.setSigla(state.getSigla());
		dao.insert(st);

		this.sendStateCreated(state);
	}
	
	@Override
	public List<StateTO> listAllStates() {
		ArrayList<StateTO> list = new ArrayList<>();
		for (State st : dao.listAll()) {
			StateTO to = new StateTO();
			to.setArea(st.getArea());
			to.setIbge(st.getIbge());
			to.setNome(st.getNome());
			to.setSigla(st.getSigla());
			list.add(to);
		}
		return list;
	}
	
	public void sendStateCreated(StateTO state){
		
		System.out.println("State created!");
		
		try {
			Connection conn = connFactory.createConnection();
			
			Session session = conn.createSession();
			
			MessageProducer producer = session.createProducer(queue);
			
			ObjectMessage msg = session.createObjectMessage(state);
			
			producer.send(msg);
			
		}catch(JMSException e) {
			e.printStackTrace();
		}
		
		
	}
}
