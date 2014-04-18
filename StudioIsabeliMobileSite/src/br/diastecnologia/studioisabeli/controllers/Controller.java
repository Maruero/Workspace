package br.diastecnologia.studioisabeli.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Result;
import br.diastecnologia.studioisabeli.daos.CustomerScrapDAO;
import br.diastecnologia.studioisabeli.daos.CustomerTokenDAO;
import br.diastecnologia.studioisabeli.daos.TipDAO;
import br.diastecnologia.studioisabeli.session.StudioSession;

public class Controller {

	protected Result result;
	protected StudioSession session;
	protected ServletContext context;
	protected CustomerTokenDAO customerTokenDAO;
	protected TipDAO tipDAO;
	protected CustomerScrapDAO customerScrapDAO;
	protected HttpServletResponse response;
	
	public Controller(HttpServletResponse _response, Result _result, StudioSession _session, ServletContext _context, CustomerTokenDAO _customerTokenDAO, TipDAO _tipDAO, CustomerScrapDAO _customerScrapDAO){
		this.response = _response;
		this.result = _result;
		this.context = _context;
		this.customerTokenDAO = _customerTokenDAO;
		this.tipDAO = _tipDAO;
		this.customerScrapDAO = _customerScrapDAO;
		this.session = _session;
	}
	
}
