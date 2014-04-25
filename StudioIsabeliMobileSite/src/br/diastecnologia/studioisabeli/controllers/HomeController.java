package br.diastecnologia.studioisabeli.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.diastecnologia.studioisabeli.Utils.CookieUtils;
import br.diastecnologia.studioisabeli.beans.Customer;
import br.diastecnologia.studioisabeli.beans.CustomerScrap;
import br.diastecnologia.studioisabeli.beans.Tip;
import br.diastecnologia.studioisabeli.daos.CustomerScrapDAO;
import br.diastecnologia.studioisabeli.daos.CustomerTokenDAO;
import br.diastecnologia.studioisabeli.daos.TipDAO;
import br.diastecnologia.studioisabeli.session.StudioSession;

@Resource
public class HomeController extends Controller{
	
	protected CustomerTokenDAO customerTokenDAO;
	protected TipDAO tipDAO;
	protected CustomerScrapDAO customerScrapDAO;
	private StudioSession session;
	
	public HomeController(HttpServletResponse _response, Result _result, StudioSession _session, ServletContext _context, CustomerTokenDAO _customerTokenDAO, TipDAO _tipDAO, CustomerScrapDAO _customerScrapDAO){
		super(_response, _result, _context);
		this.customerTokenDAO = _customerTokenDAO;
		this.tipDAO = _tipDAO;
		this.customerScrapDAO = _customerScrapDAO;
		this.session = _session;
	}
	
	@Path("/")
	public void splash( String token ){
		if( token != null ){
			Customer customer = customerTokenDAO.getCustomer(token);
			session.setCustomer(customer);
		}
	}
	
	@Path("/token")
	@Get
	public void token(){
		
	}
	
	@Path("/token")
	@Post
	public void token( String token ){
		
		if( token != null ){
			token = token.toUpperCase();
		}
		
		Customer customer = customerTokenDAO.getCustomer(token);
		session.setCustomer(customer);
		if( session.getCustomer() != null ){
			response.addCookie( CookieUtils.createCookie(token));
			result.redirectTo( HomeController.class ).tip( null );
		}else{
			result.include( "message" , "Token inválido." );
		}
	}
	
	@Path("/tip")
	public void tip( Integer tipID ){
		Tip tip = null;
		if( tipID == null ){
			tip = tipDAO.getLastTip();
		}else{
			tip = tipDAO.getTip(tipID);
		}
		
		if( tip == null ){
			result.redirectTo( HomeController.class ).scrap(null);
		}else{
			result.include( "tip" , tip );
		}
	}
	
	@Path("/scrap")
	public void scrap( Integer scrapNumber ){
		CustomerScrap scrap = null;
		if( scrapNumber == null ){
			scrap = customerScrapDAO.getLastCustomerScrap( session.getCustomer().getCustomerID() );
		}else{
			scrap = customerScrapDAO.getCustomerScrap(session.getCustomer().getCustomerID(), scrapNumber);
		}
		
		if( scrap == null ){
			result.redirectTo( HomeController.class ).scrap(null);
		}else{
			result.include( "scrap", scrap );
		}
	}
	
	
}

