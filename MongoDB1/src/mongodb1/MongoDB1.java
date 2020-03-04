package mongodb1;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MongoDB1 {
    
    	static Bson filter = null;
	static Bson query = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("tenda");
   // inserir(database);
    //actualizar(database);
    //incrementar(database);
    consulta(database);
    consulta2(database);
    consulta3(database);
    consulta4(database);
    
    }
    
    public static void inserir(MongoDatabase database){
    MongoCollection collection = database.getCollection("pedidos");
    Document document = new Document();
    document.put("_id", "p4");
    document.put("codcli", "c1");
    document.put("codpro", "pro3");
    document.put("cantidade", 5);
    document.put("data", "02/02/2019");
    collection.insertOne(document);
        System.out.println("inserido");
        
    }
    
    public static void actualizar(MongoDatabase database){
    MongoCollection collection = database.getCollection("pedidos");
    
		filter = eq("_id", "p3");
		query = set("codpro", "pro4");
		UpdateResult result = collection.updateOne(filter, query);
        System.out.println("actualizado");
        
    }
    public static void incrementar(MongoDatabase database){
    MongoCollection collection = database.getCollection("pedidos");
    
		filter = eq("_id", "p2");
		query = inc("cantidade", 7);
		UpdateResult result = collection.updateOne(filter, query);
        System.out.println("incrementado");
        
    }
    public static void consulta(MongoDatabase database){
          MongoCollection collection = database.getCollection("pedidos");
    
		filter = eq("_id", "p3");
                FindIterable<Document> docs = collection.find(filter);
        System.out.println("consultando"); 
                for (Document doc : docs) {
                    System.out.println(doc);
        }
              
 
    }
    
    public static void consulta2(MongoDatabase database){
          MongoCollection collection = database.getCollection("pedidos");
    
		filter = eq("_id", "p1");
                FindIterable<Document> docs = collection.find(filter);
        System.out.println("consultando2"); 
                for (Document doc : docs) {
                                        System.out.println(doc.getString("_id"));
                    System.out.println(doc.getString("codcli"));
                    System.out.println(doc.getString("codpro"));
        }
              
 
    }
    
    public static void consulta3(MongoDatabase database){
          MongoCollection collection = database.getCollection("pedidos");
    
		filter = gt("cantidade", 2);
                
                FindIterable<Document> docs = collection.find(filter);
                
            System.out.println("consultando3"); 
        
                for (Document doc : docs) {
                                        System.out.println(doc.getString("_id"));
                    System.out.println(doc.getString("codcli"));
                    System.out.println(doc.getString("codpro"));
                    System.out.println("\n");
        }
              
 
    }
    
    public static void consulta4(MongoDatabase database){
          MongoCollection collection = database.getCollection("pedidos");
    
		filter = and(gt("cantidade",2),lt("cantidade",5));
                
                FindIterable<Document> docs = collection.find(filter);
                
            System.out.println("consultando4"); 
        
                for (Document doc : docs) {
                    System.out.println(doc.getString("_id"));
                    System.out.println(doc.getString("codcli"));
                    System.out.println(doc.getString("codpro"));
                    System.out.println("\n");
        }
              
 
    }
    
}
