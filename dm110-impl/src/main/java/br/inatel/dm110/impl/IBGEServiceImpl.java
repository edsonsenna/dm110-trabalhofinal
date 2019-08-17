package br.inatel.dm110.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import br.inatel.dm110.api.IBGEService;
import br.inatel.dm110.ibge.interfaces.IBGERemote;
import br.inatel.dm110.ibge.to.StateTO;

@RequestScoped
public class IBGEServiceImpl implements IBGEService {

	@EJB(lookup= "ejb:dm110-ear-1.0/dm110-ejb-1.0/IBGEBean!br.inatel.dm110.ibge.interfaces.IBGERemote")
	private IBGERemote ibgeBean;
	
	@Override
	public void createNewState(StateTO state) {
		ibgeBean.createState(state);
	}
	
	@Override
	public List<StateTO> listAllStates() {
		return ibgeBean.listAllStates();
	}
}
