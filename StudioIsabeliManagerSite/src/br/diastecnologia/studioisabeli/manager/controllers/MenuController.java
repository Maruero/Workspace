package br.diastecnologia.studioisabeli.manager.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.diastecnologia.studioisabeli.controllers.Controller;
import br.diastecnologia.studioisabeli.manager.session.ManagerSession;

@Resource
public class MenuController extends Controller{

	private ManagerSession session;
	
	public MenuController(ManagerSession _session, HttpServletResponse _response, Result _result, ServletContext _context) {
		super(_response, _result, _context);
		this.session = _session;
	}
	
	
	@Get
	@Path("/")
	public void root(){
		result.redirectTo( MenuController.class ).home();
	}
	
	@Get
	@Path("/home")
	public void home(){
	}

}
