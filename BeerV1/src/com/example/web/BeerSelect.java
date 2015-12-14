/**
 * 
 */
package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author marcos.fernando
 *
 */
public class BeerSelect extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("Conselhos sobre a Escolha da cerveja: <br>");
		String c = request.getParameter("cor");
		out.println("Voçê selecionou a cor: <b>" + c + "</b>"); 
		
	}
	
}
