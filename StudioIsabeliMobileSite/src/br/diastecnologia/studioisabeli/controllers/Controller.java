package br.diastecnologia.studioisabeli.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Result;
import br.diastecnologia.studioisabeli.session.StudioSession;

public class Controller {

	protected Result result;
	protected StudioSession session;
	protected ServletContext context;
	protected HttpServletResponse response;
	
	
	public Controller(HttpServletResponse _response, Result _result, StudioSession _session, ServletContext _context){
		this.response = _response;
		this.result = _result;
		this.context = _context;
		
		this.session = _session;
	}
	
}
