package br.diastecnologia.studioisabeli.Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	public static String getCookie( Cookie[] cookies ){
		
		if( cookies != null ){
			for( Cookie cookie : cookies ){
				if( cookie.getName().equals( "token" )){
					return cookie.getValue();
				}
			}
		}
		
		return null;
	}
	
	public static Cookie createCookie( String token ){
		Cookie cookie = new Cookie("token", token);
		cookie.setMaxAge( 60 * 60 * 60);
		return cookie;
	}
	
}
