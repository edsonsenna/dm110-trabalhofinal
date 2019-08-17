package br.inatel.dm110.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.inatel.dm110.impl.CalcService;
import br.inatel.dm110.impl.HelloServiceImpl;
import br.inatel.dm110.impl.IBGEServiceImpl;
import br.inatel.dm110.pedidovenda.impl.PedidoVendaServiceImpl;

@ApplicationPath("/api")
public class RestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(HelloServiceImpl.class);
		classes.add(CalcService.class);
		classes.add(IBGEServiceImpl.class);
		classes.add(PedidoVendaServiceImpl.class);
		return classes;
	}

}
