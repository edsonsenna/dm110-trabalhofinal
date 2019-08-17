package br.inatel.dm110.impl;

import br.inatel.dm110.api.ICalcService;

public class CalcService implements ICalcService {

	@Override
	public String adiciona(String valor1, String valor2) {
		// TODO Auto-generated method stub
		return "<h1>"+(Integer.parseInt(valor1) + Integer.parseInt(valor2))+"</h1>";
	}

	@Override
	public String subtrai(String valor1, String valor2) {
		// TODO Auto-generated method stub

		return "<h1>"+(Integer.parseInt(valor1) - Integer.parseInt(valor2))+"</h1>";
	}

	@Override
	public String multiplica(String valor1, String valor2) {
		// TODO Auto-generated method stub

		return "<h1>"+(Integer.parseInt(valor1) * Integer.parseInt(valor2))+"</h1>";
	}

	@Override
	public String divide(String valor1, String valor2) {
		// TODO Auto-generated method stub

		return "<h1>"+(Integer.parseInt(valor1) / Integer.parseInt(valor2))+"</h1>";
	}

}
