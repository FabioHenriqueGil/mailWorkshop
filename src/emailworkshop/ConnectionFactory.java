package emailworkshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionFactory {

	public Connection getConnection(String servidor, String banco , String usuario, String senha) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
                        servidor = "jdbc:mysql://"+servidor+"/"+banco;
                        System.out.println(servidor+","+usuario+","+senha);
                        //"jdbc:mysql://localhost/agenda", "root", "root"
                        return DriverManager.getConnection(servidor, usuario, senha);
//return DriverManager.getConnection("jdbc:mysql://maquinaufpr.ddns.net", "root", "workshop");
		} catch(ClassNotFoundException erro){
			JOptionPane.showMessageDialog(null,"Driver JDBC-MySQL não encontrado!!");
                        System.exit(0);
			throw new RuntimeException(erro);
		}
		catch(SQLException erro){
			JOptionPane.showMessageDialog(null,"Problema na conexão com a fonte de dados");
                        System.exit(0);
			throw new RuntimeException(erro);
		}
	}
}
