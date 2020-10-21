package test;

import java.sql.*;

public class PruebaConexion {

	public static void main(String[] args) {
		
		//datos conexion mysql
		String host = "jdbc:mysql://localhost:3306/hibernate?serverTimezone=UTC&useSSL=false";
		String usr = "root";
		String pass = "";
		
		try {
			
			System.out.println("Intentando conectar");
			
			Connection miConexion = DriverManager.getConnection(host, usr, pass);
			
			System.out.println("Conexion exitosa");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
