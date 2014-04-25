package br.diastecnologia.studioisabeli.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class MenuController extends Controller{
	
	public MenuController(HttpServletResponse _response, Result _result, ServletContext _context){
		super(_response, _result, _context);
	}
	
	@Path("/menu")
	public void menu(){
		
	}
	
}
