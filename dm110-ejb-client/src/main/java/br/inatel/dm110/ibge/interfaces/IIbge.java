package br.inatel.dm110.ibge.interfaces;

import java.util.List;

import br.inatel.dm110.ibge.dto.StateDto;

public interface IIbge {
	
	public List<StateDto> listAllStates();
	
	public void createState(StateDto state);

}
