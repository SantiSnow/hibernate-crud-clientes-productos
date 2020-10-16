package src;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CierreConexiones {
	
	public static void cierre(SessionFactory myFactory, Session mySession) {
		mySession.close();
		
		myFactory.close();
		
		System.out.println("Conexiones cerradas");
	}

}
