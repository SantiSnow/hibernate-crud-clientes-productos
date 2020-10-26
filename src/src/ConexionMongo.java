package src;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class ConexionMongo {
	
	MongoClient mongoClient;
	
	public MongoDatabase mongoCon(String mongoDB, String coleccion) {
		mongoClient = new MongoClient();
		MongoDatabase dataBase = mongoClient.getDatabase(mongoDB);
		dataBase.createCollection(coleccion);
		
		return dataBase;
	}
	
	public void cerrarPool() {
		mongoClient.close();
	}

}
