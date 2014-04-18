package br.diastecnologia.studioisabeli.controllers;

import javax.servlet.ServletContext;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.diastecnologia.studioisabeli.daos.CustomerScrapDAO;
import br.diastecnologia.studioisabeli.daos.CustomerTokenDAO;
import br.diastecnologia.studioisabeli.daos.TipDAO;
import br.diastecnologia.studioisabeli.session.StudioSession;

@Resource
public class MenuController extends Controller{

	public MenuController(Result _result, StudioSession _session, ServletContext _context, CustomerTokenDAO _customerTokenDAO, TipDAO _tipDAO, CustomerScrapDAO _customerScrapDAO){
		super(_result, _session, _context, _customerTokenDAO, _tipDAO, _customerScrapDAO);
	}
	
	@Path("/menu")
	public void menu(){
		
	}
	
}
