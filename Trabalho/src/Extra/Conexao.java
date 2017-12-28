package Extra;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class Conexao {
    
	//Atributos para conexão
	Connection Conectar = null;
	String Cadastrar = null;
	Statement Ambiente = null;
	ResultSet Resultado = null;
	//Atributos para receber dados do cadastro
	String Nome;
	String Sobrenome;
	String Senha;
	

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getSobrenome() {
		return Sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		Sobrenome = sobrenome;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	
    public void conectando(){
    	try {
    		
			Class.forName("com.mysql.jdbc.Driver");
			
			Conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/cadastro", "root", "");
			
			Ambiente = Conectar.createStatement();
	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
	}
    
    public void inserirDados(){
    	conectando();
    	
    	Cadastrar = "insert into login (nome, sobrenome, senha)"
	            + "values('"+Nome+"','"+Sobrenome+"','"+Senha+"')";
    	
    	try {
			Ambiente.executeUpdate(Cadastrar);
			FacesMessage mensagem = new FacesMessage("Cadastrado");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
		} catch (SQLException e) {
			String mensagem = "Não foi possível cadastrar! Acreditamos que você já possui um cadastro";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem));
		}
    	
    	
    }
    
    public void guardarResultado(){
    	try {
			Ambiente.execute("SELECT * FROM login");
			Resultado = Ambiente.getResultSet();
		} catch (SQLException e) {
			System.out.println("Erro no resultado");
		}
        
    }
    
    public void logar(){
    	
    	MultiEscolha e = new MultiEscolha();
    	
    	try {
    		conectando();
    		guardarResultado();
    		
    		while(Resultado.next()){
    			if(Resultado.getString("nome").equals(Nome) && Resultado.getString("senha").equals(Senha)){
    				Sobrenome = Resultado.getString("sobrenome");
    				FacesContext.getCurrentInstance().getExternalContext().redirect("pagina.xhtml");
    				
    				break;
    			}
    		}
    		
    		String mensagem = "Por favor, insira seus dados corretamente e tente novamente";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem));
    		
    	}catch(Exception z){
    		System.out.println("Erro ao logar");
    	}
    }
    
    public void listagem(){
    	
    }
		
	
}

