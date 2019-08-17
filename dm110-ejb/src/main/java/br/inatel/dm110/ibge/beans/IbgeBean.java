package br.inatel.dm110.ibge.beans;

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

import br.inatel.dm110.ibge.dto.StateDto;
import br.inatel.dm110.ibge.interfaces.IIbgeLocal;
import br.inatel.dm110.ibge.interfaces.IIbgeRemote;
import br.inatel.dm110.hello.dao.IBGEDAO;
import br.inatel.dm110.hello.entities.State;


@Stateless
@Remote(IIbgeRemote.class)
@Local(IIbgeLocal.class)
public class IbgeBean implements IIbgeLocal, IIbgeRemote {
	
	@EJB
	private IBGEDAO dao;
	
	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connFactory;
	
	@Resource(lookup = "java:/jms/queue/dm110state")
	private Queue queue;

	@Override
	public List<StateDto> listAllStates() {
		
		ArrayList<StateDto> list = new ArrayList<>();
		for(State state : dao.listAll()) {
			StateDto st = new StateDto();
			
			st.setArea(state.getArea());
			st.setIbge(state.getIbge());
			st.setNome(state.getNome());
			st.setSigla(state.getSigla());
			
			list.add(st);
		}
		
		return list;
	}

	@Override
	public void createState(StateDto state) {
		// TODO Auto-generated method stub
		State st = new State();
		
		st.setArea(state.getArea());
		st.setIbge(state.getIbge());
		st.setNome(state.getNome());
		st.setSigla(state.getSigla());
	
		dao.insert(st);
		this.sendStateCreated(state);
		
	}
		
	public void sendStateCreated(StateDto state){
		
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
