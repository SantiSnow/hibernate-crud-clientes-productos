package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import model.*;

public class Main {

	public static void main(String[] args) {
		try {
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
			Statement miStatement = miConexion.createStatement();
			String sentenciaSql = "CREATE DATABASE IF NOT EXISTS hibernate";
			miStatement.execute(sentenciaSql);
			miConexion.close();
			
			//creamos un session factory
			SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Cliente.class)
				.addAnnotatedClass(DetallesCliente.class)
				.addAnnotatedClass(Pedido.class)
				.addAnnotatedClass(Producto.class)
				.addAnnotatedClass(PedidoHuerfano.class)
				.buildSessionFactory();
						
			Session mySession = myFactory.openSession();
		
			
			Ventana miVentana = new Ventana(myFactory, mySession);
			
		}
		catch(Exception e) {
			String msj = e.getMessage();
			JOptionPane.showMessageDialog(null, msj + "\nLa base de datos parece desconectada. Inicie su gestor XAMPP y pruebe de nuevo.");
			//System.out.println(msj);
		}
		finally {
			//System.out.println("Fin del programa");
		}
	}
}
