package Controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import DAO.Lista_DAO;
import DAO.Usuario_DAO;
import Entidades.Login;
import Entidades.Respostas;
import Extra.Conexao;

/** Classe usada para armazenar os métodos que serão usados para o CRUD.
 * 
 * @author Pablo Durkheim
 *
 */

@ManagedBean
@SessionScoped
public class Usar_Crud {

	@ManagedProperty("#{crud_ControleGeral}")
    private Crud_ControleGeral CCG = new Crud_ControleGeral();
	private Login login;
	private Respostas respostas;
	
	void init(){
		CCG = new Crud_ControleGeral();
		Login login = new Login();
	}
	
	/** Atributos necessários */
	
	private String nomeAtual;
	private String sobrenomeAtual;
	private String senhaAtual;
	private String novoNome;
	private String novoSobrenome;
	private String novaSenha;
	private String id;
	
	private String Respostas1;
	private String Respostas2;
	private String Respostas3;
	
	List<String> resposta1 = new ArrayList<>();
	List<String> resposta2 = new ArrayList<>();
	List<String> resposta3 = new ArrayList<>();
	
	/** Método usado para inserir/cadastrar dados no banco, verificando algum possível erro.  */
	public void inserirDados(){
	
		if(nomeAtual.length()!= 0 && !nomeAtual.contains("0") && !nomeAtual.contains("1") && !nomeAtual.contains("2") &&
				!nomeAtual.contains("3") && !nomeAtual.contains("4") && !nomeAtual.contains("5") && !nomeAtual.contains("6") &&
				!nomeAtual.contains("7") && !nomeAtual.contains("8") && !nomeAtual.contains("9")){
		
			if(sobrenomeAtual.length()!= 0 && !sobrenomeAtual.contains("0") && !sobrenomeAtual.contains("1") && !sobrenomeAtual.contains("2") &&
					!sobrenomeAtual.contains("3") && !sobrenomeAtual.contains("4") && !sobrenomeAtual.contains("5") && !sobrenomeAtual.contains("6") &&
					!sobrenomeAtual.contains("7") && !sobrenomeAtual.contains("8") && !sobrenomeAtual.contains("9")){
			
			if(senhaAtual.length()!=0){
				
			CCG = new Crud_ControleGeral();
			Login login = new Login();
				
			String senhaCrip = "";
			
			for(int i = 0; i<senhaAtual.length(); i++){
	            int temp = senhaAtual.charAt(i);
	            temp += 100;
	            senhaCrip += (char) temp;
	        }
			
			login.setNome(nomeAtual);
			login.setSobrenome(sobrenomeAtual);
			login.setSenha(senhaCrip);
			
			CCG.inserirDados(login);
				
			System.out.println("blz, inseriu os dados");
				
			FacesContext context = FacesContext.getCurrentInstance();
	        
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Parabéns!", "Dados inseridos com sucesso.") );
			
				
			}else{
				
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite sua senha!", "Sua senha não pode ser nula.");
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				
			}
				
		
			}else{
				
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite apenas o seu o sobrenome!", "Sem números ou caracteres especiais");
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				
			}
			
			
		
		}else{
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite apenas o seu o nome!", "Sem números ou caracteres especiais");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
		}
	
	}
	
	
	/** Método usado para inserir as respostas no banco, verificando algum possível erro */ 
	public void inserirRespostas(){
		
		CCG = new Crud_ControleGeral();
		CCG.iniciarConexao();
		Respostas respostas = new Respostas();
		Login login = new Login();
		Usuario_DAO dao = new Usuario_DAO();
	
		login = dao.findUsuarioByLoginSenha(nomeAtual, senhaAtual);
		
		if(Respostas1.length()!=0 && !Respostas1.contains("0") && !Respostas1.contains("1") && !Respostas1.contains("2") &&
				!Respostas1.contains("3") && !Respostas1.contains("4") && !Respostas1.contains("5") && !Respostas1.contains("6") &&
				!Respostas1.contains("7") && !Respostas1.contains("8") && !Respostas1.contains("9")){
		
			if(Respostas2.length()!=0 && !Respostas2.contains("0") && !Respostas2.contains("1") && !Respostas2.contains("2") &&
					!Respostas2.contains("3") && !Respostas2.contains("4") && !Respostas2.contains("5") && !Respostas2.contains("6") &&
					!Respostas2.contains("7") && !Respostas2.contains("8") && !Respostas2.contains("9")){
				
				if(Respostas3.length()!=0 && !Respostas3.contains("0") && !Respostas3.contains("1") && !Respostas3.contains("2") &&
						!Respostas3.contains("3") && !Respostas3.contains("4") && !Respostas3.contains("5") && !Respostas3.contains("6") &&
						!Respostas3.contains("7") && !Respostas3.contains("8") && !Respostas3.contains("9")){
					
					respostas.setNome(nomeAtual);
					respostas.setSobrenome(sobrenomeAtual);
					respostas.setResposta1(Respostas1);
					respostas.setResposta2(Respostas2);
					respostas.setResposta3(Respostas3);
					
					CCG.inserirRespostas(respostas);
					
					System.out.println("Respostas inseridas com sucesso!");
					
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Obrigado por responder!", "Parabéns, você respondeu ao nosso questionário.");
					FacesContext.getCurrentInstance().addMessage(null, mensagem);
					
				}else{
					
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Você deve preencher a 3ª pergunta!", "Sem números ou caracteres especiais");
					FacesContext.getCurrentInstance().addMessage(null, mensagem);
					
				}
				
			}else{
				
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Você deve preencher a 2ª pergunta!", "Sem números ou caracteres especiais");
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				
			}
			
		}else{
			
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Você deve preencher a 1ª pergunta!", "Sem números ou caracteres especiais");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			
		}
		
	}
	
	/** Método usado para fazer o login */
	public String logar(){
		
		Crud_ControleGeral CCG = new Crud_ControleGeral();
		Login login = new Login();
		Usuario_DAO dao = new Usuario_DAO();
			
		String senhaCrip = "";
		
		for(int i = 0; i<senhaAtual.length(); i++){
            int temp = senhaAtual.charAt(i);
            temp += 100;
            senhaCrip += (char) temp;
        }
		
			login = dao.findUsuarioByLoginSenha(nomeAtual, senhaCrip);
			
			if(login!=null){
			
				System.out.println(login.getSobrenome()+" está logado");
				System.out.println("ok");
				nomeAtual = login.getNome();
				sobrenomeAtual = login.getSobrenome();
				id = login.getId();
				
				return "pagina";
				
			}else{
			
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preencha os campos corretamente!", "Tenha paciência");
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				System.out.println("Error");
				
				return "inicio";
				
			}
		
	}
	
		/** atualiza a 1ª resposta */
		public void atualizarRespostas1(){
		
		CCG = new Crud_ControleGeral();
		Respostas respostas = new Respostas();
		Lista_DAO dao = new Lista_DAO();
	
		respostas = CCG.BuscarResposta(nomeAtual, sobrenomeAtual);
		
		if(Respostas1.length()!=0 && !Respostas1.contains("0") && !Respostas1.contains("1") && !Respostas1.contains("2") &&
				!Respostas1.contains("3") && !Respostas1.contains("4") && !Respostas1.contains("5") && !Respostas1.contains("6") &&
				!Respostas1.contains("7") && !Respostas1.contains("8") && !Respostas1.contains("9")){
			
			//respostas.setNome(nomeAtual);
			//respostas.setSobrenome(sobrenomeAtual);
			respostas.setResposta1(Respostas1);
			//respostas.setResposta2(Respostas2);
			//respostas.setResposta3(Respostas3);
			
			CCG.atualizarRespostas(respostas);
			
			System.out.println("Respostas atualizadas com sucesso!");
		
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resposta atualizada com sucesso!", null);
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			
		}else{
		
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preencha o campo da 1ª pergunta corretamente!", null);
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			System.out.println("Error");
			
		}
			
	}
		/** atualiza a 2ª resposta */
		public void atualizarRespostas2(){
			
			CCG = new Crud_ControleGeral();
			Respostas respostas = new Respostas();
			Lista_DAO dao = new Lista_DAO();
		
			respostas = CCG.BuscarResposta(nomeAtual, sobrenomeAtual);
			
			if(Respostas2.length()!=0 && !Respostas2.contains("0") && !Respostas2.contains("1") && !Respostas2.contains("2") &&
					!Respostas2.contains("3") && !Respostas2.contains("4") && !Respostas2.contains("5") && !Respostas2.contains("6") &&
					!Respostas2.contains("7") && !Respostas2.contains("8") && !Respostas2.contains("9")){
				
				//respostas.setNome(nomeAtual);
				//respostas.setSobrenome(sobrenomeAtual);
				respostas.setResposta1(Respostas2);
				//respostas.setResposta2(Respostas2);
				//respostas.setResposta3(Respostas3);
				
				CCG.atualizarRespostas(respostas);
				
				System.out.println("Respostas atualizadas com sucesso!");
			
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resposta atualizada com sucesso!", null);
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				
			}else{
			
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preencha o campo da 2ª pergunta corretamente!", null);
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				System.out.println("Error");
				
			}
		}
			
		/** atualiza a 3ª resposta */
		public void atualizarRespostas3(){
			
			CCG = new Crud_ControleGeral();
			Respostas respostas = new Respostas();
			Lista_DAO dao = new Lista_DAO();
		
			respostas = CCG.BuscarResposta(nomeAtual, sobrenomeAtual);
			
			if(Respostas3.length()!=0 && !Respostas3.contains("0") && !Respostas3.contains("1") && !Respostas3.contains("2") &&
					!Respostas3.contains("3") && !Respostas3.contains("4") && !Respostas3.contains("5") && !Respostas3.contains("6") &&
					!Respostas3.contains("7") && !Respostas3.contains("8") && !Respostas3.contains("9")){
				
				//respostas.setNome(nomeAtual);
				//respostas.setSobrenome(sobrenomeAtual);
				respostas.setResposta1(Respostas3);
				//respostas.setResposta2(Respostas2);
				//respostas.setResposta3(Respostas3);
				
				CCG.atualizarRespostas(respostas);
				
				System.out.println("Respostas atualizadas com sucesso!");
			
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resposta atualizada com sucesso!", null);
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				
			}else{
			
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preencha o campo da 1ª pergunta corretamente!", null);
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				System.out.println("Error");
				
			}
		}
	
		/** exclui respostas */
		public void excluirRespostas(){
			
			CCG = new Crud_ControleGeral();
			Respostas respostas = new Respostas();
		
			respostas = CCG.BuscarResposta(nomeAtual, sobrenomeAtual);
			
			CCG.removeRespostas(respostas);
			
			try{
			
				resposta1.set(0, "");
				resposta2.set(0, "");
				resposta3.set(0, "");
			}catch(Exception e){
				
			}
			
			
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Respostas excluídas com exito!", null);
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			
			System.out.println("Respostas removidas com sucesso!");
			
		}
		
		/** lista as respostas do usuário
		 * @return List - respostas 
		 */
		public List<Respostas> listarRespostas(){
			
			CCG = new Crud_ControleGeral();
			Respostas respostas = new Respostas();
			
			//respostas = CCG.BuscarResposta(nomeAtual, sobrenomeAtual);
			
			List<Respostas> respostas2 = CCG.listar2();
			
			for(Respostas resposta:respostas2){
				if(resposta.getNome().equals(nomeAtual) && resposta.getSobrenome().equals(sobrenomeAtual)){
					if(resposta1.size() == 0){
						resposta1.add(resposta.getResposta1());
					}else{
						resposta1.set(0, resposta.getResposta1());
					}
					if(resposta2.size() == 0){
						resposta2.add(resposta.getResposta2());
					}else{
						resposta2.set(0, resposta.getResposta2());
					}
					if(resposta3.size() == 0){
						resposta3.add(resposta.getResposta3());
					}else{
						resposta3.set(0, resposta.getResposta3());
					}
					
					System.out.println("Listagens feitas com sucesso!");
				}
				
			}
			
			return respostas2;
		}


	public String getRespostas1() {
		return Respostas1;
	}

	public void setRespostas1(String respostas1) {
		Respostas1 = respostas1;
	}

	public String getRespostas2() {
		return Respostas2;
	}

	public void setRespostas2(String respostas2) {
		Respostas2 = respostas2;
	}

	public String getRespostas3() {
		return Respostas3;
	}

	public void setRespostas3(String respostas3) {
		Respostas3 = respostas3;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeAtual() {
		return nomeAtual;
	}

	public void setNomeAtual(String nomeAtual) {
		this.nomeAtual = nomeAtual;
	}

	public String getSobrenomeAtual() {
		return sobrenomeAtual;
	}

	public void setSobrenomeAtual(String sobrenomeAtual) {
		this.sobrenomeAtual = sobrenomeAtual;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovoNome() {
		return novoNome;
	}

	public void setNovoNome(String novoNome) {
		this.novoNome = novoNome;
	}

	public String getNovoSobrenome() {
		return novoSobrenome;
	}

	public void setNovoSobrenome(String novoSobrenome) {
		this.novoSobrenome = novoSobrenome;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public Crud_ControleGeral getCCG() {
		return CCG;
	}

	public void setCCG(Crud_ControleGeral cCG) {
		CCG = cCG;
	}

	public List<String> getResposta1() {
		return resposta1;
	}

	public void setResposta1(List<String> resposta1) {
		this.resposta1 = resposta1;
	}

	public List<String> getResposta2() {
		return resposta2;
	}

	public void setResposta2(List<String> resposta2) {
		this.resposta2 = resposta2;
	}

	public List<String> getResposta3() {
		return resposta3;
	}

	public void setResposta3(List<String> resposta3) {
		this.resposta3 = resposta3;
	}

	
	
	
	
}
