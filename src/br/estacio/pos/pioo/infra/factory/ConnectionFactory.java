package br.estacio.pos.pioo.infra.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private String driver = "com.mysql.jdbc.Driver";
	private String connectionStr = "jdbc:mysql://localhost:3306/pioopublicidade";
	private String username = "estacio";
	private String password = "123456";

	private static ConnectionFactory connectionFactory = null;

	private ConnectionFactory() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(connectionStr, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static ConnectionFactory getInstance() {
		if (connectionFactory == null)
			connectionFactory = new ConnectionFactory();

		return connectionFactory;
	}
}
