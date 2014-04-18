package br.diastecnologia.studioisabeli.interceptors;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.diastecnologia.studioisabeli.Utils.CookieUtils;
import br.diastecnologia.studioisabeli.beans.Customer;
import br.diastecnologia.studioisabeli.controllers.HomeController;
import br.diastecnologia.studioisabeli.daos.CustomerTokenDAO;
import br.diastecnologia.studioisabeli.session.StudioSession;

@Intercepts
public class TokenInterceptor implements Interceptor{

	private StudioSession session;
	private CustomerTokenDAO customerTokenDAO;
	private Result result;
	private HttpServletRequest request;
	
	public TokenInterceptor( HttpServletRequest _request, Result _result, StudioSession _session , CustomerTokenDAO _customerTokenDAO){
		this.request = _request;
		this.session = _session;
		this.customerTokenDAO = _customerTokenDAO;
		this.result = _result;
		
	}

	public boolean accepts(ResourceMethod method) {
		
		if( method.getMethod().getName().equals("token")){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object params) throws InterceptionException {
		if( session.getCustomer() == null ){
			String token = CookieUtils.getCookie( request.getCookies() );
			if( method.getMethod().getName().equals( "splash" )){
				
				Customer customer = customerTokenDAO.getCustomer(token);
				session.setCustomer(customer);
				stack.next(method, params);
				
			}else{
				Customer customer = customerTokenDAO.getCustomer(token);
				session.setCustomer(customer);
				if( session.getCustomer() == null ){
					result.redirectTo( HomeController.class ).token();
				}
			}
		}else{
			stack.next(method, params);
		}
		
		
	}

}
