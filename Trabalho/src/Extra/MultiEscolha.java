package Extra;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import com.sun.org.apache.bcel.internal.generic.Select;

@ManagedBean
@SessionScoped
public class MultiEscolha {
	
	private List<SelectItem> categories = new ArrayList<SelectItem>();;    
    private String selection;
    private List<String> Res1 = new ArrayList<>();
    private List<String> Res2 = new ArrayList<>();
    private List<String> Res3 = new ArrayList<>();
    

	public List<String> getRes1() {
		return Res1;
	}

	public void setRes1(List<String> res1) {
		Res1 = res1;
	}

	public List<String> getRes2() {
		return Res2;
	}

	public void setRes2(List<String> res2) {
		Res2 = res2;
	}

	public List<String> getRes3() {
		return Res3;
	}

	public void setRes3(List<String> res3) {
		Res3 = res3;
	}

	Connection Conectar = null;
    Statement Ambiente = null;
    ResultSet Resultado = null;
	String r1;
	String r2;
	String r3;
	String Nome;
	String Sobrenome;
	
	@ManagedProperty("#{conexao}")
    private Conexao conexao; 
	
	public void setConexao(Conexao conexao) {
		this.conexao = conexao;
	}

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

	public String getR1() {
		return r1;
	}

	public void setR1(String r1) {
		this.r1 = r1;
	}

	public String getR2() {
		return r2;
	}

	public void setR2(String r2) {
		this.r2 = r2;
	}

	public String getR3() {
		return r3;
	}

	public void setR3(String r3) {
		this.r3 = r3;
	}
    
    public void mandarResposta(){
    	try {
    		
			Class.forName("com.mysql.jdbc.Driver");
			
			Conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/cadastro", "root", "");
			
			Ambiente = Conectar.createStatement();
			
			System.out.println("Conexão ok");
		
			String Cadastrar = "insert into respostas (nome, p1, p2, p3)"
		            + "values('"+conexao.getNome()+"','"+r1+"','"+r2+"','"+r3+"')";
	    	
	    	
				Ambiente.executeUpdate(Cadastrar);
			
				FacesMessage mensagem = new FacesMessage("Enviado, obrigado por nos ajudar.");
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				
			
		} catch (Exception e) {
			System.out.println("Erro");
			String mensagem = "Ops... Acreditamos que você já respondeu nosso questionário.";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem));
		}
	}
    
    public void pegarDados(){
    	try{
    		
    		Class.forName("com.mysql.jdbc.Driver");
			
			Conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/cadastro", "root", "");
			
			Ambiente = Conectar.createStatement();
    		
			Ambiente.execute("SELECT * FROM respostas");
			Resultado = Ambiente.getResultSet();
			
			while(Resultado.next()){
				if(Resultado.getString("nome").equals(conexao.getNome())){
					if(!Res1.contains(Resultado.getString("p1")) && !Res2.contains(Resultado.getString("p2"))
					&& !Res3.contains(Resultado.getString("p3"))){
						Res1.add(Resultado.getString("p1"));
						Res2.add(Resultado.getString("p2"));
						Res3.add(Resultado.getString("p3"));
						System.out.println("deu certo");
						break;
					}
				}
			}
			
    	}catch(Exception g){
    		System.out.println("Não deu certo");
    	}
    }
    
}
