package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
	private static ConexaoBD conexaoBD = new ConexaoBD();
	private Connection connection = null;

	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String DATABASE = "savingsdreams";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	private ConexaoBD() {

	}

	public static ConexaoBD getInstance() {
		return conexaoBD;
	}

	public Connection getConnection() {
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

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
