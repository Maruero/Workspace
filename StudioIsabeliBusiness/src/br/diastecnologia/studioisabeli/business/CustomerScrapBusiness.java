package br.diastecnologia.studioisabeli.business;

import java.util.List;

import br.com.caelum.vraptor.Resource;
import br.diastecnologia.studioisabeli.beans.Customer;
import br.diastecnologia.studioisabeli.daos.CustomerScrapDAO;
import br.diastecnologia.studioisabeli.daos.CustomerTokenDAO;

@Resource
public class CustomerScrapBusiness {

	private CustomerScrapDAO customerScrapDao;
	private CustomerTokenDAO customerDao;
	
	public CustomerScrapBusiness(CustomerScrapDAO _customerScrapDao, CustomerTokenDAO _customerDao){
		this.customerScrapDao = _customerScrapDao;
		this.customerDao = _customerDao;
	}
	
	public void addCustomerScrap( Integer customerID, String text ){
		if( customerID == null || customerID == 0 ){
			List<Customer> customers = customerDao.getCustomers();
			for( Customer customer : customers ){
				customerScrapDao.addCustomerScrap( customer.getCustomerID(), text);
			}
		}else{
			customerScrapDao.addCustomerScrap( customerID, text);
		}
	}
	
}
