package br.diastecnologia.studioisabeli.business;

import java.util.Date;
import java.util.Random;

import br.com.caelum.vraptor.ioc.Component;
import br.diastecnologia.studioisabeli.beans.CustomerToken;
import br.diastecnologia.studioisabeli.daos.CustomerTokenDAO;

@Component
public class CustomerTokenBusiness {

	private CustomerTokenDAO customerTokenDAO;
	
	public CustomerTokenBusiness(CustomerTokenDAO _customerTokenDAO){
		this.customerTokenDAO = _customerTokenDAO;
	}
	
	public  CustomerToken createCustomerToken( Integer customerID ){
		String token = getToken(customerID);
		return customerTokenDAO.addCustomerToken(customerID, token);
	}
	
	private String getToken( Integer customerID ){
		
		Random random = new Random( new Date().getTime() );
		while(true){
			String token = "";
			
			int i = Math.abs(random.nextInt()) % 25;
			i += 65;
			token += (char)i;
			
			token += customerID;
			
			i = Math.abs(random.nextInt()) % 25;
			i += 65;
			token += (char)i;
			
			CustomerToken customerToken = customerTokenDAO.getCustomerToken(token);
			if( customerToken == null ){
				return token;
			}
		}
	}
	
}
