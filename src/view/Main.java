package view;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Cliente;
import model.DetallesCliente;
import model.Pedido;

public class Main {

	public static void main(String[] args) {
		//creamos un session factory
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Cliente.class)
			.addAnnotatedClass(DetallesCliente.class)
			.addAnnotatedClass(Pedido.class)
			.buildSessionFactory();
						
		Session mySession = myFactory.openSession();
		try {
			
			Ventana miVentana = new Ventana(myFactory, mySession);
			
		}finally {
			//System.out.println("Fin del programa");
		}
	}
}
