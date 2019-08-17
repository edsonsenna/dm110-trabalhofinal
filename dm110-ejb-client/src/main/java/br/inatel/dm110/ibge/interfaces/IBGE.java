package br.inatel.dm110.ibge.interfaces;

import java.util.List;

import br.inatel.dm110.ibge.to.StateTO;

public interface IBGE {

	public void createState(StateTO state);
	
	public List<StateTO> listAllStates();
}
