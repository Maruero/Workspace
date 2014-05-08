package br.diastecnologia.studioisabeli.interceptors;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.diastecnologia.studioisabeli.Utils.CookieUtils;
import br.diastecnologia.studioisabeli.controllers.HomeController;

@Intercepts
public class SplashInterceptor implements Interceptor{
	private Result result;
	private HttpServletRequest request;
	
	private Logger logger = Logger.getLogger("Studio");
	
	public SplashInterceptor( HttpServletRequest _request, Result _result ){
		this.request = _request;
		this.result = _result;
	}

	public boolean accepts(ResourceMethod method) {
		
		if( method.getMethod().getName().equals( "splash" ) || method.getMethod().getName().equals( "splashIndeed" ) ){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object params) throws InterceptionException {
		String value = CookieUtils.getCookie( "splash", request.getCookies() );
		
		logger.info("SplashToken: " + value );
		
		if( value == null || value.isEmpty() ){
			result.redirectTo( HomeController.class ).splashIndeed(null);
		}else{
			stack.next(method, params);
		}
		
	}

}