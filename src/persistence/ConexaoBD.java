package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import util.PropertyBDUtil;

public class ConexaoBD {
	private static ConexaoBD conexaoBD = new ConexaoBD();
	private Connection connection = null;

	private static String URL = "";
	private static String PORTA = "";
	private static String DATABASE = "";
	private static String USERNAME = "";
	private static String PASSWORD = "";
	private static String DRIVER = "";

	private ConexaoBD() {
	}
	
	public static ConexaoBD getInstance() {
		return conexaoBD;
	}

	public Connection getConnection() {
		fillProperties();
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL + DATABASE, USERNAME, PASSWORD);
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private void fillProperties() {
		URL = "jdbc:mysql://localhost:##PORTA##/";
		DRIVER = "com.mysql.jdbc.Driver";
		try {
			
			Map<String, String> properties = PropertyBDUtil.getInstance().getBDProperty();
			
			DATABASE = properties.get("database");
			PORTA = properties.get("dbport");
			USERNAME = properties.get("dbuser");
			PASSWORD = properties.get("dbpassword");
			
			URL = StringUtils.replace(URL, "##PORTA##", PORTA);
			
			System.out.println(URL);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Nao foi possivel conectar-se com o banco\nVerifique o arquivo de propriedades e se o servidor o Mysql está ligado");
			System.exit(0);
		}
		
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
