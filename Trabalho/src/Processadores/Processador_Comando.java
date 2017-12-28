package Processadores;

import javax.persistence.EntityManager;
import DAO.Database;

public abstract class Processador_Comando {

	public final Object execute() throws Exception {
		EntityManager em = null;
		
		try {
			em = Database.getInstance().getEntityManager();
			
			em.getTransaction().begin();
			
			iniciarExecucao();
			
			em.getTransaction().commit();
			
			return getResultado();
			
		} catch (Exception e){
			e.printStackTrace();
			
			em = Database.getInstance().getEntityManager();
			
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			
			throw new Exception(e);
		} finally {
			//Limpando caches
//			if (em != null)
//				em.clear();
		}
	}
	
	/** Método que os processadores filhos devem implementar para realizar as operações necessárias. */
	protected abstract void iniciarExecucao() throws Exception;
	
	/** 
	 * Método que deve ser implementado pelos processadores filhos para retornar algum dado
	 * para quem chamou o processador. 
	 */
	protected abstract Object getResultado();
	
}

	

