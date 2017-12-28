package Controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import DAO.Usuario_DAO;
import Entidades.Login;
import Extra.Conexao;
import Processadores.Processador_Cadastro;
import Util.ValidatorUtil;

/**
 * MBean que controla o login no sistema. 
 * @author Renan
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped


public class LoginMBean {

	/** Armazena os dados informados na tela de login. */
	private Login L;
	
	/** Armazena os dados iniciais de cadastro do usuário. */
	private Login L2;
	
	@PostConstruct
	private void init(){
		L = new Login();
		L2 = new Login();
	}
	
	
    Processador_Cadastro Cad; 
	
	public String logar(){
		
		Usuario_DAO dao = new Usuario_DAO();
		dao.findUsuarioByLoginSenha(L.getNome(), L.getSenha());
		
		
		try {
			
			
			
			System.out.println("Logado");
			
			//Salva o usuário permanentemente na memória do sistema 
			return "pagina";
			
		} catch (Exception e) {
			System.out.println("erro");
			System.out.println(L.getNome() +" "+ L.getSenha());
			return null;
		}
	
}
	
	public String atualizacao(){
		
		
		
		return "pagina";
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
	
	
	
	}
