package br.diastecnologia.studioisabeli.manager.session;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.diastecnologia.studioisabeli.beans.ManagerUser;

@Component
@SessionScoped
public class ManagerSession implements Serializable{
	
	private static final long serialVersionUID = -989174815003762395L;
	private ManagerUser user;
	private String message;
	
	public boolean isHasMessage() {
		return message != null;
	}

	public void setHasMessage(boolean hasMessage) {
	}

	public String getMessage() {
		String m = message;
		message = null;
		return m;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ManagerUser getUser() {
		return user;
	}

	public void setUser(ManagerUser user) {
		this.user = user;
	}
	
}
