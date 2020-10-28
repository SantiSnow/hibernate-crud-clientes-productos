package src;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.bson.Document;

import org.hibernate.Session;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;

import model.*;

public class RespaldarBaseDeDatos {

	public static String respaldarProd(Session mySession) throws Exception{
		String folder = JOptionPane.showInputDialog(null, "Ingrese la carpeta de destino. Se recomienda copiar la dirección desde el explorador de windows.");
		
		String filename = folder + "\\respaldo-productos.csv";
	    FileWriter fw = new FileWriter(filename);
	    
	    //creamos la transaccion sql
	    mySession.beginTransaction();
	    List<Producto> listaProductos;
	    listaProductos = mySession.createQuery("from Producto").getResultList();
		//commit
		mySession.getTransaction().commit();
		
		for(Producto i: listaProductos) {
			fw.append("\n" + i.getId());
			fw.append(',');
			fw.append(i.getNombre());
			fw.append(',');
			fw.append(i.getDetalle());
			fw.append(',');
			fw.append("" + i.getPrecio());
			fw.append(',');
			fw.append(i.getSeccion());
			fw.append(',');
			fw.append("" + i.getStock());
			fw.append(',');
		}
		fw.flush();
		fw.close();
		
		return filename;
	}
	
	public static String respaldarCli(Session mySession) throws Exception{
		String folder = JOptionPane.showInputDialog(null, "Ingrese la carpeta de destino. Se recomienda copiar la dirección desde el explorador de windows.");
		String filename = folder + "\\respaldo-clientes.csv";
		FileWriter fw = new FileWriter(filename);
            
    	//creamos la transaccion sql
    		mySession.beginTransaction();
    		List<Cliente> listaClientes;
    		listaClientes = mySession.createQuery("from Cliente").getResultList();
    		//commit
    		mySession.getTransaction().commit();
    		for(Cliente i: listaClientes) {
    			fw.append("\n" + i.getId());
    			fw.append(',');
    			fw.append(i.getNombre());
    			fw.append(',');
    			fw.append(i.getApellido());
    			fw.append(',');
    			fw.append(i.getDireccion());
    			fw.append(',');
    			fw.append("" + i.getCompras());
    			fw.append(',');
    			fw.append("" + i.getTelefono());
    			fw.append(',');
    			fw.append(i.getDetallesCliente().getCorreo());
    			fw.append(',');
    			fw.append(i.getDetallesCliente().getComentarios());
    			fw.append(',');
    		}
    		fw.flush();
            fw.close();
            
            return filename;
	}
	
	public static String respaldarPed(Session mySession) throws Exception{
		String folder = JOptionPane.showInputDialog(null, "Ingrese la carpeta de destino. Se recomienda copiar la dirección desde el explorador de windows.");
		//creacion del archivo
		String filename = folder + "\\respaldo-pedidos.csv";
		FileWriter fw = new FileWriter(filename);
		
		//conexion a mysql
		//creamos la transaccion sql
		mySession.beginTransaction();
		List<Pedido> listaPedidos;
		listaPedidos = mySession.createQuery("from Pedido").getResultList();
		//commit
		mySession.getTransaction().commit();
		for(Pedido i: listaPedidos) {
			fw.append("\n" + i.getId());
			fw.append(',');
			fw.append(i.getFormaPago());
			fw.append(',');
			fw.append("" + i.getFecha());
			fw.append(',');
			fw.append("" + i.getCliente().getNombre() + " " + i.getCliente().getApellido());
			fw.append(',');
		}
		fw.flush();
        fw.close();
        
        return filename;
	}
	
	public static void respaldoNoSQLProductos(Session mySession)throws Exception{
		mySession.beginTransaction();
	    List<Producto> listaProductos;
	    listaProductos = mySession.createQuery("from Producto").getResultList();
		mySession.getTransaction().commit();
		
		ConexionMongo conexionMongo = new ConexionMongo();
		MongoDatabase dataBase = conexionMongo.mongoCon("Respaldo_Gestor", "Productos");
		
		for(Producto i: listaProductos) {
			Document documento = new Document("_id", i.getId())
				.append("Nombre", i.getNombre())
				.append("Precio", i.getPrecio())
				.append("Detalle", i.getDetalle())
				.append("Seccion", i.getSeccion())
				.append("Stock", i.getStock());
			
			dataBase.getCollection("Productos").insertOne(documento);
		}
		//cerrar los pool de conexiones
		conexionMongo.cerrarPool();
	}
	
	public static void respaldoNoSQLPedidos(Session mySession)throws Exception{
		mySession.beginTransaction();
	    List<Pedido> listaPedidos;
	    listaPedidos = mySession.createQuery("from Pedido").getResultList();
		mySession.getTransaction().commit();
		
		ConexionMongo conexionMongo = new ConexionMongo();
		MongoDatabase dataBase = conexionMongo.mongoCon("Respaldo_Gestor", "Pedidos");
		
		for(Pedido i: listaPedidos) {
			Document documento = new Document("_id", i.getId())
				.append("Forma Pago", i.getFormaPago())
				.append("Fecha", i.getFecha())
				.append("Nombre Cliente", i.getCliente().getNombre())
				.append("Apellido Cliente", i.getCliente().getApellido());
			
			dataBase.getCollection("Pedidos").insertOne(documento);
		}
		//cerrar los pool de conexiones
		conexionMongo.cerrarPool();
	}
	
	public static void respaldoNoSQLClientes(Session mySession)throws Exception{
		mySession.beginTransaction();
	    List<Cliente> listaClientes;
	    listaClientes = mySession.createQuery("from Cliente").getResultList();
		mySession.getTransaction().commit();
		
		ConexionMongo conexionMongo = new ConexionMongo();
		MongoDatabase dataBase = conexionMongo.mongoCon("Respaldo_Gestor", "Clientes");
		
		for(Cliente i: listaClientes) {
			Document documento = new Document("_id", i.getId())
				.append("Nombre", i.getNombre())
				.append("Apellido", i.getApellido())
				.append("Telefono", i.getTelefono())
				.append("Direccion", i.getDireccion())
				.append("Compras", i.getCompras())
				.append("Correo", i.getDetallesCliente().getCorreo())
				.append("Comentarios", i.getDetallesCliente().getComentarios());
			
			dataBase.getCollection("Clientes").insertOne(documento);
		}
		//cerrar los pool de conexiones
		conexionMongo.cerrarPool();
	}
	
	//Estos metodos son un poco mas eficientes que los 3 anteriores
	public static void migracionListaClientes(Session mySession)throws Exception{
		mySession.beginTransaction();
	    List<Cliente> lista;
	    lista = mySession.createQuery("from Cliente").getResultList();
		mySession.getTransaction().commit();
		
		ConexionMongo conexionMongo = new ConexionMongo();
		MongoDatabase dataBase = conexionMongo.mongoCon("Respaldo_Gestor", "Clientes");
		
		List<Document> listaDocumentos = new ArrayList<Document>();
		
		for(Cliente i: lista) {
			Document documento = new Document("_id", i.getId())
				.append("Nombre", i.getNombre())
				.append("Apellido", i.getApellido())
				.append("Telefono", i.getTelefono())
				.append("Direccion", i.getDireccion())
				.append("Compras", i.getCompras())
				.append("Correo", i.getDetallesCliente().getCorreo())
				.append("Comentarios", i.getDetallesCliente().getComentarios());
			listaDocumentos.add(documento);
		}
		
		dataBase.getCollection("Clientes").insertMany(listaDocumentos, new InsertManyOptions().ordered(false));
	}
	
	public static void migracionListaProductos(Session mySession)throws Exception{
		mySession.beginTransaction();
	    List<Producto> lista;
	    lista = mySession.createQuery("from Producto").getResultList();
		mySession.getTransaction().commit();
		
		ConexionMongo conexionMongo = new ConexionMongo();
		MongoDatabase dataBase = conexionMongo.mongoCon("Respaldo_Gestor", "Productos");
		
		List<Document> listaDocumentos = new ArrayList<Document>();
		
		for(Producto i: lista) {
			Document documento = new Document("_id", i.getId())
				.append("Nombre", i.getNombre())
				.append("Nombre", i.getNombre())
				.append("Precio", i.getPrecio())
				.append("Detalle", i.getDetalle())
				.append("Seccion", i.getSeccion())
				.append("Stock", i.getStock());
			listaDocumentos.add(documento);
		}
		
		
		dataBase.getCollection("Productos").insertMany(listaDocumentos, new InsertManyOptions().ordered(false));
	}
	
	public static void migracionListaPedidos(Session mySession)throws Exception{
		mySession.beginTransaction();
	    List<Pedido> lista;
	    lista = mySession.createQuery("from Pedido").getResultList();
		mySession.getTransaction().commit();
		
		ConexionMongo conexionMongo = new ConexionMongo();
		MongoDatabase dataBase = conexionMongo.mongoCon("Respaldo_Gestor", "Pedidos");
		
		List<Document> listaDocumentos = new ArrayList<Document>();
		
		for(Pedido i: lista) {
			Document documento = new Document("_id", i.getId())
				.append("Forma Pago", i.getFormaPago())
				.append("Fecha", i.getFecha())
				.append("Nombre Cliente", i.getCliente().getNombre())
				.append("Apellido Cliente", i.getCliente().getApellido());
			listaDocumentos.add(documento);
		}
		
		
		dataBase.getCollection("Pedidos").insertMany(listaDocumentos, new InsertManyOptions().ordered(false));
	}
	
}
