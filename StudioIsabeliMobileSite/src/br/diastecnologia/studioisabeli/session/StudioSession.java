package br.diastecnologia.studioisabeli.session;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.diastecnologia.studioisabeli.beans.Customer;

@Component
@SessionScoped
public class StudioSession implements Serializable{

	private static final long serialVersionUID = 6359440277985845542L;
	
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
