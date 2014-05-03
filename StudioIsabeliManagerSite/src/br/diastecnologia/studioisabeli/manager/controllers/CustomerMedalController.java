package br.diastecnologia.studioisabeli.manager.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.diastecnologia.studioisabeli.beans.CustomerMedal;
import br.diastecnologia.studioisabeli.controllers.Controller;
import br.diastecnologia.studioisabeli.daos.CustomerTokenDAO;
import br.diastecnologia.studioisabeli.daos.MedalDAO;

@Resource
public class CustomerMedalController extends Controller{

	private MedalDAO medalDao;
	private CustomerTokenDAO customerTokenDao;
	
	public CustomerMedalController(HttpServletResponse _response, Result _result, ServletContext _context, MedalDAO _medalDao, CustomerTokenDAO _customerTokenDao) {
		super(_response, _result, _context);
		this.medalDao = _medalDao;
		this.customerTokenDao = _customerTokenDao;
	}
	
	@Path("/ranking")
	public void ranking(){
		result.include("ranking", medalDao.getRanking());
	}
	
	@Path("/medalhas-do-aluno")
	public void customerMedals(Integer customerID ){
		result.include( "customer" , customerTokenDao.getCustomer(customerID));
		result.include( "medals" , medalDao.getMedals(customerID) );
	}
	
	@Path("/salvar-medalha-do-aluno")
	public void updateCustomerMedal( CustomerMedal medal ){
		if( medal.getMedalID() == null || medal.getMedalID() == 0){
			medalDao.addMedal(medal.getCustomerID(), medal.getDescription(), medal.getMedal().getType() );
		}else{
			medalDao.updateMedal(medal);
		}
		result.redirectTo( CustomerMedalController.class ).customerMedals( medal.getCustomerID() );
	}
	
	@Path("/adicionar-medalha-do-aluno")
	public void customerMedal( Integer customerID ){
		result.include( "customer" , customerTokenDao.getCustomer(customerID));
	}
	
	@Path("editar-medalha-do-aluno")
	public void editCustomerMedal( CustomerMedal medal){
		result.include( "medal" , medalDao.getMedal(medal.getCustomerID(), medal.getMedalID()) );
		result.forwardTo( CustomerMedalController.class ).customerMedal( medal.getCustomerID() );
	}
	
	
	
	

}
