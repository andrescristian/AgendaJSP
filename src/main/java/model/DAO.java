package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	/* Módulo de conexão */

	// Parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "";
	private String user = "";
	private String password = "";

	// Método de conexão
	private Connection conectar() {
		Connection con = null; // Objeto de nome "con" nulo por enquanto

		// Para conectar com o Banco
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// Teste de conexão
	/**
	 * public void testeConexao() {
	 *  try {
	 *   Connection con = conectar(); //Executa o Método conectar() acima 
	 *   System.out.println(con);
	 *    con.close(); //Fecha conexão com o Banco
    
	 *    }catch (Exception e) {
	 *     System.out.println(e);
	 *     }
	 * 
	 * }
	 */
	
	/*CRUD CREATE - Criar Método pra pegar os Dados do JavaBeans. Vai ser invocado na Classe Controller.java*/
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos(nome, fone, email) values (?,?,?)";
		try {
			//Abrir conexão com o Banco
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create); //Pega o que está na Variavel con
			
			//setString() vai substituir os ??? da Variável "create"
			pst.setString(1, contato.getNome());	//getNome, getFone, getEmail vai buscar o que está armazenado lá no JavaBeans
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			
			pst.executeUpdate(); //Comando que vai jogar os Dados pro Banco
			
			con.close(); //Depois de executar, feche o Banco
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	/*CRUD READ -> Criar um Método Array*/
	public ArrayList<JavaBeans> listarContatos(){
		String read = "select * from contatos order by nome ";
		
		//Criando Objeto para acessar as Variaveis do JavaBeans	Passo 5
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);	
			ResultSet rs = pst.executeQuery();	//Objeto "rs" criado. Vai executar o comando da Variável "read"
			
			//Laço de repetição será executado enquanto houver contatos para o banco 
			while(rs.next()) {
				//Variáveis de apoio que vao receber os dados do Banco. idcon é do Banco
				//Vao capturar tudo que chega do banco e armazenar temporariamente no "rs"
				String idcon = rs.getString(1);	//Passo 4
				String nome = rs.getString(2); //Os numeros ta na ordem de cada coluna
				String fone = rs.getString(3);
				String email = rs.getString(4);
			
				contatos.add(new JavaBeans(idcon, nome, fone, email));//"contatos" criado neste Método. Através daqui, ele vai pras Variáveis do JavaBeans
			}
			con.close();
			return contatos; //"contatos" daqui do Método
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/* CRUD UPDATE*/
	public void selecionarContato(JavaBeans contato) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getIdcon());
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				//Setar as Variáveis do JavaBeans
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}		
	}
	
	/*Editar Contato	16/04*/
	public void alterarContato(JavaBeans contato){
		String create = "update contatos set nome=?, fone=?,email=? where idcon=?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			
			pst.setString(1, contato.getNome());	//Lembrando que os nº sao usados para os dados serem encaminhados pro Banco de Dados
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());	// Idcon fica no final porque ele NÃO É ALTERADO
			
			pst.executeUpdate();	//Vai executar a query			
			con.close();	//Depois de executar tudo, fechamos a conexão com o Banco de Dados
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/*CRUD DELETE	18/04*/
	public void deletarContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getIdcon());	//contato obtem a variável idcon
			
			pst.executeUpdate();	//Executar a query
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
