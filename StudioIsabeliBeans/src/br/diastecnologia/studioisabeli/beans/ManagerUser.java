package br.diastecnologia.studioisabeli.beans;

import java.io.Serializable;

import br.diastecnologia.studioisabeli.enums.UserPerfil;

public class ManagerUser implements Serializable{

	private static final long serialVersionUID = -3152475547946232897L;
	
	private String username;
	private String password;
	private UserPerfil perfil;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserPerfil getPerfil() {
		return perfil;
	}
	public void setPerfil(UserPerfil perfil) {
		this.perfil = perfil;
	}
	
}
