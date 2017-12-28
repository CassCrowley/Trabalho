package Controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;

import DAO.Database;
import DAO.GenericDAOImpl;
import DAO.IGenericDAO;
import DAO.Atualizar_DAO;
import Entidades.Login;
import Entidades.ObjetoPersistivel;
import Processadores.Processador_Cadastro;

@ManagedBean
public class AtualizacaoMBean {

	EntityManager em = null;
	
	Login log = new Login();
	
	private String nome;
	private String sobrenome;
	private String senha;
	private String nome2;
	private String sobrenome2;
	private String senha2;
	protected ObjetoPersistivel obj;
	private Login login;
	private IGenericDAO dao2;
	
	/** Armazena os dados informados na tela de login. */
	private Login L;
	
	/** Armazena os dados iniciais de cadastro do usuário. */
	
	@PostConstruct
	private void init(){
		L = new Login();
		dao2 = new Atualizar_DAO();
	}
	
	
	public void atualizar_nome(){
		
		try {
			
			em = Database.getInstance().getEntityManager();
			
			em.getTransaction().begin();
			
			Atualizar_DAO dao = new Atualizar_DAO();
			dao.findUsuarioByLoginSenha(nome, sobrenome , senha);
			
			L.setNome(nome2);
			L.setSobrenome(sobrenome2);
			L.setSenha(senha2);
			
			em.merge(login);
			
			em.getTransaction().commit();
			
			
			
		} catch (Exception e){
			e.printStackTrace();
			
			em = Database.getInstance().getEntityManager();
			
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			
			
		} finally {
			//Limpando caches
//			if (em != null)
//				em.clear();
		}
		
	}

	
	
	public Login getLogin() {
		return login;
	}


	public void setLogin(Login login) {
		this.login = login;
	}


	public Login getL() {
		return L;
	}


	public void setL(Login l) {
		L = l;
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
