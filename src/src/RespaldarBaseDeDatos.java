package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RespaldarBaseDeDatos {
	
	public static void respaldarBasedeDatos() throws SQLException{
		Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
		Statement miStatement = miConexion.createStatement();
		String sentenciaSql = "BACKUP TABLE producto TO = 'C:'";
		miStatement.execute(sentenciaSql);
		miConexion.close();
	}

}
