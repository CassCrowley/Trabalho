package Controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import DAO.Atualizar_DAO;
import DAO.Lista_DAO;
import DAO.Usuario_DAO;
import Entidades.Login;
import Entidades.Respostas;


@ManagedBean
@SessionScoped
public class Crud_ControleGeral {
	
	EntityManagerFactory emf;
	EntityManager em;
	
	public Crud_ControleGeral(){
		
		emf = Persistence.createEntityManagerFactory("Hibernate-Site");
		em = emf.createEntityManager();
		
	}
	
	public Login Buscar(String nome, String sobrenome, String senha){
		Atualizar_DAO dao = new Atualizar_DAO();
		iniciarConexao();
		Login login = dao.findUsuarioByLoginSenha(nome, sobrenome, senha);
		
		return login;
		
	}
	
	public Respostas BuscarResposta(String nome, String sobrenome){
		Lista_DAO dao = new Lista_DAO();
		iniciarConexao();
		Respostas respostas = dao.findUsuarioByLoginSenha(nome, sobrenome);
		
		return respostas;
		
	}
	
	public void iniciarConexao(){
		
		em.getTransaction().begin();
		
	}
	
	public void encerrarConexao(){
		
		em.getTransaction().commit();
		emf.close();
	}
	
	public void inserirDados(Login login){
	
		em.getTransaction().begin();
		
		em.persist(login);
		
		encerrarConexao();
		
	}
	
	public void inserirRespostas(Respostas respostas){
		
		
		
		em.persist(respostas);
		
		encerrarConexao();
		
	}
	
	public void atualizar(Login login){
		
		em.merge(login);
	
		encerrarConexao();
		
	}
	
	public void atualizarRespostas(Respostas respostas){
		
		
		
		em.merge(respostas);
	
		encerrarConexao();
		
	}
	
	public void remove(Login login){
		
		login = em.merge(login);
		em.remove(login);
		
		encerrarConexao();
		
	}
	
	public void removeRespostas(Respostas respostas){
		
		Object c = em.merge(respostas);
		
		em.remove(c);
		
		encerrarConexao();
		
	}
	
	public  List<Login> listar(){
		return null;
		
	}

	public List<Respostas> listar2(){
		iniciarConexao();
		Query consulta = em.createQuery("select resposta from Respostas resposta");
		List<Respostas> resposta = consulta.getResultList();
		encerrarConexao();
		return resposta;
	}
	
	
	
}
