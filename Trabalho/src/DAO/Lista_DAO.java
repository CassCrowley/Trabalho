package DAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import Entidades.Login;
import Entidades.Respostas;

/** 
 * @author Pablo Durkheim 
 */

public class Lista_DAO extends GenericDAOImpl {

	/** Encontra um usuário a partir de seu nome e sobrenome. */
	public Respostas findUsuarioByLoginSenha(String nome, String sobrenome){
		EntityManager em = getEm();
		
		String hql = "SELECT res ";
		hql += " FROM Respostas res WHERE res.nome = :nome and  res.sobrenome = :sobrenome ";
		
		Query q = em.createQuery(hql);
		q.setParameter("nome", nome);
		q.setParameter("sobrenome", sobrenome);
		
		try {
			Respostas L = (Respostas) q.getSingleResult();
			return L;
		} catch (NoResultException e){
			return null;
		}
	
}
	
}
