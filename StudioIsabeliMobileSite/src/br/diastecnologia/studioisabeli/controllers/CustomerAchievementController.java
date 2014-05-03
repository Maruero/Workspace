package br.diastecnologia.studioisabeli.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.diastecnologia.studioisabeli.daos.AchievementDAO;
import br.diastecnologia.studioisabeli.daos.CustomerTokenDAO;
import br.diastecnologia.studioisabeli.session.StudioSession;

@Resource
public class CustomerAchievementController extends Controller{

	private AchievementDAO achievementDao;
	private CustomerTokenDAO customerTokenDao;
	private StudioSession session;
	
	public CustomerAchievementController(StudioSession _session, HttpServletResponse _response, Result _result, ServletContext _context, AchievementDAO _achievementDao, CustomerTokenDAO _customerTokenDao) {
		super(_response, _result, _context);
		this.session = _session;
		this.achievementDao = _achievementDao;
		this.customerTokenDao = _customerTokenDao;
	}
	
	@Path("/marcas-do-aluno")
	public void customerAchievements(  ){
		Integer customerID = session.getCustomer().getCustomerID();
		result.include("customer", customerTokenDao.getCustomer(customerID));
		result.include("types", achievementDao.getCustomerAchievementTypes(customerID));
		result.include("achievements" , achievementDao.getCustomerAchievements(customerID, true));
	}
	
}
