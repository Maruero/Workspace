package br.diastecnologia.studioisabeli.Utils;

import javax.servlet.http.Cookie;

public class CookieUtils {

	public static String getCookie( String name, Cookie[] cookies ){
		
		if( cookies != null ){
			for( Cookie cookie : cookies ){
				if( cookie.getName().equals( name )){
					return cookie.getValue();
				}
			}
		}
		
		return null;
	}
	
	public static Cookie createCookie( String name, String token ){
		Cookie cookie = new Cookie(name, token);
		cookie.setMaxAge( 60 * 60 * 60);
		return cookie;
	}
	
	public static Cookie toDeleteCookie(  ){
		Cookie cookie = new Cookie("token", "");
		cookie.setMaxAge( 0 );
		return cookie;
	}
	
}
