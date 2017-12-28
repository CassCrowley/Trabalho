package DAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import Entidades.Login;

/**
 * 
 *
 * @author Pablo Durkheim
 *
 */

public class Atualizar_DAO extends GenericDAOImpl {

	/** Encontra um usuário a partir de seu nome, senha e sobrenome. */
	public Login findUsuarioByLoginSenha(String nome, String sobrenome, String senha){
		EntityManager em = getEm();
		
		String hql = "SELECT log ";
		hql += " FROM Login log WHERE log.Nome = :nome and log.Senha = :senha and log.Sobrenome = :sobrenome ";
		
		Query q = em.createQuery(hql);
		q.setParameter("nome", nome);
		q.setParameter("senha", senha);
		q.setParameter("sobrenome", sobrenome);
		
		try {
			Login L = (Login) q.getSingleResult();
			return L;
		} catch (NoResultException e){
			return null;
		}
	
}
	
}
