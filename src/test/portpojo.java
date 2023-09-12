/*package test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import main.Container;
import main.Port;
import main.Truck;
import main.Vehicle;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class portpojo {
	public static void main(String[] args) {
		CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
		CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

		// Replace the uri string with your MongoDB deployment's connection string
		String uri = "mongodb+srv://root:pwd12345@cosc2081.vkez09i.mongodb.net/?retryWrites=true&w=majority";

		try (MongoClient mongoClient = MongoClients.create(uri)) {
			MongoDatabase database = mongoClient.getDatabase("PMS").withCodecRegistry(pojoCodecRegistry);
			MongoCollection<Truck> collection = database.getCollection("Vehicle", Truck.class);

			Truck container = collection.find(eq("tNumber", 1)).first();


			System.out.println(container);
		}
	}
}*/
