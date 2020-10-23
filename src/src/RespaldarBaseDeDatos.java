package src;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class RespaldarBaseDeDatos {
	
	public static void respaldarProd() throws Exception{
		String filename = "C:\\users\\Santiago\\respaldo-productos.csv";
	    FileWriter fw = new FileWriter(filename);
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernate?serverTimezone=UTC&useSSL=false", "root", "");
	    Statement miStatement = miConexion.createStatement();
	    String sentenciaSql = "SELECT * FROM producto";
	    ResultSet rs = miStatement.executeQuery(sentenciaSql);
	
	            while (rs.next()) {
	                fw.append(rs.getString(1));
	                fw.append(',');
	                fw.append(rs.getString(2));
	                fw.append(',');
	                fw.append(rs.getString(3));
	                fw.append(',');
	                fw.append(rs.getString(4));
	                fw.append(',');
	                fw.append(rs.getString(5));
	                fw.append(',');
	                fw.append(rs.getString(6));
	                fw.append('\n');
	            }
	            fw.flush();
	            fw.close();
	            miConexion.close();
	            JOptionPane.showInternalMessageDialog(null, "Respaldo de productos creado en: "
	            		+ "\nC:\\users\\Santiago\\respaldo-productos.csv");
			
	}
	
	public static void respaldarCli() throws Exception{
		String filename = "C:\\users\\Santiago\\respaldo-clientes.csv";
            FileWriter fw = new FileWriter(filename);
            Class.forName("com.mysql.jdbc.Driver");
            Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernate?serverTimezone=UTC&useSSL=false", "root", "");
            Statement miStatement = miConexion.createStatement();
            String sentenciaSql = "SELECT * FROM cliente";
            ResultSet rs = miStatement.executeQuery(sentenciaSql);

            while (rs.next()) {
                fw.append(rs.getString(1));
                fw.append(',');
                fw.append(rs.getString(2));
                fw.append(',');
                fw.append(rs.getString(3));
                fw.append(',');
                fw.append(rs.getString(4));
                fw.append(',');
                fw.append(rs.getString(5));
                fw.append(',');
                fw.append(rs.getString(6));
                fw.append('\n');
            }
            fw.flush();
            fw.close();
            miConexion.close();
            JOptionPane.showInternalMessageDialog(null, "Respaldo de clientes creado en: "
            		+ "\nC:\\users\\Santiago\\respaldo-clientes.csv");
			
	}
	
	public static void respaldarPed() throws Exception{
		String filename = "C:\\users\\Santiago\\respaldo-pedidos.csv";
            FileWriter fw = new FileWriter(filename);
            Class.forName("com.mysql.jdbc.Driver");
            Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernate?serverTimezone=UTC&useSSL=false", "root", "");
            Statement miStatement = miConexion.createStatement();
            String sentenciaSql = "SELECT * FROM pedido";
            ResultSet rs = miStatement.executeQuery(sentenciaSql);

            while (rs.next()) {
                fw.append(rs.getString(1));
                fw.append(',');
                fw.append(rs.getString(2));
                fw.append(',');
                fw.append(rs.getString(3));
                fw.append(',');
                fw.append(rs.getString(4));
                fw.append('\n');
            }
            fw.flush();
            fw.close();
            miConexion.close();
            JOptionPane.showInternalMessageDialog(null, "Respaldo de pedidos creado en: "
            		+ "\nC:\\users\\Santiago\\respaldo-pedidos.csv");
		
	}

}
