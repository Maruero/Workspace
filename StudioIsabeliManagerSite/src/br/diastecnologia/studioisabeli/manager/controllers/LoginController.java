package br.diastecnologia.studioisabeli.manager.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.diastecnologia.studioisabeli.beans.ManagerUser;
import br.diastecnologia.studioisabeli.controllers.Controller;
import br.diastecnologia.studioisabeli.daos.UserDAO;
import br.diastecnologia.studioisabeli.manager.session.ManagerSession;

@Resource
public class LoginController extends Controller{
	
	private UserDAO userDao;
	private ManagerSession session;
	
	public LoginController(HttpServletResponse _response, Result _result, ServletContext _context, ManagerSession _session, UserDAO _userDao ) {
		super(_response, _result, _context);
		this.session = _session;
		this.userDao = _userDao;
	}
	
	@Path("/login")
	public void login(){
		
	}
	
	@Post
	@Path("/logon")
	public void logon( String username, String password ){
		ManagerUser user = userDao.getUser(username, password);
		if( user != null ){
			session.setUser(user);
			result.redirectTo( MenuController.class ).home();
		}else{
			result.include("message", "Usuário e/ou senha inválidos.");
			result.redirectTo( LoginController.class ).login();
		}
	}
	
	
}
