package br.com.diastecnologia.servlets;

import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class LogServlet extends HttpServlet{

	private static final long serialVersionUID = 6815047883328915792L;

	public void doGet( HttpServletRequest request, HttpServletResponse response ){
		
		String logCategory = request.getParameter("logCategory");
		Logger logger = Logger.getLogger(logCategory);
		
		
		Enumeration<String> parameters = request.getParameterNames();
		while( parameters.hasMoreElements() ){
			String param = parameters.nextElement();
			String value = request.getParameter( param );
			logger.info( param + ": " + value );
		}
		
		response.setStatus(200);
		
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ){
		doGet(request, response);
	}
	
}
