package Controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.IGenericDAO;
import Entidades.Login;
import Processadores.Processador_Cadastro;
import DAO.Usuario_DAO;


@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class Cadastro_UsuarioMBean {
	
	private Login login;
	private IGenericDAO dao;
	private String nome;
	private String nome2;
	private String sobrenome;
	private String sobrenome2;
	private String senha;
	private String senha2;
	
	
	@PostConstruct //inicia o método do MBean
	private void init() {
		login = new Login();
		
		
		dao = new Usuario_DAO();

	}
	
	public void cadastrar(){
		
		login.setNome(nome);
		login.setSobrenome(sobrenome);
		login.setSenha(senha);
		
		Processador_Cadastro p = new Processador_Cadastro();
		p.setObj(login);
		
		try {
			p.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("E ta ficando complicado demais [...]");
			e.printStackTrace();
		}
		
	}

	

	public String getNome2() {
		return nome2;
	}

	public void setNome2(String nome2) {
		this.nome2 = nome2;
	}

	public String getSobrenome2() {
		return sobrenome2;
	}

	public void setSobrenome2(String sobrenome2) {
		this.sobrenome2 = sobrenome2;
	}

	public String getSenha2() {
		return senha2;
	}

	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}

