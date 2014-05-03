package br.diastecnologia.studioisabeli.manager.controllers;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.diastecnologia.studioisabeli.beans.Customer;
import br.diastecnologia.studioisabeli.beans.CustomerScrap;
import br.diastecnologia.studioisabeli.business.CustomerScrapBusiness;
import br.diastecnologia.studioisabeli.controllers.Controller;
import br.diastecnologia.studioisabeli.daos.CustomerScrapDAO;
import br.diastecnologia.studioisabeli.daos.CustomerTokenDAO;
import br.diastecnologia.studioisabeli.manager.session.ManagerSession;

@Resource
public class CustomerScrapController extends Controller{

	private ManagerSession session;
	private CustomerScrapDAO customerScrapDao;
	private CustomerTokenDAO customerTokenDao;
	private CustomerScrapBusiness customerScrapBusiness;
	
	public CustomerScrapController(CustomerScrapBusiness _customerScrapBusiness, ManagerSession _session, HttpServletResponse _response, Result _result, ServletContext _context, CustomerScrapDAO _customerScrapDao, CustomerTokenDAO _customerTokenDao ) {
		super(_response, _result, _context);
		this.session = _session;
		this.customerScrapDao = _customerScrapDao;
		this.customerTokenDao = _customerTokenDao;
		this.customerScrapBusiness = _customerScrapBusiness;
	}
	
	@Path("/recados-do-aluno")
	public void listScraps( Integer customerID ){
		Customer customer = customerTokenDao.getCustomer(customerID);
		List<CustomerScrap> scraps = customerScrapDao.getLastsCustomerScrap(customerID);
		result.include("customer", customer );
		result.include("scraps", scraps );
	}
	
	@Path("/adicionar-recados")
	public void addScraps(){
		List<Customer> customers = customerTokenDao.getCustomers();
		result.include("customers", customers );
	}
	
	@Path("/adicionar-recado")
	public void addScrap( Integer customerID ){
		result.include("customer", customerTokenDao.getCustomer(customerID) );
	}
	
	@Path("/editar-recado")
	public void editScrap( CustomerScrap scrap ){
		result.include("scrap", customerScrapDao.getCustomerScrap(scrap.getCustomerID(), scrap.getScrapNumber() ) );
		result.forwardTo( CustomerScrapController.class ).addScrap( scrap.getCustomerID() );
	}
	
	@Path("/salvar-recado")
	public void saveScrap(CustomerScrap scrap , Integer[] customers){
		if( scrap.getScrapNumber() != null && scrap.getScrapNumber() != 0 ){
			customerScrapDao.updateCustomerScrap(scrap);
			session.setMessage("Recado salvo com sucesso.");
			result.redirectTo( CustomerScrapController.class).listScraps(scrap.getCustomerID());
		}else{
			if( scrap.getCustomerID() == null ){
				if( customers != null ){
					for( Integer customerID : customers ){
						customerScrapBusiness.addCustomerScrap(customerID, scrap.getText());
					}
				}else{
					customerScrapBusiness.addCustomerScrap(null, scrap.getText());
				}
				session.setMessage("Recados salvo com sucesso.");
				result.redirectTo( MenuController.class ).home();
			}else{
				customerScrapBusiness.addCustomerScrap(scrap.getCustomerID(), scrap.getText());
				session.setMessage("Recados salvo com sucesso.");
				result.redirectTo( CustomerScrapController.class).listScraps(scrap.getCustomerID());
			}
		}
		
	}
	
	@Path("/remover-recado")
	public void removeScrap(CustomerScrap scrap){
		customerScrapDao.removeCustomerScrap(scrap);
		session.setMessage("Recado removido com sucesso.");
		result.redirectTo( CustomerScrapController.class).listScraps(scrap.getCustomerID());
	}

	
	
}
