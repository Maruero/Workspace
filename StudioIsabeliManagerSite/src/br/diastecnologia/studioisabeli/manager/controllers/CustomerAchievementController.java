package br.diastecnologia.studioisabeli.manager.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.diastecnologia.studioisabeli.beans.CustomerAchievement;
import br.diastecnologia.studioisabeli.controllers.Controller;
import br.diastecnologia.studioisabeli.daos.AchievementDAO;
import br.diastecnologia.studioisabeli.daos.CustomerTokenDAO;

@Resource
public class CustomerAchievementController extends Controller{

	private AchievementDAO achievementDao;
	private CustomerTokenDAO customerTokenDao;
	
	public CustomerAchievementController(HttpServletResponse _response, Result _result, ServletContext _context, AchievementDAO _achievementDao, CustomerTokenDAO _customerTokenDao) {
		super(_response, _result, _context);
		this.achievementDao = _achievementDao;
		this.customerTokenDao = _customerTokenDao;
	}
	
	@Path("/marcas-do-aluno")
	public void customerAchievements( Integer customerID ){
		result.include("customer", customerTokenDao.getCustomer(customerID));
		result.include("types", achievementDao.getCustomerAchievementTypes(customerID));
		result.include("achievements" , achievementDao.getCustomerAchievements(customerID, true));
	}
	
	@Path("/adicionar-marca-do-aluno")
	public void addCustomerAchievement( Integer customerID ){
		result.include( "customer", customerTokenDao.getCustomer(customerID));
		result.include( "types", achievementDao.getAchievementTypes() );
	}

	@Path("/salvar-marca-do-aluno")
	public void saveCustomerAchievement( CustomerAchievement customerAchievement ){
		achievementDao.addCustomerAchievement(customerAchievement, true);
		result.redirectTo( CustomerAchievementController.class).customerAchievements( customerAchievement.getCustomer().getCustomerID() );
	}
	
	
}
