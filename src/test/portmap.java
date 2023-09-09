package test;

import main.*;

import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import main.Port;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Collection;


public class portmap {

	public static void main( String[] args ) {

		// Replace the uri string with your MongoDB deployment's connection string
		String uri = "mongodb+srv://root:pwd12345@cosc2081.vkez09i.mongodb.net/?retryWrites=true&w=majority";

		try (MongoClient mongoClient = MongoClients.create(uri)) {

			MongoDatabase database = mongoClient.getDatabase("PMS");
			MongoCollection<Document> collection = database.getCollection("Port");


			Document doc = collection.find(eq("pNumber", 1)).first();

			Port port = new Port(doc.getInteger("pNumber"),
								doc.getString("name"),
								doc.getBoolean("landingAbility"),
								doc.getDouble("latitude"),
								doc.getDouble("longtitude"),
								doc.getDouble("storingCapacity"),
								doc.getList("trips", Trip.class ),
								doc.getList("containers", Container.class),
								doc.getList("vehicles", Vehicle.class)
					);



			if (doc == null) {
				System.out.println("No results found.");
			} else {
				System.out.println(doc.toJson());
			}
			System.out.println(port);
		}
	}
}