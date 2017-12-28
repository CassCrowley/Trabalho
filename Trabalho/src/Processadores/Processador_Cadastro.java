package Processadores;

import DAO.GenericDAOImpl;
import DAO.IGenericDAO;
import Entidades.ObjetoPersistivel;

public class Processador_Cadastro extends Processador_Comando {

protected ObjetoPersistivel obj;
private boolean Atualizacao = false;	
	@Override
	protected void iniciarExecucao() {
		IGenericDAO dao = new GenericDAOImpl();
		
		
			dao.create(obj);
	}

	@Override
	protected Object getResultado() {
		return obj;
	}

	public void setObj(ObjetoPersistivel obj) {
		this.obj = obj;
	}

	public boolean isAtualizacao() {
		return Atualizacao;
	}

	public void setAtualizacao(boolean atualizacao) {
		Atualizacao = atualizacao;
	}
	
	
	
}
