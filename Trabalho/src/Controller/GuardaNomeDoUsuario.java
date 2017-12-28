package Controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Entidades.Login;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class GuardaNomeDoUsuario {

private Login L;
	
	/** Armazena os dados iniciais de cadastro do usuário. */
	private Login L2;
	
	private String nome_do_Usuario;

	private String sobrenome_do_Usuario;
	
	@PostConstruct
	private void init(){
		L = new Login();
		L2 = new Login();
		
		nome_do_Usuario = L.getNome();
		nome_do_Usuario = L.getSobrenome();
	}

	public Login getL() {
		return L;
	}

	public void setL(Login l) {
		L = l;
	}

	public Login getL2() {
		return L2;
	}

	public void setL2(Login l2) {
		L2 = l2;
	}

	public String getNome_do_Usuario() {
		return nome_do_Usuario;
	}

	public void setNome_do_Usuario(String nome_do_Usuario) {
		this.nome_do_Usuario = nome_do_Usuario;
	}

	public String getSobrenome_do_Usuario() {
		return sobrenome_do_Usuario;
	}

	public void setSobrenome_do_Usuario(String sobrenome_do_Usuario) {
		this.sobrenome_do_Usuario = sobrenome_do_Usuario;
	}
	
	
	
}
