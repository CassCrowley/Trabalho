package DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.Session;


import Util.ValidatorUtil;
import Entidades.Login;

/**
 * DAO (Data Access Object - Objeto de Acesso a Dados) com 
 * métodos relativos à entidade {@link Usuario}.
 * @author Pablo Durkheim
 */


public class Usuario_DAO extends GenericDAOImpl{

	/** Encontra um usuário a partir de seu login e senha. */
	public Login findUsuarioByLoginSenha(String nome, String senha){
		EntityManager em = getEm();
		
		String hql = "SELECT log ";
		hql += " FROM Login log WHERE log.Nome = :nome and log.Senha = :senha ";
		
		Query q = em.createQuery(hql);
		q.setParameter("nome", nome);
		q.setParameter("senha", senha);
		
		try {
			Login L = (Login) q.getSingleResult();
			return L;
		} catch (NoResultException e){
			return null;
		}
	
}
		
}