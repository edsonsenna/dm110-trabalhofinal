package br.inatel.dm110.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calcula")
public class CalcServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3468;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		double valor1 = Integer.parseInt(req.getParameter("valor1"));
		double valor2 = Integer.parseInt(req.getParameter("valor2"));
		String operador = req.getParameter("operador");
		double resultado = 0;
		
		
		
		switch(operador) {
			case " ":
				resultado = valor1 + valor2;
				break;
			case "-":
				resultado = valor1 - valor2;
				break;
			case "*":
				resultado = valor1 * valor2;
				break;
			case "/":
				resultado = valor1 / valor2;
				break;
			default:
				resultado = 0;
				break;
		}
		
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.println("<h1>Resultado da Operacao: "+resultado+"</h1>");
		
	}
	

}
