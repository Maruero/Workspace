package br.diastecnologia.studioisabeli.manager.interceptors;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.diastecnologia.studioisabeli.manager.controllers.LoginController;
import br.diastecnologia.studioisabeli.manager.session.ManagerSession;

@Intercepts
public class SessionInterceptor implements Interceptor{

	private ManagerSession session;
	private Result result;
	
	public SessionInterceptor( Result _result , ManagerSession _session ){
		this.result = _result;
		this.session = _session;
	}
	
	
	@Override
	public boolean accepts(ResourceMethod method) {
		
		if( 
				method.getMethod().getName().equals( "login" )
				|| method.getMethod().getName().equals( "logon" )
		){
			return false;
		}
		
		return true;
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object params) throws InterceptionException {
		if( session == null || session.getUser() == null ){
			result.redirectTo( LoginController.class ).login();
		}else{
			stack.next(method, params);
		}
	}

}
