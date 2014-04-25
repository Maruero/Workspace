package br.diastecnologia.studioisabeli.manager.controllers;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.diastecnologia.studioisabeli.beans.Customer;
import br.diastecnologia.studioisabeli.beans.CustomerToken;
import br.diastecnologia.studioisabeli.business.CustomerTokenBusiness;
import br.diastecnologia.studioisabeli.controllers.Controller;
import br.diastecnologia.studioisabeli.daos.CustomerTokenDAO;
import br.diastecnologia.studioisabeli.manager.session.ManagerSession;

@Resource
public class CustomersController  extends Controller{

	private CustomerTokenDAO customerDao;
	private ManagerSession session;
	private CustomerTokenBusiness customerTokenBusiness;
	
	public CustomersController(ManagerSession _session, HttpServletResponse _response, Result _result, ServletContext _context, CustomerTokenBusiness _customerTokenBusiness, CustomerTokenDAO _customerDao ) {
		super(_response, _result, _context);
		this.customerTokenBusiness = _customerTokenBusiness;
		this.customerDao = _customerDao;
		this.session = _session;
	}
	
	
	@Path("/alunos")
	public void customers(){
		List<Customer> customers = customerDao.getCustomers();
		result.include("customers", customers);
	}
	
	@Path("/adicionar-aluno")
	public void addCustomer(){
		
	}
	
	@Path("/gerar-token-de-aluno")
	public void buildToken( Integer customerID ){
		String token = customerTokenBusiness.createCustomerToken(customerID).getToken();
		result.use( Results.json() ).from( token ).serialize();
	}
	
	@Path("/editar-aluno")
	public void editCustomer( Integer customerID ){
		Customer customer = customerDao.getCustomer(customerID);
		CustomerToken token = customerDao.getCustomerToken(customerID);
		result.include("customer", customer);
		if( token != null ){
			result.include("token", token.getToken());
		}
		result.forwardTo(CustomersController.class).addCustomer();
	}
	
	@Path("/salvar-aluno")
	public void saveCustomer( Customer customer ){
		if( customer.getCustomerID() != null && customer.getCustomerID() != 0 ){
			customerDao.updateCustomer(customer);
		}else{
			customerDao.addCustomer(customer.getName());
		}
		session.setMessage( "Aluno salvo com sucesso.");
		result.redirectTo( CustomersController.class).customers();
	}
	
	@Path("/remover-aluno")
	public void removeCustomer( Integer customerID ){
		customerDao.removeCustomer(customerID);
		session.setMessage( "Aluno removido com sucesso.");
		result.redirectTo( CustomersController.class).customers();
	}

}
