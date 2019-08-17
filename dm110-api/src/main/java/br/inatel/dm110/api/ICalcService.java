package br.inatel.dm110.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/calculadora")
public interface ICalcService {
	
	@GET
	@Path("/adiciona/{valor1}/{valor2}")
	@Produces(MediaType.TEXT_HTML)
	String adiciona(@PathParam("valor1") String valor1, @PathParam("valor2") String valor2);
	
	@GET
	@Path("/subtrai/{valor1}/{valor2}")
	@Produces(MediaType.TEXT_HTML)
	String subtrai(@PathParam("valor1") String valor1, @PathParam("valor2") String valor2);
	
	@GET
	@Path("/multiplica/{valor1}/{valor2}")
	@Produces(MediaType.TEXT_HTML)
	String multiplica(@PathParam("valor1") String valor1, @PathParam("valor2") String valor2);
	@GET
	@Path("/divide/{valor1}/{valor2}")
	@Produces(MediaType.TEXT_HTML)
	String divide(@PathParam("valor1") String valor1, @PathParam("valor2") String valor2);

}
